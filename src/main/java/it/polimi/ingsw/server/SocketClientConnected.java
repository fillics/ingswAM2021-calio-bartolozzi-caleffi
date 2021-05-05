package it.polimi.ingsw.server;

import java.net.Socket;

/**
 * class containing the guest name of the client and his socket. it is used for the starting lobby
 */
public class SocketClientConnected {

    private String guest = "guest";
    private String name;
    private Socket socket;

    private boolean active;

    public SocketClientConnected(int numberOfGuest, Socket socket) {
        name = guest + numberOfGuest;
        this.socket = socket;

    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * to know if this socket connection is active
     */
    public boolean isActive() {
        return active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Socket getSocket() {
        return socket;
    }



}
