package it.polimi.ingsw.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.controller.client_packets.PacketNumPlayers;
import it.polimi.ingsw.controller.client_packets.PacketUsername;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class CLI implements Runnable{
    private final PrintStream output;
    private final Scanner input;
    private SocketClientConnected socketClientConnected;
    private ObjectMapper mapper;
    //private boolean quit= false;
    //private DataOutputStream dout;

    public CLI() {
        input = new Scanner(System.in);
        output = new PrintStream(System.out);
        socketClientConnected = new SocketClientConnected();
    }

    public static void main(String[] args) {
        System.out.println(Constants.MASTEROFRENAISSANCE);
        System.out.println(Constants.AUTHORS);
        Scanner scanner = new Scanner(System.in);
        System.out.println(">Insert the server IP address");
        System.out.print(">");
        String ip = scanner.nextLine();
        Constants.setAddressServer(ip);
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
            askUsername();
            askNumberOfPlayers();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO: 05/05/2021 CHIEDERE IL NUMERO DI PLAYERS SOLO AL CREATORE DELLA PARTITA
        //askNumberOfPlayers();
        //TODO: ENTRO SOLO SE è IL MIO TURNO
        //makeAction();

        //ciclare in attesa di un messaggio fino a che il game è attivo
        input.close();
        output.close();
    }

    public void askUsername() throws IOException {
        String jsonResult;
        PacketUsername packet;
        String username;
        sendConnectionMessage(ConnectionMessages.INSERT_USERNAME);
        username= input.nextLine();
        //TODO facciamo il controllo username caratteri speciali

        mapper = new ObjectMapper();
        packet = new PacketUsername(username);
        jsonResult = mapper.writeValueAsString(packet);
        socketClientConnected.connection(jsonResult);
    }

    public void askNumberOfPlayers() throws IOException {
        String jsonResult;
        PacketNumPlayers packet;
        boolean numNotValid = false;
        int number_of_players;
        do {
            sendConnectionMessage(ConnectionMessages.INSERT_NUMBER_OF_PLAYERS);
            number_of_players = input.nextInt();
            if(number_of_players < Constants.getNumMinPlayers() || number_of_players > Constants.getNumMaxPlayers()){
                numNotValid = true;
            }
        }while(numNotValid);

        packet = new PacketNumPlayers(number_of_players);
        mapper = new ObjectMapper();
        jsonResult = mapper.writeValueAsString(packet);
        socketClientConnected.connection(jsonResult);
    }

    public void askUsernameAgain() {
        sendConnectionMessage(ConnectionMessages.INVALID_USERNAME);
    }

    private synchronized void sendConnectionMessage(ConnectionMessages message){
        System.out.println(message.getMessage());
    }

    public SocketClientConnected getSocketClientConnected() {
        return socketClientConnected;
    }
}
