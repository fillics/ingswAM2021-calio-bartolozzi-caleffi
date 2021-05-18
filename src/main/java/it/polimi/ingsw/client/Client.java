package it.polimi.ingsw.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.client_packets.PacketNumPlayers;
import it.polimi.ingsw.controller.client_packets.PacketUsername;


import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Client {
    private ClientStates clientStates;
    private ServerListener serverListener;
    private  ServerWriter serverWriter;
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
    public Client() {
        input = new Scanner(System.in);
        output = new PrintStream(System.out);
        socketClientConnection = new SocketClientConnection(this);
        clientModelView = new ClientModelView();
        clientOperationHandler = new ClientOperationHandler(socketClientConnection,clientModelView);

        //creo i due thread solo se la variabile booleana che indica se la connessione tra client e server non ha avuto problemi
        if(socketClientConnection.getConnectionToServer().get()){
            serverListener = new ServerListener(this, socketClientConnection);
            serverWriter = new ServerWriter(this, clientModelView, socketClientConnection, clientOperationHandler, output, input);
            new Thread(serverWriter).start();
            new Thread(serverListener).start();
        }

        clientStates = ClientStates.USERNAME;

    }

    public static void main(String[] args) {
        //System.out.println(Constants.MASTEROFRENAISSANCE);
        //System.out.println(Constants.AUTHORS);
        System.out.println("Master of Renaissance CLI | Welcome!");

        /*int choiceGame = choiceGameType();

        if (choiceGame == 1) {
            LocalGame localGame = new LocalGame();
            LocalGame.main(null);
        }
        */
        //ricordarsi di mettere l'else se choiceGame == 2
        serverMatch();

    }

    /**
     * Static method serverMatch used to create the communication with the server to play online
     */
    public static void serverMatch(){
        Scanner scanner = new Scanner(System.in);
        //System.out.println(">Insert the server IP address");
        //System.out.print(">");
        // String ip = scanner.nextLine(); PER ORA HO TOLTO QUESTA OPZIONE PERCHÈ TANTO È SEMPRE LO STESSO IP ADDRESS
        Constants.setAddressServer("127.0.0.1");
        System.out.println(">Insert the server port");
        System.out.print(">"); // TODO: 13/05/2021 prima di chiedere la porta meglio chiedere se vuole giocare in locale o in server
        int port = 0;
        try{
            port = scanner.nextInt();
        }catch(InputMismatchException e){
            System.err.println("insert only numbers");
        }
        Constants.setPort(port);
        Client client = new Client();

    }



    /**
     * Method sendUsername asks the username and sends it to the server
     */
    public void sendUsername(String username){
        String jsonResult;
        PacketUsername packet;

        //TODO facciamo il controllo username caratteri speciali - metodo che controlla correttezza

        mapper = new ObjectMapper();
        packet = new PacketUsername(username.toLowerCase(Locale.ROOT));
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
    public void choosePlayerNumber(int number_of_players){
        String jsonResult;
        PacketNumPlayers packet;

        do {
            try {
                if(number_of_players < Constants.getNumMinPlayers() || number_of_players > Constants.getNumMaxPlayers()){
                    Constants.printConnectionMessage(ConnectionMessages.INVALID_NUM_PLAYERS);
                    number_of_players = input.nextInt();
                }
            }catch (InputMismatchException e) {
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
     * Method choiceGameType asks to the player if he wants to play in solo (without making any connection to the server)
     * or throught the server
     */
    public static int choiceGameType(){

        Constants.printConnectionMessage(ConnectionMessages.LOCAL_OR_SERVERGAME);

        Scanner in = new Scanner(System.in);
        int choiceGame=0;
        do {
            System.out.print(">");
            try{
                choiceGame = in.nextInt();
                if (choiceGame!=1 && choiceGame!=2) Constants.printConnectionMessage(ConnectionMessages.INVALID_CHOICE);
            }catch (InputMismatchException e) {
                System.err.println("Invalid parameter: insert a numeric value.");
                choiceGameType();
            }
        }while(choiceGame!=1 && choiceGame!=2);

        return choiceGame;
    }


    public void setClientState(ClientStates clientStates) {
        this.clientStates = clientStates;
    }

    public ClientStates getClientState() {
        return clientStates;
    }

    public ClientOperationHandler getClientOperationHandler() {
        return clientOperationHandler;
    }

    public ClientModelView getClientModelView() {
        return clientModelView;
    }
}
