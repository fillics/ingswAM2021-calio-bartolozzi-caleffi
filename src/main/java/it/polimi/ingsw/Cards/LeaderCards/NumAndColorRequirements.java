package it.polimi.ingsw.Cards.LeaderCards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentSpace;
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
        HashMap<CardColor, Integer> counter = new HashMap<>();
        counter.put(CardColor.GREEN, 0);
        counter.put(CardColor.BLUE, 0);
        counter.put(CardColor.PURPLE, 0);
        counter.put(CardColor.YELLOW, 0);
        for(CardColor key : counter.keySet()){
            for (DevelopmentSpace developmentSpace : board.getDevelopmentSpaces()) {
                for (DevelopmentCard developmentCard : developmentSpace.getDevelopmentCardsOfDevSpace()) {
                    if(developmentCard.getColor().equals(key)){
                        counter.replace(key, counter.get(key) + 1);
                    }
                }
            }
        }
        for(CardColor key : color.keySet()){
            if(color.get(key) > counter.get(key)){
                return false;
            }
        }
        return true;
    }


    @Override
    public String toString() {
        return "Sono numandcolor";
    }
}
