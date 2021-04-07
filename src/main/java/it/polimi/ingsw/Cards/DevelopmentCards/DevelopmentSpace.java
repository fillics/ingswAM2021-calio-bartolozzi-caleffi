package it.polimi.ingsw.Cards.DevelopmentCards;

import java.util.ArrayList;

public class DevelopmentSpace {
    private ArrayList<DevelopmentCard> developmentSpace;
    private DevelopmentCard topCard;

    public DevelopmentSpace() {
        developmentSpace = new ArrayList<>();
    }

    public DevelopmentCard getTopCard() {
        return topCard;
    }

    public ArrayList<DevelopmentCard> getDevelopmentSpace() {
        return developmentSpace;
    }
}
