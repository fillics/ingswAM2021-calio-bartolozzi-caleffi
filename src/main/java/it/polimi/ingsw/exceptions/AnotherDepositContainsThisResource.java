package it.polimi.ingsw.exceptions;

public class AnotherDepositContainsThisResource extends Exception{
    public AnotherDepositContainsThisResource(){
        super("I'm sorry other deposits contain this resource ");
    }
}