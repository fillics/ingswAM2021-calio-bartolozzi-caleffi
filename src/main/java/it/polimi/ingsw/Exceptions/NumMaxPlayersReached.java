package it.polimi.ingsw.Exceptions;

public class NumMaxPlayersReached extends Exception{
    public NumMaxPlayersReached(){
        super("ERROR: limit of players reached");
    }
}
