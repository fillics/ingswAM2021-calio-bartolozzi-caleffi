package it.polimi.ingsw.Cards.LeaderCards;

import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.Card;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;

/**
 * This class represents the Leader Card.
 */
public class LeaderCard extends Card {
    private LeaderCardType type;
    private LeaderCardStrategy strategy;
    private Requirement requirement;
    private int victorypoint;

    public LeaderCard(LeaderCardType type, LeaderCardStrategy strategy, Requirement requirement) {
        this.type = type;
        this.strategy = strategy;
        this.requirement = requirement;
    }

    public boolean activateLeaderCard() {
        strategy.activate();
        return true;
    }

    public boolean useAbility(int numofMarbles) {
        strategy.ability(numofMarbles);
        return true;
    }

    public boolean useAbility(DevelopmentCard developmentCard) {
        strategy.ability(developmentCard);
        return true;
    }

    public boolean useAbility(ResourceType resourceObtained) {
        strategy.ability(resourceObtained);
        return true;
    }

    @Override
    public int getVictoryPoint() {
        return victorypoint;
    }

    @Override
    public String toString() {
        return "LeaderCard{" +
                "type=" + type +
                ", victorypoint=" + victorypoint +
                '}';
    }
}
