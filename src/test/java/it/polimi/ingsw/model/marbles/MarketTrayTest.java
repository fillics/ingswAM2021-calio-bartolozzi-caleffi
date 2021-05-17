package it.polimi.ingsw.model.marbles;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
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
    Marble[][] testTable;
    String line, line2;
    int numline, numline2;
    Player testPlayer;
    String username;
    int position;
    Game game;

    /*
     * Method setup setups tests.
     * */
    @BeforeEach
    void setup() {
        testMarketTray= new MarketTray();
        line= "Row";
        line2= "Column";
        numline=3;
        numline2=2;
        testTable= new Marble[3][4];
        username= "Beatrice";
        game= new Game();
        testPlayer= new Player(username,game);
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

    /** Method lineSelectionTest tests lineSelection method. */
    @Test
    @DisplayName("lineSelection method test")
    void lineSelectionTest(){
        testMarketTray.lineSelection(line,numline,testPlayer);
        testMarketTray.lineSelection(line2,numline2,testPlayer);
    }

    /** Method changeTest tests change method. */
    @Test
    @DisplayName("change method test")
    void changeTest(){
        testMarketTray.change(line,numline);
        testTable= testMarketTray.getTable();
        assertEquals(testTable[2][3],testMarketTray.getMarket().get(0));
        assertEquals(testMarketTray.getRemainingMarble(),testMarketTray.getMarket().get(9));
    }

    @Test
    @DisplayName(" print test")
    void printTest(){
        testMarketTray.dump();
    }

}
