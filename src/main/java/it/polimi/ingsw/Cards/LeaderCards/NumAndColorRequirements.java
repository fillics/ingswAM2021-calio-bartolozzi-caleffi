package it.polimi.ingsw.Cards.LeaderCards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.Cards.DevelopmentCards.Level;

import java.util.HashMap;

public class NumAndColorRequirements extends Requirement{
    private HashMap<CardColor,Integer> color;

    @JsonCreator
    public NumAndColorRequirements(@JsonProperty("color") HashMap<CardColor, Integer> color) {
        this.color = color;
    }

    public HashMap<CardColor, Integer> getColor() {
        return color;
    }


    @Override
    public boolean check(Board board) {
        return false;
    }


    @Override
    public String toString() {
        return "Sono numandcolor";
    }
}
