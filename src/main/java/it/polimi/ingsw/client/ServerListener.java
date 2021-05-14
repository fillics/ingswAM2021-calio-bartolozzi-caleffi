package it.polimi.ingsw.client;

import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;

public class ServerListener implements Runnable {

    private CLI cli;
    private SocketClientConnection socketClientConnection;

    public ServerListener(CLI cli, SocketClientConnection socketClientConnection) {
        this.socketClientConnection = socketClientConnection;
        this.cli = cli;
    }

    @Override
    public void run() {
        while(true){
            String string = socketClientConnection.listening();
            handleBeforeGameMessage(string);
        }
    }

    /**
     * Method handleSetupMessage handles the messages that the server sends. According to them, it calls the right methods.
     * @param message (type String) - it is the message arrived from the server
     */
    public synchronized void handleBeforeGameMessage(String message){

        if (ConnectionMessages.INSERT_NUMBER_OF_PLAYERS.getMessage().equals(message)) {
            printConnectionMessage(ConnectionMessages.LOBBY_MASTER);
            System.out.println(message);
            cli.setClientState(ClientState.NUMPLAYERS);
        }
        else if (ConnectionMessages.TAKEN_NICKNAME.getMessage().equals(message)) {
            System.out.println(message);
            cli.setClientState(ClientState.USERNAME);
        }
        else if(ConnectionMessages.WAITING_PEOPLE.getMessage().equals(message)){
            System.out.println(message);
            cli.setClientState(ClientState.WAITPLAYERS);
        }
        else if (ConnectionMessages.GAME_IS_STARTING.getMessage().equals(message)) {
            System.out.println(message);
            cli.setClientState(ClientState.CREATEMODEL);
           /* System.out.println("Choose the 2 IDs of the leader cards to remove: ");
            for (LeaderCard leaderCard : cli.getClientModelView().getMyPlayer().getLeaderCards()) {
                System.out.println(leaderCard.getId());
            }
            cli.setClientState(ClientState.LEADERSETUP);*/
        }
        else {
            throw new IllegalStateException("Unexpected value: " + message);
        }
    }

    /**
     * Method printConnectionMessage prints the Connection Message passed as a parameter
     */
    void printConnectionMessage(ConnectionMessages message){
        System.out.println(message.getMessage());
    }


}
