package it.polimi.ingsw.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.client.CLI;
import it.polimi.ingsw.client.SocketClientConnected;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Server class allows clients to connect, play together and also starts the match.
 */

public class Server {

    private int idClient;
    private Game game;

    private Map<String, Socket> mapUsername;

    /** List of clients waiting in the lobby. */
    private final List<SocketClientConnected> lobby = new ArrayList<>();


    public Server() {
        game = new Game();
        mapUsername = new HashMap<>();

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


    public void startServer() {
        int i=0;
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(Constants.getPort());
            System.out.println(Constants.getInfo() + "Socket Server started. Listening on port " + Constants.getPort());

        } catch (IOException e) {
            System.err.println(Constants.getErr() + "Error during Socket initialization, quitting...");
            return;
        }

        System.out.println("Server ready!");
        while (true) {
            try {

                Socket socket = serverSocket.accept();
                System.out.println("Client connected\n");

                i++;
                //lobby.add(socketClientConnected);
                executor.submit(new ClientHandler(createClientID(), socket, this)); //per ogni socket noi creiamo un thread

            } catch(IOException e) {
                System.err.println("Error! " + e.getMessage()); // Entrerei qui se serverSocket venisse chiuso
                break;
            }
        }
        executor.shutdown();
    }

    public synchronized int createClientID() {
        int id = idClient;
        idClient+=1;
        return id;
    }

    public synchronized void lobby(String username, Socket socket){

        if(mapUsername.containsKey(username)){
            System.out.println("nome gia presente");

        }
        else{
            mapUsername.put(username, socket);
            System.out.println("lobby: " + mapUsername.keySet());

        }

    }



}
