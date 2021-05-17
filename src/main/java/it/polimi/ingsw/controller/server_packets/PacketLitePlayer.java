package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;

public class PacketLitePlayer implements ServerPacketHandler{

    private final String username;
    private final int idPlayer;
    private final int totalVictoryPoint;
    //TODO: chosenResource (?)
    //private int chosenResource;

    @JsonCreator
    public PacketLitePlayer(@JsonProperty("username :")String username,@JsonProperty("idPlayer :") int idPlayer,@JsonProperty("total victory points :") int totalVictoryPoint, @JsonProperty("chosen resource :") int chosenResource) {
        this.username = username;
        this.idPlayer = idPlayer;
        this.totalVictoryPoint = totalVictoryPoint;
        //this.chosenResource = chosenResource;
    }

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
