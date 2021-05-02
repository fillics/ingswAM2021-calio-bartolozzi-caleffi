package it.polimi.ingsw.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.controller.packets.HandlePacket;
import it.polimi.ingsw.controller.packets.PacketUsername;
import it.polimi.ingsw.exceptions.NumMaxPlayersReached;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameInterface;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiEchoServer {
    private int port;
    private ArrayList<PacketUsername> lobby = new ArrayList<>();
    private Game game = new Game();


    public MultiEchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        MultiEchoServer echoServer = new MultiEchoServer(1234);
        echoServer.startServer();
    }



    public void startServer() {
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println(e.getMessage()); // Porta non disponibile
            return;
        }

        System.out.println("Server ready");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Client Connected!");
                executor.submit(new ClientHandler(socket, this)); //per ogni socket noi creiamo un thread

            } catch(IOException e) {
                break; // Entrerei qui se serverSocket venisse chiuso
            }
        }
        executor.shutdown();
    }



    public HandlePacket deserialize(String jsonResult){
        ObjectMapper mapper = new ObjectMapper();
        HandlePacket packet = null;
        try {
            packet = mapper.readValue(jsonResult, HandlePacket.class);
        } catch (JsonProcessingException  e) {
            e.printStackTrace();
        }
        return packet;
    }
}
