package it.polimi.ingsw.server;

import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.PacketConnectionMessages;
import it.polimi.ingsw.controller.server_packets.PacketReconnection;
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


    /** Map that contains all the disconnected player's username and the Game's id associated **/
    private final Map<String, Integer> peopleDisconnected;


    /** List of clients waiting in the lobby. */
    private final LinkedList<ClientHandler> lobby = new LinkedList<>();


    public Map<Integer, ArrayList<ClientHandler>> getMapIdGameClientHandler() {
        return mapIdGameClientHandler;
    }


    public Server() {
        mapUsernameClientHandler = new HashMap<>();
        mapIdGameClientHandler = new HashMap<>();
        peopleDisconnected = new HashMap<>();
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
        int numOfGuest=1;
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(Constants.getPort());

        } catch (IOException e) {
            System.err.println(Constants.getErr() + "Error during Socket initialization, quitting...");
            return;
        }

        System.out.println("Server ready! Listening on port " + Constants.getPort());
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                socket.setSoTimeout(20000);
                System.out.println("Guest"+numOfGuest+" connected");
                numOfGuest+=1;

                executor.submit(new ClientHandler(createClientID(), socket, this, numOfGuest));

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


    public Map<String, ClientHandler> getMapUsernameClientHandler() {
        return mapUsernameClientHandler;
    }


    /**
     * Method addToLobby adds the client handler to the lobby, waiting for the start of the game
     * @param clientToAdd (type ClientHandler) - it is the client to add to the lobby
     */
    public synchronized void addToLobby(ClientHandler clientToAdd){
        System.out.println("Added "+clientToAdd.getUsername()+ " to the lobby.");
        lobby.add(clientToAdd);
    }

    /**
     * Method checkFirstPositionInLobby checks if the ClientHandler, passed as a parameter, is the first person of
     * the lobby. If so, it asks to him to insert the number of players of the game.
     * @param clientHandler (type ClientHandler) - it is the person to check
     */
    public synchronized void checkFirstPositionInLobby(ClientHandler clientHandler){
        if (lobby.size() != 0 && lobby.peek() != null && lobby.peek().equals(clientHandler)) {
            clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.INSERT_NUMBER_OF_PLAYERS));
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
            clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.TAKEN_NICKNAME));

        }
        else {

            if (peopleDisconnected.containsKey(username)){
                System.out.println("Client " +username+" reconnected");
                clientHandler.sendPacketToClient(new PacketReconnection());
                peopleDisconnected.remove(username);
            }
            else{
                clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.USERNAME_VALID));
                registerUsername(username, clientHandler);
                addToLobby(clientHandler);
                checkFirstPositionInLobby(clientHandler);
            }


        }
    }

    /**
     * Method addUsernameIntoMap adds the username and the clientHandler into the map that contains all the usernames
     * and the clientHandlers. This method is called by the method checkUsernameAlreadyTaken.
     * @param username (type String) - it is the username to insert
     * @param clientHandler (type ClientHandler) - it is the clientHandler to insert
     */
    public synchronized void registerUsername(String username, ClientHandler clientHandler){
        mapUsernameClientHandler.put(username, clientHandler);
        clientHandler.setUsername(username);
        System.out.println(Constants.ANSI_GREEN+username+Constants.ANSI_RESET+ " (idPlayer: " +clientHandler.getIdClient()+") "+ Constants.ANSI_GREEN+"joined!"+Constants.ANSI_RESET);
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
            game.setState(GameStates.PHASE_ONE);
        }
        else{
            game = new Game();
            game.setState(GameStates.SETUP);
        }

        games.add(game);
        game.setIdGame(createGameID());

        System.out.print(Constants.ANSI_BLUE+"Client " + (lobby.peek() != null ? lobby.peek().getUsername() : null) + " created the game (idGame: " + game.getIdGame()+ ") " +
                "with " +numPlayers+" players: "+Constants.ANSI_RESET);

        for (int i=0; i<numPlayers; i++){
            if (lobby.peek()!= null) {
                game.createNewPlayer(lobby.peek().getUsername(), lobby.peek().getIdClient());
                playersInGame.add(lobby.peek());
            }
            lobby.remove();

        }

        setupPlayersGame(game, playersInGame);

        printPlayersOrder(game);

        checkFirstPositionInLobby(lobby.peek());

    }

    /**
     * Method setupPlayersGame calls the method setup of the Game and send to each player of the game a setup Packet,
     * containing all of the informations they need to play the game
     * @param game (type Game)
     * @param playersInGame (type ArrayList<ClientHandler>)
     */
    public synchronized void setupPlayersGame(Game game, ArrayList<ClientHandler> playersInGame){
        //to print players in the game
        for (ClientHandler clientHandler: playersInGame){
            System.out.print("[" + clientHandler.getUsername() + ", id: " + clientHandler.getIdClient() + "] ");
        }
        System.out.print("\n");
        //end of print of players in game

        game.setup();

        for (ClientHandler clientHandler: playersInGame){
            clientHandler.setGame(game);
            clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.GAME_IS_STARTING));
            clientHandler.setPosInGame(game.getPositionPlayer(clientHandler.getUsername()));
            clientHandler.sendSetupPacket();
        }

        mapIdGameClientHandler.put(game.getIdGame(), playersInGame);
        numPlayers=0;
    }

    public void printPlayersOrder(Game game){
        System.out.print("Players' order in the game " + game.getIdGame() + ": ");
        for (Player player: game.getActivePlayers()){
            System.out.print("[" + player.getUsername() + ", id: " + player.getIdClient() + "] ");
        }
        System.out.print("\n");
    }



    /**
     * Method handleDisconnection handles the operations to do when a player disconnected himself.
     * @param clientHandlerToRemove (type ClientHandler) - it is the client handler to remove
     */
    public synchronized void handleDisconnection(ClientHandler clientHandlerToRemove){
        //caso in cui è già dentro una partita
        if(clientHandlerToRemove.getGame()!=null){
            unregisterUsername(clientHandlerToRemove);

        }
        //caso in cui non è ancora entrato in partita
        else{
            deleteFromLobby(clientHandlerToRemove);
            unregisterUsername(clientHandlerToRemove);
            checkFirstPositionInLobby(lobby.peek());

        }
    }

    /**
     * Method unregisterUsername unregisters the username of the client handler to remove from the map containing
     * all the username and we put that username in a map
     * @param clientHandlerToRemove (type ClientHandler) - it is the client handler to unregister because it disconnected
     */
    public void unregisterUsername(ClientHandler clientHandlerToRemove){
        System.out.println("Unregistered the username " + clientHandlerToRemove.getUsername()+ "!");
        mapUsernameClientHandler.remove(clientHandlerToRemove.getUsername());
        peopleDisconnected.put(clientHandlerToRemove.getUsername(), clientHandlerToRemove.getGame().getIdGame());
    }

    /**
     * Method deleteFromLobby deletes the client handler from the lobby of the game
     * @param clientHandlerToRemove (type ClientHandler) - it is the client handler to remove because it disconnected
     */
    public synchronized void deleteFromLobby(ClientHandler clientHandlerToRemove){
        System.out.println("Deleted " +clientHandlerToRemove.getUsername()+ " from the lobby!");
        lobby.remove(clientHandlerToRemove);
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

}
