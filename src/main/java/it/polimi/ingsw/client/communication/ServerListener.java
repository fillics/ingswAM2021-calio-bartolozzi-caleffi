package it.polimi.ingsw.client.communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.server_packets.ServerPacketHandler;

import javax.swing.*;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;


public class ServerListener implements Runnable {

    private final Client client;
    private Scanner scanner;
    /** if it true, client and server are connected; if it is false, they are not  */
    private final AtomicBoolean connectionToServer;


    /**
     * Class' constructor
     * @param client is the client the server has sent the packets
     * @param socketClientConnection is the socket of the client
     */
    public ServerListener(Client client, SocketClientConnection socketClientConnection) {
        this.client = client;
        connectionToServer = new AtomicBoolean(true);
        try {
            scanner = new Scanner(socketClientConnection.getSocket().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that runs until the connection with the server is closed, until the method runs, all the packets send
     * by the server are deserialized and executed usually changing the client's ClientModelView
     */
    @Override
    public void run() {
        while (connectionToServer.get()) {
            ObjectMapper mapper = new ObjectMapper();
            String str = null;
            ServerPacketHandler packet = null;
            try{
                str = scanner.nextLine();
            }catch(NoSuchElementException ignored){
                connectionToServer.set(false);
            }

            if(connectionToServer.get()){

                try {
                    packet = mapper.readValue(str, ServerPacketHandler.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                assert packet != null;
                packet.execute(client);

            }
        }

        // TODO: 21/06/2021 posso togliere if??
        //if the connection terminates, the application will close
        if (!connectionToServer.get()) {
            if(client.getViewChoice().equals(ViewChoice.CLI)){
                Constants.printConnectionMessage(ConnectionMessages.CONNECTION_CLOSED);
            }
            else{
                JOptionPane.showMessageDialog(client.getGui().getjFrame(), ConnectionMessages.CONNECTION_CLOSED.getMessage());
            }
            System.exit(0);
        }

    }
}
