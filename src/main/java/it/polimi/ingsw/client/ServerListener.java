package it.polimi.ingsw.client;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.server_packets.ServerPacketHandler;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class ServerListener implements Runnable {

    private Client client;
    private SocketClientConnection socketClientConnection;
    private BufferedReader br;
    private Scanner scanner;

    public ServerListener(Client client, SocketClientConnection socketClientConnection) {
        this.socketClientConnection = socketClientConnection;
        this.client = client;
        try {
            br = new BufferedReader(new InputStreamReader(socketClientConnection.getSocket().getInputStream()));
            scanner = new Scanner(socketClientConnection.getSocket().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            //System.out.println("Stato del client: " + client.getClientState());
            ObjectMapper mapper = new ObjectMapper();
            String str;
            ServerPacketHandler packet;
            str = scanner.nextLine();
            System.out.println(str);
            try {
                packet = mapper.readValue(str, ServerPacketHandler.class);
                packet.execute(client);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

    }
}
