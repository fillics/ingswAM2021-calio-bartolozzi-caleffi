package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.State;
import it.polimi.ingsw.controller.server_packets.PacketLeaderCards;
import it.polimi.ingsw.exceptions.LeaderCardNotFound;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;


public class PacketDiscardLeaderCard implements ClientPacketHandler {
    private final int ID;

    @JsonCreator
    public PacketDiscardLeaderCard(@JsonProperty("id")int ID) {
        this.ID = ID;
    }

    //TODO: Decidere se aggiungere l'else all'if che invia il messaggio al client che gli dà errore
    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) throws LeaderCardNotFound {
        if((gameInterface.getState() == State.PHASE_ONE || gameInterface.getState() == State.PHASE_TWO)
                && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
            gameInterface.discardLeaderCard(ID);
            clientHandler.sendPacketToClient(new PacketLeaderCards(gameInterface.getIdClientActivePlayers().get(clientHandler.getIdClient()).getLeaderCards()));
        }
    }

    public int getID() {
        return ID;
    }
}
