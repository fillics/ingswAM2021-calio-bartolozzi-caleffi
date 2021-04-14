package it.polimi.ingsw.Exceptions;

public class WrongDevSpace extends Exception{
    public WrongDevSpace(){
        super("I'm sorry, you can't put your new development card in this developmente space, please choose another one");
    }
}
