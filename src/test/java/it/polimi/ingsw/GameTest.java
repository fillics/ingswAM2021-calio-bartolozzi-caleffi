package it.polimi.ingsw;

import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Board.Storage.Warehouse;
import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;
import it.polimi.ingsw.Cards.DevelopmentCards.Level;
import it.polimi.ingsw.Cards.DevelopmentCards.ProductionPower;
import it.polimi.ingsw.Cards.LeaderCards.*;
import it.polimi.ingsw.Exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class GameTest tests Game class.
 *
 * @see Game
 */

class GameTest {

    Game testGame;
    LeaderCard testLeaderCardDiscount;
    LeaderCardStrategy testStrategyDiscount;
    Requirement requirementsDiscount;
    HashMap<CardColor,Integer> colorDiscount;
    boolean choice;
    int i;
    HashMap<ResourceType,Integer> resourcePriceBuffer;
    DevelopmentCard testDevelopmentCard;
    ProductionPower testProductionPower;
    HashMap<ResourceType,Integer> testResourcePrice;
    HashMap<ResourceType,Integer> testResourcesNeeded;
    HashMap<ResourceType,Integer> testResourcesObtained;

    /**
     * Method initialization initializes values.
     */
    @BeforeEach
    void initialization() throws NumMaxPlayersReached {
        testGame = new Game();
        testGame.createNewPlayer("fil");
        testGame.createNewPlayer("bea");
        testGame.createNewPlayer("gio");
        testGame.createNewPlayer("jack");

        colorDiscount= new HashMap<>();
        colorDiscount.put(CardColor.YELLOW,1);
        colorDiscount.put(CardColor.GREEN,1);
        requirementsDiscount= new Requirement(colorDiscount,null,null);
        testStrategyDiscount= new ConcreteStrategyDiscount(ResourceType.SERVANT);
        testLeaderCardDiscount = new LeaderCard(LeaderCardType.DISCOUNT,requirementsDiscount,ResourceType.SERVANT,2);
        testLeaderCardDiscount.setStrategy(testStrategyDiscount);

        testResourcePrice= new HashMap<>();
        testResourcesNeeded = new HashMap<>();
        testResourcesObtained = new HashMap<>();
        testResourcePrice.put(ResourceType.SERVANT,2);
        testResourcesNeeded.put(ResourceType.COIN,1);
        testResourcesObtained.put(ResourceType.FAITHMARKER,1);
        testProductionPower= new ProductionPower(testResourcesNeeded,testResourcesObtained);
        testDevelopmentCard= new DevelopmentCard(Level.ONE, CardColor.PURPLE,testProductionPower,testResourcePrice, 3);
    }

    /**
     * Test method SizeArrayPlayerTest checks if the arrays that contain the players and
     * the active players have the right dimension, after the initialization
     */
    @Test
    void SizeArrayPlayerTest() {
        assertEquals(4, testGame.getActivePlayers().size());
        assertEquals(4, testGame.getPlayers().size());
    }

    /**
     * Method test PlayersPositionTest checks the correct turn's position of the active players
     */
    @Test
    void PlayersPositionTest(){
        assertEquals(1, testGame.getActivePlayers().get(0).getPosition());
        assertEquals(2, testGame.getActivePlayers().get(1).getPosition());
        assertEquals(3, testGame.getActivePlayers().get(2).getPosition());
        assertEquals(4, testGame.getActivePlayers().get(3).getPosition());
    }

    /**
     * Test method AdditionalSetupTest checks if the distribution of resources and faith points to the players
     * at the beginning of the game is correct
     */
    @Test
    void AdditionalSetupTest(){
        testGame.additionalSetup();
        testGame.getActivePlayers().get(1).setChosenResource(1);
        testGame.getActivePlayers().get(2).setChosenResource(2);
        testGame.getActivePlayers().get(3).setChosenResource(3);
        assertEquals(1, testGame.getActivePlayers().get(2).getBoard().getFaithMarker());
        assertEquals(1, testGame.getActivePlayers().get(1).getResourceBuffer().size());
        assertEquals(1, testGame.getActivePlayers().get(3).getBoard().getFaithMarker());
        assertEquals(1, testGame.getActivePlayers().get(2).getResourceBuffer().size());
        assertEquals(2, testGame.getActivePlayers().get(3).getResourceBuffer().size());
    }

