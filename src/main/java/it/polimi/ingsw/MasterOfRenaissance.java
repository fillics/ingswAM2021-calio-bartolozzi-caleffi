package it.polimi.ingsw;


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
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Hi! Welcome to Master of Renaissance!\nWhat do you want to launch?");
        System.out.println("0. SERVER\n1. CLIENT (CLI INTERFACE)");
        System.out.println("\n>Type the number of the desired option!");
        System.out.print(">");
        choice = scanner.nextInt();

        switch (choice){
            case (1):
                //ServerMain.main(null);
                break;
            case (2):
                //ClientMain.main(null);
                break;
            default:
                System.out.print("Invalid input, application will be close");
                System.exit(-1);
        }


    }
}
