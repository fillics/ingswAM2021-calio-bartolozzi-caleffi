package it.polimi.ingsw.controller.messages;


import it.polimi.ingsw.constants.Color;
import it.polimi.ingsw.constants.Constants;

/**
 * It contains all the messages that the server can send to clients
 */
public enum ConnectionMessages {

    WAITING_PEOPLE("Waiting for people to start the game..."),
    LOBBY_MASTER("You are the "+Constants.ITALIC+"lobby master"+Constants.ANSI_RESET+"! You will create the match."),
    LOBBY_MASTER_GUI("You are the lobby master! You will create the match."),
    WAIT_FOR_TURN("Waiting for your turn..."),

    USERNAME_VALID(Constants.ANSI_GREEN+"Username chosen is valid!"+Constants.ANSI_RESET),
    INSERT_USERNAME("Insert your username: "),
    INVALID_CHOICE(Constants.ANSI_RED+"The choice is invalid. Enter a new one: "+Constants.ANSI_RESET),
    TAKEN_NICKNAME(Constants.ANSI_RED+"The chosen username is already taken. Enter a new one: "+Constants.ANSI_RESET),
    INSERT_NUMBER_OF_PLAYERS("Insert the desired number of players (1, 2, 3 or 4): "),
    INVALID_NUM_PLAYERS(Constants.ANSI_RED+"The chosen number of players is invalid. Enter a new one: "+Constants.ANSI_RESET),

    PLAYER_RECONNECTED("You were playing in a match. Reconnecting to the game..."),
    GAME_IS_STARTING(Constants.UNDERLINE+"The game is starting..."+Constants.ANSI_RESET),
    GAME_IS_STARTING_GUI("The game is starting..."),

    YOUR_TURN("It's your turn bro! \n" + Constants.commands),
    YOUR_TURN_GUI("It's your turn bro!"),

    NOT_YOUR_TURN("It's NOT your turn bro"),
    SELECT_LEADERCARDS("Choose the leader cards to remove"),
    CHOOSE_FIRST_RESOURCE(Constants.ANSI_YELLOW+"Choose the first initial resource:"+Constants.ANSI_RESET),
    CHOOSE_SECOND_RESOURCE(Constants.ANSI_YELLOW+"Choose the second initial resource:"+Constants.ANSI_RESET),

    CHOOSE_ONE_RESOURCE_GUI("You can choose one resource"),
    CHOOSE_TWO_RESOURCES_GUI("You can choose two resources"),

    RESOURCE_CHOICES("Write: \n"+
            "1 to select "+Color.ANSI_YELLOW.escape() +"COIN" +Color.RESET+ "\n"+
            "2 to select "+ Color.ANSI_GREY.escape()+ "STONE" + Color.RESET + "\n"+
            "3 to select " + Color.ANSI_PURPLE.escape() +"SERVANT" + Color.RESET + "\n"+
            "4 to select "+ Color.ANSI_BLUE.escape()+"SHIELD" + Color.RESET),
    CHOOSE_DEPOSIT("Choose the deposit in which you want to place the resource [1, 2, 3]"),
    DISCARDDEVCARD(Constants.ANSI_YELLOW+"Lorenzo Il Magnifico discarded a development card"+Constants.ANSI_RESET),
    BLACKCROSS1(Constants.ANSI_YELLOW+"Lorenzo Il Magnifico's black cross has stepped forward of one cell"+Constants.ANSI_RESET),
    BLACKCROSS2(Constants.ANSI_YELLOW+"Lorenzo Il Magnifico's black cross has stepped forward of two cell"+Constants.ANSI_RESET),
    DISCARDDEVCARD_GUI("Lorenzo Il Magnifico discarded a development card"),
    BLACKCROSS1_GUI("Lorenzo Il Magnifico's black cross has stepped forward of one cell"),
    BLACKCROSS2_GUI("Lorenzo Il Magnifico's black cross has stepped forward of two cell"),
    BLACKCROSSUPDATE("Black cross updated!"),
    IMPOSSIBLEMOVE("I'm sorry, you can't do this action in this moment of the game"),
    IMPOSSIBLEENDTURN("I'm sorry, you have to do an action before ending the turn!"),
    CONNECTION_CLOSED("The server closed its connection."),
    MATCH_FINISHED_FAITH("A player reached the last cell of the faith track"),
    MATCH_FINISHED_DEVCARDS("A player bought the seventh development card"),
    PING("Ping"),
    UPDATE_AFTER_ENDTURN(""),
    PONG("Pong");

    private final String message;

    ConnectionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}