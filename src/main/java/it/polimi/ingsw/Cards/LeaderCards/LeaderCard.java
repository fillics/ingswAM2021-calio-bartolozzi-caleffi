package it.polimi.ingsw.Cards.LeaderCards;

public class LeaderCard {
    private LeaderCardType type;
    private LeaderCardStrategy strategy;
    private Requirement requirement;

    public LeaderCard(LeaderCardType type, LeaderCardStrategy strategy, Requirement requirement) {
        this.type = type;
        this.strategy = strategy;
        this.requirement = requirement;
    }

    public boolean doAbility() {
        strategy.ability();
        return true;
    }
}
