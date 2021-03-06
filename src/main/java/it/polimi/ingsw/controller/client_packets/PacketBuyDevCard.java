package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.messages.ExceptionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.*;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.gameinterfaces.GameInterface;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

import java.util.ArrayList;

/**
 * PacketBuyDevCard contains the development card that the player wants to buy
 */
public class PacketBuyDevCard implements ClientPacketHandler {
    private final int id;
    private final ArrayList<ResourceType> chosenResources;
    private final ArrayList<Integer> chosenWarehouses;
    private final int developmentSpace;

    /**
     * Class' constructor.
     * @param id is the id of the development card that the player wants to buy.
     * @param chosenResources is an ArrayList of ResourceType that represents the resources the player has chosen to use to buy the card.
     * @param chosenWarehouses is an ArrayList of Integer that represents the warehouses where to take the resources the player has chosen.
     * @param developmentSpace represents the development space where the player has chosen to place the new card.
     */
    @JsonCreator
    public PacketBuyDevCard(@JsonProperty("Id")int id,@JsonProperty("ChosenResources") ArrayList<ResourceType> chosenResources,
                            @JsonProperty("ChosenWarehouses ") ArrayList<Integer> chosenWarehouses,
                            @JsonProperty("DevelopmentSpace") int developmentSpace) {
        this.id = id;
        this.chosenResources = chosenResources;
        this.chosenWarehouses = chosenWarehouses;
        this.developmentSpace = developmentSpace;
    }

    /**
     * Method execute() calls buyDevCard method from GameInterface that modifies the model.
     * It also sends packets from server to client in order to update the light model after the changes of the model.
     */
    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        if(gameInterface.getState().equals(GameStates.PHASE_ONE)){

            if(clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
                try {
                    ArrayList<Warehouse> realChosenWarehouses = new ArrayList<>();
                    for(int i : chosenWarehouses){
                        if(i <= gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getDeposits().size()){
                            realChosenWarehouses.add(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getDeposits().get(i - 1));
                        }
                        if(i == gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getDeposits().size() + 1){
                            realChosenWarehouses.add(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getStrongbox());
                        }
                    }

                    gameInterface.buyDevCard(id, chosenResources, realChosenWarehouses, gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getDevelopmentSpaces().get(developmentSpace - 1));

                    Board board = gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard();
                    clientHandler.sendPacketToClient(new PacketDevelopmentSpaces(board.getDevelopmentSpaces()));
                    clientHandler.sendPacketToClient(new PacketWarehouse(board.getStrongbox(),board.getDeposits()));
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

            else clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.NOT_YOUR_TURN));

        }
        else clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEMOVE));


    }

    public int getId() {
        return id;
    }

    public ArrayList<ResourceType> getChosenResources() {
        return chosenResources;
    }

    public ArrayList<Integer> getChosenWarehouses() {
        return chosenWarehouses;
    }

    public int getDevelopmentSpace() {
        return developmentSpace;
    }
}
