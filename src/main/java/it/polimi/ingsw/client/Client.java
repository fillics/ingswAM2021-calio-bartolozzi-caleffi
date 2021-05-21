package it.polimi.ingsw.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.GUI.ClientGUI;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.client_packets.PacketNumPlayers;
import it.polimi.ingsw.controller.client_packets.PacketUsername;
import it.polimi.ingsw.controller.client_packets.SetupHandler;


import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Client{
    private ClientStates clientStates;
    private final Scanner input;
    private SocketClientConnection socketClientConnection;
    private final ClientOperationHandler clientOperationHandler;
    private final ClientModelView clientModelView;


    /**
     * Constructor Client creates a new Client instance
     *
     */
    public Client() {
        input = new Scanner(System.in);
        PrintStream output = new PrintStream(System.out);

        serverMatch();

        socketClientConnection = new SocketClientConnection(this);
        clientModelView = new ClientModelView();
        clientOperationHandler = new ClientOperationHandler(socketClientConnection,clientModelView);

        //creo i due thread solo se la variabile booleana che indica se la connessione tra client e server non ha avuto problemi
        if(socketClientConnection.getConnectionToServer().get()){
            ServerListener serverListener = new ServerListener(this, socketClientConnection);
            ServerWriter serverWriter = new ServerWriter(this, clientModelView, socketClientConnection, clientOperationHandler, output, input);
            new Thread(serverWriter).start();
            new Thread(serverListener).start();
        }

        clientStates = ClientStates.USERNAME;
    }


    public static void main(String[] args) {
        System.out.println(Constants.MASTEROFRENAISSANCE);
        System.out.println(Constants.AUTHORS);
        int choiceInterface;

        choiceInterface = viewInterfaceChoice();

        Client client = new Client();

        if(choiceInterface==1){
            client.setInterface(new CLI(client.getClientModelView()));
        }

        if (choiceInterface==2){
            client.setInterface(new ClientGUI(client.getClientModelView()));
        }


    }

    /**
     * Static method serverMatch used to create the communication with the server to play online
     */
    public static void serverMatch(){

        Scanner scanner = new Scanner(System.in);

        System.out.println(">Insert the server IP address");
        System.out.print(">");
        String ip = scanner.nextLine();
        Constants.setAddressServer(ip);
        System.out.println(">Insert the server port");
        System.out.print(">");
        int port = 0;
        try{
            port = scanner.nextInt();
        }catch(InputMismatchException e){
            System.err.println("insert only numbers");
        }
        Constants.setPort(port);


    }

    /**
     * Method viewInterfaceChoice asks to the client if he wants to play using CLI or GUI
     * @return the choice of the chosen view
     */
    public static int viewInterfaceChoice(){
        Scanner in = new Scanner(System.in);
        int choiceInterface;
        do{
            System.out.println("Choose the view interface: \n" + "1.CLI \n" + "2.GUI");
            System.out.print(">");
            choiceInterface = in.nextInt();
            if(choiceInterface!=1 && choiceInterface!=2)
                System.err.println("Your choice is not available, please retry");
        } while(choiceInterface!=1 && choiceInterface!=2);

        if(choiceInterface==1) System.out.println(Constants.ANSI_YELLOW+"You have chosen to play using the CLI. Enjoy the game!"+Constants.ANSI_RESET);
        if(choiceInterface==2) System.out.println(Constants.ANSI_YELLOW+"You have chosen to play using the GUII. Enjoy the game!"+Constants.ANSI_RESET);

        return choiceInterface;
    }


    public void setInterface(ViewInterface viewInterface){
        clientOperationHandler.setViewInterface(viewInterface);
    }

    /**
     * Method sendUsername asks the username and sends it to the server
     */
    public void sendUsername(String username){

        PacketUsername packet;
        packet = new PacketUsername(username.toLowerCase(Locale.ROOT));
        serializeAndSend(packet);
    }

    /**
     * Method choosePlayerNumber asks how many players there will be in the game and sends the message to the server
     */
    public void choosePlayerNumber(int number_of_players){
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
        serializeAndSend(packet);
    }

    /**
     * Method chooseReconnection
     * @param reconnectionChoice (type Integer)
     */
    public void chooseTypeConnection(int reconnectionChoice){

        do {
            try {
                if(reconnectionChoice < 1 || reconnectionChoice > 2){
                    Constants.printConnectionMessage(ConnectionMessages.INVALID_CHOICE);
                    reconnectionChoice = input.nextInt();
                }
            }catch (InputMismatchException e) {
                System.err.println("Invalid parameter: insert a numeric value.");
            }
        }while(reconnectionChoice < 1 || reconnectionChoice > 2);

    }


    /**
     * Method serializeAndSend serializes and sends to the server the packet passed as a parameter
     * @param packet (type SetupHandler) - it is the packet to send
     */
    public void serializeAndSend(SetupHandler packet){
        String jsonResult;
        ObjectMapper mapper = new ObjectMapper();
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
