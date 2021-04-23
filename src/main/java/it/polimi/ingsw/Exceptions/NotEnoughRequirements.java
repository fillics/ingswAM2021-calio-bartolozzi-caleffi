package it.polimi.ingsw.Exceptions;

public class NotEnoughRequirements extends Exception{
    public NotEnoughRequirements(){
        super("ERROR: you can't activate the leader card you don't satisfy the requirements");
    }
}
