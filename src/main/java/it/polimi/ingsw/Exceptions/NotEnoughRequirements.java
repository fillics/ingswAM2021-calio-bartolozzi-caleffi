package it.polimi.ingsw.Exceptions;

public class NotEnoughRequirements extends Exception{
    public NotEnoughRequirements(){
        super("ERROR: you cant' activate the leader card");
    }
}
