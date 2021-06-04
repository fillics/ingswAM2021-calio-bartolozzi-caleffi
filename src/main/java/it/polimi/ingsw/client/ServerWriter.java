package it.polimi.ingsw.client;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.messages.ConnectionMessages;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class ServerWriter implements Runnable{

    private final SocketClientConnection socketClientConnection;
    private final CLIOperationHandler cliOperationHandler;
    private final PrintStream output;
    private final Scanner input;
    private final Client client;


    public ServerWriter(Client client, SocketClientConnection socketClientConnection, CLIOperationHandler cliOperationHandler) {

        input = new Scanner(System.in);
        output = new PrintStream(System.out);

        this.socketClientConnection = socketClientConnection;
        this.cliOperationHandler = cliOperationHandler;

        this.client = client;
    }

    @Override
    public void run() {
        String inputString;

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
                handleGamePhase(inputString);
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


    public void handleGamePhase(String inputString){

        switch (client.getClientState()){

            case USERNAME -> client.sendUsername(inputString);

            case NUMPLAYERS -> {
                try{
                    client.getCli().choosePlayerNumber(Integer.parseInt(inputString));
                }catch(NumberFormatException e){
                    System.out.println("do not insert strings");
                }
            }

            case LEADERSETUP ->
                client.getCliOperationHandler().chooseLeaderCardToRemove();


            case RESOURCESETUP -> {
                int howManyResources = 0;

                if (client.getClientModelView().getMyPlayer().getPosInGame()==1 ||
                        client.getClientModelView().getMyPlayer().getPosInGame()==2) howManyResources = 1;
                if(client.getClientModelView().getMyPlayer().getPosInGame()==3) howManyResources = 2;

                client.getCliOperationHandler().chooseInitialResources(howManyResources);
            }

            case GAMESTARTED -> {
                if (!inputString.equals("0")) {
                    try {
                        cliOperationHandler.handleOperation(inputString);
                    } catch (IOException | NumberFormatException e) {
                        System.err.println("Error during the choice of the operation to do");
                    }
                }
            }
        }

    }
}
