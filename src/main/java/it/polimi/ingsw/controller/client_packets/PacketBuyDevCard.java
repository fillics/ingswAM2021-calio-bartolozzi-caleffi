package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentSpace;
import it.polimi.ingsw.client.SocketClientConnected;

import java.net.Socket;
import java.util.ArrayList;


public class PacketBuyDevCard implements PacketHandler {
    private int id;
    private ArrayList<ResourceType> chosenResources;
    private ArrayList<Warehouse> chosenWarehouses;
    private DevelopmentSpace developmentSpace;

    @JsonCreator
    public PacketBuyDevCard(@JsonProperty("ID: ")int id,@JsonProperty("chosen resources: ") ArrayList<ResourceType> chosenResources,@JsonProperty("chosen warehouse: ") ArrayList<Warehouse> chosenWarehouses,@JsonProperty("development space: ") DevelopmentSpace developmentSpace) {
        this.id = id;
        this.chosenResources = chosenResources;
        this.chosenWarehouses = chosenWarehouses;
        this.developmentSpace = developmentSpace;
    }



    @Override
    public void execute(GameInterface gameInterface, Socket socketClientConnected) throws DevelopmentCardNotFound, EmptyDeposit, DepositDoesntHaveThisResource, DevCardNotPlaceable, DifferentDimension, NotEnoughResources, WrongChosenResources {
        gameInterface.buyDevCard(id,chosenResources,chosenWarehouses,developmentSpace);
    }
}
