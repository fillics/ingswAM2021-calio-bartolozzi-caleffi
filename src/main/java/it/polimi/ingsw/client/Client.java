package it.polimi.ingsw.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import it.polimi.ingsw.client.cli.CLI;
import it.polimi.ingsw.client.cli.CLIOperationHandler;
import it.polimi.ingsw.client.communication.ServerListener;
import it.polimi.ingsw.client.communication.ServerWriter;
import it.polimi.ingsw.client.communication.SocketClientConnection;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.client_packets.PacketNumPlayers;
import it.polimi.ingsw.controller.client_packets.PacketUsername;
import it.polimi.ingsw.controller.client_packets.SetupHandler;
import it.polimi.ingsw.server.ArgsConnection;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Client {

    private ClientStates clientStates;
    private SocketClientConnection socketClientConnection;
    private ClientModelView clientModelView;
    private CLIOperationHandler cliOperationHandler;
    private CLI cli;
    private GUI gui;
    private ViewChoice viewChoice;
    private static String DEFAULT_ARGS = "-default";
    private static boolean defaultConnection = false;

    /**
     * Constructor Client creates a new Client instance
     *
     */
    public Client(ViewChoice viewChoice, boolean defaultConnection) {

        clientModelView = new ClientModelView();
        clientStates = ClientStates.SERVERCONNECTION;
        System.out.println(defaultConnection);

        if(defaultConnection){
            Reader reader;
            reader = new InputStreamReader(Objects.requireNonNull(this.getClass().getResourceAsStream("/json/ClientConnection.json")));
            ArgsConnection argsConnection = new Gson().fromJson(reader, ArgsConnection.class);

            try {
                reader.close();
            } catch (IOException ignored) { }

            Constants.setAddressServer(argsConnection.getIpAddress());
            Constants.setPort(Integer.parseInt(argsConnection.getServerPort()));

        }


        if(viewChoice.equals(ViewChoice.CLI)){
            cli = new CLI(this, clientModelView);
            if (!defaultConnection) cli.serverMatch();
            serverConnection(viewChoice);
        }

        if (viewChoice.equals(ViewChoice.GUI)){
            gui = new GUI(this);
            if(defaultConnection) gui.setDefaultConnection();
            new Thread(gui).start();
        }

    }

    /**
     * possiamo passare al jar la stringa -cli o -gui oppure lasciare vuoto per far decidere
     * può scrivere anche -default per caricare i valori della connessione presenti nel file json clientconnection.json
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Master of Renaissance Client | Welcome!");
        System.out.println(Constants.AUTHORS);
        ViewChoice viewChoice = null;


        if(args!=null && args.length!=0){
            if (args[0].equals("-cli")) viewChoice=ViewChoice.CLI;
            else if (args[0].equals("-gui")) viewChoice=ViewChoice.GUI;
            if (args[1].equals(DEFAULT_ARGS)) defaultConnection=true;
        }
        else viewChoice = viewInterfaceChoice();


        assert viewChoice != null;
        Client client = new Client(viewChoice, defaultConnection);
        client.setViewChoice(viewChoice);

    }


    public void serverConnection(ViewChoice viewChoice){
        socketClientConnection = new SocketClientConnection(this);

        if(viewChoice.equals(ViewChoice.CLI)) {
            cliOperationHandler = new CLIOperationHandler(socketClientConnection, clientModelView, cli);
        }

        setup(viewChoice);
    }

    public void setup(ViewChoice viewChoice){
        //creo i due thread solo se la variabile booleana che indica se la connessione tra client e server non ha avuto problemi
        if(socketClientConnection.getConnectionToServer().get()){
            if(viewChoice.equals(ViewChoice.CLI)){
                new Thread(new ServerWriter(this, socketClientConnection, cliOperationHandler)).start();
                new Thread(new ServerListener(this, socketClientConnection)).start();
            }
            if(viewChoice.equals(ViewChoice.GUI)){
                new Thread(new ServerListener(this, socketClientConnection)).start();
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

    public CLIOperationHandler getCliOperationHandler() {
        return cliOperationHandler;
    }

    public ClientModelView getClientModelView() {
        return clientModelView;
    }

    public void setClientModelView(ClientModelView clientModelView) { this.clientModelView = clientModelView;}

    public ViewChoice getViewChoice() {
        return viewChoice;
    }

    public CLI getCli() {
        return cli;
    }

    public GUI getGui() {
        return gui;
    }

    public void setViewChoice(ViewChoice viewChoice) {
        this.viewChoice = viewChoice;
    }


}
