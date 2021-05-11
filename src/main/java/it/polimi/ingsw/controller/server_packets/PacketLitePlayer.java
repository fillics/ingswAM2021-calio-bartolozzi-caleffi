package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PacketLitePlayer implements ServerPacketHandler{

    private String username;
    private int idPlayer;
    private int totalVictoryPoint;
    //TODO: chosenResource (?)
    //private int chosenResource;

    @JsonCreator
    public PacketLitePlayer(@JsonProperty("username :")String username,@JsonProperty("idPlayer :") int idPlayer,@JsonProperty("total victory points :") int totalVictoryPoint, @JsonProperty("chosen resource :") int chosenResource) {
        this.username = username;
        this.idPlayer = idPlayer;
        this.totalVictoryPoint = totalVictoryPoint;
        //this.chosenResource = chosenResource;
    }
}
