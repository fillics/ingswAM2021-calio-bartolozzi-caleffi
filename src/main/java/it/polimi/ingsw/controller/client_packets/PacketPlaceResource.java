package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.ExceptionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.PacketConnectionMessages;
import it.polimi.ingsw.controller.server_packets.PacketExceptionMessages;
import it.polimi.ingsw.exceptions.DepositHasAnotherResource;
import it.polimi.ingsw.exceptions.DepositHasReachedMaxLimit;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;


public class PacketPlaceResource implements ClientPacketHandler {
    private final int depositPosition;
    private final int resourcePosition;

    @JsonCreator
    public PacketPlaceResource(@JsonProperty("DepositPosition")int depositPosition,@JsonProperty("ResourcePosition") int resourcePosition) {
        this.depositPosition = depositPosition;
        this.resourcePosition = resourcePosition;
    }

    //TODO: Decidere se aggiungere l'else all'if che invia il messaggio al client che gli dà errore
    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        if(gameInterface.getState().equals(GameStates.PHASE_ONE) || gameInterface.getState().equals(GameStates.PHASE_TWO)
                && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
            try {
                gameInterface.placeResource(depositPosition, resourcePosition);
            } catch (DepositHasReachedMaxLimit depositHasReachedMaxLimit) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.DEPOSITHASREACHEDMAXLIMIT));
            } catch (DepositHasAnotherResource depositHasAnotherResource) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.DEPOSITHASANOTHERRSOURCE));
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
