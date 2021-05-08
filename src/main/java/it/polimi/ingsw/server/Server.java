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
    private ArrayList<Game> games = new ArrayList<>();

    private Map<String, Integer> mapUsernameId;
    //private Map<Integer, String> mapId;

    /** List of clients waiting in the lobby. */
    private final LinkedList<ClientHandler> lobby = new LinkedList<>();


    public Server() {
        game = new Game();
        mapUsernameId = new HashMap<>();

    }

    public LinkedList<ClientHandler> getLobby() {
        return lobby;
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


    public synchronized void checkFirstPositionInLobby(ClientHandler clientHandler){

        if(lobby.getFirst().equals(clientHandler)){
            clientHandler.askPlayers();
        }

    }

    /**
     * controlliamo se qualcuno ha già inserito un username
     */
    // TODO: 08/05/2021 da sistemare
    public synchronized boolean checkUsernameAlreadyTaken(String username, ClientHandler clientHandler){

        if(mapUsernameId.containsKey(username)){
            clientHandler.askUsernameAgain();
        }
     return false;
    }

    // TODO: 08/05/2021 da sistemare
    public synchronized void addUsernameIntoMap(String username, ClientHandler clientHandler){
        mapUsernameId.put(username, clientHandler.getIdClient());
        clientHandler.setUsername(username);
        System.out.println(username + " (id: " +clientHandler.getIdClient()+") joined!");
    }


    public synchronized void createMatch(int idClient, int numplayer){

        game = new Game();
        games.add(game);
        game.setIdGame(createGameID());

        System.out.println("Created the game (idGame: " + game.getIdGame()+ ") with " +numplayer+" players");

        for (int i=0; i<numplayer; i++){
            game.createNewPlayer(lobby.get(i).getUsername());
            lobby.getFirst().gameIsStarting();
            lobby.removeFirst();
        }

        game.setup();

        //stampare chi c'è nel game
    }

}
