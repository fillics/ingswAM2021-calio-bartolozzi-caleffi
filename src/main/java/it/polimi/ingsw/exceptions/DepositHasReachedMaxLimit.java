package it.polimi.ingsw.exceptions;

public class DepositHasReachedMaxLimit extends Exception{
    public DepositHasReachedMaxLimit(){
        super("I'm sorry, the deposit is full");
    }
}
