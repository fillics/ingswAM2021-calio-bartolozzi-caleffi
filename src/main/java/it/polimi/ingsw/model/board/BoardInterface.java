package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.board.storage.Deposit;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;

import java.util.ArrayList;

/**
 * Interface BoardInterface represents the interface that contains the callable Board's methods by other classes
 */
public interface BoardInterface {
    void increaseFaithMarker();
    ArrayList<Deposit> getDeposits();
    ArrayList<ProductionPower> getSpecialProductionPowers();
}