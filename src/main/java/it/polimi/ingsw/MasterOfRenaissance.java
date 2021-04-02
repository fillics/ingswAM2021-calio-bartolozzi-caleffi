package it.polimi.ingsw;


import it.polimi.ingsw.Board.Board;

import it.polimi.ingsw.Board.Resources.ConcreteStrategyResource;
import it.polimi.ingsw.Board.Resources.ConcreteStrategySpecialResource;
import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Board.Storage.Deposit;
import it.polimi.ingsw.SinglePlayer.SinglePlayerGame;

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

    public static void main( String[] args ) throws IOException {
        System.out.println("Hi! Welcome to Master of Renaissance!");
        Game game = new Game();
        game.createNewPlayer(new Player("fil", 1));
        //game.createLeaderDeck();
        //System.out.println(game.getPlayers().get(0).getUsername());
        SinglePlayerGame single = new SinglePlayerGame();
        single.setDeckSoloActionToken();
        System.out.println(single.getBlackCross());
        single.print();

        single.shuffleSoloActionToken();
        single.print();
        single.useSoloActionToken();
        single.print();
        System.out.println(single.getBlackCross());


        Resource fede = new Resource(ResourceType.FAITHMARKER);
        Resource moneta = new Resource(ResourceType.COIN);
        Player giocatore1 = new Player("Giovanni", 1);
        Deposit deposito = new Deposit(3);

        Board plancia = new Board();
        plancia.getDeposits().add(deposito);

        //System.out.println("Numero monete nel deposito prima: "+ deposito.getQuantity());
        //moneta.useResource(new ConcreteStrategyResource(0, plancia, moneta.getType()));
        //System.out.println("Numero monete nel deposito dopo: "+ deposito.getQuantity());

        //System.out.println("Posizione del faith marker prima: "+ giocatore1.getFaithMarker());
        //fede.useResource(new ConcreteStrategySpecialResource(giocatore1, 2));
        //System.out.println("Posizione del faith marker dopo: "+ giocatore1.getFaithMarker());

    }
}
