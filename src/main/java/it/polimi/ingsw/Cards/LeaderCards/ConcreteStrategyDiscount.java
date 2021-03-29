package it.polimi.ingsw.Cards.LeaderCards;

public class ConcreteStrategyDiscount implements  LeaderCardStrategy{
    @Override
    public boolean ability() {
        System.out.println("I'm a discount leader card");
        return true;
    }
}
