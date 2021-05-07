package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.controller.State;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

public class PacketActivateLeaderCard implements PacketHandler {
    private int ID;

    @JsonCreator
    public PacketActivateLeaderCard(@JsonProperty("ID: ")int ID) {
        this.ID = ID;
    }


    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) throws EmptyDeposit, DepositHasReachedMaxLimit, DepositHasAnotherResource, LeaderCardNotActivated, LeaderCardNotFound, DiscountCannotBeActivated, DevelopmentCardNotFound, DepositDoesntHaveThisResource, DevCardNotPlaceable, DifferentDimension, NotEnoughResources, WrongChosenResources, NotEnoughRequirements, TooManyResourcesRequested {
        if((gameInterface.getState() == State.PHASE_ONE || gameInterface.getState() == State.PHASE_TWO)
                && clientHandler.getIdClient() == gameInterface.getCurrentPlayer()){
            gameInterface.activateLeaderCard(ID);
        }
    }
}

