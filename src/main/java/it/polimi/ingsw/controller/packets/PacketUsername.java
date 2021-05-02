package it.polimi.ingsw.controller.packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.exceptions.NumMaxPlayersReached;
import it.polimi.ingsw.model.GameInterface;

public class PacketUsername implements HandlePacket {
    private String username;
    @JsonCreator
    public PacketUsername(@JsonProperty("username")String username) {
        this.username = username;
    }

    @Override
    public void execute(GameInterface gameInterface) throws NumMaxPlayersReached {
        gameInterface.createNewPlayer(username);
    }

    public String getUsername() {
        return username;
    }
}
