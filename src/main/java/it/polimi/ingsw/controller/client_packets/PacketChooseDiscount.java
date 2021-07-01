package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.messages.ExceptionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.PacketConnectionMessages;
import it.polimi.ingsw.controller.server_packets.PacketExceptionMessages;
import it.polimi.ingsw.exceptions.DiscountCannotBeActivated;
import it.polimi.ingsw.model.gameinterfaces.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

import java.util.ArrayList;

public class PacketChooseDiscount implements ClientPacketHandler {
    private final ArrayList<Integer> leaderCards;

    @JsonCreator
    public PacketChooseDiscount(@JsonProperty("LeaderCards")ArrayList<Integer> leaderCards) {
        this.leaderCards = leaderCards;
    }

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
