package it.polimi.ingsw.Marbles;

public class BlueMarble extends Marble{
    @Override
    public boolean transform(){
        System.out.println("Transform into Shield");
        return true;
    }
}
