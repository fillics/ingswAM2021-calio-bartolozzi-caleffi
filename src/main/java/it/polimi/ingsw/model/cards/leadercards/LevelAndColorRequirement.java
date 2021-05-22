package it.polimi.ingsw.model.cards.leadercards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Color;
import it.polimi.ingsw.client.Printable;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.cards.developmentcards.CardColor;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentCard;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentSpace;
import it.polimi.ingsw.model.cards.developmentcards.Level;

import java.util.HashMap;


/**
 *  This class represents a specific type of requirement where are needed a certain amount of development card of a certain
 *  color of a certain level in order to activate a Leader Card.
 */
public class LevelAndColorRequirement extends Requirement{
    private final Level level;
    private final HashMap<CardColor,Integer> color;

    /**
     * Constructor LevelAndColorRequirement creates a new LevelAndColorRequirement instance.
     * @param color is the map that represents the amount of development cards of a certain color needed.
     * @param level is the level of the cards needed.
     */
    @JsonCreator
    public LevelAndColorRequirement(@JsonProperty("color") HashMap<CardColor, Integer> color, @JsonProperty("level") Level level) {
        this.level = level;
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
     * get-method
     * @return tha integer that contains the level of the cards needed.
     */
    public Level getLevel() {
        return level;
    }


    /**
     * Override method that counts if the number of development cards contained in the board of a certain level and color
     * is enough to fulfill the requirement.
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
                    if(developmentCard.getColor().equals(key) && developmentCard.getLevel() == level){
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
        if(this.color.containsKey(CardColor.BLUE))
            escape= color.get(CardColor.BLUE) + " " + Color.BACKGROUND_BLUE.escape() + this.printLevel() + Color.RESET;
        if(color.containsKey(CardColor.GREEN))
            escape = escape + color.get(CardColor.GREEN) + " " + Color.BACKGROUND_GREEN.escape() + this.printLevel() + Color.RESET;
        if(color.containsKey(CardColor.PURPLE))
            escape = escape + color.get(CardColor.PURPLE) + " " + Color.BACKGROUND_PURPLE.escape() + this.printLevel() + Color.RESET;
        if(color.containsKey(CardColor.YELLOW))
            escape = escape + color.get(CardColor.YELLOW)+ " " + Color.BACKGROUND_YELLOW.escape() + this.printLevel() + Color.RESET;
        escape = escape + "   ";
        return escape;
    }

    public String printLevel(){
        String level = null;
        if(this.level.equals(Level.ONE))
            level = Printable.ONE_POINT.print();
        else if(this.level.equals(Level.TWO))
            level= Printable.TWO_POINTS.print();
        else if (this.level.equals(Level.THREE))
            level= Printable.THREE_POINTS.print();
        return level;
    }
}
