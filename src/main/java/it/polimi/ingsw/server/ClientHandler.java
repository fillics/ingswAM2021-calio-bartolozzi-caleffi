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
            // TODO: 05/05/2021 CHIEDERE IL NUMERO DI PLAYERS SOLO AL CREATORE DELLA PARTITA

            //TODO: ENTRO SOLO SE è IL MIO TURNO
            //makeAction();

            // Leggo e scrivo nella connessione finche' non ricevo "quit"
            while (!quit) {
                str = dis.readUTF();
                deserialize(str);


                if(game.isEndgame())
                    quit=true;

                /*String line = in.nextLine();
                System.out.println(line);
                if (line.equals("quit")) {
                    quit = true;
                } else {
                    output.write("Received: ".getBytes(StandardCharsets.UTF_8));
                    output.write(line.getBytes(StandardCharsets.UTF_8));
                    output.write("\n".getBytes(StandardCharsets.UTF_8));

                    output.flush();
                }*/
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
        ps.println(ConnectionMessages.INSERT_NUMBER_OF_PLAYERS.getMessage());
    }

    /**
     * method that sends a message to the client to ask him a new username
     */
    public void askUsernameAgain(){
        ps.println(ConnectionMessages.INVALID_USERNAME.getMessage());
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


    public void makeAction() throws IOException {
        int numOfAction;
        boolean numNotValid = false;
        //output = socketClientConnected.getSocket().getOutputStream();
        //in = new Scanner(socketClientConnected.getSocket().getInputStream());

        do{
            //sendConnectionMessage(ConnectionMessages.CHOOSE_ACTION);
            numOfAction = in.nextInt();

            if(numOfAction!= 1 && numOfAction!=2 && numOfAction!=3)
                numNotValid= true;
        } while(numNotValid);

        switch (numOfAction){
            case 1 : takeResources();
            case 2 : buy();
            case 3 : activateProduction();
        }
    }

    public void buy(){
    }

    public void takeResources() throws IOException {
        PacketHandler object;
        String line;
        int numline, numlimit;
        ArrayList<Integer> leaderCardsID = null;

        //output = socketClientConnected.getSocket().getOutputStream();

        do{
            //sendConnectionMessage(ConnectionMessages.CHOOSE_LINE);
            //in = new Scanner(socketClientConnected.getSocket().getInputStream());
            line = in.nextLine();
        } while(!line.equals("ROW") && !line.equals("COLUMN"));

        do{
            //sendConnectionMessage(ConnectionMessages.CHOOSE_NUMLINE);
            // in = new Scanner(socketClientConnected.getSocket().getInputStream());
            numline = in.nextInt();
            if(line.equals("ROW"))
                numlimit=3;
            else
                numlimit =4;
        } while(numline<1 || numline>numlimit);

        /*do{
            sendConnectionMessage(ConnectionMessages.CHOOSE_LEADERCARDSWHITEMARBLE);
            in = new Scanner(socketConnection.getSocket().getInputStream());
            //while()
            numline = in.nextInt();


        } while();*/

        PacketTakeResourceFromMarket packet = new PacketTakeResourceFromMarket(line,numline,leaderCardsID);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(packet);
        // object = server.deserialize(jsonResult, socketClientConnected);
    }

    public void activateProduction(){
    }

}