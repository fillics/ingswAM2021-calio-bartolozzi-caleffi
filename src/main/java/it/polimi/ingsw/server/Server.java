package it.polimi.ingsw.server;

import it.polimi.ingsw.constants.Constants;
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

    private int idClient = 0;
    private int idGame = 0;
    private Game game;

    private Map<String, Integer> mapUsernameId;
    private Map<Integer, String> mapId;

    /** List of clients waiting in the lobby. */
    private final LinkedList<ClientHandler> lobby = new LinkedList<>();


    public Server() {
        game = new Game();
        mapUsernameId = new HashMap<>();

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

        Server server = new Server();
        server.startServer();

    }


    public void startServer() {
        int i=1;
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(Constants.getPort());
            System.out.println("Socket Server started. Listening on port " + Constants.getPort());

        } catch (IOException e) {
            System.err.println(Constants.getErr() + "Error during Socket initialization, quitting...");
            return;
        }

        System.out.println("Server ready!");
        while (true) {
            try {

                Socket socket = serverSocket.accept();
                System.out.println("Guest"+i+" connected");
                i+=1;

                executor.submit(new ClientHandler(createClientID(), socket, this));

            } catch(IOException e) {
                System.err.println("Error! " + e.getMessage()); // Entrerei qui se serverSocket venisse chiuso
                break;
            }
        }
        executor.shutdown();
    }

    public synchronized int createClientID() {
        return idClient+=1;
    }

    public synchronized int createGameID() {
        return idGame+=1;
    }


    public synchronized void addToLobby(ClientHandler clientHandler){

        lobby.add(clientHandler); //qua ci sono già le persone che hanno inserito l'username (non ancora controllato)
        lobby.getFirst().askPlayers();


    }

    /**
     * controlliamo se qualcuno ha già inserito un username
     * @param username
     * @param clientHandler
     */
    public synchronized void insertUsernameIntoMap(String username, ClientHandler clientHandler){

        if(mapUsernameId.containsKey(username)){
            clientHandler.askUsernameAgain();
        }
        else {
            mapUsernameId.put(username, clientHandler.getIdClient());
            System.out.println(username + " (id: " +clientHandler.getIdClient()+") joined!");
        }
        //System.out.println(mapUsernameId);
    }

    public synchronized  void createMatch(int idClient, int numplayer){
        game = new Game();
        game.setIdGame(createGameID());
        System.out.println("Created the game (idGame: " + game.getIdGame()+ ") with " +numplayer+" players");
    }

}
