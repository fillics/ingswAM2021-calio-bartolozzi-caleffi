package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.PacketWarehouse;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

import java.io.IOException;

public class PacketChooseInitialResources implements ClientPacketHandler{
    private int depositPosition;
    private ResourceType resource;

    @JsonCreator
    public PacketChooseInitialResources(@JsonProperty("DepositPosition")int depositPosition, @JsonProperty("Resource") ResourceType resource) {
        this.depositPosition = depositPosition;
        this.resource = resource;
    }

    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) throws EmptyDeposit, DepositHasReachedMaxLimit, DepositHasAnotherResource, LeaderCardNotActivated, LeaderCardNotFound, DiscountCannotBeActivated, DevelopmentCardNotFound, DepositDoesntHaveThisResource, DevCardNotPlaceable, DifferentDimension, NotEnoughResources, WrongChosenResources, NotEnoughRequirements, TooManyResourcesRequested, IOException, ClassNotFoundException {
        if(gameInterface.getState() == GameStates.SETUP){

            gameInterface.additionalResourceSetup(resource,depositPosition,clientHandler.getIdClient());
            clientHandler.sendPacketToClient(new PacketWarehouse(gameInterface.getIdClientActivePlayers().get(clientHandler.getIdClient()).getBoard().getStrongbox(),gameInterface.getIdClientActivePlayers().get(clientHandler.getIdClient()).getBoard().getDeposits()));
        }
    }

    public int getDepositPosition() {
        return depositPosition;
    }

    public ResourceType getResource() {
        return resource;
    }
}
