package it.polimi.ingsw.exceptions;

public class NumMaxPlayersReached extends Exception{
    public NumMaxPlayersReached(){
        super("ERROR: limit of players reached");
    }
}
