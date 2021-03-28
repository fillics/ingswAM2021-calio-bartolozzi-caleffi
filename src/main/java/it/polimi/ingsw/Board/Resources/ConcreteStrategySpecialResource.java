package it.polimi.ingsw.Board.Resources;

public class ConcreteStrategySpecialResource implements ResourceActionStrategy {
    @Override
    public boolean action() {
        System.out.println("sono una risorsa da tracciato");
        return false;
    }
}
