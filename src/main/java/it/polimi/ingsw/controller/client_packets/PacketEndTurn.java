package it.polimi.ingsw.controller.client_packets;

import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.State;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

import java.io.IOException;

public class PacketEndTurn implements ClientPacketHandler{
    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) throws EmptyDeposit, DepositHasReachedMaxLimit, DepositHasAnotherResource, LeaderCardNotActivated, LeaderCardNotFound, DiscountCannotBeActivated, DevelopmentCardNotFound, DepositDoesntHaveThisResource, DevCardNotPlaceable, DifferentDimension, NotEnoughResources, WrongChosenResources, NotEnoughRequirements, TooManyResourcesRequested, IOException, ClassNotFoundException {
        //if (gameInterface.getState() == State.PHASE_ONE && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()) {
            gameInterface.nextPlayer();
        System.out.println(clientHandler.getPosInGame());
            if (clientHandler.getPosInGame() == gameInterface.getActivePlayers().size() - 1) {
                server.getMapUsernameClientHandler().get(gameInterface.getActivePlayers().get(0).getUsername()).sendMessageToClient(ConnectionMessages.YOU_TURN);
            }
            else{
                server.getMapUsernameClientHandler().get(gameInterface.getActivePlayers().get(clientHandler.getPosInGame() + 1).getUsername())
                        .sendMessageToClient(ConnectionMessages.YOU_TURN);
            }
       // }
    }
}
