package it.polimi.ingsw.client;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.controller.server_packets.ServerPacketHandler;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class ServerListener implements Runnable {

    private Client client;
    private SocketClientConnection socketClientConnection;
    private BufferedReader br;
    private DataInputStream dataInputStream;

    public ServerListener(Client client, SocketClientConnection socketClientConnection) {
        this.socketClientConnection = socketClientConnection;
        this.client = client;
        try {
            br = new BufferedReader(new InputStreamReader(socketClientConnection.getSocket().getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            ObjectMapper mapper = new ObjectMapper();
            String str = null;
            try {
                str = dataInputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(str);
        }
    }

}
