package it.polimi.ingsw.controller.client_packets;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.gameinterfaces.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;


/**
 * Packet sent from the client that wants to the end the connection with the server
 */
public class PacketEndConnection implements ClientPacketHandler{
    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        server.handleEndGame(clientHandler, gameInterface);
        clientHandler.setEndConnection();
    }
}
