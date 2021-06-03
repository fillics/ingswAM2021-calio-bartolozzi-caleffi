package it.polimi.ingsw.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.GUIOperationHandler;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.client_packets.PacketNumPlayers;
import it.polimi.ingsw.controller.client_packets.PacketUsername;
import it.polimi.ingsw.controller.client_packets.SetupHandler;

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
    private ViewChoice viewChoice;

    /**
     * Constructor Client creates a new Client instance
     *
     */
    public Client(ViewChoice viewChoice) {

        clientModelView = new ClientModelView();
        clientStates = ClientStates.SERVERCONNECTION;

        if(viewChoice.equals(ViewChoice.CLI)){
            cli = new CLI(this, clientModelView);
            cli.serverMatch();
            serverConnection(viewChoice);
        }

        if (viewChoice.equals(ViewChoice.GUI)){
            gui = new GUI(this, clientModelView);
            new Thread(gui).start();
        }

    }

    public static void main(String[] args) {
        System.out.println(Constants.MASTEROFRENAISSANCE);
        System.out.println(Constants.AUTHORS);
        ViewChoice viewChoice = viewInterfaceChoice();

        Client client = new Client(viewChoice);
        client.setViewChoice(viewChoice);

    }

    public void setViewChoice(ViewChoice viewChoice) {
        this.viewChoice = viewChoice;
    }

    public void serverConnection(ViewChoice viewChoice){
        socketClientConnection = new SocketClientConnection(this);

        if(viewChoice.equals(ViewChoice.CLI)) {
            clientOperationHandler = new CLIOperationHandler(socketClientConnection, clientModelView, cli);
            this.setClientOperationHandler(clientOperationHandler);
        }

        if(viewChoice.equals(ViewChoice.GUI)){
            clientOperationHandler = new GUIOperationHandler(socketClientConnection, clientModelView, gui);
            this.setClientOperationHandler(clientOperationHandler);
        }
        setup(viewChoice);
    }

    public void setup(ViewChoice viewChoice){
        //creo i due thread solo se la variabile booleana che indica se la connessione tra client e server non ha avuto problemi
        if(socketClientConnection.getConnectionToServer().get()){
            if(viewChoice.equals(ViewChoice.CLI)){
                ServerListener serverListener = new ServerListener(this, socketClientConnection);
                ServerWriter serverWriter = new ServerWriter(this, socketClientConnection, clientOperationHandler);
                new Thread(serverWriter).start();
                new Thread(serverListener).start();
            }
            if(viewChoice.equals(ViewChoice.GUI)){
                ServerListener serverListener = new ServerListener(this, socketClientConnection);
                new Thread(serverListener).start();
            }
        }
        clientStates = ClientStates.USERNAME;
    }


    /**
     * Method viewInterfaceChoice asks to the client if he wants to play using CLI or GUI
     */
    public static ViewChoice viewInterfaceChoice(){
        Scanner in = new Scanner(System.in);
        int choiceInterface = 0;
        ViewChoice viewChoice = null;
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

        if(choiceInterface==1) {
            viewChoice= ViewChoice.CLI;
            System.out.println(Constants.ANSI_YELLOW+"You have chosen to play using the CLI. Enjoy the game!"+Constants.ANSI_RESET);
        }
        if(choiceInterface==2) {
            viewChoice= ViewChoice.GUI;
            System.out.println(Constants.ANSI_YELLOW+"You have chosen to play using the GUI. Enjoy the game!"+Constants.ANSI_RESET);
        }
        return viewChoice;

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

    public ViewChoice getViewChoice() {
        return viewChoice;
    }

    public CLI getCli() {
        return cli;
    }

    public GUI getGui() {
        return gui;
    }
}
