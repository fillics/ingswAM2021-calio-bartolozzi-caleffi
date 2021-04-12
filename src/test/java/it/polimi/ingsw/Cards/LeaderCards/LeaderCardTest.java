package it.polimi.ingsw.Cards.LeaderCards;

import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.Cards.DevelopmentCards.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RequirementTest class tests Requirement class.
 * @see LeaderCard
 */
public class LeaderCardTest {
    LeaderCard testLeaderCardProdPower;
    LeaderCard testLeaderCardDeposit;
    LeaderCard testLeaderCardWhiteMarble;
    LeaderCardStrategy testStrategyProdPower;
    LeaderCardStrategy testStrategyDeposit;
    LeaderCardStrategy testStrategyWhiteMarble;
    HashMap<ResourceType,Integer> resourceNeeded;
    Board board;
    Requirement requirementsProdPower;
    Requirement requirementsDeposit;
    Requirement requirementsWhiteMarble;
    HashMap<CardColor,Integer> color;
    HashMap<CardColor,Integer> colorWhiteMarble;
    HashMap<ResourceType,Integer> resourcePrice;

    /*
     * Method setup setups tests.
     * */
    @BeforeEach
    void setup(){
        color= new HashMap<>();
        color.put(CardColor.YELLOW,1);
        colorWhiteMarble= new HashMap<>();
        colorWhiteMarble.put(CardColor.GREEN,2);
        colorWhiteMarble.put(CardColor.PURPLE,1);
        requirementsProdPower = new Requirement(color,Level.TWO,null);
        requirementsWhiteMarble= new Requirement(colorWhiteMarble,null,null);
        resourceNeeded= new HashMap<>();
        resourceNeeded.put(ResourceType.SHIELD,1);
        resourcePrice= new HashMap<>();
        resourcePrice.put(ResourceType.SERVANT,5);
        requirementsDeposit = new Requirement(null,null,resourcePrice);
        board= new Board();
        testStrategyProdPower = new ConcreteStrategyProductionPower(resourceNeeded,board);
        testStrategyDeposit = new ConcreteStrategyDeposit(ResourceType.SHIELD,board);
        testStrategyWhiteMarble= new ConcreteStrategyMarble(ResourceType.SHIELD);
        testLeaderCardProdPower = new LeaderCard(LeaderCardType.PRODUCTION_POWER, requirementsProdPower,ResourceType.SHIELD, 4);
        testLeaderCardDeposit = new LeaderCard(LeaderCardType.EXTRA_DEPOSIT, requirementsDeposit,ResourceType.SHIELD, 3);
        testLeaderCardWhiteMarble= new LeaderCard(LeaderCardType.WHITE_MARBLE,requirementsWhiteMarble,ResourceType.SHIELD,5);
    }

    /** Method VictoryPointGetterTest tests LeaderCard method getter. */
    @Test
    @DisplayName("VictoryPoint getter test")
    void VictoryPointGetterTest() {
        assertEquals(testLeaderCardProdPower.getVictoryPoint(),4);
    }

    /** Method setStrategyTest tests LeaderCard method setStrategy. */
    @Test
    @DisplayName("setStrategy test")
    void setStrategyTest() {
        testLeaderCardProdPower.setStrategy(testStrategyProdPower);
    }

    /** Method useAbilityTest tests LeaderCard method useAbility. */
    @Test
    @DisplayName("useAbility test")
    void useAbilityTest(){
        testLeaderCardProdPower.setStrategy(testStrategyProdPower);
        testLeaderCardProdPower.useAbility();
        testLeaderCardProdPower.useAbility();
        assertEquals(board.getSpecialProductionPowers().size(),2);

        testLeaderCardDeposit.setStrategy(testStrategyDeposit);
        testLeaderCardDeposit.useAbility();
        testLeaderCardDeposit.useAbility();
        assertEquals(board.getDeposits().size(),4);

        testLeaderCardWhiteMarble.setStrategy(testStrategyWhiteMarble);
        testLeaderCardWhiteMarble.useAbility();
        assertTrue(testStrategyWhiteMarble.isActive());
        testLeaderCardWhiteMarble.useAbility();
    }
}
