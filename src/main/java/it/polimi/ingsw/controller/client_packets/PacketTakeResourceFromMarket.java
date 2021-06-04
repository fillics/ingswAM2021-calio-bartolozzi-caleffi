package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.messages.ExceptionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.*;
import it.polimi.ingsw.exceptions.LeaderCardNotActivated;
import it.polimi.ingsw.exceptions.LeaderCardNotFound;
import it.polimi.ingsw.model.gameinterfaces.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

import java.util.ArrayList;

public class PacketTakeResourceFromMarket implements ClientPacketHandler {
    private final String line;
    private final int numline;
    private final ArrayList<Integer> leaderCardsID;

    @JsonCreator
    public PacketTakeResourceFromMarket(@JsonProperty("Line")String line,@JsonProperty("NumLine") int numline,@JsonProperty("LeaderCardsID") ArrayList<Integer> leaderCardsID) {
        this.line = line;
        this.numline = numline;
        this.leaderCardsID = leaderCardsID;
    }


    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        if(gameInterface.getState().equals(GameStates.PHASE_ONE) && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
            try {
                gameInterface.takeResourceFromMarket(line, numline, leaderCardsID);
                clientHandler.sendPacketToClient(new PacketResourceBuffer(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getResourceBuffer()));
                clientHandler.sendPacketToClient(new PacketFaithTrack(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getTrack(), gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getFaithMarker(), gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getVaticanReportSections()));
                server.sendAll(new PacketLiteMarketTray(gameInterface.getTable(), gameInterface.getRemainingMarble()), gameInterface);
                gameInterface.setState(GameStates.PHASE_TWO);
            } catch (LeaderCardNotFound leaderCardNotFound) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.LEADERCARDNOTFOUND));
            } catch (LeaderCardNotActivated leaderCardNotActivated) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.LEADERCARDNOTACTIVATED));
            }

           }
        else {
            clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEMOVE));
        }
    }

    public String getLine() {
        return line;
    }

    public int getNumline() {
        return numline;
    }

    public ArrayList<Integer> getLeaderCardsID() {
        return leaderCardsID;
    }
}
