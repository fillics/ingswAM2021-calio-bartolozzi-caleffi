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
    private int numPlayers;

    private Map<String, ClientHandler> mapUsernameClientHandler;
    private Map<Integer, Integer> mapIdGame; // per sapere a quale idGame appartiene un idPlayer
    //private Map<Integer, String> mapId;

    /** List of clients waiting in the lobby. */
    private final Queue<ClientHandler> lobby = new LinkedList<>();


    public Server() {
        game = new Game();
        mapUsernameClientHandler = new HashMap<>();

    }

    public Queue<ClientHandler> getLobby() {
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
                System.out.println("Guest"+i+" connected: "+socket);
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

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * aggiungiamo la persona alla lobby
     */
    public synchronized void addToLobby(String username, ClientHandler clientHandler){
        lobby.add(clientHandler);
        checkUsernameAlreadyTaken(username, clientHandler);
        checkFirstPositionInLobby(clientHandler);

    }

    /**
     * se la persona aggiunta è la prima della lobby, chiediamo il numero di players
     * @param clientHandler
     */
    public synchronized void checkFirstPositionInLobby(ClientHandler clientHandler){
        if (lobby.peek() != null && lobby.peek().equals(clientHandler)) {
            clientHandler.askPlayers();
        }
    }

    /**
     * controlliamo se qualcuno ha già inserito un username
     */
    public synchronized void checkUsernameAlreadyTaken(String username, ClientHandler clientHandler){

        if(mapUsernameClientHandler.containsKey(username)){
            clientHandler.askUsernameAgain();
        }
        else addUsernameIntoMap(username, clientHandler);
    }

    /**
     * aggiungiamo l'username nella mappa con tutti gli username scelti
     * @param username
     * @param clientHandler
     */
    public synchronized void addUsernameIntoMap(String username, ClientHandler clientHandler){
        mapUsernameClientHandler.put(username, clientHandler);
        clientHandler.setUsername(username);
        System.out.println(username + " (idPlayer: " +clientHandler.getIdClient()+") joined!");
    }

    /**
     * iniziamo il match solo quando numPlayers è uguale alla dimensione della lobby
     */
    public synchronized void checkStartOfTheGame(){
        //System.out.println("sono dentrocheckstart of the game");
        System.out.println("num players settati: "+numPlayers);
        System.out.println("dimensione lobby: "+lobby.size());
        if(numPlayers <= lobby.size()){
            createMatch();
        }
    }


    public synchronized void createMatch(){

        game = new Game();
        games.add(game);
        game.setIdGame(createGameID());

        System.out.println("Created the game (idGame: " + game.getIdGame()+ ") with " +numPlayers+" players");

        for (int i=0; i<numPlayers; i++){
            if (lobby.peek() != null) {
                game.createNewPlayer(lobby.peek().getUsername());
                lobby.peek().gameIsStarting();
            }
            lobby.remove();
        }
        numPlayers=0;

        if (lobby.size()!=0){
            lobby.peek().askPlayers();
        }


        game.setup();

        //stampare chi c'è nel game


    }

}
