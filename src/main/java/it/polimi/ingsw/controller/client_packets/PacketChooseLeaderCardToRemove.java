package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.State;
import it.polimi.ingsw.controller.server_packets.PacketLeaderCards;
import it.polimi.ingsw.exceptions.LeaderCardNotFound;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;


public class PacketChooseLeaderCardToRemove implements ClientPacketHandler {
    private final int Id1;
    private final int Id2;

    @JsonCreator
    public PacketChooseLeaderCardToRemove(@JsonProperty("id1")int Id1,@JsonProperty("id2") int Id2) {
        this.Id1 = Id1;
        this.Id2 = Id2;
    }

    //TODO: Decidere se aggiungere l'else all'if che invia il messaggio al client che gli dà errore
    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) throws LeaderCardNotFound {

        if(gameInterface.getState() == State.SETUP){
            gameInterface.chooseLeaderCardToRemove(Id1, Id2);
            clientHandler.sendPacketToClient(new PacketLeaderCards(gameInterface.getIdClientActivePlayers().get(clientHandler.getIdClient()).getLeaderCards()));
        }
    }

    public int getId1() {
        return Id1;
    }

    public int getId2() {
        return Id2;
    }
}
