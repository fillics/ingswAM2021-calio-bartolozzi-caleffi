package it.polimi.ingsw.Exceptions;

public class NegativeNumberBlackCross extends Exception {
    public NegativeNumberBlackCross(){
        super("The black cross cannot move backwards. ");
    }
}
