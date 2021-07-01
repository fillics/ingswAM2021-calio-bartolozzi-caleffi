package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;

public class PacketLitePlayer implements ServerPacketHandler{

    private final String username;
    private final int idPlayer;
    private final int totalVictoryPoint;

    /**
     * Class' constructor.
     * @param username is the username of the player.
     * @param idPlayer is the Id of the player in the game.
     * @param totalVictoryPoint is the value of the victory points of the player.
     */
    @JsonCreator
    public PacketLitePlayer(@JsonProperty("username :")String username,@JsonProperty("idPlayer :") int idPlayer,@JsonProperty("total victory points :") int totalVictoryPoint, @JsonProperty("chosen resource :") int chosenResource) {
        this.username = username;
        this.idPlayer = idPlayer;
        this.totalVictoryPoint = totalVictoryPoint;
    }

    /**
     * Method execute() updates the username, id client and total victory points values in LitePlayer class.
     */
    @Override
    public void execute(Client client) {
        client.getClientModelView().getMyPlayer().setIdClient(idPlayer);
        client.getClientModelView().getMyPlayer().setTotalVictoryPoint(totalVictoryPoint);
        client.getClientModelView().getMyPlayer().setUsername(username);
    }

    public String getUsername() {
        return username;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public int getTotalVictoryPoint() {
        return totalVictoryPoint;
    }
}
