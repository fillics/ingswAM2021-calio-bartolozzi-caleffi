package it.polimi.ingsw;

import it.polimi.ingsw.Board.Resources.ConcreteStrategyResource;
import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.DevelopmentCards.Level;
import it.polimi.ingsw.Cards.LeaderCards.LeaderCard;
import it.polimi.ingsw.Cards.LeaderCards.LeaderCardType;
import it.polimi.ingsw.Cards.LeaderCards.Requirement;
import it.polimi.ingsw.Exceptions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * PlayerTest class tests Player class.
 *
 * @see Player
 */

class PlayerTest {

    Game testGame;
    Player testPlayer;
    private static final String username = "fil";


    /** Method setup setups test. */
    @BeforeEach
    void setup() {
        testGame = new Game();
        testPlayer = new Player(username,0, testGame);
        testGame.setup();
    }


    /** Method cardAdditionTest test the addition of a single card to the player. */
    @Test
    void cardAdditionTest() {
        assertEquals(0, testPlayer.getLeaderCards().size());
        HashMap<ResourceType, Integer> resourcePrice = new HashMap<>();
        resourcePrice.put(ResourceType.COIN, 2);
        Requirement requirement = new Requirement(null, Level.ONE, resourcePrice);
        LeaderCard card1 = new LeaderCard(LeaderCardType.DISCOUNT, requirement, ResourceType.SERVANT, 4);

        testPlayer.addLeaderCard(card1);
        assertEquals(1, testPlayer.getLeaderCards().size());

    }

    /**
     * Test method checkRemoveLeaderCard checks if after removing a player's leader card, the size of
     * the array containing all the player's leader cards decreases
     * @throws LeaderCardNotFound if the player has not got the card to remove
     */
    @Test
    void checkRemoveLeaderCard() throws LeaderCardNotFound {
        HashMap<ResourceType, Integer> resourcePrice = new HashMap<>();
        resourcePrice.put(ResourceType.COIN, 2);
        Requirement requirement = new Requirement(null, Level.ONE, resourcePrice);
        LeaderCard card1 = new LeaderCard(LeaderCardType.DISCOUNT, requirement, ResourceType.SERVANT, 4);
        testPlayer.addLeaderCard(card1);
        assertEquals(1, testPlayer.getLeaderCards().size());
        testPlayer.removeLeaderCard(card1);
        assertEquals(0, testPlayer.getLeaderCards().size());

    }

    /**
     * Test method increaseFaithMarker checks the correct increase of the player's faith marker
     */
    @Test
    void increaseFaithMarkerTest() {
        assertEquals(0, testPlayer.getBoard().getFaithMarker());
        testPlayer.getBoard().increaseFaithMarker();
        assertEquals(1, testPlayer.getBoard().getFaithMarker());
    }

    /**
     * Test method FillBufferTest checks the correct increase of the player's resource buffer
     */
    @Test
    void FillBufferTest() throws DepositHasReachedMaxLimit, DepositHasAnotherResource, EmptyDeposit {
        //putting 3 coins in a deposit
        Resource resource1 = new Resource(ResourceType.COIN);
        ConcreteStrategyResource concreteStrategyResource = new ConcreteStrategyResource(2,testPlayer.getBoard(),resource1.getType());
        resource1.setStrategy(concreteStrategyResource);
        resource1.useResource();
        resource1.useResource();
        resource1.useResource();
        assertEquals(3,testPlayer.getBoard().getDeposits().get(2).getQuantity());
        assertEquals(ResourceType.COIN,testPlayer.getBoard().getDeposits().get(2).getResourcetype());

        //taking them and filling the resourceBuffer using fill buffer
        assertEquals(0,testPlayer.getResourceBuffer().size());
        testPlayer.fillBuffer(2);
        assertEquals(1,testPlayer.getResourceBuffer().size());
        assertEquals(ResourceType.COIN, testPlayer.getResourceBuffer().get(0).getType());


        testPlayer.fillBuffer(2);
        testPlayer.fillBuffer(2);
        assertEquals(3,testPlayer.getResourceBuffer().size());
        assertEquals(0,testPlayer.getBoard().getDeposits().get(2).getQuantity());
        assertNull(testPlayer.getBoard().getDeposits().get(2).getResourcetype());
    }

    /**
     * Test method checkChooseResourceBeginningGame verifies if the array containing the player's resources
     * increases itself, when a player chooses a resource at the beginning of the game.
     */
    @Test
    void checkChooseResourceBeginningGame(){
        assertEquals(0, testPlayer.getResourceBuffer().size());
        testPlayer.setChosenResource(2);
        testPlayer.addResourcesBeginningGame(testPlayer.getChosenResource());
        assertEquals(1, testPlayer.getResourceBuffer().size());

    }

    /**
     * Test method checkIncreaseNumberOfDevCards checks the correct increase of the number of development cards
     * purchased by a player. If a player purchases 7 cards, the game ends.
     */
    @Test
    void checkIncreaseNumberOfDevCards(){
        assertEquals(0, testPlayer.getBoard().getNumOfDevCards());
        for (int i = 0; i < 7; i++) {
            testPlayer.getBoard().increaseNumOfDevCards();
        }
        assertEquals(7, testPlayer.getBoard().getNumOfDevCards());
        assertTrue(testGame.isEndgame());
    }



}