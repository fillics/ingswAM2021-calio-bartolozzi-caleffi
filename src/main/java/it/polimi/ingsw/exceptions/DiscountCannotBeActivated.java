package it.polimi.ingsw.exceptions;

public class DiscountCannotBeActivated extends Exception{
    public DiscountCannotBeActivated(){
        super("I'm sorry, discount is not applicable");
    }

}
