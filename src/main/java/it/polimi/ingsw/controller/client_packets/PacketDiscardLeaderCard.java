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
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.gameinterfaces.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;


/**
 * PacketDiscardLeaderCard contains the card that the player wants to discard
 */
public class PacketDiscardLeaderCard implements ClientPacketHandler {
    private final int ID;

    /**
     * Class' constructor.
     * @param ID is the id of the leader card that the player wants to discard.
     */
    @JsonCreator
    public PacketDiscardLeaderCard(@JsonProperty("id")int ID) {
        this.ID = ID;
    }

    /**
     * Method execute() calls discardLeaderCard method from GameInterface that modifies the model.
     * It also sends packets from server to client in order to update the light model after the changes of the model.
     */
    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        if((gameInterface.getState().equals(GameStates.PHASE_ONE) || gameInterface.getState().equals(GameStates.PHASE_TWO))
                && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
            try {
                gameInterface.discardLeaderCard(ID);
                Board board = gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard();

                clientHandler.sendPacketToClient(new PacketLeaderCards(gameInterface.getUsernameClientActivePlayers().get(clientHandler.getUsername()).getLeaderCards()));
                clientHandler.sendPacketToClient(new PacketFaithTrack(board.getTrack(), board.getFaithMarker(), board.getVaticanReportSections()));
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
