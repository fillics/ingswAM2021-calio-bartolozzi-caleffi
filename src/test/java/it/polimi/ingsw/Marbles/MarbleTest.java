package it.polimi.ingsw.Marbles;

import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.Cards.LeaderCards.*;
import it.polimi.ingsw.Game;
import it.polimi.ingsw.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

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
    Board board;
    Marble white,white2,red,yellow,blue,purple,grey;
    LeaderCard testLeaderCardWhiteMarble;
    LeaderCardStrategy testStrategyWhiteMarble;
    Requirement requirementsWhiteMarble;
    HashMap<CardColor,Integer> colorWhiteMarble;
    LeaderCard testLeaderCardWhiteMarble2;
    LeaderCardStrategy testStrategyWhiteMarble2;
    Requirement requirementsWhiteMarble2;
    HashMap<CardColor,Integer> colorWhiteMarble2;
    boolean useAbilityChoiceCard1;
    boolean useAbilityChoiceCard2;

    /*
     * Method setup setups tests.
     * */
    @BeforeEach
    void setup(){
        white= new WhiteMarble();
        white2= new WhiteMarble();
        red= new RedMarble();
        yellow= new YellowMarble();
        blue= new BlueMarble();
        purple= new PurpleMarble();
        grey=new GreyMarble();
        username= "Beatrice";
        position=2;
        game= new Game();
        testPlayer= new Player(username,position,game);
        colorWhiteMarble= new HashMap<>();
        colorWhiteMarble.put(CardColor.GREEN,2);
        colorWhiteMarble.put(CardColor.PURPLE,1);
        requirementsWhiteMarble= new Requirement(colorWhiteMarble,null,null);
        board= new Board();
        testStrategyWhiteMarble= new ConcreteStrategyMarble(ResourceType.SHIELD);
        testLeaderCardWhiteMarble= new LeaderCard(LeaderCardType.WHITE_MARBLE,requirementsWhiteMarble,ResourceType.SHIELD,5);
        testPlayer.addLeaderCard(testLeaderCardWhiteMarble);
        colorWhiteMarble2= new HashMap<>();
        colorWhiteMarble2.put(CardColor.YELLOW,2);
        colorWhiteMarble2.put(CardColor.BLUE,1);
        requirementsWhiteMarble2= new Requirement(colorWhiteMarble2,null,null);
        testStrategyWhiteMarble2= new ConcreteStrategyMarble(ResourceType.SERVANT);
        testLeaderCardWhiteMarble2= new LeaderCard(LeaderCardType.WHITE_MARBLE,requirementsWhiteMarble2,ResourceType.SERVANT,5);
        testPlayer.addLeaderCard(testLeaderCardWhiteMarble2);
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

        useAbilityChoiceCard1=true;
        testLeaderCardWhiteMarble.setStrategy(testStrategyWhiteMarble);
        testLeaderCardWhiteMarble.setUseDiscountChoice(useAbilityChoiceCard1);
        testLeaderCardWhiteMarble.useAbility();
        white.transform(testPlayer);

        useAbilityChoiceCard1=false;
        useAbilityChoiceCard2=true;
        testLeaderCardWhiteMarble2.setStrategy(testStrategyWhiteMarble2);
        testLeaderCardWhiteMarble.setUseDiscountChoice(useAbilityChoiceCard1);
        testLeaderCardWhiteMarble2.setUseDiscountChoice(useAbilityChoiceCard2);
        testLeaderCardWhiteMarble2.useAbility();
        white2.transform(testPlayer);
    }

}
