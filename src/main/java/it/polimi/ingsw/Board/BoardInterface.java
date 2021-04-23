package it.polimi.ingsw.Board;

import it.polimi.ingsw.Board.Storage.Deposit;
import it.polimi.ingsw.Cards.DevelopmentCards.ProductionPower;

import java.util.ArrayList;

/**
 * Interface BoardInterface represents the interface that contains the callable Board's methods by other classes
 */
public interface BoardInterface {
    void increaseFaithMarker();
    ArrayList<Deposit> getDeposits();
    ArrayList<ProductionPower> getSpecialProductionPowers();
}