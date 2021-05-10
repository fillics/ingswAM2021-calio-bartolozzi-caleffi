package it.polimi.ingsw.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.client_packets.PacketNumPlayers;
import it.polimi.ingsw.controller.client_packets.PacketUsername;


import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class CLI implements Runnable{
    private final PrintStream output;
    private final Scanner input;
    private SocketClientConnection socketClientConnection;
    private ObjectMapper mapper;
    private boolean gameStarted = false;


    /**
     * Constructor CLI creates a new CLI instance
     *
     */
    public CLI() {
        input = new Scanner(System.in);
        output = new PrintStream(System.out);
        socketClientConnection = new SocketClientConnection();
    }

    public static void main(String[] args) {
        //System.out.println(Constants.MASTEROFRENAISSANCE);
        //System.out.println(Constants.AUTHORS);
        System.out.println("Master of Renaissance CLI | Welcome!");
        Scanner scanner = new Scanner(System.in);
        //System.out.println(">Insert the server IP address");
        //System.out.print(">");
       // String ip = scanner.nextLine(); PER ORA HO TOLTO QUESTA OPZIONE PERCHÈ TANTO È SEMPRE LO STESSO IP ADDRESS
        Constants.setAddressServer("127.0.0.1");
        System.out.println(">Insert the server port");
        System.out.print(">");
        int port = scanner.nextInt();
        Constants.setPort(port);
        CLI cli = new CLI();
        cli.run();
    }
    public SocketClientConnection getSocketClientConnected() {
        return socketClientConnection;
    }

    @Override
    public void run() {

        beforeBeginningOfTheGame();

        //ciclare in attesa di un messaggio fino a che il game è attivo
        //while(game active){
            //TODO: ENTRO SOLO SE è IL MIO TURNO
            //makeAction();
        //}

        input.close();
        output.close();
    }

    /**
     * Method beforeBeginningOfTheGame handles the
     * gestire fase pre game: username (eventualmente username invalido) e numero di players
     */
    public void beforeBeginningOfTheGame(){
        printConnectionMessage(ConnectionMessages.INSERT_USERNAME);
        sendUsername();

        //ciclare fino a quando la fase di setup non è finita
        while(!gameStarted){
            String str = socketClientConnection.listening();
            handleSetupMessage(str);
        }
    }

    /**
     * Method sendUsername asks the username and sends it to the server
     */
    public void sendUsername(){
        String jsonResult;
        PacketUsername packet;
        String username;

        username = input.nextLine();
        //TODO facciamo il controllo username caratteri speciali - metodo che controlla correttezza

        mapper = new ObjectMapper();
        packet = new PacketUsername(username);
        try {
            jsonResult = mapper.writeValueAsString(packet);
            socketClientConnection.sendToServer(jsonResult);
        } catch (JsonProcessingException ignored) {
            System.err.println("Error during the write of the values in the variable jsonResult");
        }
    }

    /**
     * Method choosePlayerNumber asks how many players there will be in the game and sends the message to the server
     */
    public void choosePlayerNumber(){
        String jsonResult;
        PacketNumPlayers packet;
        int number_of_players = 0;

        do {
            System.out.print(">");
            try {
                number_of_players = input.nextInt();
                if(number_of_players < Constants.getNumMinPlayers() || number_of_players > Constants.getNumMaxPlayers()){
                    printConnectionMessage(ConnectionMessages.INVALID_NUM_PLAYERS);
                }
            }catch (NumberFormatException e) {
                System.err.println("Invalid parameter: insert a numeric value.");
            }
        }while(number_of_players < Constants.getNumMinPlayers() || number_of_players > Constants.getNumMaxPlayers());

        packet = new PacketNumPlayers(number_of_players);
        mapper = new ObjectMapper();
        try {
            jsonResult = mapper.writeValueAsString(packet);
            socketClientConnection.sendToServer(jsonResult);
        } catch (JsonProcessingException ignored) {
            System.err.println("Error during the write of the values in the variable jsonResult");
        }
    }


    /**
     * Method handleSetupMessage handles the messages that the server sends. According to them, it calls the right methods.
     * @param message (type String) - it is the message arrived from the server
     */
    public void handleSetupMessage(String message){

        if (ConnectionMessages.INSERT_NUMBER_OF_PLAYERS.getMessage().equals(message)) {
            printConnectionMessage(ConnectionMessages.LOBBY_MASTER);
            System.out.println(message);
            choosePlayerNumber();
        }
        else if (ConnectionMessages.TAKEN_NICKNAME.getMessage().equals(message)) {
            System.out.println(message);
            sendUsername();
        }
        else if (ConnectionMessages.GAME_STARTED.getMessage().equals(message)) {
            System.out.println(message);
            gameStarted = true;
        }

        else if(ConnectionMessages.WAITING_PEOPLE.getMessage().equals(message)){
            System.out.println(message);
        }

        else {
            throw new IllegalStateException("Unexpected value: " + message);
        }
    }

    /**
     *
     * @param message
     */
    private void printConnectionMessage(ConnectionMessages message){
        System.out.println(message.getMessage());
    }

}
