package it.polimi.ingsw.exceptions;

public class InvalidResource extends Exception{
    public InvalidResource(){
        super("You can't place this resource here, this deposit can contain only one type of resource");
    }

}
