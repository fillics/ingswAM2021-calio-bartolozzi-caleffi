package it.polimi.ingsw.controller.packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.controller.State;
import it.polimi.ingsw.exceptions.NumMaxPlayersReached;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.SocketConnection;

public class PacketUsername implements PacketHandler {
    private String username;
    @JsonCreator
    public PacketUsername(@JsonProperty("username")String username) {
        this.username = username;
    }

    //TODO: Spostare la parte di if nel server
    @Override
    public void execute(GameInterface gameInterface, SocketConnection socketConnection) throws NumMaxPlayersReached {
        if(gameInterface.getState() == State.FILL_LOBBY){
            gameInterface.createNewPlayer(username);
            System.out.println(socketConnection.getIdClient());
            if(gameInterface.getActivePlayers().size() == gameInterface.getNumof_players() ){
                gameInterface.setState(State.SETUP);
            }
        }

    }

    public String getUsername() {
        return username;
    }
}
