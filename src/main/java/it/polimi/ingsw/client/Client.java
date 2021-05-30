package it.polimi.ingsw.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.GUIOperationHandler;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.client_packets.PacketNumPlayers;
import it.polimi.ingsw.controller.client_packets.PacketUsername;
import it.polimi.ingsw.controller.client_packets.SetupHandler;

import java.io.IOException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Client {

    private ClientStates clientStates;
    private SocketClientConnection socketClientConnection;
    private ClientModelView clientModelView;
    private ClientOperationHandler clientOperationHandler;
    private CLI cli;
    private GUI gui;
    private ViewInterface viewInterface;
    private int choiceInterface;
    private Scanner input;

    /**
     * Constructor Client creates a new Client instance
     *
     */
    public Client(int choiceInterface) {

        clientModelView = new ClientModelView();
        clientStates = ClientStates.SERVERCONNECTION;

        if(choiceInterface==1){
            cli = new CLI(this, clientModelView);
            cli.serverMatch();
            serverConnection(choiceInterface);
        }

        if (choiceInterface==2){
            gui = new GUI(this, clientModelView);
            new Thread(gui).start();
        }

    }

    public void serverConnection(int choiceInterface){
        socketClientConnection = new SocketClientConnection(this);

        if(choiceInterface==1) {
            clientOperationHandler = new CLIOperationHandler(socketClientConnection, clientModelView, cli);
            this.setClientOperationHandler(clientOperationHandler);
        }

        if(choiceInterface==2){
            clientOperationHandler = new GUIOperationHandler(socketClientConnection, clientModelView, gui);
            this.setClientOperationHandler(clientOperationHandler);
        }
        setup(choiceInterface);
    }


    public static void main(String[] args) {
        System.out.println(Constants.MASTEROFRENAISSANCE);
        System.out.println(Constants.AUTHORS);
        int choiceInterface;

        choiceInterface = viewInterfaceChoice();
        Client client = new Client(choiceInterface);
        client.setChoiceInterface(choiceInterface);
    }

    public void setup(int choiceInterface){
        //creo i due thread solo se la variabile booleana che indica se la connessione tra client e server non ha avuto problemi
        if(socketClientConnection.getConnectionToServer().get()){
            if(choiceInterface==1){
                ServerListener serverListener = new ServerListener(this, socketClientConnection);
                ServerWriter serverWriter = new ServerWriter(this, socketClientConnection, clientOperationHandler);
                new Thread(serverWriter).start();
                new Thread(serverListener).start();
            }
            if(choiceInterface==2){
                ServerListener serverListener = new ServerListener(this, socketClientConnection);
                new Thread(serverListener).start();
            }
        }
        clientStates = ClientStates.USERNAME;
    }


    /**
     * Method viewInterfaceChoice asks to the client if he wants to play using CLI or GUI
     * @return the choice of the chosen view
     */
    public static int viewInterfaceChoice(){
        Scanner in = new Scanner(System.in);
        int choiceInterface = 0;
        do{
            System.out.println("Choose the view interface: \n" + "1.CLI \n" + "2.GUI");
            System.out.print(">");
            try{
                choiceInterface = Integer.parseInt(in.nextLine());
            }catch (InputMismatchException|NumberFormatException e){
                System.err.println("Please, insert a number!");
                viewInterfaceChoice();
            }
            if(choiceInterface!=1 && choiceInterface!=2)
                System.err.println("Your choice is not available, please retry");
        } while(choiceInterface!=1 && choiceInterface!=2);

        if(choiceInterface==1) System.out.println(Constants.ANSI_YELLOW+"You have chosen to play using the CLI. Enjoy the game!"+Constants.ANSI_RESET);
        if(choiceInterface==2) System.out.println(Constants.ANSI_YELLOW+"You have chosen to play using the GUI. Enjoy the game!"+Constants.ANSI_RESET);

        return choiceInterface;
    }

    /**
     * Method sendUsername sends the username to the server
     */
    public void sendUsername(String username){
        PacketUsername packet;
        packet = new PacketUsername(username.toLowerCase(Locale.ROOT));
        serializeAndSend(packet);
    }

    // TODO: 28/05/2021 mergeare sendusername e sendnumplayers in un unico metodo
    /**
     * Method sendNumPlayers sends the num of players to the server
     */
    public void sendNumPlayers(int numPlayers){
        PacketNumPlayers packet;
        packet = new PacketNumPlayers(numPlayers);
        serializeAndSend(packet);
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

    public SocketClientConnection getSocketClientConnection() {
        return socketClientConnection;
    }

    public void setClientState(ClientStates clientStates) {
        this.clientStates = clientStates;
    }

    public ClientStates getClientState() {
        return clientStates;
    }

    public void setClientOperationHandler(ClientOperationHandler clientOperationHandler) {
        this.clientOperationHandler = clientOperationHandler;
    }
    public ClientOperationHandler getClientOperationHandler() {
        return clientOperationHandler;
    }

    public ClientModelView getClientModelView() {
        return clientModelView;
    }

    public void setClientModelView(ClientModelView clientModelView) { this.clientModelView = clientModelView;}

    public void setInterface(ViewInterface viewInterface){
        clientOperationHandler.setViewInterface(viewInterface);
    }
    public void setChoiceInterface(int choiceInterface) {
        this.choiceInterface = choiceInterface;
    }

    public int getChoiceInterface() {
        return choiceInterface;
    }

    public CLI getCli() {
        return cli;
    }

    public GUI getGui() {
        return gui;
    }
}

