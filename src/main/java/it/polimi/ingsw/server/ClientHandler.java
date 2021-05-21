package it.polimi.ingsw.server;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.client_packets.SetupHandler;
import it.polimi.ingsw.controller.client_packets.ClientPacketHandler;
import it.polimi.ingsw.controller.server_packets.*;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.singleplayer.SinglePlayerGame;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private Game game;
    private int numberOfGuest;
    private final int idClient;
    private int idGame;
    private int posInGame; //parte da 0
    private boolean quit = false;
    private final Server server;
    private DataInputStream dis;
    private PrintStream output;
    private String username;
    private ObjectMapper mapper;
    private String jsonResult;
    private boolean gameStarted= false;
    private boolean sendSetup = false;
    private final AtomicBoolean clientConnected;


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



    public void run() {
        String guest = "Guest";
        try {
            while (!quit) {
                if (sendSetup) sendSetupPacket();

                try{
                    String str = dis.readUTF();
                    deserialize(str);
                }catch (Exception e){

                    if(username!=null){ //il player ha inserito l'username
                        System.out.println(username + " disconnected!");
                        server.handleDisconnection(this);
                    }
                    else{ //il player non ha ancora inserito l'username: viene solo chiusa la disconnessione
                        System.out.println(guest+numberOfGuest + " disconnected!");

                    }
                    clientConnected.compareAndSet(true, false); //setto la variabile a false

                }finally {
                    if(!clientConnected.get()){ //se client non più connesso al server
                        dis.close();
                        socket.close();
                        quit=true;
                    }
                }
                if(gameStarted && game.isEndgame()) {
                    quit=true;

                    if(!(game instanceof SinglePlayerGame)){
                        server.sendAll(new PacketEndGameStarted(username), game);
                        game.setState(GameStates.FINAL_TURN);
                    }

                }
            }

            if(username!=null) System.out.println("Connection with " + username + " is closed!");
            else System.out.println("Connection with " + guest+numberOfGuest + " is closed!");


        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setGame(Game game) {
        this.game = game;
        gameStarted=true;
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


    /**
     * Method startPingPong sends a Ping Packet to the client to check if it is connected
     */
    public synchronized void startPingPong(){

        new Thread(() -> {
            while (!quit) {
                sendPacketToClient(new PacketPingFromServer());
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    public synchronized void deserialize(String jsonResult) throws DevelopmentCardNotFound,
            EmptyDeposit, LeaderCardNotActivated, LeaderCardNotFound, DevCardNotPlaceable,
            DifferentDimension, DepositDoesntHaveThisResource, DiscountCannotBeActivated,
            NotEnoughRequirements, TooManyResourcesRequested, DepositHasReachedMaxLimit,
            NotEnoughResources, DepositHasAnotherResource, WrongChosenResources, IOException, ClassNotFoundException {

        ObjectMapper mapper = new ObjectMapper();

        if (jsonResult.contains("USERNAME") || jsonResult.contains("NUMOFPLAYERS") || jsonResult.contains("PONG") ){
            SetupHandler packet = null;
            try {
                packet = mapper.readValue(jsonResult, SetupHandler.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            if (packet != null) {
                packet.execute(server,this);
            }
        }
        else{
            ClientPacketHandler packet = null;
            try {
                packet = mapper.readValue(jsonResult, ClientPacketHandler.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            if (packet != null) {
                packet.execute(server,game,this);

            }
        }


    }

    public synchronized void sendSetupPacket(){
        mapper = new ObjectMapper();

        PacketSetupMultiplayer packetSetup = new PacketSetupMultiplayer(username, idClient, posInGame,0,game.getIdClientActivePlayers().get(idClient).getBoard().getFaithMarker(), game.getDevGridLite(), game.getTable(), game.getRemainingMarble(),
                game.getIdClientActivePlayers().get(idClient).getBoard().getDevelopmentSpaces(), game.getIdClientActivePlayers().get(idClient).getResourceBuffer(), game.getIdClientActivePlayers().get(idClient).getBoard().getSpecialProductionPowers(),
                game.getIdClientActivePlayers().get(idClient).getBoard().getStrongbox(),
                game.getIdClientActivePlayers().get(idClient).getBoard().getDeposits(),
                game.getIdClientActivePlayers().get(idClient).getWhiteMarbleCardChoice(), game.getIdClientActivePlayers().get(idClient).getLeaderCards(),
                game.getIdClientActivePlayers().get(idClient).getBoard().getTrack(), game.getIdClientActivePlayers().get(idClient).getBoard().getVaticanReportSections());

        sendPacketToClient(packetSetup);
        sendSetup = false;
    }

    /**
     * Method sendUpdatePacket sends updates to the client
     * @param serverPacketHandler (type ServerPacketHandler) -
     */
    public synchronized void sendPacketToClient(ServerPacketHandler serverPacketHandler){
        mapper = new ObjectMapper();
        try {
            jsonResult = mapper.writeValueAsString(serverPacketHandler);
            System.out.println(jsonResult);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        sendToClient(jsonResult);

    }


    public synchronized void sendToClient(String jsonResult){
        output.println(jsonResult);
        output.flush();
    }

}