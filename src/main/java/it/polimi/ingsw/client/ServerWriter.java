package it.polimi.ingsw.client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

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





            in = input.nextLine();

            if (client.getClientState().equals(ClientStates.USERNAME)) {
                client.sendUsername(in);
            }

            else if (client.getClientState().equals(ClientStates.NUMPLAYERS)) {
                client.choosePlayerNumber(Integer.parseInt(in));
            }

            else if (client.getClientState().equals(ClientStates.LEADERSETUP)) {
                try {
                    while(!in.equals("1")){
                        in = input.nextLine();
                        if(!in.equals("1")) System.err.println("Number not valid");
                    }
                    client.getClientOperationHandler().chooseLeaderCardToRemove();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else if (client.getClientState().equals(ClientStates.RESOURCESETUP)) {

                try {
                    while(!in.equals("1")){
                        in = input.nextLine();
                    }
                    if (client.getClientModelView().getMyPlayer().getPosInGame()!=0){
                        client.getClientOperationHandler().chooseInitialResources();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            else if (client.getClientState().equals(ClientStates.GAMESTARTED)) {
                if (!in.equals("0")) {
                    try {
                        clientOperationHandler.handleCLIOperation(Integer.parseInt(in));
                    } catch (IOException e) {
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


}
