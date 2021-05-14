package it.polimi.ingsw.client;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.localgame.LocalGame;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ServerWriter implements Runnable, ViewInterface{

    private ClientModelView clientModelView;
    private SocketClientConnection socketClientConnection;
    private ClientOperationHandler clientOperationHandler;
    private final PrintStream output;
    private final Scanner input;
    private ObjectMapper mapper;
    private boolean gameStarted;
    int choiceGame;
    CLI cli;

    public ServerWriter(CLI cli, ClientModelView clientModelView, SocketClientConnection socketClientConnection, ClientOperationHandler clientOperationHandler, PrintStream output, Scanner input, ObjectMapper mapper, boolean gameStarted, int choiceGame) {
        this.clientModelView = clientModelView;
        this.socketClientConnection = socketClientConnection;
        this.clientOperationHandler = clientOperationHandler;
        this.output = output;
        this.input = input;
        this.mapper = mapper;
        this.gameStarted = gameStarted;
        this.choiceGame = choiceGame;
        this.cli = cli;
    }

    @Override
    public void run() {
        String in;
        choiceGameType();
        if (choiceGame == 1) {
            LocalGame localGame = new LocalGame();
        }
        if (choiceGame == 2) {
            System.out.println("insert username");
            while (cli.getClientState() != ClientState.END) {
                in = input.nextLine();
                if (cli.getClientState() == ClientState.USERNAME) {
                    cli.sendUsername(in);
                }

                else if (cli.getClientState() == ClientState.NUMPLAYERS) {
                    cli.choosePlayerNumber(Integer.parseInt(in));
                }


                else if (cli.getClientState() == ClientState.LEADERSETUP) {
                    try {
                        cli.getClientOperationHandler().chooseLeaderCardToRemove(Integer.parseInt(in));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else if (cli.getClientState() == ClientState.RESOURCESETUP) {

                }

                else if (cli.getClientState() == ClientState.GAMESTARTED) {
                    System.out.println("We're ready to play! Choose one of the operations you can do:\nText 0 to quit");
                    int operation;
                    do {
                        System.out.println("1: Activate a Leader Card\n" +
                                "2: Buy a Development Card\n" +
                                "3: Choose Discount\n" +
                                "4: Use production powers\n" +
                                "5: Discard a Leader Card\n" +
                                "6: Move one of you resources\n" +
                                "7: Place one of your resources\n" +
                                "8: Take resources from the market\n" +
                                "9: End Turn\n");
                        operation = input.nextInt();
                        if (operation != 0) {
                            try {
                                clientOperationHandler.handleCLIOperation(operation);
                            } catch (IOException e) {
                                System.err.println("Error during the choice of the operation to do");
                            }
                        }
                    } while (operation != 0);
                }
            }
            //the game has been created


            try {
                socketClientConnection.deserialize();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //System.out.println("username pescato dal packet : " + clientModelView.getMyPlayer().getUsername());


            try {
                socketClientConnection.deserialize();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Ti rimangono queste leader cards:" + clientModelView.getMyPlayer().getLeaderCards().get(0).getId());
            System.out.println("Ti rimangono queste leader cards:" + clientModelView.getMyPlayer().getLeaderCards().get(1).getId());

            System.out.println("----------------");


            input.close();
            output.close();
        }

        }


    /**
     * Method choiceGameType asks to the player if he wants to play in solo (without making any connection to the server)
     * or throught the server
     */
    @Override
    public void choiceGameType(){

        cli.printConnectionMessage(ConnectionMessages.LOCAL_OR_SERVERGAME);

        Scanner in = new Scanner(System.in);
        do {
            System.out.print(">");
            try{
                choiceGame = in.nextInt();
                if (choiceGame!=1 && choiceGame!=2) cli.printConnectionMessage(ConnectionMessages.INVALID_CHOICE);
            }catch (InputMismatchException e) {
                System.err.println("Invalid parameter: insert a numeric value.");
                choiceGameType();
            }
        }while(choiceGame!=1 && choiceGame!=2);
    }



}
