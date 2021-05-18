package it.polimi.ingsw.server;

import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.PacketMessage;
import it.polimi.ingsw.controller.server_packets.ServerPacketHandler;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.singleplayer.SinglePlayerGame;

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
    private final ArrayList<Game> games = new ArrayList<>();
    private int numPlayers;



    /** Map that contains all the username already taken and the clientHandler associated */
    private final Map<String, ClientHandler> mapUsernameClientHandler;

    /** Map that contains all the games with the players of that game */
    private final Map<Integer, ArrayList<ClientHandler>> mapIdGameClientHandler;


    /** Map that contains all the games with the players of that game */
    //private final Map<Integer, ArrayList<ClientHandler>> map;


    /** List of clients waiting in the lobby. */
    private final LinkedList<ClientHandler> lobby = new LinkedList<>();


    public Map<Integer, ArrayList<ClientHandler>> getMapIdGameClientHandler() {
        return mapIdGameClientHandler;
    }

    // TODO: 11/05/2021 aggiungere struttura dati che contiene game e i relativi idplayers
    public Server() {
        mapUsernameClientHandler = new HashMap<>();
        mapIdGameClientHandler = new HashMap<>();
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
                socket.setSoTimeout(20000);
                System.out.println("Guest"+i+" connected");
                i+=1;

                executor.submit(new ClientHandler(createClientID(), socket, this, i));

            } catch(IOException e) {
                System.err.println("Error! " + e.getMessage()); // Entrerei qui se serverSocket venisse chiuso
                break;
            }
        }
        executor.shutdown();
    }

    /**
     * Method createClientID returns a new idClient to assign to the correct ClientHandler
     */
    public synchronized int createClientID() {
        return idClient+=1;
    }

    /**
     * Method createGameID returns a new idGame to assign to the correct Game created
     */
    public synchronized int createGameID() {
        return idGame+=1;
    }

    /**
     * Method setNumPlayers sets the number of the players needed to create a new Game.
     * @param numPlayers (type Int) - it is the number of players that the first person in the lobby has decided
     */
    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    /**
     * Method addToLobby adds to the lobby's queue the clientHandler who inserted his username. After that, it calls the
     * method checkUsernameAlreadyTaken to verify the uniqueness of the username and then checks if that person is the
     * first person in the lobby (to ask him how many players he wants).
     * @param username (type String) - it is the username to check
     * @param clientHandler (type ClientHandler) - it is the clientHandler who has that username
     */
    public synchronized void addToLobby(String username, ClientHandler clientHandler){
        lobby.add(clientHandler);
        checkUsernameAlreadyTaken(username, clientHandler);

    }

    /**
     * Method checkFirstPositionInLobby checks if the ClientHandler, passed as a parameter, is the first person of
     * the lobby. If so, it asks to him to insert the number of players of the game.
     * @param clientHandler (type ClientHandler) - it is the person to check
     */
    public synchronized void checkFirstPositionInLobby(ClientHandler clientHandler){
        if (lobby.peek() != null && lobby.peek().equals(clientHandler)) {
            clientHandler.sendPacketToClient(new PacketMessage(ConnectionMessages.INSERT_NUMBER_OF_PLAYERS));
        }
    }

    /**
     * Method checkUsernameAlreadyTaken checks if the username of the clientHandler already exists in the map that
     * contains all the username already taken. If so, it asks to the person to reinsert the username. Otherwise, that
     * username will be added into the map.
     * @param username (type String) - it is the username to check
     * @param clientHandler (type ClientHandler) - it is the clientHandler who has that username
     */
    public synchronized void checkUsernameAlreadyTaken(String username, ClientHandler clientHandler){

        if(mapUsernameClientHandler.containsKey(username)){
            clientHandler.sendPacketToClient(new PacketMessage(ConnectionMessages.TAKEN_NICKNAME));
        }
        else {
            addUsernameIntoMap(username, clientHandler);
            checkFirstPositionInLobby(clientHandler);
        }
    }

    /**
     * Method addUsernameIntoMap adds the username and the clientHandler into the map that contains all the usernames
     * and the clientHandlers. This method is called by the method checkUsernameAlreadyTaken.
     * @param username (type String) - it is the username to insert
     * @param clientHandler (type ClientHandler) - it is the clientHandler to insert
     */
    public synchronized void addUsernameIntoMap(String username, ClientHandler clientHandler){
        mapUsernameClientHandler.put(username, clientHandler);
        clientHandler.setUsername(username);
        System.out.println(username + " (idPlayer: " +clientHandler.getIdClient()+") joined!");
    }

    /**
     * Method checkStartOfTheGame checks if there are enough players in the lobby to start the game.
     */
    public synchronized void checkStartOfTheGame(){
        if(numPlayers <= lobby.size()){
            createMatch();
        }
    }


    /**
     * Method createMatch is called by the method checkStartOfTheGame and creates a new match. It removes the people in
     * the lobby and creates a new Game's player for each person. If there are other people in the lobby, it asks to the
     * person to insert the number of players to create a new match.
     */
    public synchronized void createMatch(){
        Game game;
        ArrayList<ClientHandler> playersInGame = new ArrayList<>();

        if(numPlayers==1){
             game = new SinglePlayerGame();
        }
        else game = new Game();

        game.setState(GameStates.SETUP);
        games.add(game);
        game.setIdGame(createGameID());
        game.setNumof_players(numPlayers);
        
        System.out.println("Client " + (lobby.peek() != null ? lobby.peek().getUsername() : null) + " created the game (idGame: " + game.getIdGame()+ ") " +
                "with " +numPlayers+" players");

        for (int i=0; i<numPlayers; i++){
            if (lobby.peek()!= null) {
                game.createNewPlayer(lobby.peek().getUsername(), lobby.peek().getIdClient());
                playersInGame.add(lobby.peek());
            }
            lobby.remove();

        }

        //to print players in the game
        System.out.print("Players in the game " + game.getIdGame() + " :");
        for (ClientHandler clientHandler: playersInGame){
            System.out.print("[" + clientHandler.getUsername() + ", id: " + clientHandler.getIdClient() + "] ");
        }
        System.out.println("\n");
        //end of print of players in game

        game.setup();

        for (ClientHandler clientHandler: playersInGame){
            clientHandler.setGame(game);
            clientHandler.sendPacketToClient(new PacketMessage(ConnectionMessages.GAME_IS_STARTING));
            clientHandler.setGameStarted();
            clientHandler.setPosInGame(game.getPositionPlayer(clientHandler.getUsername()));
            clientHandler.sendSetupPacket();
        }

        for (ClientHandler clientHandler: playersInGame){
            clientHandler.setSetupEnded();
        }

        System.out.print("Ordine dei Players in the game " + game.getIdGame() + " :");
        for (Player player: game.getActivePlayers()){
            System.out.print("[" + player.getUsername() + ", id: " + player.getIdClient() + "] ");
        }
        System.out.println("\n");

        mapIdGameClientHandler.put(game.getIdGame(), playersInGame);

        numPlayers=0;
        playersInGame.clear();


        if (lobby.size()!=0){
            lobby.peek().sendPacketToClient(new PacketMessage(ConnectionMessages.INSERT_NUMBER_OF_PLAYERS));
        }
    }

    /**
     * Method sendAll sends to all the players of a game an update about changes of the game
     * @param packet (type ServerPacketHandler)
     * @param gameInterface (type GameInterface)
     */
    public synchronized void sendAll(ServerPacketHandler packet, GameInterface gameInterface) {

        for (ClientHandler clientHandler: mapIdGameClientHandler.get(gameInterface.getIdGame())){
            clientHandler.sendPacketToClient(packet);
        }
    }

    public synchronized void sendWaitMessageToLobby(){
        for(ClientHandler clientHandler: lobby){
            clientHandler.sendPacketToClient(new PacketMessage(ConnectionMessages.WAITING_PEOPLE));
        }
    }

    public Map<String, ClientHandler> getMapUsernameClientHandler() {
        return mapUsernameClientHandler;
    }


    public synchronized void handleDisconnection(){

    }

    // TODO: 17/05/2021 da testare 
    /**
     * 
     * @param clientHandlerToRemove
     */
    public synchronized void deleteFromLobby(ClientHandler clientHandlerToRemove){
        lobby.remove(clientHandlerToRemove);
    }


}
