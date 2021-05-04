package it.polimi.ingsw.server;

import java.net.Socket;

public class SocketConnection {

    private String guest = "guest";
    private String name;
    private int numberOfGuest;
    private Socket socket;
    private int idClient;


    public int getIdClient() {
        return idClient;
    }

    public SocketConnection(int numberOfGuest, Socket socket, int id) {

        name = guest + numberOfGuest;
        this.socket = socket;
        this.idClient = id;
    }


    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }



    @Override
    public String toString() {
        return "SocketConnection{" +
                "name='" + name + '\'' +
                ", socket=" + socket +
                ", idClient=" + idClient +
                '}';
    }
}
