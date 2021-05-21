package it.polimi.ingsw.model.cards.developmentcards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.polimi.ingsw.constants.Color;
import it.polimi.ingsw.constants.Printable;

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

    public DevelopmentCard getTopCard() {
        return topCard;
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


    @Override
    public String toString(){
        StringBuilder escape = new StringBuilder();
        int size = developmentCardsOfDevSpace.size();

        if(size!=0){
            escape.append(topCard).append("\n");
            if(size>1){
                for(int i=size-2;i>=0;i--){
                    escape.append(Printable.DOUBLE_LINE.print());
                    escape.append(developmentCardsOfDevSpace.get(i).printColor());
                    escape.append("   ");
                    escape.append(developmentCardsOfDevSpace.get(i).getVictorypoint()).append(Color.ANSI_YELLOW.escape()).append("VP").append(Color.RESET);
                    if(developmentCardsOfDevSpace.get(i).getLevel()==Level.THREE)
                        escape.append(" ".repeat(3));
                    else if((developmentCardsOfDevSpace.get(i).getLevel()==Level.ONE || developmentCardsOfDevSpace.get(i).getLevel()==Level.TWO) && developmentCardsOfDevSpace.get(i).getVictorypoint()>9)
                        escape.append(" ".repeat(2));
                    else if ((developmentCardsOfDevSpace.get(i).getLevel()==Level.ONE || developmentCardsOfDevSpace.get(i).getLevel()==Level.TWO) && developmentCardsOfDevSpace.get(i).getVictorypoint()<10)
                        escape.append(" ".repeat(3));
                    escape.append(developmentCardsOfDevSpace.get(i).printColor()).append(Printable.DOUBLE_LINE.print()).append("\n");
                    escape.append(Printable.SUD_OVEST.print()).append(String.valueOf(Printable.MIDDLE.print()).repeat(11)).append(Printable.SUD_EST.print());
                    escape.append("\n");
                }
            }
        }

        return escape.toString();
    }

    public void dump(){
        System.out.println(this);
    }
}
