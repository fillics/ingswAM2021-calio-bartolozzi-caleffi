package it.polimi.ingsw.exceptions;

public class LeaderCardIsActive extends Exception{
    public LeaderCardIsActive(){
        super("I'm sorry, your leader card is already activated");
    }

}
