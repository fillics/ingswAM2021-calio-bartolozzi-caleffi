package it.polimi.ingsw.Exceptions;

public class DepositHasReachedMaxLimit extends Exception{
    public DepositHasReachedMaxLimit(){
        super("I'm sorry, the deposit is full");
    }
}
