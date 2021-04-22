package it.polimi.ingsw;


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
        //chiamiamo server e client

        Game game = new Game();
        game.createLeaderDeck();
        System.out.println(game.getLeaderDeck().get(0).getRequirements());

    }
}
