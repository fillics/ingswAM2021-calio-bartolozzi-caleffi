package it.polimi.ingsw.server;


import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Socket socket;
    private boolean quit = false;
    private MultiEchoServer multiEchoServer;


    public ClientHandler(Socket socket, MultiEchoServer multiEchoServer) {
        this.socket = socket;
        this.multiEchoServer = multiEchoServer;
    }


    public void run() {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            OutputStream out = socket.getOutputStream();

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

                    out.flush();
                }
            }
            // Chiudo gli stream e il socket -> client non è più connesso al server
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void askUsername() throws IOException {
        String username;
        OutputStream output = socket.getOutputStream();
        output.write("Insert username: ".getBytes(StandardCharsets.UTF_8));

        Scanner in = new Scanner(socket.getInputStream());
        username = in.nextLine();
        multiEchoServer.getLobby().add(username);
        System.out.println("Players in lobby: " + multiEchoServer.getLobby());

    }
}