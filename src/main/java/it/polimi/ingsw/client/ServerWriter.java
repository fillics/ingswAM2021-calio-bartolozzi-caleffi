package it.polimi.ingsw.client;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class ServerWriter implements Runnable, ViewInterface{

    private ClientModelView clientModelView;
    private SocketClientConnection socketClientConnection;
    private ClientOperationHandler clientOperationHandler;
    private final PrintStream output;
    private final Scanner input;
    private ObjectMapper mapper;
    private boolean gameStarted;
    private Client client;

    // TODO servono tutti questi parametri in ingresso??
    public ServerWriter(Client client, ClientModelView clientModelView, SocketClientConnection socketClientConnection, ClientOperationHandler clientOperationHandler, PrintStream output, Scanner input, ObjectMapper mapper, boolean gameStarted) {
        this.clientModelView = clientModelView;
        this.socketClientConnection = socketClientConnection;
        this.clientOperationHandler = clientOperationHandler;
        this.output = output;
        this.input = input;
        this.mapper = mapper;
        this.gameStarted = gameStarted;
        this.client = client;
    }

    @Override
    public void run() {
        String in;
        Constants.printConnectionMessage(ConnectionMessages.INSERT_USERNAME);
        System.out.print(">");

        while (!client.getClientState().equals(ClientStates.END)) {
            //in = input.nextLine();
            //handleSetupPhase(in);

            in = input.nextLine();


            if (client.getClientState().equals(ClientStates.USERNAME)) {
                client.sendUsername(in);
            }
            else if (client.getClientState().equals(ClientStates.NUMPLAYERS)) {
                try{
                    client.choosePlayerNumber(Integer.parseInt(in));
                }catch(NumberFormatException e){
                    System.out.println("do not insert strings");
                }
            }
            else if (client.getClientState().equals(ClientStates.LEADERSETUP)) {
                System.out.println("scegli carte leader");
                client.getClientOperationHandler().chooseLeaderCardToRemove();

               /* try {
                    while (!in.equals("1")) {
                        in = input.nextLine();
                        if (!in.equals("1")) System.err.println("Number not valid");
                    }
                    client.getClientOperationHandler().chooseLeaderCardToRemove();
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }
            else if (client.getClientState().equals(ClientStates.RESOURCESETUP)) {
                int howManyResources = 0;

                if (client.getClientModelView().getMyPlayer().getPosInGame()==1 ||
                        client.getClientModelView().getMyPlayer().getPosInGame()==2) howManyResources = 1;
                if(client.getClientModelView().getMyPlayer().getPosInGame()==3) howManyResources = 2;

                client.getClientOperationHandler().chooseInitialResources(howManyResources);
                /*try {
                    while (!in.equals("1")) {
                        in = input.nextLine();
                        if (!in.equals("1")) System.err.println("Number not valid");
                    }
                    if (client.getClientModelView().getMyPlayer().getPosInGame() != 0) {
                        client.getClientOperationHandler().chooseInitialResources();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }
            else if (client.getClientState().equals(ClientStates.GAMESTARTED)) {
                if (!in.equals("0")) {
                    try {
                        clientOperationHandler.handleCLIOperation(Integer.parseInt(in));
                    } catch (IOException | NumberFormatException e) {
                        System.err.println("Error during the choice of the operation to do");
                    }
                }

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

    /*public void handleSetupPhase(String in){

        switch (client.getClientState()){
            case USERNAME -> client.sendUsername(in);

            case NUMPLAYERS -> {
                try{
                    client.choosePlayerNumber(Integer.parseInt(in));
                }catch(NumberFormatException e){
                    System.out.println("reinsert value");
                }
            }

            case LEADERSETUP -> {

                // TODO: 17/05/2021 mettere number not valid gia prima
                try {
                    while (!in.equals("1")) {
                        in = input.nextLine();
                        if (!in.equals("1")) System.err.println("Number not valid");
                    }
                    client.getClientOperationHandler().chooseLeaderCardToRemove();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            case RESOURCESETUP -> {
                try {
                    while (!in.equals("1")) {
                        in = input.nextLine();
                        if (!in.equals("1")) System.err.println("Number not valid");
                    }
                    if (client.getClientModelView().getMyPlayer().getPosInGame() != 0) {
                        client.getClientOperationHandler().chooseInitialResources(1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            case GAMESTARTED -> {
                if (!in.equals("0")) {
                    try {
                        clientOperationHandler.handleCLIOperation(Integer.parseInt(in));
                    } catch (IOException | NumberFormatException e) {
                        System.err.println("Error during the choice of the operation to do");
                    }
                }
            }
        }

    }*/

}
