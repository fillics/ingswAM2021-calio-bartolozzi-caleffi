package it.polimi.ingsw.Marbles;

import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Game;
import it.polimi.ingsw.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * MarbleTest class tests Marble class and its subclasses.
 * @see Marble
 */
public class MarbleTest {
    Player testPlayer;
    String username;
    int position;
    Game game;
    Marble white,red,yellow,blue,purple,grey;

    /*
     * Method setup setups tests.
     * */
    @BeforeEach
    void setup(){
        white= new WhiteMarble();
        red= new RedMarble();
        yellow= new YellowMarble();
        blue= new BlueMarble();
        purple= new PurpleMarble();
        grey=new GreyMarble();
        username= "Beatrice";
        position=2;
        game= new Game();
        testPlayer= new Player(username,position,game);
    }

    /** Method transformTest tests transform method. */
    @Test
    @DisplayName("transform method test")
    void transformTest(){
        grey.transform(testPlayer);
        assertEquals(testPlayer.getResourceBuffer().get(0).getType(), ResourceType.STONE);
        purple.transform(testPlayer);
        assertEquals(testPlayer.getResourceBuffer().get(1).getType(), ResourceType.SERVANT);
        blue.transform(testPlayer);
        assertEquals(testPlayer.getResourceBuffer().get(2).getType(), ResourceType.SHIELD);
        yellow.transform(testPlayer);
        assertNotEquals(testPlayer.getResourceBuffer().get(3).getType(), ResourceType.FAITHMARKER);
        red.transform(testPlayer);
        assertEquals(testPlayer.getBoard().getFaithMarker(),1);
        white.transform(testPlayer);
        assertEquals(testPlayer.getResourceBuffer().size(),4);
    }


}
