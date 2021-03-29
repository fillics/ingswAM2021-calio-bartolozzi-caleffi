package it.polimi.ingsw.Cards.LeaderCards;

public class ConcreteStrategyDeposit implements LeaderCardStrategy{
    @Override
    public boolean ability() {
        System.out.println("I'm a deposit leader card");
        return true;
    }
}
