package it.polimi.ingsw.server;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.SetupHandler;
import it.polimi.ingsw.controller.client_packets.ClientPacketHandler;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameInterface;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Socket socket;
    private GameInterface game;
    private int idClient, idGame;
    private boolean quit = false;
    private Server server;
    private DataInputStream dis;
    private PrintStream ps;
    private String username;


    public ClientHandler(int idClient, Socket socket, Server server) {
        game= new Game();
        this.idClient = idClient;
        this.socket = socket;
        this.server = server;
        try {
            dis = new DataInputStream(socket.getInputStream());  // to read data coming from the client
            ps = new PrintStream(socket.getOutputStream());// to send data to the client
        } catch (IOException e) {
            System.err.println(Constants.getErr() + "Error during initialization of the client!");
        }
    }

    public void run() {
        try {

            while (!quit) {
                String str = dis.readUTF();
                deserialize(str);

                if(game.isEndgame())
                    quit=true;

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

    public Socket getSocket() {
        return socket;
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
}