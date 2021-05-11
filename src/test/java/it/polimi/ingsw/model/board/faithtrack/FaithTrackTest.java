package it.polimi.ingsw.model.board.faithtrack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * BoardTest class tests Board class.
 * @see Cell , VaticanReportSection, PopeFavorTile
 */
public class FaithTrackTest {
    Cell testCell1;
    Cell testCell2;
    Cell testCell3;
    Cell testCell4;
    PopeFavorTile testPopeFavorTile;
    VaticanReportSection testVaticanReportSection;

    /** Method setup setups tests. */
    @BeforeEach
    void setup() {
        testCell1 = new Cell(3,false, 1);
        testCell2 = new Cell(0,false, 1);
        testCell3 = new Cell(1,false, 2);
        testCell4 = new Cell(5,true, 3);
        testPopeFavorTile = new PopeFavorTile(PopeFavorTileColor.YELLOW, 3 );
        testVaticanReportSection = new VaticanReportSection(testPopeFavorTile);
    }
    /** Method CellTest tests board getter. */
    @Test
    @DisplayName("Cell getters test")
    void CellTest() {
        assertEquals(3, testCell1.getVictoryPoint());
        assertEquals(0, testCell2.getVictoryPoint());
        assertEquals(1, testCell3.getVictoryPoint());
        assertEquals(5, testCell4.getVictoryPoint());

        assertFalse(testCell1.isPopeSpace());
        assertFalse(testCell2.isPopeSpace());
        assertFalse(testCell3.isPopeSpace());
        assertTrue(testCell4.isPopeSpace());

    }
    /** Method PopeFavorTileTest tests board getter. */
    @Test
    @DisplayName("PopeFavorTile getters test")
    void PopeFavorTileTest() {
        assertFalse(testPopeFavorTile.isVisible());
        testPopeFavorTile.setVisible();
        assertTrue(testPopeFavorTile.isVisible());
        assertEquals(3,testPopeFavorTile.getVictorypoint());
        assertEquals(PopeFavorTileColor.YELLOW, testPopeFavorTile.getColor());
    }
    /** Method VaticanReportSectionTest tests board getter. */
    @Test
    @DisplayName("VaticanReportSection getters test")
    void VaticanReportSectionTest() {
        assertEquals(testPopeFavorTile,testVaticanReportSection.getPopefavortile());
        /*I don't know how to test the section array, but it's used in other tests like in the board setup,
        it works*/
    }


}
