package it.polimi.ingsw.exceptions;

public class DepositHasAnotherResource extends Exception{
    public DepositHasAnotherResource(){
        super("I'm sorry you can't put this resource in here, move the recent resource and try again");
    }
}
