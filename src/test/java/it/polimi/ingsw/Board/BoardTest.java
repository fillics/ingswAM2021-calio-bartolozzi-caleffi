package it.polimi.ingsw.Board;

import it.polimi.ingsw.Board.FaithTrack.Cell;
import it.polimi.ingsw.Board.FaithTrack.PopeFavorTile;
import it.polimi.ingsw.Board.FaithTrack.PopeFavorTileColor;
import it.polimi.ingsw.Board.FaithTrack.VaticanReportSection;
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

    /** Method setup setups tests. */
    @BeforeEach
    void setup() {
        testHashMap = new HashMap<>();
        testHashMap.put(ResourceType.COIN, 0);
        testProductionPower = new ProductionPower(testHashMap,testHashMap);
        testBoard = new Board();
        testDeposit1 = new Deposit(1);
        testDeposit2 = new Deposit(2);
        testDeposit3 = new Deposit(3);
        testBoard.getDeposits().add(testDeposit1);
        testBoard.getDeposits().add(testDeposit2);
        testBoard.getDeposits().add(testDeposit3);


        testBoard.getStrongbox().getStrongbox().put(ResourceType.COIN,0);
        testBoard.getStrongbox().getStrongbox().put(ResourceType.STONE,0);
        testBoard.getStrongbox().getStrongbox().put(ResourceType.SERVANT,0);
        testBoard.getStrongbox().getStrongbox().put(ResourceType.SHIELD,0);


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



        testPopeFavorTile = new PopeFavorTile(PopeFavorTileColor.YELLOW, 1, true);
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
        testarray1 = new ArrayList<>();
        testarray2 = new ArrayList<>();
        testarray3 = new ArrayList<>();
        testarray1.add(cell1);
        testarray1.add(cell2);
        testarray1.add(cell3);
        testarray2.add(cell4);
        testarray2.add(cell5);
        testarray2.add(cell6);
        testarray3.add(cell7);
        testarray3.add(cell8);
        testarray3.add(cell9);
        testarray3.add(cell10);
        testVaticanReportSection1 = new VaticanReportSection(testarray1,testPopeFavorTile);
        testVaticanReportSection2 = new VaticanReportSection(testarray2,testPopeFavorTile);
        testVaticanReportSection3 = new VaticanReportSection(testarray3,testPopeFavorTile);
        testBoard.getVaticanReportSections().add(testVaticanReportSection1);
        testBoard.getVaticanReportSections().add(testVaticanReportSection2);
        testBoard.getVaticanReportSections().add(testVaticanReportSection3);


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
    @DisplayName("Board attributes' get-methods test")
    void BoardGetterTest() {
        assertEquals(6,testBoard.getBoardVictoryPoint());
    }
}
