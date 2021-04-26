package it.polimi.ingsw.exceptions;

public class NotEnoughResources extends Exception{
    public NotEnoughResources(){
        super("I'm sorry, you don't have enough resources to buy this development card");
    }
}
