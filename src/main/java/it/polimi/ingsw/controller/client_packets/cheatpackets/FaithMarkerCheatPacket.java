package it.polimi.ingsw.controller.client_packets.cheatpackets;

import it.polimi.ingsw.controller.server_packets.PacketFaithTrack;
import it.polimi.ingsw.model.gameinterfaces.CheatGameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

public class FaithMarkerCheatPacket implements CheatClientPacketHandler {

    @Override
    public void execute(Server server, CheatGameInterface gameInterface, ClientHandler clientHandler) {
        gameInterface.cheatFaithMarker(clientHandler.getUsername());
        clientHandler.sendPacketToClient(new PacketFaithTrack(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getTrack(),
                gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getFaithMarker(),
                gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getVaticanReportSections()));

    }
}
