package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.messages.ExceptionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.PacketConnectionMessages;
import it.polimi.ingsw.controller.server_packets.PacketExceptionMessages;
import it.polimi.ingsw.controller.server_packets.PacketLeaderCards;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

public class PacketActivateLeaderCard implements ClientPacketHandler {
    private final int id;

    @JsonCreator
    public PacketActivateLeaderCard(@JsonProperty("Id")int id) {
        this.id = id;
    }

    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        if((gameInterface.getState().equals(GameStates.PHASE_ONE) || gameInterface.getState().equals(GameStates.PHASE_TWO))
                && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
            try {
                gameInterface.activateLeaderCard(id);
                clientHandler.sendPacketToClient(new PacketLeaderCards(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getLeaderCards()));
            } catch (LeaderCardNotFound leaderCardNotFound) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.LEADERCARDNOTFOUND));
            } catch (NotEnoughRequirements notEnoughRequirements) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.NOTENOUGHREQUIREMENTS));
            }
        }
        else {
            clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEMOVE));
        }
    }

    public int getId() {
        return id;
    }
}

