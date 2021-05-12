package it.polimi.ingsw;


import it.polimi.ingsw.client.CLI;
import it.polimi.ingsw.server.Server;

import java.util.Scanner;

/**
 * Class MasterOfRenaissance is the main class of whole project.
 *
 * @author Filippo Caliò, Beatrice Bartolozzi, Giovanni Caleffi
 */

public class MasterOfRenaissance {

    /**
     * Method main selects CLI, GUI or Server based on the arguments provided.
     *
     * @param args of type String[]
     */

    public static void main( String[] args ) {
        for (int i = 0; i < 15; i++) {
            int random = (int)(Math.random()*(4));
            System.out.println(random);
        }

        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Hi! Welcome to Master of Renaissance!\nWhat do you want to launch?");
        System.out.println("1. SERVER\n2. CLIENT (CLI INTERFACE)\n3. CLIENT (GUI INTERFACE)");
        System.out.println("\n>Type the number of the desired option!");
        System.out.print(">");
        choice = scanner.nextInt();

        switch (choice){
            case (1):
                Server.main(null);
                break;
            case (2):
                CLI.main(null);
                break;
            case (3):
                //GUI.main(null);
                break;
            default:
                System.out.print("Invalid input, application will be close");
                System.exit(-1);
        }


    }
}