    /**
     * Test method SizeDevelopmentGridTest checks if the method createDevelopmentGrid creates
     * the grid with the correct dimension
     */
    @Test
    void SizeDevelopmentGridTest() {
        int bound = testGame.getDevelopmentGrid().size();
        IntStream.range(0, bound).forEach(i -> assertEquals(4, testGame.getDevelopmentGrid().get(i).size()));
    }

    /**
     * Test method SizeLeaderDeckTest checks if the method createLeaderDeck creates
     * the deck with the correct dimension
     */
    @Test
    void SizeLeaderDeckTest() {
        testGame.createLeaderDeck();
        assertEquals(16, testGame.getLeaderDeck().size());
    }

    /**
     * Test method SizeLeaderDeckAfterDistributeTest checks the size of the leader deck
     * after the distribution of the leader cards to each player
     */
    @Test
    void SizeLeaderDeckAfterDistributeTest() {
        testGame.setup();
        assertEquals(16-4*testGame.getActivePlayers().size(), testGame.getLeaderDeck().size());
    }

    /**
     * Test method RightSortDevelopmentCardTest checks if the development grid is sorted in the correct way,
     * with the cards in the right position
     */
    @Test
    void RightSortDevelopmentCardTest() {
        testGame.createDevelopmentGrid();
        assertEquals(CardColor.YELLOW, testGame.getDevelopmentGrid().get(0).get(0).getColor()); //first element first array
        assertEquals(CardColor.YELLOW, testGame.getDevelopmentGrid().get(0).get(3).getColor()); //forth element first array
        assertEquals(CardColor.GREEN, testGame.getDevelopmentGrid().get(1).get(0).getColor()); //first element second array
        assertEquals(CardColor.GREEN, testGame.getDevelopmentGrid().get(1).get(2).getColor()); //third element second array
        assertEquals(CardColor.PURPLE, testGame.getDevelopmentGrid().get(2).get(0).getColor()); //first element third array
        assertEquals(CardColor.PURPLE, testGame.getDevelopmentGrid().get(2).get(3).getColor()); //forth element third array
        assertEquals(CardColor.BLUE, testGame.getDevelopmentGrid().get(3).get(0).getColor()); //first element third array
        assertEquals(CardColor.BLUE, testGame.getDevelopmentGrid().get(3).get(1).getColor()); //second element forth array
    }


    /**
     * Test method LeaderCardsDistributionTest checks if each player receives 4 leader cards at the beginning of the game
     */
    @Test
    void LeaderCardsDistributionTest(){
        testGame.setup();
        for (int i = 0; i < testGame.getActivePlayers().size(); i++) {
            assertEquals(4, testGame.getActivePlayers().get(i).getLeaderCards().size());
        }
    }

    /**
     * Test method ChooseCardFromDevDeckTest checks if the Game's method chooseCardFromDevelopmentGrid returns
     * the selected card
     */
    @Test
    void ChooseCardFromDevDeckTest() throws DevelopmentCardNotFound {
        testGame.setup();
        assertEquals(CardColor.BLUE,testGame.chooseCardFromDevelopmentGrid(CardColor.BLUE, Level.ONE).getColor());
        assertEquals(Level.ONE,testGame.chooseCardFromDevelopmentGrid(CardColor.BLUE, Level.ONE).getLevel());
    }

