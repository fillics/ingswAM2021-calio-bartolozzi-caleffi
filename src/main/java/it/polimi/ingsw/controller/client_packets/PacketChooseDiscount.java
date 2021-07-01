package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.messages.ExceptionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.PacketConnectionMessages;
import it.polimi.ingsw.controller.server_packets.PacketExceptionMessages;
import it.polimi.ingsw.controller.server_packets.PacketLeaderCards;
import it.polimi.ingsw.exceptions.DiscountCannotBeActivated;
import it.polimi.ingsw.model.gameinterfaces.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

import java.util.ArrayList;

public class PacketChooseDiscount implements ClientPacketHandler {
    private final ArrayList<Integer> leaderCards;

    /**
     * Class' constructor.
     * @param leaderCards is an ArrayList of integer that represents the leader cards that the player wants to use in order to activate a discount.
     */
    @JsonCreator
    public PacketChooseDiscount(@JsonProperty("LeaderCards")ArrayList<Integer> leaderCards) {
        this.leaderCards = leaderCards;
    }

    /**
     * Method execute() calls chooseDiscountActivation method from GameInterface that modifies the model.
     * It also sends packets from server to client in order to update the light model after the changes of the model.
     */
    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        if(gameInterface.getState().equals(GameStates.PHASE_ONE) &&
                clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
            try {
                gameInterface.chooseDiscountActivation(leaderCards);
            } catch (DiscountCannotBeActivated discountCannotBeActivated) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.DISCOUNTCANNOTBEACTIVATED));
            }

        }
        else {
            clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEMOVE));
        }
    }

    public ArrayList<Integer> getLeaderCards() {
        return leaderCards;
    }
}
