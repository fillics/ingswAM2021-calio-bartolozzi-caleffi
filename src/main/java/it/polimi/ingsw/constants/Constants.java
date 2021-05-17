package it.polimi.ingsw.constants;

import it.polimi.ingsw.controller.ConnectionMessages;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {
    private static int port;
    private static String addressServer;

    private static final int NUM_MAXPLAYERS = 4;
    private static final int NUM_MINPLAYERS = 1;
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

    public static final String MASTEROFRENAISSANCE =
    "███╗   ███╗ █████╗ ███████╗████████╗███████╗██████╗      ██████╗ ███████╗    ██████╗ ███████╗███╗   ██╗ █████╗ ██╗███████╗███████╗ █████╗ ███╗   ██╗ ██████╗███████╗\n"
            + "████╗ ████║██╔══██╗██╔════╝╚══██╔══╝██╔════╝██╔══██╗    ██╔═══██╗██╔════╝    ██╔══██╗██╔════╝████╗  ██║██╔══██╗██║██╔════╝██╔════╝██╔══██╗████╗  ██║██╔════╝██╔════╝\n"
            + "██╔████╔██║███████║███████╗   ██║   █████╗  ██████╔╝    ██║   ██║█████╗      ██████╔╝█████╗  ██╔██╗ ██║███████║██║███████╗███████╗███████║██╔██╗ ██║██║     █████╗\n"
           + "██║╚██╔╝██║██╔══██║╚════██║   ██║   ██╔══╝  ██╔══██╗    ██║   ██║██╔══╝      ██╔══██╗██╔══╝  ██║╚██╗██║██╔══██║██║╚════██║╚════██║██╔══██║██║╚██╗██║██║     ██╔══╝\n"
           + "██║ ╚═╝ ██║██║  ██║███████║   ██║   ███████╗██║  ██║    ╚██████╔╝██║         ██║  ██║███████╗██║ ╚████║██║  ██║██║███████║███████║██║  ██║██║ ╚████║╚██████╗███████╗\n"
            +"╚═╝     ╚═╝╚═╝  ╚═╝╚══════╝   ╚═╝   ╚══════╝╚═╝  ╚═╝     ╚═════╝ ╚═╝         ╚═╝  ╚═╝╚══════╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝╚══════╝╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝╚══════╝";

    public static final String AUTHORS =
            "\nby "
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

    public static void setPort(int port) {
        Constants.port = port;
    }

    public static String getAddressServer() {
        return addressServer;
    }

    public static void setAddressServer(String addressServer) {
        Constants.addressServer = addressServer;
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

}
