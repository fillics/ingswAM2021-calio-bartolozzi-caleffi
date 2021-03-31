package it.polimi.ingsw;


import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Board.FaithTrack.Cell;
import it.polimi.ingsw.Board.FaithTrack.VaticanReportSection;
import it.polimi.ingsw.Board.Resources.ConcreteStrategyResource;
import it.polimi.ingsw.Board.Resources.ConcreteStrategySpecialResource;
import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Board.Storage.Deposit;
import it.polimi.ingsw.Board.Storage.Strongbox;

import java.util.ArrayList;
import java.util.HashMap;

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

    public static void main( String[] args )    {
        System.out.println("Hi! Welcome to Master of Renaissance!");
        Resource fede = new Resource(ResourceType.FAITHMARKER);
        Resource moneta = new Resource(ResourceType.COIN);
        Player giocatore1 = new Player("Giovanni", 1);
        Cell[] tracciato = new Cell[24];
        HashMap<ResourceType, Integer> mappa = new HashMap<>();
        mappa.put(ResourceType.COIN, 0);
        Strongbox forziere =  new Strongbox(mappa);
        ArrayList<Deposit> depositi = new ArrayList<>();
        Deposit deposito = new Deposit(ResourceType.COIN, 0, 3);
        depositi.add(deposito);
        ArrayList<VaticanReportSection> vats = new ArrayList<>();

        Board plancia = new Board(tracciato, 0, 0 , forziere, depositi, vats );

        System.out.println("Numero monete nel deposito prima: "+ deposito.getQuantity());
        moneta.useResource(new ConcreteStrategyResource(0, plancia, moneta.getType()));
        System.out.println("Numero monete nel deposito dopo: "+ deposito.getQuantity());

        System.out.println("Posizione del faith marker prima: "+ giocatore1.getFaithMarker());
        fede.useResource(new ConcreteStrategySpecialResource(giocatore1, 1));
        System.out.println("Posizione del faith marker dopo: "+ giocatore1.getFaithMarker());

    }
}
