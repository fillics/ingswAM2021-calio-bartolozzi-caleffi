package it.polimi.ingsw.server;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.client.*;
import it.polimi.ingsw.client.liteclasses.LiteBoard;
import it.polimi.ingsw.client.liteclasses.LiteDevelopmentGrid;
import it.polimi.ingsw.client.liteclasses.LiteMarketTray;
import it.polimi.ingsw.client.liteclasses.LitePlayer;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.client_packets.SetupHandler;
import it.polimi.ingsw.controller.client_packets.ClientPacketHandler;
import it.polimi.ingsw.controller.client_packets.cheatpackets.CheatClientPacketHandler;
import it.polimi.ingsw.controller.server_packets.*;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.faithtrack.Cell;
import it.polimi.ingsw.model.board.faithtrack.VaticanReportSection;
import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.board.storage.Deposit;
import it.polimi.ingsw.model.board.storage.Strongbox;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentCard;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentSpace;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;
import it.polimi.ingsw.model.marbles.Marble;
import it.polimi.ingsw.model.singleplayer.SinglePlayerGame;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class used to deserialize the packets coming from the client
 */
public class ClientHandler implements Runnable {
    private final Socket socket;
    private String username;
    private Game game;
    private final int numberOfGuest;
    private final int idClient;
    private int posInGame;
    private boolean endConnection = false;
    private final Server server;
    private DataInputStream dis;
    private PrintStream output;
    private ObjectMapper mapper;
    private String jsonResult;
    private boolean sendSetup = false;
    private boolean isSingleGame;
    private final AtomicBoolean clientConnected;
    private ClientModelView clientModelView;



    public ClientHandler(int idClient, Socket socket, Server server, int numberOfGuest) {
        this.idClient = idClient;
        this.socket = socket;
        this.server = server;
        this.numberOfGuest = numberOfGuest;

        clientConnected = new AtomicBoolean(true);
        try {
            dis = new DataInputStream(socket.getInputStream());  // to read data coming from the client
            output = new PrintStream(socket.getOutputStream());// to send data to the client
        } catch (IOException e) {
            System.err.println(Constants.getErr() + "Error during initialization of the client!");
        }
        startPingPong();
    }


