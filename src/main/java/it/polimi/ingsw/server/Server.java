package it.polimi.ingsw.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.PacketHandler;
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

    private int idClient;

    private Game game;


    /** List of clients waiting in the lobby. */
    private final List<SocketClientConnected> waiting = new ArrayList<>();


    public Server() {
        game = new Game();

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

                SocketClientConnected socketClientConnected = new SocketClientConnected(i, socket);
                i++;
                waiting.add(socketClientConnected);

                System.out.println(socketClientConnected.getName() + " connected!");

                executor.submit(new ClientHandler(createClientID(), socketClientConnected, this)); //per ogni socket noi creiamo un thread

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

    public boolean checkUsername(){
        return true;
    }

    public PacketHandler deserialize(String jsonResult, SocketClientConnected socket){
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
