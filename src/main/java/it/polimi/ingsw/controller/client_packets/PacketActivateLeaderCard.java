package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.State;
import it.polimi.ingsw.controller.server_packets.PacketLeaderCards;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

public class PacketActivateLeaderCard implements ClientPacketHandler {
    private int id;

    @JsonCreator
    public PacketActivateLeaderCard(@JsonProperty("Id")int id) {
        this.id = id;
    }

    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) throws EmptyDeposit, DepositHasReachedMaxLimit, DepositHasAnotherResource, LeaderCardNotActivated, LeaderCardNotFound, DiscountCannotBeActivated, DevelopmentCardNotFound, DepositDoesntHaveThisResource, DevCardNotPlaceable, DifferentDimension, NotEnoughResources, WrongChosenResources, NotEnoughRequirements, TooManyResourcesRequested {
        if((gameInterface.getState() == State.PHASE_ONE || gameInterface.getState() == State.PHASE_TWO)
                && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
            gameInterface.activateLeaderCard(id);
            clientHandler.sendUpdatePacket(new PacketLeaderCards(gameInterface.getIdClientActivePlayers().get(clientHandler.getIdClient()).getLeaderCards()));
        }
    }

    public int getId() {
        return id;
    }
}

