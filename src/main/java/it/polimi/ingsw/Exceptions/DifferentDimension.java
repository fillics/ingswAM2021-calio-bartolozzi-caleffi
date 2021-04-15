package it.polimi.ingsw.Exceptions;

public class DifferentDimension extends Exception{
    public DifferentDimension(){
        super("The dimension of this arrays must be the same");
    }
}
