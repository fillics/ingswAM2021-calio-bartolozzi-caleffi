package it.polimi.ingsw.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.LocalGame;
import it.polimi.ingsw.controller.client_packets.PacketNumPlayers;
import it.polimi.ingsw.controller.client_packets.PacketUsername;


import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class CLI implements Runnable, ViewInterface{
    private final PrintStream output;
    private final Scanner input;
    private SocketClientConnection socketClientConnection;
    private ObjectMapper mapper;
    private boolean gameStarted = false;
    private ClientOperationHandler clientOperationHandler;
    private ClientModelView clientModelView;
    int choiceGame = 0;

    /**
     * Constructor CLI creates a new CLI instance
     *
     */
    public CLI() {
        input = new Scanner(System.in);
        output = new PrintStream(System.out);
        socketClientConnection = new SocketClientConnection(this);
        clientModelView = new ClientModelView();
        clientOperationHandler = new ClientOperationHandler(socketClientConnection,clientModelView);
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

    public ClientModelView getClientModelView() {
        return clientModelView;
    }

    @Override
    public void run() {

        choiceGameType();
        if (choiceGame==1) {
            LocalGame localGame = new LocalGame();
        }
        if (choiceGame==2){
            beforeBeginningOfTheGame(); //username and number of players
            //the game has been created
            socketClientConnection.sendToServer(ConnectionMessages.SEND_SETUP_PACKETS.getMessage());

            try {
                socketClientConnection.deserialize();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("username pescato dal packet : " + clientModelView.getMyPlayer().getUsername());

            additionalSetupGame(); //choice of the leader cards and placing additional resources


            System.out.println("We're ready to play! Choose one of the operations you can do:\nText 0 to quit");
            int operation;
            do{
                System.out.println("1: Activate a Leader Card\n" +
                        "2: Buy a Development Card\n" +
                        "3: Choose Discount\n" +
                        "4: Choose the 2 Leader Card to remove\n" +
                        "5: Discard a Leader Card\n" +
                        "6: Move one of you resources\n" +
                        "7: Place one of your resources\n" +
                        "8: Take resources from the market\n" +
                        "9: Use production powers\n");
                operation= input.nextInt();
                if(operation!=0){
                    try {
                        clientOperationHandler.handleOperation(operation);
                    } catch (IOException e) {
                        System.err.println("Error during the choice of the operation to do");
                    }
                }
            }while(operation!=0);

            input.close();
            output.close();
        }

    }

    /**
     * Method choiceGameType asks to the player if he wants to play in solo (without making any connection to the server)
     * or throught the server
     */
    @Override
    public void choiceGameType(){

        printConnectionMessage(ConnectionMessages.LOCAL_OR_SERVERGAME);

        Scanner in = new Scanner(System.in);
        do {
            System.out.print(">");
            try{
                choiceGame = in.nextInt();
                if (choiceGame!=1 && choiceGame!=2) printConnectionMessage(ConnectionMessages.INVALID_CHOICE);
            }catch (NumberFormatException e) {
                System.err.println("Invalid parameter: insert a numeric value.");
            }
        }while(choiceGame!=1 && choiceGame!=2);
    }

    /**
     * Method beforeBeginningOfTheGame handles the initial phase where the game is created (asking for example
     * to the clients the username)
     */
    public void beforeBeginningOfTheGame(){
        printConnectionMessage(ConnectionMessages.INSERT_USERNAME);
        sendUsername();

        while(!gameStarted){
            String str = socketClientConnection.listening();
            handleBeforeGameMessage(str);
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
    public void handleBeforeGameMessage(String message){

        if (ConnectionMessages.INSERT_NUMBER_OF_PLAYERS.getMessage().equals(message)) {
            printConnectionMessage(ConnectionMessages.LOBBY_MASTER);
            System.out.println(message);
            choosePlayerNumber();
        }
        else if (ConnectionMessages.TAKEN_NICKNAME.getMessage().equals(message)) {
            System.out.println(message);
            sendUsername();
        }
        else if (ConnectionMessages.GAME_IS_STARTING.getMessage().equals(message)) {
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

    public void additionalSetupGame(){

    }

    /**
     * Method printConnectionMessage prints the Connection Message passed as a parameter
     */
    private void printConnectionMessage(ConnectionMessages message){
        System.out.println(message.getMessage());
    }

}
