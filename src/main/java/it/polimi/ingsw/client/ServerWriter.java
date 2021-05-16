package it.polimi.ingsw.client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.localgame.LocalGame;

import java.io.IOException;
import java.io.PrintStream;
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
    Client client;

    public ServerWriter(Client client, ClientModelView clientModelView, SocketClientConnection socketClientConnection, ClientOperationHandler clientOperationHandler, PrintStream output, Scanner input, ObjectMapper mapper, boolean gameStarted, int choiceGame) {
        this.clientModelView = clientModelView;
        this.socketClientConnection = socketClientConnection;
        this.clientOperationHandler = clientOperationHandler;
        this.output = output;
        this.input = input;
        this.mapper = mapper;
        this.gameStarted = gameStarted;
        this.choiceGame = choiceGame;
        this.client = client;
    }

    @Override
    public void run() {
        String in;

        Constants.printConnectionMessage(ConnectionMessages.INSERT_USERNAME);
        System.out.print(">");
        while (!client.getClientState().equals(ClientState.END)) {
            in = input.nextLine();

            if (client.getClientState().equals(ClientState.USERNAME)) {
                client.sendUsername(in);
            }

            else if (client.getClientState().equals(ClientState.NUMPLAYERS)) {
                client.choosePlayerNumber(Integer.parseInt(in));
            }

            else if (client.getClientState().equals(ClientState.LEADERSETUP)) {
                try {
                    while(!in.equals("1")){
                        in = input.nextLine();
                        if(!in.equals("1")) System.err.println("Number not valid");
                    }
                    client.getClientOperationHandler().chooseLeaderCardToRemove();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else if (client.getClientState().equals(ClientState.RESOURCESETUP)) {

                try {
                    while(!in.equals("1")){
                        in = input.nextLine();
                    }
                    if (client.getClientModelView().getMyPlayer().getPosInGame()!=0){
                        int howManyResources = 0;

                        if(client.getClientModelView().getMyPlayer().getPosInGame() == 1 ||
                                client.getClientModelView().getMyPlayer().getPosInGame() == 2) howManyResources=1;
                        if(client.getClientModelView().getMyPlayer().getPosInGame() == 3) howManyResources=2;

                        client.getClientOperationHandler().chooseInitialResources(howManyResources);


                    }
                    else{
                        System.out.println("You're the first player, you can't have any resources or faith points");
                        client.setClientState(ClientState.GAMESTARTED);
                    }


                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            else if (client.getClientState().equals(ClientState.GAMESTARTED)) {
                System.out.println("Sei in posizione: " + client.getClientModelView().getMyPlayer().getPosInGame());

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


        try {
            socketClientConnection.deserialize();
        } catch (IOException e) {
            e.printStackTrace();
        }

        input.close();
        output.close();
    }


}
