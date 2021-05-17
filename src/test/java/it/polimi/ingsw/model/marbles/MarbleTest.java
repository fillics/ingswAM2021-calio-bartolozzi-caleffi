package it.polimi.ingsw.model.marbles;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.cards.developmentcards.CardColor;
import it.polimi.ingsw.model.cards.leadercards.*;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.marbles.*;
import it.polimi.ingsw.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MarbleTest class tests Marble class and its subclasses.
 * @see Marble
 */
public class MarbleTest {
    Game testGame;
    Player testPlayer;
    String username;
    int position;
    Game game;
    Board board;
    Marble white,white2,white3,red,yellow,blue,purple,grey;
    LeaderCard testLeaderCardWhiteMarble;
    LeaderCardStrategy testStrategyWhiteMarble;
    NumAndColorRequirement requirementsWhiteMarble;
    HashMap<CardColor,Integer> colorWhiteMarble;
    LeaderCard testLeaderCardWhiteMarble2;
    LeaderCardStrategy testStrategyWhiteMarble2;
    NumAndColorRequirement requirementsWhiteMarble2;
    HashMap<CardColor,Integer> colorWhiteMarble2;
    ArrayList<Integer> whiteMarbleCardChoice;

    public int createIdPlayer(){
        return (int)(Math.random()*(20));
    }

    /*
     * Method setup setups tests.
     * */
    @BeforeEach
    void setup(){
        testGame = new Game();
        white= new WhiteMarble();
        white2= new WhiteMarble();
        white3= new WhiteMarble();
        red= new RedMarble();
        yellow= new YellowMarble();
        blue= new BlueMarble();
        purple= new PurpleMarble();
        grey=new GreyMarble();
        username= "Beatrice";
        game= new Game();
        board= new Board(testGame);
        testPlayer= new Player(username,game, createIdPlayer());
        colorWhiteMarble= new HashMap<>();
        colorWhiteMarble.put(CardColor.GREEN,2);
        colorWhiteMarble.put(CardColor.PURPLE,1);
        requirementsWhiteMarble= new NumAndColorRequirement(colorWhiteMarble);
        testStrategyWhiteMarble= new ConcreteStrategyMarble(ResourceType.SHIELD);
        testLeaderCardWhiteMarble= new LeaderCard(1, LeaderCardType.WHITE_MARBLE,requirementsWhiteMarble,ResourceType.SHIELD,5);
        testPlayer.addLeaderCard(testLeaderCardWhiteMarble);
        testLeaderCardWhiteMarble.setStrategy(testStrategyWhiteMarble);
        colorWhiteMarble2= new HashMap<>();
        colorWhiteMarble2.put(CardColor.YELLOW,2);
        colorWhiteMarble2.put(CardColor.BLUE,1);
        requirementsWhiteMarble2= new NumAndColorRequirement(colorWhiteMarble2);
        testStrategyWhiteMarble2= new ConcreteStrategyMarble(ResourceType.SERVANT);
        testLeaderCardWhiteMarble2= new LeaderCard(2,LeaderCardType.WHITE_MARBLE,requirementsWhiteMarble2,ResourceType.SERVANT,5);
        testPlayer.addLeaderCard(testLeaderCardWhiteMarble2);
        testLeaderCardWhiteMarble2.setStrategy(testStrategyWhiteMarble2);
        whiteMarbleCardChoice= new ArrayList<>();
        whiteMarbleCardChoice.add(testLeaderCardWhiteMarble.getId());
        whiteMarbleCardChoice.add(testLeaderCardWhiteMarble2.getId());
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

        assertEquals(testPlayer.getLeaderCards().size(),2);

        testPlayer.setWhiteMarbleCardChoice(whiteMarbleCardChoice);
        testLeaderCardWhiteMarble.useAbility();
        testLeaderCardWhiteMarble2.useAbility();

        assertEquals(testPlayer.getLeaderCards().get(0).getStrategy().getResourceType(),ResourceType.SHIELD);
        assertEquals(testPlayer.getLeaderCards().get(1).getStrategy().getResourceType(),ResourceType.SERVANT);

        white.transform(testPlayer);
        assertEquals(testPlayer.getResourceBuffer().get(4).getType(), ResourceType.SHIELD);
        assertEquals(whiteMarbleCardChoice.size(),1);

        white2.transform(testPlayer);
        assertEquals(testPlayer.getResourceBuffer().get(5).getType(), ResourceType.SERVANT);
        assertEquals(whiteMarbleCardChoice.size(),0);

        white3.transform(testPlayer);
    }

}
