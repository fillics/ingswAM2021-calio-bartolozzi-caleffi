package it.polimi.ingsw.exceptions;

public class DevCardNotPlaceable extends Exception{
    public DevCardNotPlaceable(){
        super("I'm sorry,this development card is not placeable in any of yours development spaces");
    }
}
