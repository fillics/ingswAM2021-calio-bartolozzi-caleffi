package it.polimi.ingsw.client;

import it.polimi.ingsw.model.board.faithtrack.Cell;
import it.polimi.ingsw.model.board.storage.Deposit;
import it.polimi.ingsw.model.board.storage.Strongbox;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentSpace;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;

import java.util.ArrayList;

public class LiteBoard {

    private Strongbox strongbox;
    private ArrayList<Deposit> deposits;
    private ArrayList<DevelopmentSpace> developmentSpaces;
    private ArrayList<ProductionPower> specialProductionPowers;
    private ArrayList<Cell> track;

    public LiteBoard(Strongbox strongbox, ArrayList<Deposit> deposits, ArrayList<DevelopmentSpace> developmentSpaces, ArrayList<ProductionPower> specialProductionPowers, ArrayList<Cell> track) {
        this.strongbox = strongbox;
        this.deposits = deposits;
        this.developmentSpaces = developmentSpaces;
        this.specialProductionPowers = specialProductionPowers;
        this.track = track;
    }

    public Strongbox getStrongbox() {
        return strongbox;
    }

    public ArrayList<Deposit> getDeposits() {
        return deposits;
    }

    public ArrayList<DevelopmentSpace> getDevelopmentSpaces() {
        return developmentSpaces;
    }

    public ArrayList<ProductionPower>  getSpecialProductionPower() { return specialProductionPowers; }

    public void setStrongbox(Strongbox strongbox) {
        this.strongbox = strongbox;
    }

    public void setDeposits(ArrayList<Deposit> deposits) {
        this.deposits = deposits;
    }

    public void setDevelopmentSpaces(ArrayList<DevelopmentSpace> developmentSpaces) {
        this.developmentSpaces = developmentSpaces;
    }

    public void setSpecialProductionPowers(ArrayList<ProductionPower> specialProductionPowers) {
        this.specialProductionPowers = specialProductionPowers;
    }

    public ArrayList<Cell> getTrack() {
        return track;
    }

    public void setTrack(ArrayList<Cell> track) {
        this.track = track;
    }
}
