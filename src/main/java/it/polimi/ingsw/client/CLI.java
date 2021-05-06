package it.polimi.ingsw.client;

import it.polimi.ingsw.constants.Constants;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class CLI implements Runnable{
    private final PrintStream output;
    private final Scanner input;
    private SocketClientConnected socketClientConnected;
    private boolean quit= false;
    private DataOutputStream dout;

    public CLI() {
        input = new Scanner(System.in);
        output = new PrintStream(System.out);
    }

    public static void main(String[] args) {
        System.out.println(Constants.MASTEROFRENAISSANCE);
        System.out.println(Constants.AUTHORS);
        Scanner scanner = new Scanner(System.in);
        System.out.println(">Insert the server IP address");
        System.out.print(">");
        String ip = scanner.nextLine();
        Constants.setAddressServer(ip);
        System.out.println(">Insert the server port");
        System.out.print(">");
        int port = scanner.nextInt();
        Constants.setPort(port);
        CLI cli = new CLI();
        cli.run();
    }

    @Override
    public void run() {

        try {
            askUsername();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO: 05/05/2021 CHIEDERE IL NUMERO DI PLAYERS SOLO AL CREATORE DELLA PARTITA
        //askNumberOfPlayers();
        //TODO: ENTRO SOLO SE è IL MIO TURNO
        //makeAction();

        //ciclare in attesa di un messaggio fino a che il game è attivo


        input.close();
        output.close();
    }

    public void askUsername() throws IOException {
        String username;
        System.out.println("Inserisci username\n");
        username= input.nextLine();


        socketClientConnected = new SocketClientConnected();

        dout=new DataOutputStream(socketClientConnected.getSocket().getOutputStream());
        //TODO verificare correttezza

        dout.writeUTF(username);
        dout.flush();
        dout.close();

        //socketClientConnected.setUsername(username);

    }

    public SocketClientConnected getSocketClientConnected() {
        return socketClientConnected;
    }
}
