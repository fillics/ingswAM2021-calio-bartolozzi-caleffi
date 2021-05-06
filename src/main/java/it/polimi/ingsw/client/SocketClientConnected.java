package it.polimi.ingsw.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.client_packets.PacketUsername;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * SocketClientConnected class handles the connection between the client and the server.
 *
 */
public class SocketClientConnected {

    private String serverAddress;
    private int port;
    private Socket socket;
    private DataOutputStream output;
    private Scanner in;

    public SocketClientConnected() {
        this.serverAddress = Constants.getAddressServer();
        this.port = Constants.getPort();
        try {
            socket = new Socket(serverAddress, port);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in = new Scanner(socket.getInputStream());
            output=new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.err.println(Constants.getErr() + "Error during initialization of the client!");
            System.err.println(e.getMessage());
        }
    }

    public void connection(String jsonResult) throws IOException {
        output.writeUTF(jsonResult);
        output.flush();
        //output.close();
    }
}
