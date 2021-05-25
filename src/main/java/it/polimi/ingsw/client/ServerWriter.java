package it.polimi.ingsw.client;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.messages.ConnectionMessages;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class ServerWriter implements Runnable{

    private final ClientModelView clientModelView;
    private final SocketClientConnection socketClientConnection;
    private final ClientCLIOperationHandler clientCLIOperationHandler;
    private final PrintStream output;
    private final Scanner input;
    private final Client client;


    public ServerWriter(Client client, ClientModelView clientModelView, SocketClientConnection socketClientConnection, ClientCLIOperationHandler clientCLIOperationHandler, PrintStream output, Scanner input) {
        this.clientModelView = clientModelView;
        this.socketClientConnection = socketClientConnection;
        this.clientCLIOperationHandler = clientCLIOperationHandler;
        this.output = output;
        this.input = input;
        this.client = client;
    }

    @Override
    public void run() {
        String inputString;

        //Constants.printConnectionMessage(ConnectionMessages.WELCOME);
        //Constants.printConnectionMessage(ConnectionMessages.CONNECTION_OR_RECONNECTION);

        Constants.printConnectionMessage(ConnectionMessages.INSERT_USERNAME);

        while (!client.getClientState().equals(ClientStates.END)) {

            do {
                inputString = input.nextLine();
                if(inputString.length()==0) System.err.println("do not insert empty strings");
            }while(inputString.length()==0);


            if(inputString.equals("commands")){
                System.out.println(Constants.menu);
            }
            else{
                handleInitialGamePhase(inputString);
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



    public void handleInitialGamePhase(String inputString){

        switch (client.getClientState()){

            case USERNAME -> client.sendUsername(inputString);


            case NUMPLAYERS -> {
                try{
                    client.choosePlayerNumber(Integer.parseInt(inputString));
                }catch(NumberFormatException e){
                    System.out.println("do not insert strings");
                }
            }

            case LEADERSETUP ->
                client.getClientOperationHandler().chooseLeaderCardToRemove();


            case RESOURCESETUP -> {
                int howManyResources = 0;

                if (client.getClientModelView().getMyPlayer().getPosInGame()==1 ||
                        client.getClientModelView().getMyPlayer().getPosInGame()==2) howManyResources = 1;
                if(client.getClientModelView().getMyPlayer().getPosInGame()==3) howManyResources = 2;

                client.getClientOperationHandler().chooseInitialResources(howManyResources);
            }

            case GAMESTARTED -> {
                if (!inputString.equals("0")) {
                    try {
                        clientCLIOperationHandler.handleCLIOperation(inputString);
                    } catch (IOException | NumberFormatException e) {
                        System.err.println("Error during the choice of the operation to do");
                    }
                }
            }
        }

    }
}
