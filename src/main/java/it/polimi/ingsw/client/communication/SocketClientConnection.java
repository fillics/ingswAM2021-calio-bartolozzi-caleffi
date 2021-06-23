package it.polimi.ingsw.client.communication;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.constants.Constants;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ConnectionSocket class handles the connection between the client and the server.
 *
 */
public class SocketClientConnection {

    private final Client client;
    private Socket socket;
    private DataOutputStream output;
    private DataInputStream dataInputStream;
    private final AtomicBoolean connectionToServer = new AtomicBoolean(false);

    /**
     * Class' constructor
     * @param client is the client linked to the socket
     */
    public SocketClientConnection(Client client) {
        this.client = client;
        try {
            socket = new Socket(Constants.getAddressServer(), Constants.getPort());
            socket.setSoTimeout(20000);
            connectionToServer.compareAndSet(false, true);
        } catch (IOException ignored) {
            System.err.println("Error during connection to the client");
            Client.main(null);

        }
        if (connectionToServer.get()) creationStreams();
    }

    /**
     * Method that creates the data input and output streams
     */
    public synchronized void creationStreams() {
        try {
            output = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream()); // to read data coming from the server
        } catch (IOException e) {
            System.err.println("Error during initialization of the client!");
        }
    }

    /**
     * Method sendToServer sends a new message to the server.
     * @param jsonResult (type String) - it is the message to send to the server
     */
    public synchronized void sendToServer(String jsonResult){
        try {
            output.writeUTF(jsonResult);
            output.flush();
        } catch (IOException e) {
            System.err.println("Error during the communication from client to server!");
            Client.main(null);
        }
    }

    /**
     * Class' getter
     * @return true if the client is connected to the server, else false
     */
    public AtomicBoolean getConnectionToServer() {
        return connectionToServer;
    }

    /**
     * Class' getter
     * @return the socket of the client
     */
    public Socket getSocket() {
        return socket;
    }
}
