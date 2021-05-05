package it.polimi.ingsw.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.controller.client_packets.PacketUsername;
import it.polimi.ingsw.model.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Server class allows clients to connect, play together and also starts the match.
 */

public class Server {
    private int port;
    private int idClient;

    private Game game;

    private ArrayList<SocketConnection> guestsConnected;
    private ArrayList<SocketConnection> lobby;

    /** List of clients waiting in the lobby. */
    private final List<SocketConnection> waiting = new ArrayList<>();


    public Server() {
        game = new Game();
        guestsConnected = new ArrayList<>();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Master of Renaissance Server | Welcome!");
        System.out.println(">Insert the port which server will listen on.");
        System.out.print(">");
        int port = 0;
        try {
            port = scanner.nextInt();
        } catch (Exception e) {
            System.err.println("Numeric format requested, application will now close...");
            System.exit(-1);
        }
        if (port < 0 || (port > 0 && port < 1024)) {
            System.err.println("Error: ports accepted started from 1024! Please insert a new value.");
            main(null);
        }

        Constants.setPort(port);

        System.err.println(Constants.getInfo() + "Starting Socket Server");
        Server server = new Server();
        server.startServer();

    }

    public ArrayList<SocketConnection> getGuestsConnected() {
        return guestsConnected;
    }

    public void startServer() {
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(Constants.getPort());
        } catch (IOException e) {
            System.err.println(e.getMessage()); // Porta non disponibile
            return;
        }

        System.out.println("Server ready!");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                // TODO: 05/05/2021 da sistemare
                SocketConnection socketConnection = new SocketConnection(createClientID(), socket, createClientID());

                guestsConnected.add(socketConnection);

                System.out.println(socketConnection.getName() + " connected!");

                executor.submit(new ClientHandler(socketConnection, this)); //per ogni socket noi creiamo un thread

            } catch(IOException e) {
                break; // Entrerei qui se serverSocket venisse chiuso
            }
        }
        executor.shutdown();
    }

    public synchronized int createClientID() {
        int id = idClient;
        idClient+=1;
        return id;
    }


    public PacketHandler deserialize(String jsonResult, SocketConnection socket){
        ObjectMapper mapper = new ObjectMapper();
        PacketHandler packet = null;
        try {
            packet = mapper.readValue(jsonResult, PacketHandler.class);
        } catch (JsonProcessingException  e) {
            e.printStackTrace();
        }
        if (packet != null) {
            packet.execute(game,socket);
        }
        System.out.println("Numero di player: " + game.getNumof_players());
        return packet;
    }




}
