package it.polimi.ingsw.exceptions;

public class TooManyResourcesRequested extends Exception{
    public TooManyResourcesRequested(){
        super("The warehouse doesn't have sufficient resources");
    }
    @Override
    public void printStackTrace() {
        System.out.println("The warehouse doesn't have sufficient resources");
        super.printStackTrace();
    }
}
