package it.polimi.ingsw.client.liteclasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentCard;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * LiteDevelopmentGrid class is a light representation of developmentGrid attribute of the model.
 */

public class LiteDevelopmentGrid {

    private ArrayList<DevelopmentCard> developmentCards;

    @JsonCreator
    public LiteDevelopmentGrid(@JsonProperty("devCards")ArrayList<DevelopmentCard> developmentCards) {
        this.developmentCards = developmentCards;
    }

    @JsonCreator
    public LiteDevelopmentGrid() {
        developmentCards=new ArrayList<>();
    }

    public ArrayList<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }

    public void setDevelopmentCards(ArrayList<DevelopmentCard> developmentCards) {
        this.developmentCards = developmentCards;
    }

}
