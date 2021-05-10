package it.polimi.ingsw.client;

import it.polimi.ingsw.model.cards.developmentcards.DevelopmentCard;

public class LiteDevelopmentGrid {

    private DevelopmentCard[][] developmentCards= new DevelopmentCard[3][4];

    public DevelopmentCard[][] getDevelopmentCards() {
        return developmentCards;
    }

    public void setDevelopmentCards(DevelopmentCard[][] developmentCards) {
        this.developmentCards = developmentCards;
    }
}
