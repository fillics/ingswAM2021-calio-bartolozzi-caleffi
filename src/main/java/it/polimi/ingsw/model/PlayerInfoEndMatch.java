package it.polimi.ingsw.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Class used when the match is ended and we want to show all the stats of the players
 */
public class PlayerInfoEndMatch {
    private final String username;
    private final int faithMarker;
    private final int numDevCards;
    private final int numCoins;
    private final int numStones;
    private final int numShields;
    private final int numServants;
    private final int totVictory;

    @JsonCreator
    public PlayerInfoEndMatch(@JsonProperty("username") String username, @JsonProperty("faithMarker") int faithMarker,
                              @JsonProperty("numDevCards") int numDevCards,
                              @JsonProperty("numCoins") int numCoins, @JsonProperty("numStones") int numStones,
                              @JsonProperty("numShields") int numShields,
                              @JsonProperty("numServants") int numServants,
                              @JsonProperty("totVictory") int totVictory) {
        this.username = username;
        this.faithMarker = faithMarker;
        this.numDevCards = numDevCards;
        this.numCoins = numCoins;
        this.numStones = numStones;
        this.numShields = numShields;
        this.numServants = numServants;
        this.totVictory = totVictory;
    }

    public String getUsername() {
        return username;
    }

    public int getFaithMarker() {
        return faithMarker;
    }

    public int getNumDevCards() {
        return numDevCards;
    }

    public int getNumCoins() {
        return numCoins;
    }

    public int getNumStones() {
        return numStones;
    }

    public int getNumShields() {
        return numShields;
    }

    public int getNumServants() {
        return numServants;
    }

    public int getTotVictory() {
        return totVictory;
    }

}
