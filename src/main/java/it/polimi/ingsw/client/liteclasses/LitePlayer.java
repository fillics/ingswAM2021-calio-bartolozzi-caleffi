package it.polimi.ingsw.client.liteclasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.cards.leadercards.*;

import java.util.ArrayList;

/**
 * LitePlayer class contains a light representation of Player class of the model.
 * It contains the only attributes that have to be updated in order to be printed in the view interface.
 */

public class LitePlayer {
    private String username;
    private int idClient;
    private int totalVictoryPoint;
    private ArrayList<LeaderCard> leaderCards;
    private ArrayList<Resource> resourceBuffer;
    private final LiteBoard board;
    private int posInGame;
    //private int chosenResource;
    private ArrayList<Integer> whiteMarbleCardChoice;

    @JsonCreator
    public LitePlayer(@JsonProperty("username") String username,@JsonProperty("id") int idPlayer,@JsonProperty("posInGame") int posInGame,
                      @JsonProperty("totVictoryPoint")int totalVictoryPoint, @JsonProperty("leadCards")ArrayList<LeaderCard> leaderCards,
                      @JsonProperty("resourceBuffer") ArrayList<Resource> resourceBuffer, @JsonProperty("board")LiteBoard board,
                      @JsonProperty("whiteMarble")ArrayList<Integer> whiteMarbleCardChoice) {
        this.username = username;
        this.idClient = idPlayer;
        this.posInGame = posInGame;
        this.board = board;
        this.totalVictoryPoint = totalVictoryPoint;
        this.leaderCards = leaderCards;
        this.resourceBuffer = resourceBuffer;
        this.whiteMarbleCardChoice = whiteMarbleCardChoice;
    }

    @JsonCreator
    public LitePlayer( ) {
        username = null;
        idClient = 0;
        totalVictoryPoint = 0;
        leaderCards = new ArrayList<>();
        resourceBuffer = new ArrayList<>();
        board = new LiteBoard();
        posInGame = -1;
        whiteMarbleCardChoice = new ArrayList<>();
    }

    public int getPosInGame() {
        return posInGame;
    }

    public void setPosInGame(int posInGame) {
        this.posInGame = posInGame;
    }


    public String getUsername() {
        return username;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setTotalVictoryPoint(int totalVictoryPoint) {
        this.totalVictoryPoint = totalVictoryPoint;
    }

    public void setLeaderCards(ArrayList<LeaderCard> leaderCards) {
        this.leaderCards = leaderCards;
    }

    public void setResourceBuffer(ArrayList<Resource> resourceBuffer) {
        this.resourceBuffer = resourceBuffer;
    }

    public void setWhiteMarbleCardChoice(ArrayList<Integer> whiteMarbleCardChoice) {
        this.whiteMarbleCardChoice = whiteMarbleCardChoice;
    }

    public ArrayList<Resource> getResourceBuffer() {
        return resourceBuffer;
    }

    public ArrayList<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
