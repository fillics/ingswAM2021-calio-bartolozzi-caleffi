package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.State;
import it.polimi.ingsw.controller.server_packets.PacketLiteDevelopmentGrid;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentSpace;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

import java.util.ArrayList;


public class PacketBuyDevCard implements ClientPacketHandler {
    private final int id;
    private final ArrayList<ResourceType> chosenResources;
    private final ArrayList<Warehouse> chosenWarehouses;
    private final DevelopmentSpace developmentSpace;

    @JsonCreator
    public PacketBuyDevCard(@JsonProperty("Id")int id,@JsonProperty("ChosenResources") ArrayList<ResourceType> chosenResources,@JsonProperty("ChosenWarehouses ") ArrayList<Warehouse> chosenWarehouses,@JsonProperty("DevelopmentSpace") DevelopmentSpace developmentSpace) {
        this.id = id;
        this.chosenResources = chosenResources;
        this.chosenWarehouses = chosenWarehouses;
        this.developmentSpace = developmentSpace;
    }


    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) throws EmptyDeposit, DepositHasReachedMaxLimit, DepositHasAnotherResource, LeaderCardNotActivated, LeaderCardNotFound, DiscountCannotBeActivated, DevelopmentCardNotFound, DepositDoesntHaveThisResource, DevCardNotPlaceable, DifferentDimension, NotEnoughResources, WrongChosenResources, NotEnoughRequirements, TooManyResourcesRequested {
        if(gameInterface.getState() == State.PHASE_ONE && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
            gameInterface.buyDevCard(id, chosenResources, chosenWarehouses, developmentSpace);
            gameInterface.setState(State.PHASE_TWO);

            // TODO: 14/05/2021  mandare a tutti il nuove development grid - sistemare il metodo getInitalDevGrid con getDevGrideLite (vedi metodo di Game)
            server.sendAll(new PacketLiteDevelopmentGrid(gameInterface.getInitialDevGrid()), gameInterface);
        }

    }

    public int getId() {
        return id;
    }

    public ArrayList<ResourceType> getChosenResources() {
        return chosenResources;
    }

    public ArrayList<Warehouse> getChosenWarehouses() {
        return chosenWarehouses;
    }

    public DevelopmentSpace getDevelopmentSpace() {
        return developmentSpace;
    }
}
