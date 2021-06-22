package it.polimi.ingsw.controller.client_packets.cheatpackets;

import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.server_packets.PacketConnectionMessages;
import it.polimi.ingsw.controller.server_packets.PacketWarehouse;
import it.polimi.ingsw.model.gameinterfaces.CheatGameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;


public class ResourcesInStrongboxCheatPacket implements CheatClientPacketHandler {


    @Override
    public void execute(Server server, CheatGameInterface gameInterface, ClientHandler clientHandler) {
        if(clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()) {

            gameInterface.cheatResourcesStrongbox(clientHandler.getUsername());
            clientHandler.sendPacketToClient(new PacketWarehouse(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getStrongbox(),
                    gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getDeposits()));

        }
        else {
            clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLE_CHEAT));
        }
    }
}
