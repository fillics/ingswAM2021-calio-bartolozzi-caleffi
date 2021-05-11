package it.polimi.ingsw.server;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.SetupHandler;
import it.polimi.ingsw.controller.client_packets.ClientPacketHandler;
import it.polimi.ingsw.controller.server_packets.PacketSetup;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentCard;
import it.polimi.ingsw.model.marbles.Marble;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Socket socket;
    private Game game;
    private int idClient, idGame;
    private boolean quit = false;
    private Server server;
    private DataInputStream dis;
    private DataOutputStream output;
    private PrintStream ps;
    private String username;
    private ObjectMapper mapper;


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

    public void run() {
        try {

            while (!quit) {
                String str = dis.readUTF();
                if(str.equals(ConnectionMessages.SEND_SETUP_PACKETS.getMessage())){
                    sendSetupPacket();
                }
                else {
                    deserialize(str);
                    clientMessagesHandle(str);
                }
                //TODO: IMPLEMENTARE FINE DEL WHILE SENZA ENDGAME
                //if(game.isEndgame())
                //   quit=true;
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

    public int getIdClient() {
        return idClient;
    }

    public void sendMessageToClient(ConnectionMessages msg){
        String messageToSend = msg.getMessage();
        try{
            ps.println(messageToSend);
        }catch (Exception e){
            close();
        }
    }

    public void close(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deserialize(String jsonResult) throws DevelopmentCardNotFound,
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

    //TODO: SISTEMARE PROBLEMA FOR E CREARE PACCHETTO VERO
    public void sendSetupPacket() throws JsonProcessingException {
        String jsonResult;
        ArrayList<DevelopmentCard> topDevelopmentGrid = new ArrayList<>();
        mapper = new ObjectMapper();
       /*for(int i=0; i<12;i++){
            topDevelopmentGrid.add(game.getDevelopmentGrid().get(i).getLast());
        }*/
        PacketSetup packetSetup = new PacketSetup(username,idClient,0,null,topDevelopmentGrid,null,null,null,null,null,null,null);

        //PacketSetup packetSetup = new PacketSetup(username,idClient,0,game.getTable(), topDevelopmentGrid,game.getActivePlayers().get(0).getBoard().getDevelopmentSpaces(),game.getActivePlayers().get(0).getLeaderCards(),
        //game.getActivePlayers().get(0).getResourceBuffer(),game.getActivePlayers().get(0).getBoard().getSpecialProductionPowers(),
       //game.getActivePlayers().get(0).getBoard().getStrongbox(), game.getActivePlayers().get(0).getBoard().getDeposits(), game.getActivePlayers().get(0).getWhiteMarbleCardChoice());

        jsonResult = mapper.writeValueAsString(packetSetup);
        System.out.println(jsonResult);
        sendtoClient(jsonResult);
    }

    public void clientMessagesHandle(String jsonResult){

    }

    public void sendtoClient(String jsonResult){
        try {
            output.writeUTF(jsonResult);
            output.flush();
        } catch (IOException e) {
            System.err.println("Error during the communication from server to client!");
        }
    }
}