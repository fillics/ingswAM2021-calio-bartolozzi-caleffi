package it.polimi.ingsw.server;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.client_packets.SetupHandler;
import it.polimi.ingsw.controller.client_packets.ClientPacketHandler;
import it.polimi.ingsw.controller.server_packets.PacketSetup;
import it.polimi.ingsw.controller.server_packets.ServerPacketHandler;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.Game;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private Game game;
    private final int idClient;
    private int idGame;
    private int posInGame;
    private boolean quit = false;
    private final Server server;
    private DataInputStream dis;
    private DataOutputStream output;
    private PrintStream ps;
    private String username;
    private ObjectMapper mapper;
    private String jsonResult;
    private boolean gameStarted= false;




    public ClientHandler(int idClient, Socket socket, Server server) {
        this.idClient = idClient;
        this.socket = socket;
        this.server = server;
        try {
            dis = new DataInputStream(socket.getInputStream());  // to read data coming from the client
            ps = new PrintStream(socket.getOutputStream());// to send data to the client
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.err.println(Constants.getErr() + "Error during initialization of the client!");
        }
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public void run() {
        try {

            while (!quit) {
                String str = dis.readUTF();
                if(str.equals(ConnectionMessages.SEND_SETUP_PACKETS.getMessage())){
                    if (gameStarted)
                        sendSetupPacket();
                }
                else {
                    deserialize(str);
                }

                if(gameStarted) {
                    if(game.isEndgame())
                        quit=true;
                }
            }
            // Chiudo gli stream e il socket -> client non è più connesso al server
            dis.close();
            ps.close();
            socket.close();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (DevelopmentCardNotFound | EmptyDeposit | LeaderCardNotActivated | LeaderCardNotFound | DevCardNotPlaceable | DifferentDimension | DepositDoesntHaveThisResource | DiscountCannotBeActivated | NotEnoughRequirements | TooManyResourcesRequested | DepositHasReachedMaxLimit | NotEnoughResources | DepositHasAnotherResource | WrongChosenResources developmentCardNotFound) {
            developmentCardNotFound.printStackTrace();
        }
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


    public void close(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public synchronized void deserialize(String jsonResult) throws DevelopmentCardNotFound,
            EmptyDeposit, LeaderCardNotActivated, LeaderCardNotFound, DevCardNotPlaceable,
            DifferentDimension, DepositDoesntHaveThisResource, DiscountCannotBeActivated,
            NotEnoughRequirements, TooManyResourcesRequested, DepositHasReachedMaxLimit,
            NotEnoughResources, DepositHasAnotherResource, WrongChosenResources, IOException, ClassNotFoundException {

        ObjectMapper mapper = new ObjectMapper();

        if (jsonResult.contains("USERNAME") || jsonResult.contains("NUMOFPLAYERS")){
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


        PacketSetup packetSetup = new PacketSetup(username,idClient,posInGame,game.getNumof_players(), game.getCurrentPlayer(),0,game.getTable(), game.getInitialDevGrid(), game.getIdClientActivePlayers().get(idClient).getBoard().getDevelopmentSpaces(), game.getIdClientActivePlayers().get(idClient).getLeaderCards(),
           game.getIdClientActivePlayers().get(idClient).getResourceBuffer(),game.getIdClientActivePlayers().get(idClient).getBoard().getSpecialProductionPowers(),
           game.getIdClientActivePlayers().get(idClient).getBoard().getStrongbox(),game.getIdClientActivePlayers().get(idClient).getBoard().getDeposits(), game.getIdClientActivePlayers().get(idClient).getWhiteMarbleCardChoice());


        try {
            jsonResult = mapper.writeValueAsString(packetSetup);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //System.out.println(jsonResult);
        sendToClient(jsonResult);
    }

    public void clientMessagesHandle(String jsonResult){

    }

    /**
     * Method sendUpdatePacket sends updates to the client
     * @param serverPacketHandler (type ServerPacketHandler) -
     */
    public synchronized void sendPacketToClient(ServerPacketHandler serverPacketHandler){
        mapper = new ObjectMapper();
        try {
            jsonResult = mapper.writeValueAsString(serverPacketHandler);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        sendToClient(jsonResult);

    }

    public synchronized void sendMessageToClient(ConnectionMessages msg){
        String messageToSend = msg.getMessage();
        try{
            ps.println(messageToSend);
        }catch (Exception e){
            close();
        }
    }

    public synchronized void sendToClient(String jsonResult){
        try {
            output.writeUTF(jsonResult);
            output.flush();
        } catch (IOException e) {
            System.err.println("Error during the communication from server to client!");
        }
    }
}