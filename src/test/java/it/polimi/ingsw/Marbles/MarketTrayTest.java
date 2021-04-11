package it.polimi.ingsw.Marbles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * MarketTrayTest class tests MarketTray class.
 * @see MarketTray
 */
public class MarketTrayTest {
    MarketTray testMarketTray;
    Marble [][] testTable;
    String line;
    int numline;

    /*
     * Method setup setups tests.
     * */
    @BeforeEach
    void setup(){
        testMarketTray= new MarketTray();
        line= "Row";
        numline=3;
        testTable= new Marble[3][4];
    }

    /** Method getRemainingMarbleTest tests getRemainingMarble method. */
    @Test
    @DisplayName("getRemainingMarble method test")
    void getRemainingMarbleTest(){
        assertEquals(testMarketTray.getRemainingMarble(),testMarketTray.getMarket().get(0));
    }

    /** Method getTableTest tests getTable method. */
    @Test
    @DisplayName("getTable method test")
    void getTableTest(){
        testTable= testMarketTray.getTable();
        assertEquals(testMarketTray.getMarket().get(12),testTable[2][3]);
    }

    /** Method  tests  method. */
    @Test
    @DisplayName("change method test")
    void changeTest(){
        testMarketTray.change(line,numline);
        testTable= testMarketTray.getTable();
        assertEquals(testTable[2][3],testMarketTray.getMarket().get(0));
        assertEquals(testMarketTray.getRemainingMarble(),testMarketTray.getMarket().get(9));
    }

    //TODO: Testare lineSelection

}
