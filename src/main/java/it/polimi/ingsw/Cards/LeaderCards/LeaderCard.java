package it.polimi.ingsw.Cards.LeaderCards;

import it.polimi.ingsw.Cards.Card;

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

    public boolean doAbility() {
        strategy.ability();
        return true;
    }

    @Override
    public int getVictoryPoint() {
        return victorypoint;
    }
}
