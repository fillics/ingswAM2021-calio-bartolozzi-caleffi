package it.polimi.ingsw.constants;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {
    private static int port;
    private static final int NUM_MAXPLAYERS = 4;
    private static final int NUM_MINPLAYERS = 1;

    public static void setPort(int port) {
        Constants.port = port;
    }

    public static String getInfo() {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " INFO: ");
    }

    public static int getPort() {
        return port;
    }

    public static int getNumMaxplayers() {
        return NUM_MAXPLAYERS;
    }

    public static int getNumMinplayers() {
        return NUM_MINPLAYERS;
    }
}
