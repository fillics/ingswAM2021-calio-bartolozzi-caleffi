package it.polimi.ingsw.model.cards.leadercards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Color;
import it.polimi.ingsw.client.Printable;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.cards.developmentcards.CardColor;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentCard;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentSpace;

import java.util.HashMap;



/**
 *  This class represents a specific type of requirement where are needed a certain amount of development card of a certain
 *  color in order to activate a Leader Card.
 */
public class NumAndColorRequirement extends Requirement{
    private final HashMap<CardColor,Integer> color;

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

    @Override
    public String toString() {
        String escape= "";
        if(color.containsKey(CardColor.BLUE))
            escape= color.get(CardColor.BLUE)  + Color.ANSI_BLUE.escape() + Printable.BLOCK.print() + Color.RESET + " ";
        if(color.containsKey(CardColor.GREEN))
            escape = escape + color.get(CardColor.GREEN)  + Color.ANSI_GREEN.escape() + Printable.BLOCK.print() + Color.RESET+ " ";
        if(color.containsKey(CardColor.PURPLE))
            escape = escape + color.get(CardColor.PURPLE) + Color.ANSI_PURPLE.escape() + Printable.BLOCK.print() + Color.RESET+ " ";
        if(color.containsKey(CardColor.YELLOW))
            escape = escape + color.get(CardColor.YELLOW) + Color.ANSI_YELLOW.escape() + Printable.BLOCK.print() + Color.RESET+ " ";

        return escape;
    }

}
