package it.polimi.ingsw;


import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.SinglePlayer.SinglePlayerGame;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Class MasterOfRenaissance is the main class of whole project.
 *
 * @author Filippo Caliò, Beatrice Bartolozzi, Giovanni Caleffi
 */

public class MasterOfRenaissance {

    /**
     * Method main selects CLI, GUI or Server based on the arguments provided.
     *
     * @param args of type String[]
     */

    public static void main( String[] args ) {
        System.out.println("Hi! Welcome to Master of Renaissance!");
        Game game = new Game();



    }
}
