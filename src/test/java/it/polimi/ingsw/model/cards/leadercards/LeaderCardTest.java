package it.polimi.ingsw.model.cards.leadercards;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.cards.developmentcards.CardColor;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentCard;
import it.polimi.ingsw.model.cards.developmentcards.Level;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;
import it.polimi.ingsw.model.Game;
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
    Game testGame;
    LeaderCard testLeaderCardProdPower;
    LeaderCard testLeaderCardDeposit;
    LeaderCard testLeaderCardWhiteMarble;
    LeaderCard testLeaderCardDiscount;
    LeaderCardStrategy testStrategyProdPower;
    LeaderCardStrategy testStrategyDeposit;
    LeaderCardStrategy testStrategyWhiteMarble;
    LeaderCardStrategy testStrategyDiscount;
    HashMap<ResourceType,Integer> resourceNeeded;
    Board board;
    Requirement requirementsProdPower;
    Requirement requirementsDeposit;
    Requirement requirementsWhiteMarble;
    Requirement requirementsDiscount;
    HashMap<CardColor,Integer> color;
    HashMap<CardColor,Integer> colorWhiteMarble;
    HashMap<CardColor,Integer> colorDiscount;
    HashMap<ResourceType,Integer> resourcePriceDeposit;
    boolean choice1;
    boolean choice2;
    DevelopmentCard DevelopmentCard;
    ProductionPower ProductionPower;
    HashMap<ResourceType,Integer> ResourcePrice;
    HashMap<ResourceType,Integer> ResourcesNeeded;
    HashMap<ResourceType,Integer> ResourcesObtained;
    HashMap<ResourceType,Integer> resourcePriceBuffer;

    /*
     * Method setup setups tests.
     * */
    @BeforeEach
    void setup(){
        testGame = new Game();
        color= new HashMap<>();
        color.put(CardColor.YELLOW,1);
        colorWhiteMarble= new HashMap<>();
        colorWhiteMarble.put(CardColor.GREEN,2);
        colorWhiteMarble.put(CardColor.PURPLE,1);
        colorDiscount= new HashMap<>();
        colorDiscount.put(CardColor.YELLOW,1);
        colorDiscount.put(CardColor.GREEN,1);
        requirementsProdPower = new LevelAndColorRequirement(color, Level.TWO);
        requirementsWhiteMarble= new NumAndColorRequirement(colorWhiteMarble);
        requirementsDiscount= new NumAndColorRequirement(colorDiscount);
        resourceNeeded= new HashMap<>();
        resourceNeeded.put(ResourceType.SHIELD,1);
        resourcePriceDeposit = new HashMap<>();
        resourcePriceDeposit.put(ResourceType.SERVANT,5);
        requirementsDeposit = new ResourcesRequirement(resourcePriceDeposit);
        board= new Board(testGame);
        testStrategyProdPower = new ConcreteStrategyProductionPower(board, ResourceType.SHIELD);
        testStrategyDeposit = new ConcreteStrategyDeposit(ResourceType.SHIELD,board);
        testStrategyWhiteMarble= new ConcreteStrategyMarble(ResourceType.SHIELD);
        testStrategyDiscount= new ConcreteStrategyDiscount(ResourceType.SERVANT);
        testLeaderCardProdPower = new LeaderCard(1,LeaderCardType.PRODUCTION_POWER, requirementsProdPower,ResourceType.SHIELD, 4);
        testLeaderCardDeposit = new LeaderCard(2,LeaderCardType.EXTRA_DEPOSIT, requirementsDeposit,ResourceType.SHIELD, 3);
        testLeaderCardWhiteMarble= new LeaderCard(3,LeaderCardType.WHITE_MARBLE,requirementsWhiteMarble,ResourceType.SHIELD,5);
        testLeaderCardDiscount= new LeaderCard(4,LeaderCardType.DISCOUNT,requirementsDiscount,ResourceType.SERVANT,2);
        choice1= true;
        choice2= false;
        ResourcePrice= new HashMap<>();
        ResourcesNeeded = new HashMap<>();
        ResourcesObtained = new HashMap<>();
        ResourcePrice.put(ResourceType.SERVANT,2);
        ResourcesNeeded.put(ResourceType.COIN,1);
        ResourcesObtained.put(ResourceType.FAITHMARKER,1);
        ProductionPower= new ProductionPower(ResourcesNeeded,ResourcesObtained);
        DevelopmentCard= new DevelopmentCard(1,Level.ONE, CardColor.PURPLE,ProductionPower,ResourcePrice, 3);
        resourcePriceBuffer=new HashMap<>();
    }

    /** Method VictoryPointGetterTest tests LeaderCard method getter. */
    @Test
    @DisplayName("VictoryPoint getter test")
    void VictoryPointGetterTest() {
        assertEquals(testLeaderCardProdPower.getVictorypoint(),4);
    }

    /** Method StrategySetterTest tests LeaderCard method Strategy setter. */
    @Test
    @DisplayName("Strategy setter test")
    void StrategySetterTest() {
        testLeaderCardProdPower.setStrategy(testStrategyProdPower);
    }

    /** Method StrategyGetterTest tests LeaderCard method strategy getter. */
    @Test
    @DisplayName("Strategy getter test")
    void StrategyGetterTest() {
        testLeaderCardDiscount.setStrategy(testStrategyDiscount);
        assertEquals(testLeaderCardDiscount.getStrategy(),testStrategyDiscount);
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

        testLeaderCardDiscount.setStrategy(testStrategyDiscount);
        testLeaderCardDiscount.useAbility();
        assertTrue(testStrategyDiscount.isActive());
    }

    /** Method useDiscountTest tests LeaderCard method useDiscount. */
    @Test
    @DisplayName("useDiscount test")
    void useDiscountTest() {
        resourcePriceBuffer.putAll(DevelopmentCard.getResourcePrice());
        testLeaderCardDiscount.setStrategy(testStrategyDiscount);
        testLeaderCardDiscount.useDiscount(DevelopmentCard,resourcePriceBuffer);
        assertEquals(resourcePriceBuffer.get(ResourceType.SERVANT),1);
        assertEquals(ResourcePrice.get(ResourceType.SERVANT),2);
    }

    @Test
    @DisplayName(" print test")
    void printTest(){
        testLeaderCardDiscount.setStrategy(testStrategyDiscount);
        testLeaderCardDeposit.setStrategy(testStrategyDeposit);
        testLeaderCardWhiteMarble.setStrategy(testStrategyWhiteMarble);
        testLeaderCardProdPower.setStrategy(testStrategyProdPower);
        testLeaderCardWhiteMarble.dump();
        testLeaderCardProdPower.dump();
        testLeaderCardDeposit.dump();
        testLeaderCardDiscount.dump();
    }
}
