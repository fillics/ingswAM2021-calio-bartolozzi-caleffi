package it.polimi.ingsw.Board;

import it.polimi.ingsw.Board.FaithTrack.Cell;
import it.polimi.ingsw.Board.FaithTrack.PopeFavorTile;
import it.polimi.ingsw.Board.FaithTrack.PopeFavorTileColor;
import it.polimi.ingsw.Board.FaithTrack.VaticanReportSection;
import it.polimi.ingsw.Board.Resources.ConcreteStrategyResource;
import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Board.Storage.Deposit;
import it.polimi.ingsw.Board.Storage.Strongbox;
import it.polimi.ingsw.Cards.DevelopmentCards.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * BoardTest class tests Board class.
 * @see Board
 */
public class BoardTest {
    Board testBoard;
    Deposit testDeposit1;
    Deposit testDeposit2;
    Deposit testDeposit3;
    Strongbox testStrongbox;
    DevelopmentSpace testDevelopmentSpace1;
    DevelopmentSpace testDevelopmentSpace2;
    DevelopmentSpace testDevelopmentSpace3;
    DevelopmentCard testDevelopmentCard1;
    DevelopmentCard testDevelopmentCard2;
    DevelopmentCard testDevelopmentCard3;
    Cell cell1;
    Cell cell2;
    Cell cell3;
    Cell cell4;
    Cell cell5;
    Cell cell6;
    Cell cell7;
    Cell cell8;
    Cell cell9;
    Cell cell10;
    ArrayList<Cell> testarray1;
    ArrayList<Cell> testarray2;
    ArrayList<Cell> testarray3;
    VaticanReportSection testVaticanReportSection1;
    VaticanReportSection testVaticanReportSection2;
    VaticanReportSection testVaticanReportSection3;
    ProductionPower testProductionPower;
    HashMap<ResourceType,Integer> testHashMap;
    PopeFavorTile testPopeFavorTile;
    Resource coin;
    Resource servant;

    /** Method setup setups tests. */
    @BeforeEach
    void setup() {
        testBoard = new Board();
        coin = new Resource(ResourceType.COIN);
        servant = new Resource(ResourceType.SERVANT);
        servant.setStrategy(new ConcreteStrategyResource(0, testBoard, ResourceType.SERVANT));
        coin.setStrategy(new ConcreteStrategyResource(2, testBoard, ResourceType.COIN));
        testHashMap = new HashMap<>();
        testHashMap.put(ResourceType.COIN, 0);
        testProductionPower = new ProductionPower(testHashMap,testHashMap);
        testDeposit1 = new Deposit(1);
        testDeposit2 = new Deposit(2);
        testDeposit3 = new Deposit(3);
        testBoard.getDeposits().add(testDeposit1);
        testBoard.getDeposits().add(testDeposit2);
        testBoard.getDeposits().add(testDeposit3);
        coin.useResource();
        coin.useResource();
        coin.useResource();
        servant.useResource();


        testBoard.getStrongbox().getStrongbox().put(ResourceType.COIN,3);
        testBoard.getStrongbox().getStrongbox().put(ResourceType.STONE,50);
        testBoard.getStrongbox().getStrongbox().put(ResourceType.SERVANT,7);
        testBoard.getStrongbox().getStrongbox().put(ResourceType.SHIELD,1);


        testDevelopmentCard1 = new DevelopmentCard(Level.ONE, CardColor.GREEN, testProductionPower, 1);
        testDevelopmentCard2 = new DevelopmentCard(Level.ONE, CardColor.GREEN, testProductionPower, 1);
        testDevelopmentCard3 = new DevelopmentCard(Level.ONE, CardColor.GREEN, testProductionPower, 1);
        testDevelopmentSpace1 = new DevelopmentSpace();
        testDevelopmentSpace2 = new DevelopmentSpace();
        testDevelopmentSpace3 = new DevelopmentSpace();
        testDevelopmentSpace1.getDevelopmentSpace().add(testDevelopmentCard1);
        testDevelopmentSpace2.getDevelopmentSpace().add(testDevelopmentCard2);
        testDevelopmentSpace3.getDevelopmentSpace().add(testDevelopmentCard3);
        testBoard.getDevelopmentSpaces().add(testDevelopmentSpace1);
        testBoard.getDevelopmentSpaces().add(testDevelopmentSpace2);
        testBoard.getDevelopmentSpaces().add(testDevelopmentSpace3);



        testPopeFavorTile = new PopeFavorTile(PopeFavorTileColor.YELLOW, 1);
        testPopeFavorTile.setVisible(true);
        cell1 = new Cell(1,true);
        cell2 = new Cell(1,true);
        cell3 = new Cell(1,true);
        cell4 = new Cell(1,true);
        cell5 = new Cell(1,true);
        cell6 = new Cell(1,true);
        cell7 = new Cell(1,true);
        cell8 = new Cell(1,true);
        cell9 = new Cell(1,true);
        cell10 = new Cell(1,true);
        testVaticanReportSection1 = new VaticanReportSection(testPopeFavorTile);
        testVaticanReportSection2 = new VaticanReportSection(testPopeFavorTile);
        testVaticanReportSection3 = new VaticanReportSection(testPopeFavorTile);
        testBoard.getVaticanReportSections().add(testVaticanReportSection1);
        testBoard.getVaticanReportSections().add(testVaticanReportSection2);
        testBoard.getVaticanReportSections().add(testVaticanReportSection3);
        testBoard.getVaticanReportSections().get(0).getSection().add(cell1);
        testBoard.getVaticanReportSections().get(0).getSection().add(cell2);
        testBoard.getVaticanReportSections().get(0).getSection().add(cell3);
        testBoard.getVaticanReportSections().get(1).getSection().add(cell4);
        testBoard.getVaticanReportSections().get(1).getSection().add(cell5);
        testBoard.getVaticanReportSections().get(1).getSection().add(cell6);
        testBoard.getVaticanReportSections().get(2).getSection().add(cell7);
        testBoard.getVaticanReportSections().get(2).getSection().add(cell8);
        testBoard.getVaticanReportSections().get(2).getSection().add(cell9);
        testBoard.getVaticanReportSections().get(2).getSection().add(cell10);




        testBoard.getTrack().add(cell1);
        testBoard.getTrack().add(cell2);
        testBoard.getTrack().add(cell3);
        testBoard.getTrack().add(cell4);
        testBoard.getTrack().add(cell5);
        testBoard.getTrack().add(cell6);
        testBoard.getTrack().add(cell7);
        testBoard.getTrack().add(cell8);
        testBoard.getTrack().add(cell9);
        testBoard.getTrack().add(cell10);
    }

    /** Method BoardGetterTest tests board methods getter. */
    @Test
    @DisplayName("getBoardVictoryPoint test")
    void VictoryPointGetterTest() {
        assertEquals(19,testBoard.getBoardVictoryPoint());
    }

    /** Method BoardTest tests board getter. */
    @Test
    @DisplayName("Board getters test")
    void BoardGetterTest() {
        assertEquals(1,testBoard.getTotalShields());
        assertEquals(8,testBoard.getTotalServants());
        assertEquals(6,testBoard.getTotalCoins());
        assertEquals(50,testBoard.getTotalStones());
        assertEquals(0,testBoard.getNumOfDevCard());
        assertEquals(0,testBoard.getFaithMarker());
        testBoard.increaseFaithMarker();
        assertEquals(1, testBoard.getFaithMarker());
    }
    /*I don't know how to test the other 6 getters that return the structures that contain development cards,
    deposits, strongbox, production power, vatican report sections and track. Anyway, this method were used in
    the setup method to modify the board.*/
}

