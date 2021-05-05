package it.polimi.ingsw.server;


import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.controller.client_packets.PacketNumPlayers;
import it.polimi.ingsw.controller.client_packets.PacketUsername;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.ToDoubleBiFunction;

public class ClientHandler implements Runnable {
    private SocketClientConnected socketClientConnected;
    private int idClient, idGame;
    private boolean quit = false;
    private Server server;
    private OutputStream output;
    private Scanner in;
    private Lock lock = new ReentrantLock();


    public ClientHandler(int idClient, SocketClientConnected socketClientConnected, Server server) {
        this.idClient = idClient;
        this.socketClientConnected = socketClientConnected;
        this.server = server;
        try {
            in = new Scanner(socketClientConnected.getSocket().getInputStream());
            output = socketClientConnected.getSocket().getOutputStream();
        } catch (IOException e) {
            System.err.println(Constants.getErr() + "Error during initialization of the client!");
            System.err.println(e.getMessage());
        }

    }

    public void run() {
        try {

            askUsername();

            // TODO: 05/05/2021 CHIEDERE IL NUMERO DI PLAYERS SOLO AL CREATORE DELLA PARTITA
            askNumberOfPlayers();


            // Leggo e scrivo nella connessione finche' non ricevo "quit"
            while (!quit) {
                String line = in.nextLine();
                if (line.equals("quit")) {
                    quit = true;
                } else {
                    output.write("Received: ".getBytes(StandardCharsets.UTF_8));
                    output.write(line.getBytes(StandardCharsets.UTF_8));
                    output.write("\n".getBytes(StandardCharsets.UTF_8));

                    output.flush();
                }
            }
            // Chiudo gli stream e il socket -> client non è più connesso al server
            in.close();
            output.close();
            socketClientConnected.getSocket().close();
        } catch (IOException  e) {
            System.err.println(e.getMessage());
        }
    }

    public void askUsername() throws IOException {
        PacketHandler object;
        String username;

        sendConnectionMessage(ConnectionMessages.INSERT_USERNAME);

        username = in.nextLine();
        // TODO: 05/05/2021 CONTROLLO FORMA USERNAME, NO CARATTERI
        askUsernameAgain();
        // TODO: 04/05/2021  CONTROLLO SE CI SONO GIA ALTRI USERNAME
        do {

        }while(server.checkUsername());



        PacketUsername packet = new PacketUsername(username);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(packet);
        object = server.deserialize(jsonResult, socketClientConnected);

    }

    public void askUsernameAgain() throws IOException {
        sendConnectionMessage(ConnectionMessages.INVALID_USERNAME);
    }

    public void askNumberOfPlayers() throws IOException {
        PacketHandler object;
        boolean numNotValid = false;
        int number_of_players;


        do {
            sendConnectionMessage(ConnectionMessages.INSERT_NUMBER_OF_PLAYERS);
            number_of_players = in.nextInt();

            if(number_of_players < Constants.getNumMinPlayers() || number_of_players > Constants.getNumMaxPlayers()){
                numNotValid = true;

            }
        }while(numNotValid);

        PacketNumPlayers packet = new PacketNumPlayers(number_of_players);
        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(packet);
        object = server.deserialize(jsonResult, socketClientConnected);
    }


    private synchronized void sendConnectionMessage(ConnectionMessages message) throws IOException{

        output.write(message.getMessage().getBytes(StandardCharsets.UTF_8));
        output.flush();

    }
}