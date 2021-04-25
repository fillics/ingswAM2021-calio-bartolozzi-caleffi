package it.polimi.ingsw.Cards.LeaderCards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentSpace;

import java.util.HashMap;



/**
 *  This class represents a specific type of requirement where are needed a certain amount of development card of a certain
 *  color in order to activate a Leader Card.
 */
public class NumAndColorRequirement extends Requirement{
    private HashMap<CardColor,Integer> color;

    /**
     * Constructor NumAndColorRequirement creates a new NumAndColorRequirement instance.
     * @param color is the map that represents the number of development card for each color needed.
     */
    @JsonCreator
    public NumAndColorRequirement(@JsonProperty("color") HashMap<CardColor, Integer> color) {
        this.color = color;
    }

    /**
     * get-method
     * @return tha hashmap that contains the number of development card for each color needed.
     */
    public HashMap<CardColor, Integer> getColor() {
        return color;
    }

    /**
     * Override method that counts if the number of development cards contained in the board is enough to fulfill the requirement
     * @param board is the board of the player where the check will be done .
     * @return true if the requirement is fulfilled, false if not.
     */
    @Override
    public boolean checkRequirement(Board board) {
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


}
