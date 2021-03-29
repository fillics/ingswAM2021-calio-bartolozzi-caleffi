package it.polimi.ingsw.Cards.LeaderCards;

public class ConcreteStrategyMarble implements LeaderCardStrategy{
    @Override
    public boolean ability() {
        System.out.println("I'm a white marble leader card");
        return true;
    }
}
