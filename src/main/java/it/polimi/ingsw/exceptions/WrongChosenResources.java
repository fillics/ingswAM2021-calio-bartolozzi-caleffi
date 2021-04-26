package it.polimi.ingsw.exceptions;

public class WrongChosenResources extends Exception{
    public WrongChosenResources(){
        super("I'm sorry, you have chosen some wrong resources to buy this development card, please choose anothers");
    }
}
