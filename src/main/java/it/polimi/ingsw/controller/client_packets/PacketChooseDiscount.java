package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.ExceptionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.PacketConnectionMessages;
import it.polimi.ingsw.controller.server_packets.PacketExceptionMessages;
import it.polimi.ingsw.controller.server_packets.PacketLeaderCards;
import it.polimi.ingsw.exceptions.DiscountCannotBeActivated;
import it.polimi.ingsw.model.GameInterface;
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
        if(gameInterface.getState().equals(GameStates.PHASE_ONE) && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
            try {
                gameInterface.chooseDiscountActivation(leaderCards);
                clientHandler.sendPacketToClient(new PacketLeaderCards(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getLeaderCards()));
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
