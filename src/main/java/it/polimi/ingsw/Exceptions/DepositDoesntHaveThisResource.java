package it.polimi.ingsw.Exceptions;

public class DepositDoesntHaveThisResource extends Exception{
    public DepositDoesntHaveThisResource(){
        super("I'm sorry this deposit doesn't contain this resource ");
    }
}
