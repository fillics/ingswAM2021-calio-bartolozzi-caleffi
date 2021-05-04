package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.controller.State;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.SocketConnection;

public class PacketNumPlayers implements PacketHandler {
    private int numof_players;

    @JsonCreator
    public PacketNumPlayers(@JsonProperty("number of players: ")int numof_players) {
        this.numof_players = numof_players;
    }

    @Override
    public void execute(GameInterface gameInterface, SocketConnection socketConnection) {
        if(gameInterface.getState() == State.NUMOF_PLAYERS){

            if(numof_players >= Constants.getNumMinPlayers() && numof_players <= Constants.getNumMaxPlayers()){
                gameInterface.setNumof_players(numof_players);
                gameInterface.setState(State.FILL_LOBBY);
            }
        }
    }

    public int getNumof_players() { return numof_players; }
}
