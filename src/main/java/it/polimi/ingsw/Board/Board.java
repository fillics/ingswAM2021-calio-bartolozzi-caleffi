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
    public Board(Cell[] track, int numberDevCard, int boardVictoryPoint, Strongbox strongbox, ArrayList<Deposit> deposits, ArrayList<VaticanReportSection> vaticanReportSections) {
        this.track = track;
        this.numberDevCard = numberDevCard;
        this.boardVictoryPoint = boardVictoryPoint;
        this.strongbox = strongbox;
        this.deposits = deposits;
        this.vaticanReportSections = vaticanReportSections;
    }

    /**
     * Get-methods in order to obtain the attributes' values
     */
    public Cell[] getTrack() {
        return track;
    }

    public int getBoardVictoryPoint() {
        return boardVictoryPoint;
    }

    public int getNumberDevCard() {
        return numberDevCard;
    }

    public Strongbox getStrongbox() {
        return strongbox;
    }

    public ArrayList<Deposit> getDeposits() {
        return deposits;
    }

    public ArrayList<VaticanReportSection> getVaticanReportSections() {
        return vaticanReportSections;
    }

    public ProductionPower getBaseProdPower() { return baseProdPower; }
}
