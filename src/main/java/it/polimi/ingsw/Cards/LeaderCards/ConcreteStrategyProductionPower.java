package it.polimi.ingsw.Cards.LeaderCards;

public class ConcreteStrategyProductionPower implements LeaderCardStrategy{
    @Override
    public boolean ability() {
        System.out.println("I'm a production power leader card");
        return true;
    }
}
