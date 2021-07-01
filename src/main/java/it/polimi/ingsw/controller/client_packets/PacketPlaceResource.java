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
import it.polimi.ingsw.exceptions.AnotherDepositContainsThisResource;
import it.polimi.ingsw.exceptions.DepositHasAnotherResource;
import it.polimi.ingsw.exceptions.DepositHasReachedMaxLimit;
import it.polimi.ingsw.exceptions.InvalidResource;
import it.polimi.ingsw.model.gameinterfaces.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;


public class PacketPlaceResource implements ClientPacketHandler {
    private final int depositPosition;
    private final int resourcePosition;

    /**
     * Class' constructor.
     * @param depositPosition represents the deposit in which the player wants to put the resource.
     * @param resourcePosition represents the position in the resource buffer of the resource the player wants to place,
     */
    @JsonCreator
    public PacketPlaceResource(@JsonProperty("DepositPosition")int depositPosition,@JsonProperty("ResourcePosition") int resourcePosition) {
        this.depositPosition = depositPosition;
        this.resourcePosition = resourcePosition;
    }

    /**
     * Method execute() calls placeResource method from GameInterface that modifies the model.
     * It also sends packets from server to client in order to update the light model after the changes of the model.
     */
    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        if(gameInterface.getState().equals(GameStates.PHASE_ONE) || gameInterface.getState().equals(GameStates.PHASE_TWO)
                && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){

            try {
                gameInterface.placeResource(depositPosition, resourcePosition);
                clientHandler.sendPacketToClient(new PacketResourceBuffer(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getResourceBuffer()));
                clientHandler.sendPacketToClient(new PacketWarehouse(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getStrongbox(),
                        gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getDeposits()));
            } catch (DepositHasReachedMaxLimit depositHasReachedMaxLimit) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.DEPOSITHASREACHEDMAXLIMIT));
            } catch (DepositHasAnotherResource depositHasAnotherResource) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.DEPOSITHASANOTHERRSOURCE));
            } catch (AnotherDepositContainsThisResource anotherDepositContainsThisResource) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.ANOTHERDEPOSITCONTAINSTHISRESOURCE));
            } catch (InvalidResource invalidResource) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.INVALIDRESOURCE));
            }
        }
        else {
            clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEMOVE));
        }
    }

    public int getDepositPosition() {
        return depositPosition;
    }

    public int getResourcePosition() {
        return resourcePosition;
    }
}
