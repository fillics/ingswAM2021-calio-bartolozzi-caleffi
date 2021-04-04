package it.polimi.ingsw.Marbles;

public class PurpleMarble extends Marble{
    @Override
    public boolean transform(){
        System.out.println("Transform into Servant");
        return true;
    }
}
