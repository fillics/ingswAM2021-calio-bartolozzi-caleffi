package it.polimi.ingsw.server;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.controller.State;
import it.polimi.ingsw.controller.client_packets.PacketNumPlayers;
import it.polimi.ingsw.controller.client_packets.PacketTakeResourceFromMarket;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameInterface;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientHandler implements Runnable {
    private Socket socket;
    private GameInterface game;
    private int idClient, idGame;
    private boolean quit = false;
    private Server server;
    private OutputStream output;
    private Scanner in;
    private DataInputStream dis;
    private String str;
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
            System.err.println(e.getMessage());
        }
    }

    public void run() {
        try {
            //TODO: ENTRO SOLO SE è IL MIO TURNO

            // Leggo e scrivo nella connessione finche' non ricevo "quit"
            while (!quit) {
                str = dis.readUTF();
                deserialize(str);

                if(game.isEndgame())
                    quit=true;

            }
            // Chiudo gli stream e il socket -> client non è più connesso al server
            in.close();
            output.close();
            socket.close();

        } catch (IOException  e) {
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

    /**
     * method to ask the number of the players to the first client of the lobby
     */
    public void askPlayers(){
        sendMessageToClient(ConnectionMessages.INSERT_NUMBER_OF_PLAYERS);
    }

    /**
     * method that sends a message to the client to ask him a new username
     */
    public void askUsernameAgain(){
        sendMessageToClient(ConnectionMessages.TAKEN_NICKNAME);
    }

    public void gameIsStarting(){
        sendMessageToClient(ConnectionMessages.GAME_STARTED);
    }

    public void waitingPeople(){
        sendMessageToClient(ConnectionMessages.WAITING_PEOPLE);
    }

    public void sendMessageToClient(ConnectionMessages msg){
        ps.println(msg.getMessage());
    }

    public void deserialize(String jsonResult) throws DevelopmentCardNotFound,
            EmptyDeposit, LeaderCardNotActivated, LeaderCardNotFound, DevCardNotPlaceable,
            DifferentDimension, DepositDoesntHaveThisResource, DiscountCannotBeActivated,
            NotEnoughRequirements, TooManyResourcesRequested, DepositHasReachedMaxLimit,
            NotEnoughResources, DepositHasAnotherResource, WrongChosenResources {

        ObjectMapper mapper = new ObjectMapper();
        PacketHandler packet = null;
        try {
            packet = mapper.readValue(jsonResult, PacketHandler.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (packet != null) {
            packet.execute(server,game,this);
        }
    }
}