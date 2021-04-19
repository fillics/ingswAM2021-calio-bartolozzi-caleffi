package it.polimi.ingsw;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Locale;
import java.util.Scanner;

public class EchoServerClientHandler implements Runnable {
    private Socket socket;
    private boolean quit= false;
    public EchoServerClientHandler(Socket socket) {
        this.socket = socket;
    }


    public void run() {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            // Leggo e scrivo nella connessione finche' non ricevo "quit"
            while (!quit) {
                String line = in.nextLine();
                if (line.equals("quit")) {
                    quit = true;
                }
                else if(line.equals("ciao")){
                    out.println("Received: " + line);

                }
                else {
                    out.println("Received: " + line.toUpperCase(Locale.ROOT));
                    out.flush();

                }
                System.out.println(line);

            }
            // Chiudo gli stream e il socket
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}