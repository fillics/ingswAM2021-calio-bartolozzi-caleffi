package it.polimi.ingsw.exceptions;

public class EmptyProductionPower extends Exception{
    public EmptyProductionPower(){
        super("You didn't select ant production power");
    }
}
