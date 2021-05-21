package it.polimi.ingsw.controller.messages;


import it.polimi.ingsw.client.Color;
import it.polimi.ingsw.constants.Constants;

/**
 * It contains all the messages that the server can send to clients
 */
public enum ConnectionMessages {

    WAITING_PEOPLE("Waiting for people to start the game..."),
    LOBBY_MASTER("You are the "+Constants.ITALIC+"lobby master"+Constants.ANSI_RESET+"! You will create the match."),
    USERNAME_VALID(Constants.ANSI_GREEN+"Username chosen is valid!"+Constants.ANSI_RESET),
    INSERT_USERNAME("Insert your username: "),
    LOCAL_OR_SERVERGAME("Choose how you want to play:\n" + "" +
            "1. Solo game without making a connection with the server\n"+
            "2. Game with connection to the server"),
    INVALID_CHOICE(Constants.ANSI_RED+"The choice is invalid. Enter a new one: "+Constants.ANSI_RESET),
    TAKEN_NICKNAME(Constants.ANSI_RED+"The chosen username is already taken. Enter a new one: "+Constants.ANSI_RESET),
    INSERT_NUMBER_OF_PLAYERS("Insert the desired number of players (1, 2, 3 or 4): "),
    INVALID_NUM_PLAYERS(Constants.ANSI_RED+"The chosen number of players is invalid. Enter a new one: "+Constants.ANSI_RESET),

    PLAYER_RECONNECTED("You were playing in a match. Reconnecting to the game..."),
    GAME_IS_STARTING(Constants.UNDERLINE+"The game is starting..."+Constants.ANSI_RESET),
    YOUR_TURN("It's your turn bro \n" + "Choose one of the operations you can do:\n"+
            "1: Activate a Leader Card\n" +
            "2: Buy a Development Card\n" +
            "3: Choose Discount\n" +
            "4: Use production powers\n" +
            "5: Discard a Leader Card\n" +
            "6: Move one of you resources\n" +
            "7: Place one of your resources\n" +
            "8: Take resources from the market\n" +
            "9: See the market tray\n"+
            "10: See the development grid\n"+
            "11: See the faith track\n"+
            "12: End Turn\n"),
    NOT_YOUR_TURN("It's NOT your turn bro"),
    SELECT_LEADERCARDS("Choose the leader cards to remove"),
    CHOOSE_FIRST_RESOURCE("Choose the first initial resource:"),
    CHOOSE_SECOND_RESOURCE("Choose the second initial resource:"),
    RESOURCE_CHOICES("Write: \n"+
            "1 to select "+Color.ANSI_YELLOW.escape() +"COIN" +Color.RESET+ "\n"+
            "2 to select "+ Color.ANSI_GREY.escape()+ "STONE" + Color.RESET + "\n"+
            "3 to select " + Color.ANSI_PURPLE.escape() +"SERVANT" + Color.RESET + "\n"+
            "4 to select "+ Color.ANSI_BLUE.escape()+"SHIELD" + Color.RESET),
    CHOOSE_DEPOSIT("Choose the deposit in which you want to place the resource [1, 2, 3]"),
    DISCARDDEVCARD(Constants.ANSI_YELLOW+"Lorenzo Il Magnifico discarded a development card"+Constants.ANSI_RESET),
    BLACKCROSS1(Constants.ANSI_YELLOW+"Lorenzo Il Magnifico's black cross has stepped forward of one cell"+Constants.ANSI_RESET),
    BLACKCROSS2(Constants.ANSI_YELLOW+"Lorenzo Il Magnifico's black cross has stepped forward of two cell"+Constants.ANSI_RESET),
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