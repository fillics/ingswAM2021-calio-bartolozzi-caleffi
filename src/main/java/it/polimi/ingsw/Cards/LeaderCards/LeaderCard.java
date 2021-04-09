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

    public LeaderCard(LeaderCardType type, Requirement requirement) {
        this.type = type;
        this.requirement = requirement;
    }

    public void setStrategy(LeaderCardStrategy strategy) {
        this.strategy = strategy;
    }

    public void useAbility() {
        strategy.ability();
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
