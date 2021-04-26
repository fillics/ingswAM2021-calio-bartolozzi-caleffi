package it.polimi.ingsw.exceptions;


public class DevelopmentCardNotFound extends Exception{
    public DevelopmentCardNotFound(){
        super("ERROR: Development Card not found in the Development Grid");
    }
}
