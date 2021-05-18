package it.polimi.ingsw.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.server_packets.ServerPacketHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;


public class ServerListener implements Runnable {

    private Client client;
    private SocketClientConnection socketClientConnection;
    private BufferedReader br;
    private Scanner scanner;
    /** if it true, client and server are connected; if it is false, they are not  */
    private final AtomicBoolean connectionToServer;


    public ServerListener(Client client, SocketClientConnection socketClientConnection) {
        this.socketClientConnection = socketClientConnection;
        this.client = client;
        connectionToServer = new AtomicBoolean(true);
        try {
            br = new BufferedReader(new InputStreamReader(socketClientConnection.getSocket().getInputStream()));
            scanner = new Scanner(socketClientConnection.getSocket().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (connectionToServer.get()) {
            ObjectMapper mapper = new ObjectMapper();
            String str = null;
            ServerPacketHandler packet;
            try{
                str = scanner.nextLine();
                //System.out.println(str);
            }catch(NoSuchElementException ignored){
                connectionToServer.set(false);

            }

            if(connectionToServer.get()){
                try {
                    //System.out.println("dentro al try");
                    packet = mapper.readValue(str, ServerPacketHandler.class);
                    packet.execute(client);
                } catch (JsonProcessingException|IllegalArgumentException ignored) {
                }
            }

        }

        //se connessione terminata, chiudo il client
        if (!connectionToServer.get()) {
            Constants.printConnectionMessage(ConnectionMessages.CONNECTION_CLOSED);
            System.exit(0);
        }

    }
}
