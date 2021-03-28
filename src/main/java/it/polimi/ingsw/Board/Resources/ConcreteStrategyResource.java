package it.polimi.ingsw.Board.Resources;

public class ConcreteStrategyResource implements ResourceActionStrategy{
    @Override
    public boolean action() {
        System.out.println("sono una risorsa da magazzino");
        return true;
    }
}
