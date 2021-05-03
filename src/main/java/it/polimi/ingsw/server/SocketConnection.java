package it.polimi.ingsw.server;

import java.net.Socket;

public class SocketConnection {

    private String guest = "guest";
    private String name;
    private int numberOfGuest;
    private Socket socket;

    public SocketConnection(int numberOfGuest, Socket socket) {

        name = guest + String.valueOf(numberOfGuest);
        this.socket = socket;
    }

    @Override
    public String toString() {
        return "SocketConnection{" +
                "name='" + name + '\'' +
                ", socket=" + socket +
                '}';
    }

    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }

    public int getNumberOfGuest() {
        return numberOfGuest;
    }
}
