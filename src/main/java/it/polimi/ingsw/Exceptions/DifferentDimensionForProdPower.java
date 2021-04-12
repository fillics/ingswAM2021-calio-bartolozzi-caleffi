package it.polimi.ingsw.Exceptions;

public class DifferentDimensionForProdPower extends Exception{
    public DifferentDimensionForProdPower(){
        super("The dimension of this arrays must be the same");
    }
}
