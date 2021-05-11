package it.polimi.ingsw.client;

import it.polimi.ingsw.model.cards.developmentcards.DevelopmentCard;

import java.util.ArrayList;

public class LiteDevelopmentGrid {

    private ArrayList<DevelopmentCard> developmentCards;

    public LiteDevelopmentGrid(ArrayList<DevelopmentCard> developmentCards) {
        this.developmentCards = developmentCards;
    }

    public ArrayList<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }

    public void setDevelopmentCards(ArrayList<DevelopmentCard> developmentCards) {
        this.developmentCards = developmentCards;
    }
}
