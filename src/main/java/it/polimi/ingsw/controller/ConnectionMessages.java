package it.polimi.ingsw.controller;


/**
 * It contains all the messages that the server can send to clients
 */
public enum ConnectionMessages {

    INVALID_PACKET("Invalid packet"),
    INSERT_USERNAME("Insert your username"),
    INVALID_USERNAME("The chosen username is invalid. Enter a new one"),
    TAKEN_NICKNAME("The chosen username is already taken. Enter a new one"),
    INSERT_NUMBER_OF_PLAYERS("Insert the desired number of players (1, 2, 3 or 4)"),
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