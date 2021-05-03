package it.polimi.ingsw.server;


import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.controller.packets.PacketNumPlayers;
import it.polimi.ingsw.controller.packets.PacketUsername;
import it.polimi.ingsw.exceptions.NumMaxPlayersReached;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private SocketConnection socket;
    private boolean quit = false;
    private Server server;


    public ClientHandler(SocketConnection socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {
            Scanner in = new Scanner(socket.getSocket().getInputStream());
            OutputStream out = socket.getSocket().getOutputStream();

            askNumberOfPlayers();
            askUsername();


            // Leggo e scrivo nella connessione finche' non ricevo "quit"
            while (!quit) {
                String line = in.nextLine();
                if (line.equals("quit")) {
                    quit = true;
                } else {
                    out.write("Received: ".getBytes(StandardCharsets.UTF_8));
                    out.write(line.getBytes(StandardCharsets.UTF_8));
                    out.write("\n".getBytes(StandardCharsets.UTF_8));
                    System.out.println(socket.getIdClient());
                    out.flush();
                }
            }
            // Chiudo gli stream e il socket -> client non è più connesso al server
            in.close();
            out.close();
            socket.getSocket().close();
        } catch (IOException | NumMaxPlayersReached e) {
            System.err.println(e.getMessage());
        }
    }

    public void askUsername() throws IOException, NumMaxPlayersReached {
        PacketHandler object;
        String username;
        OutputStream output = socket.getSocket().getOutputStream();
        output.write("Insert username: ".getBytes(StandardCharsets.UTF_8));
        Scanner in = new Scanner(socket.getSocket().getInputStream());
        username = in.nextLine();
        PacketUsername packet = new PacketUsername(username);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(packet);
        object = server.deserialize(jsonResult, socket);

    }

    public void askNumberOfPlayers() throws IOException, NumMaxPlayersReached {
        PacketHandler object;
        String number_of_players;
        OutputStream output = socket.getSocket().getOutputStream();
        output.write("Insert number of players: ".getBytes(StandardCharsets.UTF_8));
        Scanner in = new Scanner(socket.getSocket().getInputStream());
        number_of_players = in.nextLine();
        PacketNumPlayers packet = new PacketNumPlayers(Integer.parseInt(number_of_players));
        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(packet);
        object = server.deserialize(jsonResult, socket);
    }
}