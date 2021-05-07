package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.controller.State;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.ClientHandler;


public class PacketUsername implements PacketHandler {
    private String username;
    @JsonCreator
    public PacketUsername(@JsonProperty("username")String username) {
        this.username = username;
    }

    //TODO: Decidere se aggiungere l'else all'if che invia il messaggio al client che gli dà errore
    @Override
    public void execute(GameInterface gameInterface, ClientHandler clientHandler) {
        if(gameInterface.getState() == State.FILL_LOBBY){
            gameInterface.createNewPlayer(username);
            if(gameInterface.getNumof_players() == gameInterface.getActivePlayers().size()){
                gameInterface.setState(State.NUMOF_PLAYERS);
            }
        }
    }

    public String getUsername() {
        return username;
    }
}
