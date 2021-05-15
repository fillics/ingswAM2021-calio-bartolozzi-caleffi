package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.exceptions.EmptyDeposit;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;


public class PacketMoveResource implements ClientPacketHandler {
    private final int position;

    @JsonCreator
    public PacketMoveResource(@JsonProperty("Position")int position) {
        this.position = position;
    }

    //TODO: Decidere se aggiungere l'else all'if che invia il messaggio al client che gli dà errore
    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) throws EmptyDeposit {
        if((gameInterface.getState() == GameStates.PHASE_ONE || gameInterface.getState() == GameStates.PHASE_TWO)
                && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
            gameInterface.moveResource(position);
        }
    }

    public int getPosition() {
        return position;
    }
}
