package it.polimi.ingsw.model.cards.developmentcards;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

/**
 * This class represents a Development Space of the Board.
 */
public class DevelopmentSpace {
    private final ArrayList<DevelopmentCard> developmentCardsOfDevSpace;
    private DevelopmentCard topCard;

    /**
     * Constructor DevelopmentSpace creates a new DevelopmentSpace instance.
     */
    public DevelopmentSpace() {
        developmentCardsOfDevSpace = new ArrayList<>();
        topCard = null;
    }

    /**
     * Method addDevelopmentCard adds the developmentCard to the developmentSpace and sets topCard to the last devCard bought
     */
    public void addDevelopmentCard(DevelopmentCard developmentCard){
        developmentCardsOfDevSpace.add(developmentCard);
        topCard = developmentCard;
    }

    /**
     * Method placeableCard verifies if the developmentCard is placeable in the development space.
     */
    public boolean isPlaceableCard(DevelopmentCard developmentCard){
        if((topCard==null)&&((developmentCard.getLevel()==Level.TWO)||(developmentCard.getLevel()==Level.THREE))){
            return false;
        } else if((topCard!=null)&&((topCard.getLevel()==Level.ONE)&&((developmentCard.getLevel()==Level.ONE)||(developmentCard.getLevel()==Level.THREE)))){
            return false;
        } else return (topCard == null) || (topCard.getLevel().compareTo(developmentCard.getLevel())) < 0;
    }

    /**
     * Method getTopCardProductionPower returns the Production Power of the last card placed in the development space.
     */
    @JsonIgnore
    public ProductionPower getTopCardProductionPower() {
        return topCard.getProductionPower();
    }

    /**
     * Method getDevelopmentSpace returns the arraylist of Development Cards of the Development Space.
     */
    public ArrayList<DevelopmentCard> getDevelopmentCardsOfDevSpace() {
        return developmentCardsOfDevSpace;
    }
}
