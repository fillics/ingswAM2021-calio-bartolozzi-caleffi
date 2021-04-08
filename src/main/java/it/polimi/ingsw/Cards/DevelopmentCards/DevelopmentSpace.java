package it.polimi.ingsw.Cards.DevelopmentCards;

import java.util.ArrayList;

/**
 * This class represents a Development Space of the Board.
 */
public class DevelopmentSpace {
    private ArrayList<DevelopmentCard> developmentSpace;
    private DevelopmentCard topCard;

    /**
     * Constructor DevelopmentSpace creates a new DevelopmentSpace instance.
     */
    public DevelopmentSpace() {
        developmentSpace= new ArrayList<>();
        //topCard = null;
    }

    /**
     * Method addDevelopmentCard adds the developmentCard to the developmentSpace and sets topCard to the last devCard bought
     */
    public void addDevelopmentCard(DevelopmentCard developmentCard){
        developmentSpace.add(developmentCard);
        topCard= developmentCard;
    }

    /**
     * Method placeableCard verifies if the developmentCard is placeable in the development space.
     */
    public boolean isPlaceableCard(DevelopmentCard developmentCard){
        if((topCard==null)&&((developmentCard.getLevel()==Level.TWO)||(developmentCard.getLevel()==Level.THREE))){
            return false;
        } else if((topCard.getLevel()==Level.ONE)&&((developmentCard.getLevel()==Level.ONE)||(developmentCard.getLevel()==Level.THREE))){
            return false;
        }else if(topCard.getLevel().compareTo(developmentCard.getLevel())>=0){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Method getTopCardProductionPower returns the Production Power of the last card placed in the development space.
     */
    public ProductionPower getTopCardProductionPower() {
        return topCard.getProductionPower();
    }

    public ArrayList<DevelopmentCard> getDevelopmentSpace() {
        return developmentSpace;
    }
}
