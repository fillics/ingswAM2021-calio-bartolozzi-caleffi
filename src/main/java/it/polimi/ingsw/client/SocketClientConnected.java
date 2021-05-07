package it.polimi.ingsw.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.client_packets.PacketUsername;

import java.io.*;
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
    private BufferedReader br;

    public SocketClientConnected() {
        this.serverAddress = Constants.getAddressServer();
        this.port = Constants.getPort();
        try {
            socket = new Socket(serverAddress, port);
            in = new Scanner(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            // to read data coming from the server
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

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

    public String listening(){
        String str = null;
        try {
            str =  br.readLine();
        } catch (IOException e) {
            try {
                br.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return str;
    }
}
