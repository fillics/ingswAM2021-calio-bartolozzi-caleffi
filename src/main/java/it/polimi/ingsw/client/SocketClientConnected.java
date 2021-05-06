package it.polimi.ingsw.client;

import it.polimi.ingsw.constants.Constants;

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
    private String username;
    private OutputStream output;
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
            output = socket.getOutputStream();
        } catch (IOException e) {
            System.err.println(Constants.getErr() + "Error during initialization of the client!");
            System.err.println(e.getMessage());
        }
    }


    public Socket getSocket() {
        return socket;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws IOException {
        this.username = username;
        //output.write(username.getBytes(StandardCharsets.UTF_8));
        //output.flush();
    }
}
