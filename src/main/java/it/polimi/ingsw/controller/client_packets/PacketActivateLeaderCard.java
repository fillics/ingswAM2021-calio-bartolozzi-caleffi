package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.controller.State;
import it.polimi.ingsw.exceptions.LeaderCardNotFound;
import it.polimi.ingsw.exceptions.NotEnoughRequirements;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.ClientHandler;


public class PacketActivateLeaderCard implements PacketHandler {
    private int ID;

    @JsonCreator
    public PacketActivateLeaderCard(@JsonProperty("ID: ")int ID) {
        this.ID = ID;
    }


    //TODO: Decidere se aggiungere l'else all'if che invia il messaggio al client che gli dà errore
    @Override
    public void execute(GameInterface gameInterface, ClientHandler clientHandler) throws LeaderCardNotFound, NotEnoughRequirements {
        if((gameInterface.getState() == State.PHASE_ONE || gameInterface.getState() == State.PHASE_TWO)
                && clientHandler.getIdClient() == gameInterface.getCurrentPlayer()){
            gameInterface.activateLeaderCard(ID);
        }
    }
}
