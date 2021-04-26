package it.polimi.ingsw.exceptions;

public class EmptyDeposit extends Exception{
    public EmptyDeposit (){
        super("I'm sorry, you can't take resources from this deposit, it's empty");
    }
}
