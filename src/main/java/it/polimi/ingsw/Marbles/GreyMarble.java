package it.polimi.ingsw.Marbles;

public class GreyMarble extends Marble{
    @Override
    public boolean transform(){
        System.out.println("Transform into Stone");
        return true;
    }
}
