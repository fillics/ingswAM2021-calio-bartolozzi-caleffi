package it.polimi.ingsw.Exceptions;

public class TooManyResourcesRequested extends Exception{
    public TooManyResourcesRequested(){
        super("The warehouse doesn't have sufficient resources");
    }
}