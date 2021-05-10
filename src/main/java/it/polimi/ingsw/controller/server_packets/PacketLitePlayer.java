package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.LiteBoard;
import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;

import java.util.ArrayList;

public class PacketLitePlayer implements ServerPacketHandler{

    private String username;
    private int idPlayer;
    private int totalVictoryPoint;
    private ArrayList<LeaderCard> leaderCards;
    private ArrayList<Resource> resourceBuffer;
    private LiteBoard board;
    private int chosenResource;
    private ArrayList<Integer> whiteMarbleCardChoice;

    @JsonCreator
    public PacketLitePlayer(@JsonProperty("username :")String username,@JsonProperty("idPlayer :") int idPlayer,@JsonProperty("total victory points :") int totalVictoryPoint,@JsonProperty("leader cards :") ArrayList<LeaderCard> leaderCards,@JsonProperty("resource buffer :") ArrayList<Resource> resourceBuffer,@JsonProperty("board :") LiteBoard board,@JsonProperty("chosen resource :") int chosenResource,@JsonProperty("white marble leader card's id :") ArrayList<Integer> whiteMarbleCardChoice) {
        this.username = username;
        this.idPlayer = idPlayer;
        this.totalVictoryPoint = totalVictoryPoint;
        this.leaderCards = leaderCards;
        this.resourceBuffer = resourceBuffer;
        this.board = board;
        this.chosenResource = chosenResource;
        this.whiteMarbleCardChoice = whiteMarbleCardChoice;
    }
}
