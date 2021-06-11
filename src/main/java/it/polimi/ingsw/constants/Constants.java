package it.polimi.ingsw.constants;

import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.messages.ExceptionMessages;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {
    private static int port;
    private static String addressServer;

    private static final int NUM_MAXPLAYERS = 4;
    private static final int NUM_MINPLAYERS = 1;
    private static int width;
    private static int height;
    public static final String ITALIC= "\u001B[3m";
    public static final String UNDERLINE = "\u001B[4m";
    public static final String ANSI_BACKGROUND_GREEN = "\u001B[42m";
    public static final String ANSI_RESET = "\033[0m";
    public static final String ANSI_RED = "\033[31m";
    public static final String ANSI_GREEN = "\033[32m";
    public static final String ANSI_YELLOW = "\033[33m";
    public static final String ANSI_BLUE = "\033[34m";
    public static final String ANSI_PURPLE = "\033[35m";
    public static final String ANSI_CYAN = "\033[36m";
    public static final String ANSI_WHITE = "\033[37m";
    public static final String ANSI_BACKGROUND_BLACK = "\033[40m";
    public static final String ANSI_BACKGROUND_PURPLE = "\033[45m";

    public static final String menu = "Choose one of the operations you can do:\n"+
            "1: Activate a Leader Card ["+printItalic("activate")+"]\n" +
            "2: Buy a Development Card ["+printItalic("buy")+"]\n" +
            "3: Choose Discount ["+printItalic("choose")+"]\n" +
            "4: Use production powers ["+printItalic("prodpowers")+"]\n" +
            "5: Discard a Leader Card ["+printItalic("discard")+"]\n" +
            "6: Move one of you resources ["+printItalic("move")+"]\n" +
            "7: Place one of your resources ["+printItalic("place")+"]\n" +
            "8: Take resources from the market ["+printItalic("take")+"]\n" +
            "9: Show the market tray ["+printItalic("showmarket")+"]\n"+
            "10: Show the development grid ["+printItalic("showgrid")+"]\n"+
            "11: Show my personal board ["+printItalic("showboard")+"]\n"+
            "12: Show the board of another player ["+printItalic("showanotherboard")+"]\n" +
            "13: +20 resources ["+printItalic("resourceCheat")+"]\n" +
            "14: +1 faith marker ["+printItalic("faithCheat")+"]\n" +
            "15: End Turn ["+printItalic("end")+"]\n";


    public static final String MASTEROFRENAISSANCE =
            "███╗   ███╗ █████╗ ███████╗████████╗███████╗██████╗      ██████╗ ███████╗    ██████╗ ███████╗███╗   ██╗ █████╗ ██╗███████╗███████╗ █████╗ ███╗   ██╗ ██████╗███████╗\n"
                    + "████╗ ████║██╔══██╗██╔════╝╚══██╔══╝██╔════╝██╔══██╗    ██╔═══██╗██╔════╝    ██╔══██╗██╔════╝████╗  ██║██╔══██╗██║██╔════╝██╔════╝██╔══██╗████╗  ██║██╔════╝██╔════╝\n"
                    + "██╔████╔██║███████║███████╗   ██║   █████╗  ██████╔╝    ██║   ██║█████╗      ██████╔╝█████╗  ██╔██╗ ██║███████║██║███████╗███████╗███████║██╔██╗ ██║██║     █████╗\n"
                    + "██║╚██╔╝██║██╔══██║╚════██║   ██║   ██╔══╝  ██╔══██╗    ██║   ██║██╔══╝      ██╔══██╗██╔══╝  ██║╚██╗██║██╔══██║██║╚════██║╚════██║██╔══██║██║╚██╗██║██║     ██╔══╝\n"
                    + "██║ ╚═╝ ██║██║  ██║███████║   ██║   ███████╗██║  ██║    ╚██████╔╝██║         ██║  ██║███████╗██║ ╚████║██║  ██║██║███████║███████║██║  ██║██║ ╚████║╚██████╗███████╗\n"
                    +"╚═╝     ╚═╝╚═╝  ╚═╝╚══════╝   ╚═╝   ╚══════╝╚═╝  ╚═╝     ╚═════╝ ╚═╝         ╚═╝  ╚═╝╚══════╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝╚══════╝╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝╚══════╝";

    public static final String AUTHORS =
            "by "
                    + ANSI_RED
                    + "Filippo Caliò"
                    + ANSI_RESET
                    + ", "
                    + ANSI_CYAN
                    + "Beatrice Bartolozzi"
                    + ANSI_RESET
                    + ", "
                    + ANSI_GREEN
                    + "Giovanni Caleffi"
                    + ANSI_RESET;

    public static final String commands = "Type "+Constants.ITALIC+" commands "+Constants.ANSI_RESET+" to check what operations you can do!";

    public static void setPort(int port) {
        Constants.port = port;
    }

    public static String getAddressServer() {
        return addressServer;
    }

    public static void setAddressServer(String addressServer) {
        Constants.addressServer = addressServer;
    }

    public static String printItalic(String message){
        return ITALIC+message+ANSI_RESET;
    }
    public static String getInfo() {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " INFO: ");
    }
    public static String getErr() {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " ERR: ");
    }

    public static int getPort() {
        return port;
    }

    public static int getNumMaxPlayers() {
        return NUM_MAXPLAYERS;
    }

    public static int getNumMinPlayers() {
        return NUM_MINPLAYERS;
    }

    /**
     * Method printConnectionMessage prints the Connection Message passed as a parameter
     */
    public static void printConnectionMessage(ConnectionMessages message){
        System.out.println(message.getMessage());
    }

    public static void printExceptionMessage(ExceptionMessages message){
        System.out.println(ANSI_RED+ message.getMessage() + ANSI_RESET);
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setWidth(int width) {
        Constants.width = width;
    }

    public static void setHeight(int height) {
        Constants.height = height;
    }
}
