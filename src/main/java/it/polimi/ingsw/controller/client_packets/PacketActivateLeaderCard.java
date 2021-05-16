package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.PacketLeaderCards;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

public class PacketActivateLeaderCard implements ClientPacketHandler {
    private final int id;

    @JsonCreator
    public PacketActivateLeaderCard(@JsonProperty("Id")int id) {
        this.id = id;
    }

    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) throws EmptyDeposit, DepositHasReachedMaxLimit, DepositHasAnotherResource, LeaderCardNotActivated, LeaderCardNotFound, DiscountCannotBeActivated, DevelopmentCardNotFound, DepositDoesntHaveThisResource, DevCardNotPlaceable, DifferentDimension, NotEnoughResources, WrongChosenResources, NotEnoughRequirements, TooManyResourcesRequested {
        if((gameInterface.getState() == GameStates.PHASE_ONE || gameInterface.getState() == GameStates.PHASE_TWO)
                && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
            gameInterface.activateLeaderCard(id);
            clientHandler.sendPacketToClient(new PacketLeaderCards(gameInterface.getIdClientActivePlayers().get(clientHandler.getIdClient()).getLeaderCards()));
        }
        else Constants.printConnectionMessage(ConnectionMessages.NOT_YOU_TURN);
    }

    public int getId() {
        return id;
    }
}

