package it.polimi.ingsw.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Logger;

public class EchoServerClientHandler implements Runnable {
    private Socket socket;
    private ObjectOutputStream os;
    private ObjectInputStream is;





    private boolean quit= false;


    public EchoServerClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {

            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());

            askUsername();

            while (!quit) {
                Object packetFromClient = is.readObject();



            }
            // Chiudo gli stream e il socket
            os.close();
            is.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    public void askUsername(){

    }
}