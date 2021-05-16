package it.polimi.ingsw.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.server_packets.ServerPacketHandler;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ConnectionSocket class handles the connection between the client and the server.
 *
 */
public class SocketClientConnection {

    private final Client client;
    private final String serverAddress;
    private final int port;
    private Socket socket;
    private DataOutputStream output;
    private DataInputStream dataInputStream;
    private BufferedReader br;
    private final AtomicBoolean connectionToServer = new AtomicBoolean(false);

    public SocketClientConnection(Client client) {
        this.client = client;
        this.serverAddress = Constants.getAddressServer();
        this.port = Constants.getPort();
        try {
            socket = new Socket(serverAddress, port);
            connectionToServer.compareAndSet(false, true);
        } catch (IOException ignored) {
            System.err.println("Error during connection to the client");
            Client.main(null);
        }
        if (connectionToServer.get()) creationStreams();
    }

    public synchronized void creationStreams() {
        try {
            output = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream()); // to read data coming from the server
            br = new BufferedReader(new InputStreamReader(socket.getInputStream())); // to read data coming from the server
        } catch (IOException e) {
            System.err.println("Error during initialization of the client!");
        }
    }

    public AtomicBoolean getConnectionToServer() {
        return connectionToServer;
    }

    /**
     * Method sendToServer sends a new message to the server.
     * @param jsonResult (type String) - it is the message to send to the server
     */
    public synchronized void sendToServer(String jsonResult){
        try {
            output.writeUTF(jsonResult);
            System.out.println(jsonResult);
            output.flush();
        } catch (IOException e) {
            System.err.println("Error during the communication from client to server!");
            Client.main(null);
        }
    }



    public synchronized void deserialize() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String str = dataInputStream.readUTF();
        System.out.println(str);
        ServerPacketHandler packet = null;

        try {
            packet = mapper.readValue(str, ServerPacketHandler.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (packet != null) {
            packet.execute(client);
        }
    }

    public void closeConnection(){
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Socket getSocket() {
        return socket;
    }
}
