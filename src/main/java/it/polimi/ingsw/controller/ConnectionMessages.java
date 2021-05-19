package it.polimi.ingsw.controller;


import it.polimi.ingsw.constants.Constants;

/**
 * It contains all the messages that the server can send to clients
 */
public enum ConnectionMessages {

    WAITING_PEOPLE("Waiting for people to start the game..."),
    LOBBY_MASTER("You are the lobby master! You will create the match."),
    USERNAME_VALID(Constants.ANSI_GREEN+"Username chosen is valid!"+Constants.ANSI_RESET),
    INSERT_USERNAME("Insert your username: "),
    LOCAL_OR_SERVERGAME("Choose how you want to play:\n" + "" +
            "1. Solo game without making a connection with the server\n"+
            "2. Game with connection to the server"),
    INVALID_CHOICE("The choice is invalid. Enter a new one: "),
    TAKEN_NICKNAME(Constants.ANSI_RED+"The chosen username is already taken. Enter a new one: "+Constants.ANSI_RESET),
    INSERT_NUMBER_OF_PLAYERS("Insert the desired number of players (1, 2, 3 or 4): "),
    INVALID_NUM_PLAYERS("The chosen number of players is invalid. Enter a new one: "),
    GAME_IS_STARTING("The game is starting..."),
    SEND_SETUP_PACKETS("Send setup packets"),
    YOUR_TURN("It's your turn bro"),
    NOT_YOUR_TURN("It's NOT your turn bro"),
    SELECT_LEADERCARDS("Choose the leader cards to remove"),
    CHOOSE_FIRST_RESOURCE("Choose the first initial resource:"),
    CHOOSE_SECOND_RESOURCE("Choose the second initial resource:"),
    RESOURCE_CHOICES("Write 1 to select COIN, 2 to select STONE, 3 to select SERVANT, 4 to select SHIELD"),
    CHOOSE_DEPOSIT("Choose the deposit in which you want to place the resource [1, 2, 3]"),
    DISCARDDEVCARD("Lorenzo Il Magnifico discarded a development card"),
    BLACKCROSS1("Lorenzo Il Magnifico's black cross has stepped forward of one cell"),
    BLACKCROSS2("Lorenzo Il Magnifico's black cross has stepped forward of two cell"),
    IMPOSSIBLEMOVE("I'm sorry, you can't do this action in this moment of the game"),

    CONNECTION_CLOSED("The served closed its connection."),
    MATCH_FINISHED_FAITH("A player reached the last cell of the faith track"),
    MATCH_FINISHED_DEVCARDS("A player bought the seventh development card"),
    PING("Ping"),
    PONG("Pong");

    private final String message;

    ConnectionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}