    /**
     * Test method RemoveDevCardTest checks if the removal of the development card from the grid works
     */
    @Test
    void RemoveDevCardTest() throws DevelopmentCardNotFound {
        testGame.setup();
        DevelopmentCard cardToRemove = testGame.chooseCardFromDevelopmentGrid(CardColor.BLUE, Level.ONE);
        testGame.removeCardFromDevelopmentGrid(cardToRemove);
        assertEquals(3, testGame.getDevelopmentGrid().get(3).size());
        testGame.removeCardFromDevelopmentGrid(cardToRemove);
        assertEquals(2, testGame.getDevelopmentGrid().get(3).size());
        testGame.removeCardFromDevelopmentGrid(cardToRemove);
        testGame.removeCardFromDevelopmentGrid(cardToRemove);
        assertEquals(0, testGame.getDevelopmentGrid().get(3).size());
        assertEquals(4, testGame.getDevelopmentGrid().get(7).size());
    }

    /**
     * Test method NextPlayerTest checks if the method nextPlayer works correctly. It tests what happens
     * when the last player ends his turn.
     */
    @Test
    void NextPlayerTest(){
        testGame.setup();
        assertEquals(0, testGame.getCurrentPlayer());
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).endTurn();
        testGame.nextPlayer();
        assertEquals(1, testGame.getCurrentPlayer());
        testGame.nextPlayer();
        assertEquals(2, testGame.getCurrentPlayer());
        testGame.nextPlayer();
        assertEquals(3, testGame.getCurrentPlayer());
        testGame.nextPlayer();
        assertEquals(0, testGame.getCurrentPlayer());
    }

    /**
     * Test method ActivationLeaderCardTest checks if the activation of the leader cards works correctly
     * @throws LeaderCardNotFound if the player has not got the card to activate
     */
    @Test
    void ActivationLeaderCardTest() throws LeaderCardNotFound {
        assertEquals(0, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().size());
        HashMap<ResourceType,Integer> resourcePrice = new HashMap<>();
        resourcePrice.put(ResourceType.SERVANT,2);
        Requirement requirement = new Requirement(null, Level.ONE, resourcePrice);
        LeaderCard card1 = new LeaderCard(LeaderCardType.DISCOUNT, requirement, ResourceType.COIN, 4);
        card1.setStrategy(new ConcreteStrategyDiscount(ResourceType.COIN));

        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(card1);
        assertEquals(1, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().size());
        testGame.activateLeaderCard(card1);
        assertTrue(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().get(0).getStrategy().isActive());
    }

    /**
     * Test method DiscardLeaderCardTest checks if when a player discards one of his leader cards, his faith marker
     * increases correctly
     * @throws LeaderCardNotFound if the player has not got the card to discard
     */
    @Test
    void DiscardLeaderCardTest() throws LeaderCardNotFound {
        HashMap<ResourceType,Integer> resourcePrice = new HashMap<>();
        resourcePrice.put(ResourceType.COIN,2);
        Requirement requirement = new Requirement(null, Level.TWO, resourcePrice);
        LeaderCard card1 = new LeaderCard(LeaderCardType.WHITE_MARBLE, requirement, ResourceType.SERVANT, 4);
        card1.setStrategy(new ConcreteStrategyDiscount(ResourceType.COIN));
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(card1);

        assertEquals(0, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getFaithMarker());
        testGame.discardLeaderCard(card1);
        assertEquals(1, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getFaithMarker());
    }

    /**
     * Test method ChooseLeaderCardTest checks if the Game's method chooseLeaderCards works correctly and if at the end of
     * the method, the player has only two leader cards.
     * @throws LeaderCardNotFound if the player has not got the two chosenCards
     */
    @Test
    void ChooseLeaderCardTest() throws LeaderCardNotFound {
        HashMap<ResourceType,Integer> resourcePrice1 = new HashMap<>();
        resourcePrice1.put(ResourceType.COIN,2);
        HashMap<ResourceType,Integer> resourcePrice2 = new HashMap<>();
        resourcePrice2.put(ResourceType.SERVANT,1);
        HashMap<ResourceType,Integer> resourcePrice3 = new HashMap<>();
        resourcePrice3.put(ResourceType.SHIELD,3);
        HashMap<ResourceType,Integer> resourcePrice4 = new HashMap<>();
        resourcePrice4.put(ResourceType.STONE,1);
        HashMap<CardColor,Integer> color;
        color = new HashMap<>();
        color.put(CardColor.YELLOW,1);
        Requirement requirement1 = new Requirement(null, Level.TWO, resourcePrice1);
        Requirement requirement2 = new Requirement(color, Level.TWO, resourcePrice2);
        Requirement requirement3 = new Requirement(null, Level.ONE, resourcePrice3);
        Requirement requirement4 = new Requirement(color, null, resourcePrice4);

        LeaderCard card1 = new LeaderCard(LeaderCardType.WHITE_MARBLE, requirement1, ResourceType.SERVANT, 4);
        card1.setStrategy(new ConcreteStrategyDiscount(ResourceType.COIN));
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(card1);

        LeaderCard card2 = new LeaderCard(LeaderCardType.DISCOUNT, requirement2, ResourceType.COIN, 2);
        card2.setStrategy(new ConcreteStrategyDiscount(ResourceType.SHIELD));
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(card2);

        LeaderCard card3 = new LeaderCard(LeaderCardType.EXTRA_DEPOSIT, requirement3, ResourceType.SHIELD, 1);
        card3.setStrategy(new ConcreteStrategyDiscount(ResourceType.COIN));
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(card3);

        LeaderCard card4 = new LeaderCard(LeaderCardType.WHITE_MARBLE, requirement4, ResourceType.STONE, 3);
        card4.setStrategy(new ConcreteStrategyDiscount(ResourceType.COIN));
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(card4);

        assertEquals(4, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().size());
        testGame.chooseLeaderCard(card2, card4);
        assertEquals(2, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().size());

        assertTrue(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().contains(card2));
        assertTrue(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().contains(card4));
        assertFalse(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().contains(card1));
        assertFalse(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().contains(card3));
    }

    /** Method chooseDiscountActivationTest tests Game method chooseDiscountActivation. */
    @Test
    @DisplayName("chooseDiscountActivation test")
    void chooseDiscountActivationTest() throws DiscountCannotBeActivated {
        choice= true;
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(testLeaderCardDiscount);
        i= testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().indexOf(testLeaderCardDiscount);
        assertEquals(1, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().size());
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().get(i).useAbility();

        testGame.chooseDiscountActivation(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().get(i),choice);
        assertEquals(testLeaderCardDiscount.getUseDiscountChoice(),choice);
    }

    /** Method checkDiscountActivatedTest tests Game method checkDiscountActivated. */
    @Test
    @DisplayName("checkDiscountActivated test")
    void checkDiscountActivatedTest() throws DiscountCannotBeActivated {
        choice= true;
        resourcePriceBuffer= new HashMap<>();
        resourcePriceBuffer.putAll(testDevelopmentCard.getResourcePrice());
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(testLeaderCardDiscount);
        i = testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().indexOf(testLeaderCardDiscount);
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().get(i).useAbility();
        testGame.chooseDiscountActivation(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().get(i),choice);

        testGame.checkAndUseDiscount(resourcePriceBuffer,testDevelopmentCard);
        assertTrue(resourcePriceBuffer.containsKey(ResourceType.SERVANT));
        assertEquals(resourcePriceBuffer.get(ResourceType.SERVANT),1);
    }

    /** Method buyDevCardTest tests Game method buyDevCard. */
    @Test
    @DisplayName("buyDevCard test")
    void buyDevCardTest() throws DiscountCannotBeActivated {
        ArrayList<ResourceType> chosenResources1 = new ArrayList<>();
        ArrayList<Warehouse> chosenWarehouses1 = new ArrayList<>();
        ArrayList<ResourceType> chosenResources2 = new ArrayList<>();
        ArrayList<Warehouse> chosenWarehouses2= new ArrayList<>();
        ArrayList<ResourceType> chosenResources3 = new ArrayList<>();
        ArrayList<Warehouse> chosenWarehouses3= new ArrayList<>();
        ArrayList<ResourceType> chosenResources4 = new ArrayList<>();
        ArrayList<Warehouse> chosenWarehouses4= new ArrayList<>();
        testGame.setup();
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getStrongbox().put(ResourceType.SERVANT,4);
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getStrongbox().put(ResourceType.STONE,2);
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getStrongbox().put(ResourceType.COIN,1);
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getStrongbox().put(ResourceType.SHIELD,1);
        chosenResources1.add(ResourceType.SERVANT);
        chosenWarehouses1.add( testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox());


        chosenResources2.add(ResourceType.SERVANT);
        chosenWarehouses2.add( testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox());
        chosenResources2.add(ResourceType.SERVANT);
        chosenWarehouses2.add( testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox());


        chosenResources3.add(ResourceType.SERVANT);
        chosenWarehouses3.add( testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox());
        chosenResources3.add(ResourceType.STONE);
        chosenWarehouses3.add( testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox());
        chosenResources3.add(ResourceType.STONE);
        chosenWarehouses3.add( testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox());


        chosenResources4.add(ResourceType.COIN);
        chosenWarehouses4.add( testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox());
        chosenResources4.add(ResourceType.SHIELD);
        chosenWarehouses4.add( testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox());

        choice= true;
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(testLeaderCardDiscount);
        i= testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().indexOf(testLeaderCardDiscount);
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().get(i).useAbility();
        testGame.chooseDiscountActivation(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().get(i),choice);

        try {
            testGame.buyDevCard(CardColor.PURPLE,Level.ONE, chosenResources1, chosenWarehouses1, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDevelopmentSpaces().get(0));
            assertEquals(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getTotalServants(),3);
        } catch (DevelopmentCardNotFound | DevCardNotPlaceable | NotEnoughResources | WrongChosenResources | DifferentDimension problemsToBuy) {
            problemsToBuy.printStackTrace();
            try {
                testGame.buyDevCard(CardColor.PURPLE,Level.ONE, chosenResources2, chosenWarehouses2, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDevelopmentSpaces().get(0));
                assertEquals(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getTotalServants(),2);
            } catch (DevelopmentCardNotFound | DevCardNotPlaceable | NotEnoughResources | WrongChosenResources | DifferentDimension problemsToBuy2) {
                problemsToBuy2.printStackTrace();
                try {
                    testGame.buyDevCard(CardColor.PURPLE,Level.ONE, chosenResources3, chosenWarehouses3, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDevelopmentSpaces().get(0));
                    assertEquals(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getTotalServants(),3);
                } catch (DevelopmentCardNotFound | DevCardNotPlaceable | NotEnoughResources | WrongChosenResources | DifferentDimension problemsToBuy3) {
                    problemsToBuy3.printStackTrace();
                    try {
                        testGame.buyDevCard(CardColor.PURPLE,Level.ONE, chosenResources4, chosenWarehouses4, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDevelopmentSpaces().get(0));
                        assertEquals(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getTotalServants(),4);
                    } catch (DevelopmentCardNotFound | DevCardNotPlaceable | NotEnoughResources | WrongChosenResources | DifferentDimension problemsToBuy4) {
                        problemsToBuy4.printStackTrace();
                    }
                }
            }
        }
        assertEquals(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDevelopmentSpaces().get(0).getDevelopmentSpace().size(),1);
    }

    /** Method chooseWhiteMarbleActivationTest tests Game method chooseWhiteMarbleActivation. */
    @Test
    @DisplayName("chooseWhiteMarbleActivation test")
    void chooseWhiteMarbleActivationTest(){
        ArrayList<LeaderCard> whiteMarbleCardChoice= new ArrayList<>();
        ArrayList<Boolean> whiteMarbleChoice= new ArrayList<>();
        HashMap<CardColor,Integer> colorWhiteMarble= new HashMap<>();
        colorWhiteMarble.put(CardColor.GREEN,2);
        colorWhiteMarble.put(CardColor.PURPLE,1);
        Requirement requirementsWhiteMarble= new Requirement(colorWhiteMarble,null,null);
        LeaderCardStrategy testStrategyWhiteMarble= new ConcreteStrategyMarble(ResourceType.SHIELD);
        LeaderCard testLeaderCardWhiteMarble= new LeaderCard(LeaderCardType.WHITE_MARBLE,requirementsWhiteMarble,ResourceType.SHIELD,5);
        testLeaderCardWhiteMarble.setStrategy(testStrategyWhiteMarble);
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(testLeaderCardWhiteMarble);

        HashMap<CardColor,Integer> colorWhiteMarble2= new HashMap<>();
        colorWhiteMarble2.put(CardColor.YELLOW,2);
        colorWhiteMarble2.put(CardColor.BLUE,1);
        Requirement requirementsWhiteMarble2= new Requirement(colorWhiteMarble2,null,null);
        LeaderCardStrategy testStrategyWhiteMarble2= new ConcreteStrategyMarble(ResourceType.SERVANT);
        LeaderCard testLeaderCardWhiteMarble2= new LeaderCard(LeaderCardType.WHITE_MARBLE,requirementsWhiteMarble2,ResourceType.SERVANT,5);
        testLeaderCardWhiteMarble2.setStrategy(testStrategyWhiteMarble2);
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(testLeaderCardWhiteMarble2);

        testLeaderCardWhiteMarble.useAbility();
        testLeaderCardWhiteMarble2.useAbility();

        whiteMarbleCardChoice.add(testLeaderCardWhiteMarble);
        whiteMarbleCardChoice.add(testLeaderCardWhiteMarble2);
        whiteMarbleChoice.add(true);
        whiteMarbleChoice.add(true);

        try {
            testGame.chooseWhiteMarbleActivation(whiteMarbleCardChoice,whiteMarbleChoice);
            assertEquals(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getWhiteMarbleCardChoice(), whiteMarbleCardChoice);
            assertEquals(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getWhiteMarbleChoice(), whiteMarbleChoice);
        } catch (DifferentDimension | LeaderCardNotFound | LeaderCardNotActivated problems) {
            problems.printStackTrace();
        }

    }

    /** Method checkPlayersFaithMarkersTest tests Game method checkPlayersFaithMarkers. */
    @Test
    @DisplayName("checkPlayersFaithMarkersTest test")
    void checkPlayersFaithMarkersTest() {
        for(int k = 0;  k < 3; k++){
            for(int j = 0; j< 4; j++){
                assertFalse(testGame.getActivePlayers().get(j).getBoard().getVaticanReportSections().get(k).isActivated());
                assertFalse(testGame.getActivePlayers().get(j).getBoard().getVaticanReportSections().get(k).getPopefavortile().isVisible());
            }
        }
        for(int i = 0; i < 24; i++){
            testGame.getActivePlayers().get(0).getBoard().increaseFaithMarker();
            testGame.getActivePlayers().get(2).getBoard().increaseFaithMarker();
        }
        for(int k = 0;  k < 3; k++){
            for(int j = 0; j< 4; j++){
                assertTrue(testGame.getActivePlayers().get(j).getBoard().getVaticanReportSections().get(k).isActivated());
            }
            assertTrue(testGame.getActivePlayers().get(0).getBoard().getVaticanReportSections().get(k).getPopefavortile().isVisible());
            assertFalse(testGame.getActivePlayers().get(1).getBoard().getVaticanReportSections().get(k).getPopefavortile().isVisible());
            assertTrue(testGame.getActivePlayers().get(2).getBoard().getVaticanReportSections().get(k).getPopefavortile().isVisible());
            assertFalse(testGame.getActivePlayers().get(3).getBoard().getVaticanReportSections().get(k).getPopefavortile().isVisible());
        }
    }
}