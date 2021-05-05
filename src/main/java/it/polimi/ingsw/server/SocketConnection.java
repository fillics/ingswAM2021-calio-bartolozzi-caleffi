package it.polimi.ingsw.server;

import java.net.Socket;

public class SocketConnection {

    private String guest = "guest";
    private String name;
    private int numberOfGuest;
    private Socket socket;
    private int idClient;
    private int idGame;


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

    public void setIdGame(int idGame) {
        this.idGame = idGame;
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
