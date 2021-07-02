package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.messages.ExceptionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.*;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;
import it.polimi.ingsw.model.gameinterfaces.GameInterface;
import it.polimi.ingsw.model.cards.leadercards.ConcreteStrategyProductionPower;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

import java.util.ArrayList;

/**
 * PacketActivateLeaderCard contains the leader card that the player wants to activate
 */
public class PacketActivateLeaderCard implements ClientPacketHandler {
    private final int id;

    /**
     * Class' constructor.
     * @param id is the id of the leader card that the player wants to activate.
     */
    @JsonCreator
    public PacketActivateLeaderCard(@JsonProperty("Id")int id) {
        this.id = id;
    }

    /**
     * Method execute() calls activateLeaderCard method from GameInterface that modifies the model.
     * It also sends packets from server to client in order to update the light model after the changes of the model.
     */
    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        if((gameInterface.getState().equals(GameStates.PHASE_ONE) || gameInterface.getState().equals(GameStates.PHASE_TWO))){

            if(clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
                try {
                    gameInterface.activateLeaderCard(id);

                    Board board = gameInterface.getUsernameClientActivePlayers().get(clientHandler.getUsername()).getBoard();
                    clientHandler.sendPacketToClient(new PacketWarehouse(board.getStrongbox(),board.getDeposits()));
                    clientHandler.sendPacketToClient(new PacketDevelopmentSpaces(board.getDevelopmentSpaces()));
                    clientHandler.sendPacketToClient(new PacketLeaderCards(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getLeaderCards()));

                    ArrayList<LeaderCard> leaderCards = gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getLeaderCards();
                    for (LeaderCard leaderCard : leaderCards) {
                        if (leaderCard.getId() == id && leaderCard.getStrategy() instanceof ConcreteStrategyProductionPower) {
                            clientHandler.sendPacketToClient(new PacketSpecialProdPowers(board.getSpecialProductionPowers()));
                        }
                    }
                } catch (LeaderCardNotFound leaderCardNotFound) {
                    clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.LEADERCARDNOTFOUND));
                } catch (NotEnoughRequirements notEnoughRequirements) {
                    clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.NOTENOUGHREQUIREMENTS));
                }
            }
            else clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.NOT_YOUR_TURN));
        }
        else clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEMOVE));

    }

    public int getId() {
        return id;
    }
}

