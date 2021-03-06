package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.messages.ExceptionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.PacketConnectionMessages;
import it.polimi.ingsw.controller.server_packets.PacketExceptionMessages;
import it.polimi.ingsw.controller.server_packets.PacketResourceBuffer;
import it.polimi.ingsw.controller.server_packets.PacketWarehouse;
import it.polimi.ingsw.exceptions.EmptyDeposit;
import it.polimi.ingsw.model.gameinterfaces.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;


public class PacketMoveResource implements ClientPacketHandler {
    private final int position;

    /**
     * Class' constructor.
     * @param position represents the deposit from which the player wants to take the resource in order to move it.
     */
    @JsonCreator
    public PacketMoveResource(@JsonProperty("Position")int position) {
        this.position = position;
    }

    /**
     * Method execute() calls moveResource method from GameInterface that modifies the model.
     * It also sends packets from server to client in order to update the light model after the changes of the model.
     */
    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        if((gameInterface.getState().equals(GameStates.PHASE_ONE) || gameInterface.getState().equals(GameStates.PHASE_TWO))){
            if(clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
                try {
                    gameInterface.moveResource(position);
                    clientHandler.sendPacketToClient(new PacketResourceBuffer(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getResourceBuffer()));
                    clientHandler.sendPacketToClient(new PacketWarehouse(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getStrongbox(),
                            gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getDeposits()));
                } catch (EmptyDeposit emptyDeposit) {
                    clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.EMPTYDEPOSIT));
                }
            }
          else clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.NOT_YOUR_TURN));

        }
        else clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEMOVE));

    }

    public int getPosition() {
        return position;
    }
}
