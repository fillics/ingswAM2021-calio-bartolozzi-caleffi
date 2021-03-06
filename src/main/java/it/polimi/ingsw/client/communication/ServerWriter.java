package it.polimi.ingsw.client.communication;
import com.fasterxml.jackson.core.JsonProcessingException;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientStates;
import it.polimi.ingsw.client.cli.CLIOperationHandler;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.client_packets.PacketEndConnection;
import it.polimi.ingsw.controller.client_packets.PacketUsername;
import it.polimi.ingsw.controller.messages.ConnectionMessages;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;


/**
 * ServerWriter sends to the server the messages written by the Client throught the keyboard
 */
public class ServerWriter implements Runnable{

    private final CLIOperationHandler cliOperationHandler;
    private final PrintStream output;
    private final Scanner input;
    private final Client client;


    /**
     * Class' constructor
     * @param client is the client
     * @param socketClientConnection is the socket linked to the client
     * @param cliOperationHandler is the class that handles all the operation in game
     */
    public ServerWriter(Client client, SocketClientConnection socketClientConnection, CLIOperationHandler cliOperationHandler) {

        input = new Scanner(System.in);
        output = new PrintStream(System.out);

        this.cliOperationHandler = cliOperationHandler;

        this.client = client;
    }

    /**
     * Method that handle all the strings written by the user based on the client state, it runs until the client state is END.
     */
    @Override
    public void run() {
        String inputString;

        Constants.printConnectionMessage(ConnectionMessages.INSERT_USERNAME);

        while (!client.getClientState().equals(ClientStates.CLOSE_CONNECTION)) {

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

        input.close();
        output.close();
    }

    /**
     * Method that based on the client state, the input string written by the user calls a method that handle the command
     * @param inputString the the input string written by the user
     */
    public void handleGamePhase(String inputString){

        switch (client.getClientState()){

            case USERNAME -> client.sendPacketToServer(new PacketUsername(inputString.toLowerCase(Locale.ROOT)));

            case NUMPLAYERS -> {
                try{
                    client.getCli().choosePlayerNumber(Integer.parseInt(inputString));
                }catch(NumberFormatException e){
                    System.out.println("Do not insert strings");
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
            case GAME_ENDING -> System.err.println("Please wait the other players while they are ending their own turn");

            case END -> {
                if (inputString.equals("close")){
                    System.out.println("Closing connection...");
                    client.setClientState(ClientStates.CLOSE_CONNECTION);
                    try {
                        cliOperationHandler.sendPacket(new PacketEndConnection());
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Connection with the server closed!");
                    System.out.println("Bye Bye");
                    System.exit(0);
                }
            }

        }

    }
}
