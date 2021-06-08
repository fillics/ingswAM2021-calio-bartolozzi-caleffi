package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.messages.ExceptionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.PacketConnectionMessages;
import it.polimi.ingsw.controller.server_packets.PacketExceptionMessages;
import it.polimi.ingsw.controller.server_packets.PacketFaithTrack;
import it.polimi.ingsw.controller.server_packets.PacketLeaderCards;
import it.polimi.ingsw.exceptions.LeaderCardIsActive;
import it.polimi.ingsw.exceptions.LeaderCardNotFound;
import it.polimi.ingsw.model.gameinterfaces.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;


public class PacketDiscardLeaderCard implements ClientPacketHandler {
    private final int ID;

    @JsonCreator
    public PacketDiscardLeaderCard(@JsonProperty("id")int ID) {
        this.ID = ID;
    }

    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        if((gameInterface.getState().equals(GameStates.PHASE_ONE) || gameInterface.getState().equals(GameStates.PHASE_TWO))
                && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
            try {
                gameInterface.discardLeaderCard(ID);
                clientHandler.sendPacketToClient(new PacketLeaderCards(gameInterface.getUsernameClientActivePlayers().get(clientHandler.getUsername()).getLeaderCards()));
                clientHandler.sendPacketToClient(new PacketFaithTrack(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getTrack(), gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getFaithMarker(),
                        gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getVaticanReportSections()));
            } catch (LeaderCardNotFound leaderCardNotFound) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.LEADERCARDNOTFOUND));
            }  catch (LeaderCardIsActive leaderCardIsActive){
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.LEADERCARDISACTIVE));
            }
        }
        else {
            clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEMOVE));
        }
    }

    public int getID() {
        return ID;
    }
}
