package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.controller.State;
import it.polimi.ingsw.exceptions.DepositHasAnotherResource;
import it.polimi.ingsw.exceptions.DepositHasReachedMaxLimit;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.ClientHandler;


public class PacketPlaceResource implements PacketHandler {
    private int depositPosition;
    private int resourcePosition;

    @JsonCreator
    public PacketPlaceResource(@JsonProperty("deposit position: ")int depositPosition,@JsonProperty("resource position: ") int resourcePosition) {
        this.depositPosition = depositPosition;
        this.resourcePosition = resourcePosition;
    }

    //TODO: Decidere se aggiungere l'else all'if che invia il messaggio al client che gli dà errore
    @Override
    public void execute(GameInterface gameInterface, ClientHandler clientHandler) throws DepositHasReachedMaxLimit, DepositHasAnotherResource {
        if((gameInterface.getState() == State.PHASE_ONE || gameInterface.getState() == State.PHASE_TWO)
                && clientHandler.getIdClient() == gameInterface.getCurrentPlayer()){
            gameInterface.placeResource(depositPosition, resourcePosition);
        }
    }
}
