package it.polimi.ingsw.Board;

import it.polimi.ingsw.Board.FaithTrack.Cell;
import it.polimi.ingsw.Board.FaithTrack.VaticanReportSection;
import it.polimi.ingsw.Board.Storage.Deposit;
import it.polimi.ingsw.Board.Storage.Strongbox;
import it.polimi.ingsw.Cards.DevelopmentCards.ProductionPower;

import java.util.ArrayList;

public class Board {
    private int numberDevCard;
    private int boardVictoryPoint;
    private Strongbox strongbox;
    private ArrayList<Deposit> deposits;

    private Cell[] track;
    private ArrayList<VaticanReportSection> vaticanReportSections;
    public ProductionPower baseProdPower;

    /**
     * Class's constructor that'll be used in the setup method
     */
    public Board() {
        track = new Cell[24];
        numberDevCard = 0;
        boardVictoryPoint = 0;
        strongbox = new Strongbox();
        deposits = new ArrayList<>();
        vaticanReportSections = new ArrayList<>();
    }

    /**
     * Get-methods in order to obtain the attributes' values
     */
    public Cell[] getTrack() {
        return track;
    }

    /**
     * Method getBoardVictoryPoint returns the amount of the victory points of this Board object.
     *
     * @return the amount of the victory points (type int) of this Board object.
     */
    public int getBoardVictoryPoint() {
        return boardVictoryPoint;
    }

    /**
     * Get-methods in order to obtain the attributes' values
     */

    public int getNumberDevCard() {
        return numberDevCard;
    }

    /**
     * Get-methods in order to obtain the attributes' values
     */
    public Strongbox getStrongbox() {
        return strongbox;
    }

    /**
     * Get-methods in order to obtain the attributes' values
     */
    public ArrayList<Deposit> getDeposits() {
        return deposits;
    }

    /**
     * Get-methods in order to obtain the attributes' values
     */
    public ArrayList<VaticanReportSection> getVaticanReportSections() {
        return vaticanReportSections;
    }

    /**
     * Get-methods in order to obtain the attributes' values
     */
    public ProductionPower getBaseProdPower() { return baseProdPower; }
}