    /**
     * Run method starts when a new client connected to the server
     */
    public void run() {
        String guest = "Guest";
        try {
            while (!endConnection) {
                if (sendSetup) sendSetupPacket();
                try{
                    String str = dis.readUTF();
                    deserialize(str);
                }catch (Exception e){
                    if(username!=null){ //player has already inserted the username
                        System.out.println(Constants.ANSI_RED+username+Constants.ANSI_RESET+ " (idPlayer: " +idClient+") "+ Constants.ANSI_RED+"disconnected!"+Constants.ANSI_RESET);
                        server.handleDisconnection(this);
                    }
                    else{ //player has not inserted the username
                        System.out.println(guest+numberOfGuest + " disconnected!");
                    }
                    clientConnected.compareAndSet(true, false);

                }finally {
                    if(!clientConnected.get()){ //if client not more connected to the server
                        dis.close();
                        socket.close();
                        endConnection=true;
                    }
                }
            }
            if(username!=null) System.out.println("Connection with " + username + " is closed!");
            else System.out.println("Connection with " + guest+numberOfGuest + " is closed!");


        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }



    /**
     * Method startPingPong sends a Ping Packet to the client to check if it is connected
     */
    public synchronized void startPingPong(){
        new Thread(() -> {
            while (!endConnection) {
                sendPacketToClient(new PacketPingFromServer());
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Method that deserializes the strings coming from the client and it creates the correct Packet Handler class to
     * execute the packet
     * @param jsonResult (type String) - it is the string to deserialize
     */
    public synchronized void deserialize(String jsonResult) throws DevelopmentCardNotFound, EmptyDeposit, LeaderCardNotActivated, LeaderCardNotFound, DevCardNotPlaceable, IOException, DifferentDimension, DepositDoesntHaveThisResource, DiscountCannotBeActivated, NotEnoughRequirements, TooManyResourcesRequested, ClassNotFoundException, DepositHasReachedMaxLimit, NotEnoughResources, DepositHasAnotherResource, WrongChosenResources {

        ObjectMapper mapper = new ObjectMapper();

        if (jsonResult.contains("USERNAME") || jsonResult.contains("NUMOFPLAYERS") || jsonResult.contains("PONG") ){
            SetupHandler packet = null;
            try {
                packet = mapper.readValue(jsonResult, SetupHandler.class);
            } catch (JsonProcessingException ignored) {}
            if (packet != null) packet.execute(server,this);

        }
        else if(jsonResult.contains("RESOURCE_CHEAT") || jsonResult.contains("FAITHMARKER_CHEAT")){
            CheatClientPacketHandler packet = null;
            try {
                packet = mapper.readValue(jsonResult, CheatClientPacketHandler.class);
            } catch (JsonProcessingException ignored) {}
            if (packet != null) packet.execute(server, game,this);
        }
        else{
            ClientPacketHandler packet = null;
            try {
                packet = mapper.readValue(jsonResult, ClientPacketHandler.class);
            } catch (JsonProcessingException ignored) {}

            if (packet != null) packet.execute(server,game,this);
        }
    }



    /**
     * Method sendSetupPacket sends to the client the packet to play in multiplayer
     */
    public synchronized void sendSetupPacket(){
        mapper = new ObjectMapper();
        ArrayList<String> players = new ArrayList<>();
        for (Player player: game.getPlayers()){
            players.add(player.getUsername());
        }
        Board board = game.getUsernameClientActivePlayers().get(username).getBoard();
        Player player = game.getUsernameClientActivePlayers().get(username);
        PacketSetup packetSetup = new PacketSetup(username, idClient, posInGame, isSingleGame , game.getDevGridLite(), game.getTable(), game.getRemainingMarble(),
                board.getDevelopmentSpaces(), game.getUsernameClientActivePlayers().get(username).getResourceBuffer(), board.getSpecialProductionPowers(),
                board.getStrongbox(), board.getDeposits(),  player.getWhiteMarbleCardChoice(), player.getLeaderCards(), board.getTrack(),
                board.getVaticanReportSections(), players);


        saveProxy();

        sendPacketToClient(packetSetup);

        sendSetup = false;
    }

    /**
     * Method saveProxy creates a new instance of the class ClientProxy to save che model view of the player
     */
    public synchronized void saveProxy(){
        Board board = game.getUsernameClientActivePlayers().get(username).getBoard();
        Player player = game.getUsernameClientActivePlayers().get(username);

        Strongbox strongbox = board.getStrongbox();
        ArrayList<Deposit> deposits = board.getDeposits();
        ArrayList<DevelopmentSpace> developmentSpaces = board.getDevelopmentSpaces();
        ArrayList<ProductionPower> specialProductionPowers = board.getSpecialProductionPowers();
        ArrayList<Cell> track = board.getTrack();
        ArrayList<VaticanReportSection> vaticanReportSections = board.getVaticanReportSections();
        ArrayList<LeaderCard> leaderCards = player.getLeaderCards();
        ArrayList<Resource> resourceBuffer = player.getResourceBuffer();
        ArrayList<Integer> whiteMarbleCardChoice = player.getWhiteMarbleCardChoice();
        ArrayList<DevelopmentCard> developmentCards = game.getDevGridLite();
        Marble[][] table = game.getTable();
        Marble remainingMarble = game.getRemainingMarble();


        LiteBoard liteBoard = new LiteBoard(strongbox,deposits,developmentSpaces,specialProductionPowers,track,vaticanReportSections);
        LitePlayer litePlayer = new LitePlayer(username, idClient, posInGame, 0, leaderCards,resourceBuffer, liteBoard, whiteMarbleCardChoice);
        LiteDevelopmentGrid liteDevelopmentGrid = new LiteDevelopmentGrid(developmentCards);
        LiteMarketTray liteMarketTray = new LiteMarketTray(table,remainingMarble);


        clientModelView = new ClientModelView(litePlayer, liteMarketTray, liteDevelopmentGrid, liteBoard);

        ClientProxy clientProxy = new ClientProxy(clientModelView);
        if(isSingleGame) clientProxy.getClientModelView().setSingleGame();

        server.getMapForReconnection().put(username, clientProxy);
    }

    /**
     * Method sendUpdatePacket sends updates to the client
     * @param serverPacketHandler (type ServerPacketHandler) - it is the packet to be sent
     */
    public synchronized void sendPacketToClient(ServerPacketHandler serverPacketHandler){
        mapper = new ObjectMapper();
        try {
            jsonResult = mapper.writeValueAsString(serverPacketHandler);
        } catch (JsonProcessingException ignored) {}

        sendToClient(jsonResult);

    }


    /**
     * Method that sends the json string to the client
     * @param jsonResult (type String) - it is the string to be sent to the client
     */
    public synchronized void sendToClient(String jsonResult){
        output.println(jsonResult);
        output.flush();
    }

    public ClientModelView getClientModelView() {
        return clientModelView;
    }

    public void setClientModelView(ClientModelView clientModelView) {
        this.clientModelView = clientModelView;
    }

    /**
     * Method that sets the end of the connection client-server
     */
    public void setEndConnection() {
        endConnection=true;
    }


    public void setGame(Game game) {
        this.game = game;
        boolean gameStarted = true;
    }

    public Game getGame() {
        return game;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPosInGame() {
        return posInGame;
    }

    public void setPosInGame(int posInGame) {
        this.posInGame = posInGame;
    }

    public int getIdClient() {
        return idClient;
    }

    public boolean isSingleGame() {
        return isSingleGame;
    }

    public void setSingleGame() {
        isSingleGame = true;
    }

    public AtomicBoolean getClientConnected() {
        return clientConnected;
    }
}