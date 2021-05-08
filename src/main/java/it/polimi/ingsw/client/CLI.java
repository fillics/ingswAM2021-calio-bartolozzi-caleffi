package it.polimi.ingsw.client;

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

    //private DataOutputStream dout;

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

    @Override
    public void run() {

        //PUOI FARE QUESTE OPERAZIONI 1, 2, 3 ...

        try {
            printConnectionMessage(ConnectionMessages.INSERT_USERNAME);
            sendUsername();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true){
            String str = socketClientConnection.listening();
            handleSetupMessage(str);
        }



        //TODO: ENTRO SOLO SE è IL MIO TURNO
        //makeAction();

        //ciclare in attesa di un messaggio fino a che il game è attivo
        //input.close();
        //output.close();
    }

    public SocketClientConnection getSocketClientConnected() {
        return socketClientConnection;
    }

    /**
     * Method sendUsername asks the username and sends it to the server
     * @throws IOException
     */
    public void sendUsername() throws IOException {
        String jsonResult;
        PacketUsername packet;
        String username;

        username= input.nextLine();
        //TODO facciamo il controllo username caratteri speciali

        mapper = new ObjectMapper();
        packet = new PacketUsername(username);
        jsonResult = mapper.writeValueAsString(packet);
        socketClientConnection.sendToServer(jsonResult);
    }

    public void choosePlayerNumber() throws IOException {
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
                System.out.println("Invalid parameter: insert a numeric value.");
            }
        }while(number_of_players < Constants.getNumMinPlayers() || number_of_players > Constants.getNumMaxPlayers());

        packet = new PacketNumPlayers(number_of_players);
        mapper = new ObjectMapper();
        jsonResult = mapper.writeValueAsString(packet);
        socketClientConnection.sendToServer(jsonResult);
        System.out.println("You created a new game with " + number_of_players + " players");

    }

    private void printConnectionMessage(ConnectionMessages message){
        System.out.println(message.getMessage());
    }

    public void handleSetupMessage(String message){

        if (message.equals(ConnectionMessages.INSERT_NUMBER_OF_PLAYERS.getMessage())){
            printConnectionMessage(ConnectionMessages.LOBBY_MASTER);
            System.out.println(message);
            try {
                choosePlayerNumber();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (message.equals(ConnectionMessages.INVALID_USERNAME.getMessage())){
            System.out.println(message);
            try {
                sendUsername();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
