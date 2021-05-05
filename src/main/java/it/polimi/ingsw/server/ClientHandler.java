package it.polimi.ingsw.server;


import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.client.SocketClientConnected;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.controller.client_packets.PacketNumPlayers;
import it.polimi.ingsw.controller.client_packets.PacketTakeResourceFromMarket;
import it.polimi.ingsw.controller.client_packets.PacketUsername;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientHandler implements Runnable {
    private Socket socket;
    private int idClient, idGame;
    private boolean quit = false;
    private Server server;
    private OutputStream output;
    private Scanner in;
    private DataInputStream dis;
    private Lock lock = new ReentrantLock();


    public ClientHandler(int idClient, Socket socket, Server server) {
        this.idClient = idClient;
        this.socket = socket;
        this.server = server;
        try {
             dis= new DataInputStream(socket.getInputStream());
            output = socket.getOutputStream();
        } catch (IOException e) {
            System.err.println(Constants.getErr() + "Error during initialization of the client!");
            System.err.println(e.getMessage());
        }

    }

    public void run() {
        try {
            String str = dis.readUTF();
            System.out.println("Message is " + str);
            //askUsername();

            // TODO: 05/05/2021 CHIEDERE IL NUMERO DI PLAYERS SOLO AL CREATORE DELLA PARTITA
            //askNumberOfPlayers();

            //TODO: ENTRO SOLO SE è IL MIO TURNO
            //makeAction();

            // Leggo e scrivo nella connessione finche' non ricevo "quit"
            while (!quit) {
                String line = in.nextLine();
                System.out.println(line);
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
            socket.close();
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

        PacketUsername packet = new PacketUsername(username);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(packet);
        //object = server.deserialize(jsonResult, socketClientConnected);

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
       // object = server.deserialize(jsonResult, socketClientConnected);
    }


    private synchronized void sendConnectionMessage(ConnectionMessages message) throws IOException{

        output.write(message.getMessage().getBytes(StandardCharsets.UTF_8));
        output.flush();

    }

    public void makeAction() throws IOException {
        int numOfAction;
        boolean numNotValid = false;
        //output = socketClientConnected.getSocket().getOutputStream();
        //in = new Scanner(socketClientConnected.getSocket().getInputStream());

        do{
            sendConnectionMessage(ConnectionMessages.CHOOSE_ACTION);
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
            sendConnectionMessage(ConnectionMessages.CHOOSE_LINE);
            //in = new Scanner(socketClientConnected.getSocket().getInputStream());
            line = in.nextLine();
        } while(!line.equals("ROW") && !line.equals("COLUMN"));

        do{
            sendConnectionMessage(ConnectionMessages.CHOOSE_NUMLINE);
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