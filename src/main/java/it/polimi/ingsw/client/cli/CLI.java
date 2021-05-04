package it.polimi.ingsw.client.cli;

import it.polimi.ingsw.constants.Constants;

import java.io.PrintStream;
import java.util.Scanner;

public class CLI {
    private final PrintStream output;
    private final Scanner input;

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
        System.out.println(">Insert the server port");
        System.out.print(">");
        int port = scanner.nextInt();
        CLI cli = new CLI();

    }

}
