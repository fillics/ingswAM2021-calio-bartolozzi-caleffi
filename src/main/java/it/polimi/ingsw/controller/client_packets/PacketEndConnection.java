package it.polimi.ingsw.controller.client_packets;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.gameinterfaces.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;


public class PacketEndConnection  implements ClientPacketHandler{
    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        server.handleEndGame(clientHandler, gameInterface);
        clientHandler.setEndConnection();
    }
}
