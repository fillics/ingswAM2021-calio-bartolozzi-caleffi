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

public class ClientHandler implements Runnable {
    private SocketConnection socketConnection;
    private boolean quit = false;
    private Server server;
    private OutputStream output;
    private Scanner in;
    private Lock lock = new ReentrantLock();


    public ClientHandler(SocketConnection socketConnection, Server server) {
        this.socketConnection = socketConnection;
        this.server = server;
    }

    public void run() {
        try {
            in = new Scanner(socketConnection.getSocket().getInputStream());
            output = socketConnection.getSocket().getOutputStream();

            if(socketConnection.getSocket().equals(server.getGuestsConnected().get(0).getSocket())){
                askNumberOfPlayers();
            }

            // TODO: 04/05/2021 tutti i thread in wait fino a quando il primo player non mette il numero
            askUsername();


            // Leggo e scrivo nella connessione finche' non ricevo "quit"
            while (!quit) {
                String line = in.nextLine();
                if (line.equals("quit")) {
                    quit = true;
                } else {
                    output.write("Received: ".getBytes(StandardCharsets.UTF_8));
                    output.write(line.getBytes(StandardCharsets.UTF_8));
                    output.write("\n".getBytes(StandardCharsets.UTF_8));
                    System.out.println(socketConnection.getIdClient());
                    output.flush();
                }
            }
            // Chiudo gli stream e il socket -> client non è più connesso al server
            in.close();
            output.close();
            socketConnection.getSocket().close();
        } catch (IOException  e) {
            System.err.println(e.getMessage());
        }
    }

    public void askUsername() throws IOException {
        PacketHandler object;
        String username;
        output = socketConnection.getSocket().getOutputStream();

        sendConnectionMessage(ConnectionMessages.INSERT_USERNAME);

        in = new Scanner(socketConnection.getSocket().getInputStream());
        username = in.nextLine();
        // TODO: 04/05/2021  fare controllo username
        PacketUsername packet = new PacketUsername(username);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(packet);
        object = server.deserialize(jsonResult, socketConnection);

    }

    public void askNumberOfPlayers() throws IOException {
        PacketHandler object;
        boolean numNotValid = false;
        int number_of_players;
        output = socketConnection.getSocket().getOutputStream();
        in = new Scanner(socketConnection.getSocket().getInputStream());

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
        object = server.deserialize(jsonResult, socketConnection);
    }


    private void sendConnectionMessage(ConnectionMessages message) throws IOException{
        lock.lock();
        try{
            output.write(message.getMessage().getBytes(StandardCharsets.UTF_8));
            output.flush();
        }
        finally {
            lock.unlock();
        }

    }
}