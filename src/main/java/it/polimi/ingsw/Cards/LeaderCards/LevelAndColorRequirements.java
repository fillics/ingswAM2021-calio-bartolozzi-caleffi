package it.polimi.ingsw.Cards.LeaderCards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.Cards.DevelopmentCards.Level;

import java.util.HashMap;

public class LevelAndColorRequirements extends Requirement{
    private Level level;
    private HashMap<CardColor,Integer> color;

    @JsonCreator
    public LevelAndColorRequirements(@JsonProperty("resourcePrice") HashMap<CardColor, Integer> color, @JsonProperty("level") Level level) {
        this.level = level;
        this.color = color;
    }

    public HashMap<CardColor, Integer> getColor() {
        return color;
    }

    public Level getLevel() {
        return level;
    }


    @Override
    public boolean check(Board board) {


        return false;
    }

}
