package it.polimi.ingsw.server;

public class VirtualClient {
    private String username;
    private SocketConnection socketConnection;
    private int idClient;

    public VirtualClient(String username, SocketConnection socketConnection, int idClient) {
        this.username = username;
        this.socketConnection = socketConnection;
        this.idClient = idClient;
    }
}
