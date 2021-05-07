package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.client.SocketClientConnected;
import it.polimi.ingsw.server.Server;

import java.net.Socket;

public class PacketAdditionalSetup implements PacketHandler {
    private Resource resource;
    private int depositPosition;

    @JsonCreator
    public PacketAdditionalSetup(@JsonProperty("resources: ")Resource resource, @JsonProperty("deposit position: ")int depositPosition) {
        this.resource = resource;
        this.depositPosition = depositPosition;
    }

    @Override
    public void execute(Server server, GameInterface gameInterface, Socket socket) throws EmptyDeposit, DepositHasReachedMaxLimit, DepositHasAnotherResource, LeaderCardNotActivated, LeaderCardNotFound, DiscountCannotBeActivated, DevelopmentCardNotFound, DepositDoesntHaveThisResource, DevCardNotPlaceable, DifferentDimension, NotEnoughResources, WrongChosenResources, NotEnoughRequirements, TooManyResourcesRequested {
        gameInterface.additionalResourceSetup(resource,depositPosition);
    }
}
