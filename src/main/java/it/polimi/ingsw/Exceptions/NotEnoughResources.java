package it.polimi.ingsw.Exceptions;

public class NotEnoughResources extends Exception{
    public NotEnoughResources(){
        super("I'm sorry, you don't have enough resources to buy this development card");
    }
}
