package it.polimi.ingsw.Board;

import it.polimi.ingsw.Board.FaithTrack.Cell;
import it.polimi.ingsw.Board.FaithTrack.VaticanReportSection;
import it.polimi.ingsw.Board.Storage.Deposit;
import it.polimi.ingsw.Board.Storage.Strongbox;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentSpace;
import it.polimi.ingsw.Cards.DevelopmentCards.ProductionPower;

import java.util.ArrayList;

public class Board {
    private int NumOfDevCard;
    private int faithMarker;
    private int boardVictoryPoint;
    private Strongbox strongbox;
    private ArrayList<Deposit> deposits;
    private ArrayList<DevelopmentSpace> developmentSpaces;
    private ArrayList<Cell> track;
    private ArrayList<VaticanReportSection> vaticanReportSections;
    private ProductionPower baseProdPower;
    private ArrayList<ProductionPower> boardProdPower;


    /**
     * Class's constructor that'll be used in the setup method
     */
    public Board() {
        NumOfDevCard = 0;
        track = new ArrayList<>();
        faithMarker = 0;
        boardVictoryPoint = 0;
        strongbox = new Strongbox();
        deposits = new ArrayList<>();
        vaticanReportSections = new ArrayList<>();
        developmentSpaces = new ArrayList<>();
        boardProdPower= new ArrayList<>();
    }

    /**
     * Get-methods in order to obtain the attributes' values
     */
    public int getNumOfDevCard() { return NumOfDevCard; }

    /**
     * Get-methods in order to obtain the attributes' values
     */
    public ArrayList<DevelopmentSpace> getDevelopmentSpaces() {
        return developmentSpaces;
    }

    /**
     * Get-methods in order to obtain the attributes' values
     */
    public ArrayList<ProductionPower> getBoardProdPower() {
        return boardProdPower;
    }

    /**
     * Get-methods in order to obtain the attributes' values
     */
    public ArrayList<Cell> getTrack() {
        return track;
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

    /**
     * Method getFaithMarker returns the Faith Marker of this Board object.
     */
    public int getFaithMarker() {
        return faithMarker;
    }

    /**
     * Method increaseFaithMarker adds to the faith marker a specific amount, to move forward it.
     */
    public void increaseFaithMarker(){
        faithMarker += 1;
    }

    /**
     * Method getBoardVictoryPoint returns the amount of the victory points of this Board object.
     * @return the amount of the victory points (type int) of this Board object.
     */
    public int getBoardVictoryPoint() {
        for (VaticanReportSection vaticanReportSection : vaticanReportSections) {
            if (vaticanReportSection.getPopefavortile().isVisible()) {
                boardVictoryPoint += vaticanReportSection.getPopefavortile().getVictorypoint();
            }
        }
        for (DevelopmentSpace developmentSpace : developmentSpaces) {
            for (int i = 0; i < developmentSpace.getDevelopmentSpace().size(); i++) {
                boardVictoryPoint += developmentSpace.getDevelopmentSpace().get(i).getVictoryPoint();
            }
        }
        for (int i = 0; i< faithMarker; i++){
            boardVictoryPoint += track.get(i).getVictoryPoint();
        }
        boardVictoryPoint += (getTotalCoins() + getTotalServants() + getTotalStones() + getTotalShields()) / 5;
        return boardVictoryPoint;
    }

    /**
     * Get-methods that return the total amount of resources of a player
     * @return total resources
     */
    public int getTotalCoins() {
        int total;
        total = strongbox.getTotalCoins();
        for (Deposit deposit : deposits) {
            if(deposit.getResourcetype() != null){
                total += deposit.getTotalCoins();
            }
        }
        return total;
    }

    public int getTotalStones() {
        int total;
        total = strongbox.getTotalStones();
        for (Deposit deposit : deposits) {
            if(deposit.getResourcetype() != null){
                total += deposit.getTotalStones();
            }
        }
        return total;
    }

    public int getTotalServants() {
        int total;
        total = strongbox.getTotalServants();
        for (Deposit deposit : deposits) {
            if(deposit.getResourcetype() != null){
                total += deposit.getTotalServants();
            }
        }
        return total;
    }

    public int getTotalShields() {
        int total;
        total = strongbox.getTotalShields();
        for (Deposit deposit : deposits) {
            if(deposit.getResourcetype() != null){
                total += deposit.getTotalShields();
            }        }
        return total;
    }
}
