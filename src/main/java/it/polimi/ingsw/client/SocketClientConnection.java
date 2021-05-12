package it.polimi.ingsw.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.server_packets.ServerPacketHandler;

import java.io.*;
import java.net.Socket;

/**
 * ConnectionSocket class handles the connection between the client and the server.
 *
 */
public class SocketClientConnection {

    private CLI cli;
    private String serverAddress;
    private int port;
    private Socket socket;
    private DataOutputStream output;
    private DataInputStream dataInputStream;
    private BufferedReader br;

    public SocketClientConnection(CLI cli) {
        this.cli= cli;
        this.serverAddress = Constants.getAddressServer();
        this.port = Constants.getPort();
        try {
            socket = new Socket(serverAddress, port);
        } catch (IOException ignored) {
            System.err.println("Error during connection to the client");
            CLI.main(null);
        }
        try {
            output = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream()); // to read data coming from the server
            br = new BufferedReader(new InputStreamReader(socket.getInputStream())); // to read data coming from the server
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
            CLI.main(null);
        }
    }

    /**
     * ci mettiamo in ascolto dei messaggi che arrivano dal server
     */
    public String listening(){
        String str=null;
        try {
            str = br.readLine();
        } catch (IOException e) {
            //System.out.println("server disconnesso");
        }
        return str;
    }

    public void closeConnection(){
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void deserialize() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String str = dataInputStream.readUTF();
        ServerPacketHandler packet = null;

        try {
            packet = mapper.readValue(str, ServerPacketHandler.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (packet != null) {
            packet.execute(cli.getClientModelView());
        }
    }
}
