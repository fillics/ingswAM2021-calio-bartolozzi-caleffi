package it.polimi.ingsw.controller.client_packets.cheatpackets;

import it.polimi.ingsw.controller.server_packets.PacketFaithTrack;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.gameinterfaces.CheatGameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

public class FaithMarkerCheatPacket implements CheatClientPacketHandler {

    @Override
    public void execute(Server server, CheatGameInterface gameInterface, ClientHandler clientHandler) {
        gameInterface.cheatFaithMarker(clientHandler.getUsername());
        Board board = gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard();
        clientHandler.sendPacketToClient(new PacketFaithTrack(board.getTrack(), board.getFaithMarker(), board.getVaticanReportSections()));
    }
}
