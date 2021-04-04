package it.polimi.ingsw.Marbles;

public class YellowMarble extends Marble{
    @Override
    public boolean transform(){
        System.out.println("Transform into Coin");
        return true;
    }
}
