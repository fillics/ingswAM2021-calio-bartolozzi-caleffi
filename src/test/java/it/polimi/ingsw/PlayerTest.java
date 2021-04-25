package it.polimi.ingsw;

import it.polimi.ingsw.Board.Resources.ConcreteStrategyResource;
import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;
import it.polimi.ingsw.Cards.DevelopmentCards.Level;
import it.polimi.ingsw.Cards.DevelopmentCards.ProductionPower;
import it.polimi.ingsw.Cards.LeaderCards.*;
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

    LeaderCard testLeaderCardDiscount;
    NumAndColorRequirement requirementsDiscount;
    HashMap<CardColor,Integer> colorDiscount;
    ResourcesRequirement requirementsExtraDep;
    LeaderCard testLeaderCardExtraDep;


    boolean choice;
    int i;
    HashMap<ResourceType,Integer> resourcePriceBuffer;
    DevelopmentCard testDevelopmentCard;
    ProductionPower testProductionPower;
    HashMap<ResourceType,Integer> testResourcePrice;
    HashMap<ResourceType,Integer> testResourcesNeeded;
    HashMap<ResourceType,Integer> testResourcesObtained;


    /** Method setup setups test. */
    @BeforeEach
    void setup(){
        testGame = new Game();
        testPlayer = new Player(username, 0, testGame);

        colorDiscount= new HashMap<>();
        colorDiscount.put(CardColor.YELLOW,1);
        colorDiscount.put(CardColor.GREEN,1);
        testResourcePrice= new HashMap<>();
        testResourcePrice.put(ResourceType.SERVANT,2);
        requirementsDiscount= new NumAndColorRequirement(colorDiscount);
        testLeaderCardDiscount = new LeaderCard(1, LeaderCardType.DISCOUNT,requirementsDiscount,ResourceType.SERVANT,2);
        testLeaderCardDiscount.setStrategy(new ConcreteStrategyDiscount(ResourceType.SERVANT));

        requirementsExtraDep = new ResourcesRequirement(testResourcePrice);
        testLeaderCardExtraDep = new LeaderCard(2, LeaderCardType.EXTRA_DEPOSIT, requirementsExtraDep, ResourceType.COIN, 5);
        testLeaderCardExtraDep.setStrategy(new ConcreteStrategyDeposit(ResourceType.SERVANT, testPlayer.getBoard()));

        testResourcesNeeded = new HashMap<>();
        testResourcesObtained = new HashMap<>();

        testResourcesNeeded.put(ResourceType.COIN,1);
        testResourcesObtained.put(ResourceType.FAITHMARKER,1);
        testProductionPower= new ProductionPower(testResourcesNeeded,testResourcesObtained);
        testDevelopmentCard= new DevelopmentCard(2,Level.ONE, CardColor.PURPLE,testProductionPower,testResourcePrice, 3);


    }


    /** Method cardAdditionTest tests the addition of a single card to the player. */
    @Test
    void cardAdditionTest() {
        assertEquals(0, testPlayer.getLeaderCards().size());

        testPlayer.addLeaderCard(testLeaderCardDiscount);
        assertEquals(1, testPlayer.getLeaderCards().size());

    }

    /**
     * Test method checkRemoveLeaderCard checks if after removing a player's leader card, the size of
     * the array containing all the player's leader cards decreases
     * @throws LeaderCardNotFound if the player has not got the card to remove
     */
    @Test
    void checkRemoveLeaderCard() throws LeaderCardNotFound {

        testPlayer.addLeaderCard(testLeaderCardDiscount);
        assertEquals(1, testPlayer.getLeaderCards().size());
        testPlayer.removeLeaderCard(testLeaderCardDiscount);
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
        resource1.setStrategy(new ConcreteStrategyResource(2,testPlayer.getBoard(),resource1.getType()));
        for (int j = 0; j < 3; j++) {
            resource1.useResource();
        }

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
    void checkIncreaseNumberOfDevCards() {
        assertEquals(0, testPlayer.getBoard().getNumOfDevCards());
        for (int i = 0; i < 7; i++) {
            testPlayer.getBoard().increaseNumOfDevCards();
        }
        assertEquals(7, testPlayer.getBoard().getNumOfDevCards());
        assertTrue(testGame.isEndgame());
    }

    /**
     * Test method checkTotalVictoryPoints checks if the victory points' counting is correct. This test adds two leader
     * cards to the player, it activates them and then it increases the player's faith marker (to get the faith marker's
     * victory points)
     */
    @Test
    void checkTotalVictoryPoints(){
        int leaderpoints1, leaderpoints2, boardpoints, totalpoints;

        testPlayer.addLeaderCard(testLeaderCardDiscount);
        testPlayer.addLeaderCard(testLeaderCardExtraDep);

        assertEquals(2, testPlayer.getLeaderCards().size());

        //activating the leader cards
        testPlayer.getLeaderCards().get(0).getStrategy().ability();
        testPlayer.getLeaderCards().get(1).getStrategy().ability();
        assertTrue(testPlayer.getLeaderCards().get(0).getStrategy().isActive());
        assertTrue(testPlayer.getLeaderCards().get(1).getStrategy().isActive());

        leaderpoints1 = testPlayer.getLeaderCards().get(0).getVictoryPoint();
        leaderpoints2 = testPlayer.getLeaderCards().get(1).getVictoryPoint();
        for (int k = 0; k < 10; k++) {
            testPlayer.getBoard().increaseFaithMarker();
        }
        boardpoints = testPlayer.getBoard().getBoardVictoryPoint();
        assertEquals(7, boardpoints);

        totalpoints = testPlayer.getTotalVictoryPoints();
        assertEquals(leaderpoints1 + leaderpoints2 + boardpoints, totalpoints);
    }



}