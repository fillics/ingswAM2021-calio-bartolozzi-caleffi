package it.polimi.ingsw.Exceptions;

public class DiscountCannotBeActivated extends Exception{
    public DiscountCannotBeActivated(){
        super("I'm sorry, discount is not applicable");
    }

}
