package it.polimi.ingsw.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import it.polimi.ingsw.model.board.faithtrack.Cell;
import it.polimi.ingsw.model.board.faithtrack.PopeFavorTileColor;
import it.polimi.ingsw.model.board.faithtrack.VaticanReportSection;
import it.polimi.ingsw.model.board.storage.Deposit;
import it.polimi.ingsw.model.board.storage.Strongbox;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentSpace;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;

import java.util.ArrayList;

public class LiteBoard {

    private int faithMarker;
    private int blackCross;
    private Strongbox strongbox;
    private ArrayList<Deposit> deposits;
    private ArrayList<DevelopmentSpace> developmentSpaces;
    private ArrayList<ProductionPower> specialProductionPowers;
    private ArrayList<Cell> track;
    private ArrayList<VaticanReportSection> vaticanReportSections;

    @JsonCreator
    public LiteBoard(@JsonProperty("strongbox") Strongbox strongbox,@JsonProperty("deposits") ArrayList<Deposit> deposits,
                     @JsonProperty("devSpaces") ArrayList<DevelopmentSpace> developmentSpaces,
                     @JsonProperty("specProdPower") ArrayList<ProductionPower> specialProductionPowers,
                     @JsonProperty("track") ArrayList<Cell> track, @JsonProperty("vaticanReport") ArrayList<VaticanReportSection> vaticanReportSections) {
        faithMarker=0;
        blackCross=0;
        this.strongbox = strongbox;
        this.deposits = deposits;
        this.developmentSpaces = developmentSpaces;
        this.specialProductionPowers = specialProductionPowers;
        this.track = track;
        this.vaticanReportSections= vaticanReportSections;
    }

    @JsonCreator
    public LiteBoard() {
        faithMarker = 0;
        blackCross = 0;
        strongbox= new Strongbox();
        deposits=new ArrayList<>();
        developmentSpaces=new ArrayList<>();
        specialProductionPowers=new ArrayList<>();
        track=new ArrayList<>();
        vaticanReportSections=new ArrayList<>();
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

    @JsonIgnore
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

    @JsonSetter
    public void setSpecialProductionPowers(ArrayList<ProductionPower> specialProductionPowers) {
        this.specialProductionPowers = specialProductionPowers;
    }

    public ArrayList<Cell> getTrack() {
        return track;
    }

    public void setTrack(ArrayList<Cell> track) {
        this.track = track;
    }

    public ArrayList<VaticanReportSection> getVaticanReportSections() {
        return vaticanReportSections;
    }

    public void setVaticanReportSections(ArrayList<VaticanReportSection> vaticanReportSections) {
        this.vaticanReportSections = vaticanReportSections;
    }

    public int getFaithMarker() {
        return faithMarker;
    }

    public void setFaithMarker(int faithMarker) {
        this.faithMarker = faithMarker;
    }

    public int getBlackCross() {
        return blackCross;
    }

    public void setBlackCross(int blackCross) {
        this.blackCross = blackCross;
    }
}
