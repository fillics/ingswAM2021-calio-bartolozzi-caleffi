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
    LeaderCardStrategy testStrategyProdPower;
    LeaderCardStrategy testStrategyDeposit;
    HashMap<ResourceType,Integer> resourceNeeded;
    Board board;
    LeaderCardType typeProdPower;
    LeaderCardType typeDeposit;
    Requirement requirementsProdPower;
    Requirement requirementsDeposit;
    ResourceType resourceType;
    HashMap<CardColor,Integer> color;
    HashMap<ResourceType,Integer> resourcePrice;
    Level level;
    int victoryPointProdPower;
    int victoryPointDeposit;

    /*
     * Method setup setups tests.
     * */
    @BeforeEach
    void setup(){
        color= new HashMap<>();
        color.put(CardColor.YELLOW,1);
        level= Level.TWO;
        requirementsProdPower = new Requirement(color,level,null);
        resourceType= ResourceType.SHIELD;
        typeProdPower = LeaderCardType.PRODUCTION_POWER;
        typeDeposit = LeaderCardType.EXTRA_DEPOSIT;
        resourceNeeded= new HashMap<>();
        resourceNeeded.put(ResourceType.SHIELD,1);
        resourcePrice= new HashMap<>();
        resourcePrice.put(ResourceType.SERVANT,5);
        requirementsDeposit = new Requirement(null,null,resourcePrice);
        board= new Board();
        victoryPointProdPower = 4;
        victoryPointDeposit = 3;
        testStrategyProdPower = new ConcreteStrategyProductionPower(resourceNeeded,board);
        testStrategyDeposit = new ConcreteStrategyDeposit(resourceType,board);
        testLeaderCardProdPower = new LeaderCard(typeProdPower, requirementsProdPower,resourceType, victoryPointProdPower);
        testLeaderCardDeposit = new LeaderCard(typeDeposit, requirementsDeposit,resourceType, victoryPointDeposit);
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
        assertEquals(board.getSpecialProductionPowers().size(),1);
        testLeaderCardDeposit.setStrategy(testStrategyDeposit);
        testLeaderCardDeposit.useAbility();
        testLeaderCardDeposit.useAbility();
        assertEquals(board.getDeposits().size(),4);
    }
}
