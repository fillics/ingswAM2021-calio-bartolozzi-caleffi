package it.polimi.ingsw.controller;


/**
 * It contains all the messages that the server can send to clients
 */
public enum ConnectionMessages {

    WAITING_PEOPLE("Waiting for people to start the game..."),
    LOBBY_MASTER("You are the lobby master! You will create the match."),
    INSERT_USERNAME("Insert your username: "),
    LOCAL_OR_SERVERGAME("Choose how you want to play:\n" + "" +
            "1. Solo game without making a connection with the server\n"+
            "2. Game with connection to the server"),
    INVALID_CHOICE("The choice is invalid. Enter 1 or 2: "),
    INVALID_USERNAME("The chosen username is invalid. Enter a new one: "),
    TAKEN_NICKNAME("The chosen username is already taken. Enter a new one: "),
    INSERT_NUMBER_OF_PLAYERS("Insert the desired number of players (1, 2, 3 or 4): "),
    INVALID_NUM_PLAYERS("The chosen number of players is invalid. Enter a new one: "),
    GAME_IS_STARTING("The game is starting..."),
    SEND_SETUP_PACKETS("Send setup packets"),
    YOU_TURN("It's your turn bro"),
    SELECT_LEADERCARDS("Choose the leader cards to remove"),

    //CHOOSE_ONE_INITIAL_MARBLE ("Choose initial marble"),
    //CHOOSE_TWO_INITIAL_MARBLES (""),
    CONNECTION_CLOSED("Connection closed"),
    MATCH_FINISHED_FAITH("A player reached the last cell of the faith track"),
    MATCH_FINISHED_DEVCARDS("A player bought the seventh development card"),
    TIMER_ENDED("You took too long to make your decision"),
    PING("Ping");

    private final String message;

    ConnectionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}