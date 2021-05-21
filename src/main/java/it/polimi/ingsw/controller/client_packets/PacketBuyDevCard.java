package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.messages.ExceptionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.*;
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
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        if(gameInterface.getState().equals(GameStates.PHASE_ONE) && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
            try {
                gameInterface.buyDevCard(id, chosenResources, chosenWarehouses, developmentSpace);
                System.out.println(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getDevelopmentSpaces());
                System.out.println(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getStrongbox());
                System.out.println(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getDeposits());

                clientHandler.sendPacketToClient(new PacketDevelopmentSpaces(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getDevelopmentSpaces()));
                clientHandler.sendPacketToClient(new PacketWarehouse(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getStrongbox(),
                        gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getDeposits()));
                server.sendAll(new PacketLiteDevelopmentGrid(gameInterface.getDevGridLite()), gameInterface);
                gameInterface.setState(GameStates.PHASE_TWO);
            } catch (DevelopmentCardNotFound developmentCardNotFound) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.DEVELOPMENTCARDNOTFOURND));
            } catch (DevCardNotPlaceable devCardNotPlaceable) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.DEVCARDNOTPLACEABLE));
            } catch (NotEnoughResources notEnoughResources) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.NOTENOUGHRESOURCES));
            } catch (WrongChosenResources wrongChosenResources) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.WRONGCHOSENRESOURCES));
            } catch (DifferentDimension differentDimension) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.DIFFERENTDIMENSION));
            } catch (EmptyDeposit emptyDeposit) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.EMPTYDEPOSIT));
            } catch (DepositDoesntHaveThisResource depositDoesntHaveThisResource) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.DEPOSITDOESNTHAVETHISRESOURCE));
            }
        }
        else {
            clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEMOVE));
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
