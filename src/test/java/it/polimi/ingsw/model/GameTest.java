package it.polimi.ingsw.model;

import it.polimi.ingsw.model.board.resources.ConcreteStrategyResource;
import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.model.cards.developmentcards.CardColor;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentCard;
import it.polimi.ingsw.model.cards.developmentcards.Level;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.cards.leadercards.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class GameTest tests Game class.
 * @see Game
 */

class GameTest {

    Game testGame;

    LeaderCard testLeaderCardDiscount;
    LeaderCard testLeaderCardWhiteMarble;
    LeaderCard testLeaderCardProdPower;
    LeaderCard testLeaderCardThatDoesNotExists;
    NumAndColorRequirement requirementsDiscount;
    HashMap<CardColor,Integer> colorDiscount;
    HashMap<CardColor,Integer> colorProdPower;
    LeaderCard testLeaderCardExtraDep;
    ResourcesRequirement requirementsExtraDep;
    HashMap<ResourceType,Integer> testResourcePrice;
    ArrayList<Integer> leaderCardsChosen;
    LevelAndColorRequirement requirementsLevCol;


    boolean choice;
    int i;
    HashMap<ResourceType,Integer> resourcePriceBuffer;
    DevelopmentCard testDevelopmentCard;
    ProductionPower testProductionPower;

    HashMap<ResourceType,Integer> testResourcesNeeded;
    HashMap<ResourceType,Integer> testResourcesObtained;

    /**
     * Method initialization initializes values.
     */
    @BeforeEach
    void initialization(){
        testGame = new Game();
        testGame.createNewPlayer("fil",0);
        testGame.createNewPlayer("bea",1);
        testGame.createNewPlayer("gio",2);
        testGame.createNewPlayer("jack",3);

        colorDiscount= new HashMap<>();
        colorProdPower = new HashMap<>();
        colorDiscount.put(CardColor.YELLOW,1);
        colorDiscount.put(CardColor.GREEN,1);
        colorProdPower.put(CardColor.BLUE, 1);
        testResourcePrice= new HashMap<>();
        testResourcePrice.put(ResourceType.SERVANT,2);
        testResourcesNeeded = new HashMap<>();
        testResourcesObtained = new HashMap<>();
        testResourcesNeeded.put(ResourceType.COIN,1);
        testResourcesObtained.put(ResourceType.FAITHMARKER,1);
        testProductionPower= new ProductionPower(testResourcesNeeded,testResourcesObtained);
        testDevelopmentCard= new DevelopmentCard(2,Level.ONE, CardColor.PURPLE,testProductionPower,testResourcePrice, 3, "");

        //creating 4 leader cards useful for some tests
        requirementsDiscount= new NumAndColorRequirement(colorDiscount);
        testLeaderCardDiscount = new LeaderCard(1,LeaderCardType.DISCOUNT,requirementsDiscount,ResourceType.SERVANT,2, "",0,0);
        testLeaderCardDiscount.setStrategy(new ConcreteStrategyDiscount(ResourceType.SERVANT));

        requirementsExtraDep = new ResourcesRequirement(testResourcePrice);
        testLeaderCardExtraDep = new LeaderCard(2, LeaderCardType.EXTRA_DEPOSIT, requirementsExtraDep, ResourceType.COIN, 5, "",0,0);
        testLeaderCardExtraDep.setStrategy(new ConcreteStrategyDeposit(ResourceType.SERVANT, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard()));

        testLeaderCardWhiteMarble = new LeaderCard(3, LeaderCardType.WHITE_MARBLE, requirementsDiscount, ResourceType.SHIELD,3, "",0,0);
        testLeaderCardWhiteMarble.setStrategy(new ConcreteStrategyMarble(ResourceType.SHIELD));

        requirementsLevCol = new LevelAndColorRequirement(colorProdPower, Level.TWO);
        testLeaderCardProdPower = new LeaderCard(4, LeaderCardType.PRODUCTION_POWER, requirementsLevCol, ResourceType.STONE, 4, "",0,0);
        testLeaderCardProdPower.setStrategy(new ConcreteStrategyProductionPower(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard(),ResourceType.SHIELD));

        testLeaderCardThatDoesNotExists = new LeaderCard(454, LeaderCardType.PRODUCTION_POWER, requirementsLevCol, ResourceType.STONE, 4,"",0,0);

        leaderCardsChosen = new ArrayList<>();
        leaderCardsChosen.add(testLeaderCardDiscount.getId());
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

    @Test
    void checkGetLiteDevGrid() throws DevelopmentCardNotFound {
        testGame.createDevelopmentGrid();
        assertEquals(12, testGame.getDevGridLite().size());

        testGame.removeCardFromDevelopmentGrid(testGame.getDevelopmentGrid().get(2).getLast());
        assertEquals(12, testGame.getDevGridLite().size());
        testGame.removeCardFromDevelopmentGrid(testGame.getDevelopmentGrid().get(2).getLast());
        testGame.removeCardFromDevelopmentGrid(testGame.getDevelopmentGrid().get(2).getLast());
        testGame.removeCardFromDevelopmentGrid(testGame.getDevelopmentGrid().get(2).getLast());
        /*for (int i = 0; i < 12; i++) {
            System.out.println(testGame.getDevGridLite().get(i));
        }*/
        assertEquals(12, testGame.getDevGridLite().size());
        assertNull(testGame.getDevGridLite().get(2));
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
        //System.out.println(testGame.getDevelopmentGrid().get(0).getFirst().getProductionPower().getResourceNeeded());
        //System.out.println(testGame.getDevelopmentGrid().get(0).getFirst().getVictorypoint());
    }

    /** Method checkUsername tests username's getter. */
    @Test
    void checkUsername() {
        assertEquals("fil", testGame.getActivePlayers().get(0).getUsername());
        assertEquals("bea", testGame.getActivePlayers().get(1).getUsername());
        assertEquals("gio", testGame.getActivePlayers().get(2).getUsername());
        assertEquals("jack", testGame.getActivePlayers().get(3).getUsername());

    }

    /** Method checkTotalVictoryPoints tests totalVictoryPoints's getter for each player. */
    @Test
    void checkTotalVictoryPoints(){
        IntStream.range(0, testGame.getActivePlayers().size()).forEach(j -> assertEquals(0, testGame.getActivePlayers().get(j).getTotalVictoryPoints()));
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
        int idCard = testGame.getDevelopmentGrid().get(0).getLast().getId();
        assertEquals(testGame.getDevelopmentGrid().get(0).getLast(), testGame.chooseCardFromDevelopmentGrid(idCard));
    }

    /**
     * Test method RemoveDevCardTest checks if the removal of the development card from the grid works
     */
    @Test
    void RemoveDevCardTest() throws DevelopmentCardNotFound {
        testGame.setup();
        DevelopmentCard cardToRemove;
        for (int j = 3; j >= 0; j--) {
            cardToRemove = testGame.chooseCardFromDevelopmentGrid(testGame.getDevelopmentGrid().get(3).getLast().getId());
            testGame.removeCardFromDevelopmentGrid(cardToRemove);
            assertEquals(j, testGame.getDevelopmentGrid().get(3).size());
        }

        assertEquals(4, testGame.getDevelopmentGrid().get(7).size());
    }

    /**
     * Test method NextPlayerTest checks if the method nextPlayer works correctly. It tests what happens
     * when the last player ends his turn.
     */
    @Test
    void NextPlayerTest(){
        /*testGame.setup();
        assertEquals(0, testGame.getCurrentPlayer());
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).endTurn();
        testGame.nextPlayer();
        assertEquals(1, testGame.getCurrentPlayer());
        testGame.nextPlayer();
        assertEquals(2, testGame.getCurrentPlayer());
        testGame.nextPlayer();
        assertEquals(3, testGame.getCurrentPlayer());
        testGame.nextPlayer();
        assertEquals(0, testGame.getCurrentPlayer());*/
    }

    /**
     * Test method ActivationLeaderCardExtraDepTest checks if the activation of the leader card with the ability of
     *  the extra deposit works correctly
     * @throws LeaderCardNotFound if the player has not got the card to activate
     */
    @Test
    void ActivationLeaderCardExtraDepTest() throws LeaderCardNotFound, NotEnoughRequirements, DepositHasReachedMaxLimit, DepositHasAnotherResource, AnotherDepositContainsThisResource, InvalidResource {
        assertEquals(0, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().size());

        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(testLeaderCardExtraDep);
        assertEquals(1, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().size());

        Resource resource1 = new Resource(ResourceType.SERVANT);
        resource1.setStrategy(new ConcreteStrategyResource(2,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard(),resource1.getType()));
        resource1.useResource();
        resource1.useResource();

        testGame.activateLeaderCard(testLeaderCardExtraDep.getId());
        assertTrue(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().get(0).getStrategy().isActive());
    }

    /**
     * Test method ActivationLeaderCardWhiteMarble checks if the activation of the leader card with the ability of
     *  the changing the resource of the white marble works correctly
     * @throws LeaderCardNotFound if the player has not got the card to activate
     */
    @Test
    void ActivationLeaderCardWhiteMarble() throws LeaderCardNotFound, NotEnoughRequirements {
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(testLeaderCardWhiteMarble);

        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDevelopmentSpaces().get(0).
                addDevelopmentCard(new DevelopmentCard(1, Level.ONE, CardColor.GREEN, testProductionPower, testResourcePrice, 3,""));

        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDevelopmentSpaces().get(1).
                addDevelopmentCard(new DevelopmentCard(2, Level.ONE, CardColor.YELLOW, testProductionPower, testResourcePrice, 3, ""));

        testGame.activateLeaderCard(testLeaderCardWhiteMarble.getId());
        assertTrue(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().get(0).getStrategy().isActive());
    }

    /**
     * Test method ActivationLeaderCardDiscount checks if the activation of the leader card with the ability of
     *  the discount works correctly
     * @throws LeaderCardNotFound if the player has not got the card to activate
     */
    @Test
    void ActivationLeaderCardDiscount() throws LeaderCardNotFound, NotEnoughRequirements {
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(testLeaderCardDiscount);

        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDevelopmentSpaces().get(0).
                addDevelopmentCard(new DevelopmentCard(1, Level.ONE, CardColor.GREEN, testProductionPower, testResourcePrice, 3, ""));

        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDevelopmentSpaces().get(1).
                addDevelopmentCard(new DevelopmentCard(2, Level.ONE, CardColor.YELLOW, testProductionPower, testResourcePrice, 3, ""));

        testGame.activateLeaderCard(testLeaderCardDiscount.getId());
        assertTrue(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().get(0).getStrategy().isActive());

    }

    /**
     * Test method ActivationLeaderCardProdPower checks if the activation of the leader card with the ability of
     *  the extra production power works correctly
     * @throws LeaderCardNotFound if the player has not got the card to activate
     */
    @Test
    void ActivationLeaderCardProdPower() throws LeaderCardNotFound, NotEnoughRequirements {
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(testLeaderCardProdPower);

        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDevelopmentSpaces().get(0).
                addDevelopmentCard(new DevelopmentCard(6, Level.TWO, CardColor.BLUE, testProductionPower, testResourcePrice, 3, ""));

        testGame.activateLeaderCard(testLeaderCardProdPower.getId());
        assertTrue(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().get(0).getStrategy().isActive());

    }

    /** Method ActivationLeaderTestExceptions tests the exceptions of a leader card activation. */
    @Test
    @DisplayName("ActivationLeaderTestExceptions test")
    void ActivationLeaderTestExceptions() {
        //NotEnoughRequirement exception
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(testLeaderCardProdPower);

        try {
            testGame.activateLeaderCard(testLeaderCardProdPower.getId());
            fail();
        } catch (LeaderCardNotFound | NotEnoughRequirements ignore) {}

        //LeaderCardNotFound exception
        try {
            testGame.activateLeaderCard(testLeaderCardThatDoesNotExists.getId());
            fail();
        } catch (LeaderCardNotFound | NotEnoughRequirements ignored) {
        }
    }

    /**
     * Test method DiscardLeaderCardTest checks if when a player discards one of his leader cards, his faith marker
     * increases correctly
     * @throws LeaderCardNotFound if the player has not got the card to discard
     */
    @Test
    void DiscardLeaderCardTest() throws LeaderCardNotFound, LeaderCardIsActive {
        HashMap<ResourceType,Integer> resourcePrice = new HashMap<>();
        resourcePrice.put(ResourceType.COIN,2);
        ResourcesRequirement requirement = new ResourcesRequirement(resourcePrice);
        LeaderCard card1 = new LeaderCard(1,LeaderCardType.WHITE_MARBLE, requirement, ResourceType.SERVANT, 4, "",0,0);
        card1.setStrategy(new ConcreteStrategyDiscount(ResourceType.COIN));
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(card1);

        assertEquals(0, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getFaithMarker());
        testGame.discardLeaderCard(card1.getId());
        assertEquals(1, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getFaithMarker());
    }

    /**
     * Test method ChooseLeaderCardTest checks if the Game's method chooseLeaderCards works correctly and if at the end of
     * the method, the player has only two leader cards.
     * @throws LeaderCardNotFound if the player has not got the two chosenCards
     */
    @Test
    void ChooseLeaderCardToRemoveTest() throws LeaderCardNotFound {

        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(testLeaderCardDiscount);
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(testLeaderCardExtraDep);
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(testLeaderCardWhiteMarble);
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(testLeaderCardProdPower);

        assertEquals(4, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().size());
        testGame.chooseLeaderCardToRemove(testLeaderCardExtraDep.getId(), testLeaderCardProdPower.getId(),  testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getUsername());
        assertEquals(2, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().size());

        assertTrue(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().contains(testLeaderCardDiscount));
        assertTrue(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().contains(testLeaderCardWhiteMarble));
        assertFalse(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().contains(testLeaderCardExtraDep));
        assertFalse(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().contains(testLeaderCardProdPower));
    }

    /** Method chooseDiscountActivationTest tests Game method chooseDiscountActivation. */
    @Test
    @DisplayName("chooseDiscountActivation test")
    void chooseDiscountActivationTest() throws DiscountCannotBeActivated {
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(testLeaderCardDiscount);
        i= testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().indexOf(testLeaderCardDiscount);
        assertEquals(1, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().size());
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().get(i).useAbility();

        testGame.chooseDiscountActivation(leaderCardsChosen);
        assertEquals(leaderCardsChosen,testGame.getLeaderCardsChosen());
    }

    /** Method chooseDiscountActivationTest2 tests Game method chooseDiscountActivation. */
    @Test
    @DisplayName("chooseDiscountActivation test 2")
    void chooseDiscountActivationTest2() {
       try{
           testGame.chooseDiscountActivation(leaderCardsChosen);
       } catch (DiscountCannotBeActivated problems){
           assertEquals(0, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().size());
       }
    }

    /** Method useDiscountActivationTest tests Game method useDiscountActivation. */
    @Test
    @DisplayName("useDiscountActivation test")
    void useDiscountActivationTest() throws DiscountCannotBeActivated {
        resourcePriceBuffer= new HashMap<>();
        resourcePriceBuffer.putAll(testDevelopmentCard.getResourcePrice());
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(testLeaderCardDiscount);
        i = testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().indexOf(testLeaderCardDiscount);
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getLeaderCards().get(i).useAbility();
        testGame.chooseDiscountActivation(leaderCardsChosen);

        testGame.useDiscountActivation(resourcePriceBuffer,testDevelopmentCard);
        assertTrue(resourcePriceBuffer.containsKey(ResourceType.SERVANT));
        assertEquals(resourcePriceBuffer.get(ResourceType.SERVANT),1);
    }

    /** Method buyDevCardTest tests Game method buyDevCard. */
    @Test
    @DisplayName("buyDevCard test")
    void buyDevCardTest() throws DiscountCannotBeActivated, EmptyDeposit, DepositDoesntHaveThisResource {
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
        testGame.chooseDiscountActivation(leaderCardsChosen);

        try {
            testGame.buyDevCard(1, chosenResources1, chosenWarehouses1, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDevelopmentSpaces().get(0));
            assertEquals(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getTotalServants(),3);
        } catch (DevelopmentCardNotFound |DevCardNotPlaceable | DifferentDimension|  NotEnoughResources| WrongChosenResources problems1){
            try{
                testGame.buyDevCard(1, chosenResources2, chosenWarehouses2, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDevelopmentSpaces().get(0));
                assertEquals(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getTotalServants(),2);
            }catch (DevelopmentCardNotFound |DevCardNotPlaceable | DifferentDimension|  NotEnoughResources| WrongChosenResources problems2){
                try{
                    testGame.buyDevCard(1, chosenResources3, chosenWarehouses3, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDevelopmentSpaces().get(0));
                    assertEquals(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getTotalServants(),3);
                }catch (DevelopmentCardNotFound |DevCardNotPlaceable | DifferentDimension|  NotEnoughResources| WrongChosenResources problems3){
                    try{
                        testGame.buyDevCard(1, chosenResources4, chosenWarehouses4, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDevelopmentSpaces().get(0));
                        assertEquals(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getTotalServants(),4);
                    }catch (DevelopmentCardNotFound |DevCardNotPlaceable | DifferentDimension|  NotEnoughResources| WrongChosenResources problems4){
                        assertEquals(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDevelopmentSpaces().get(0).getDevelopmentCardsOfDevSpace().size(),0);
                    }
                }
            }
        }
    }

    /** Method chooseWhiteMarbleActivationTest tests Game method chooseWhiteMarbleActivation. */
    @Test
    @DisplayName("chooseWhiteMarbleActivation test")
    void chooseWhiteMarbleActivationTest(){
        ArrayList<Integer> whiteMarbleCardChoice= new ArrayList<>();
        HashMap<CardColor,Integer> colorWhiteMarble= new HashMap<>();
        colorWhiteMarble.put(CardColor.GREEN,2);
        colorWhiteMarble.put(CardColor.PURPLE,1);
        NumAndColorRequirement requirementsWhiteMarble= new NumAndColorRequirement(colorWhiteMarble);
        LeaderCardStrategy testStrategyWhiteMarble= new ConcreteStrategyMarble(ResourceType.SHIELD);
        LeaderCard testLeaderCardWhiteMarble= new LeaderCard(1,LeaderCardType.WHITE_MARBLE,requirementsWhiteMarble,ResourceType.SHIELD,5, "",0,0);
        testLeaderCardWhiteMarble.setStrategy(testStrategyWhiteMarble);
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(testLeaderCardWhiteMarble);

        HashMap<CardColor,Integer> colorWhiteMarble2= new HashMap<>();
        colorWhiteMarble2.put(CardColor.YELLOW,2);
        colorWhiteMarble2.put(CardColor.BLUE,1);
        NumAndColorRequirement requirementsWhiteMarble2= new NumAndColorRequirement(colorWhiteMarble2);
        LeaderCardStrategy testStrategyWhiteMarble2= new ConcreteStrategyMarble(ResourceType.SERVANT);
        LeaderCard testLeaderCardWhiteMarble2= new LeaderCard(2,LeaderCardType.WHITE_MARBLE,requirementsWhiteMarble2,ResourceType.SERVANT,5, "",0,0);
        testLeaderCardWhiteMarble2.setStrategy(testStrategyWhiteMarble2);
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(testLeaderCardWhiteMarble2);

        testLeaderCardWhiteMarble.useAbility();
        testLeaderCardWhiteMarble2.useAbility();

        whiteMarbleCardChoice.add(testLeaderCardWhiteMarble.getId());
        whiteMarbleCardChoice.add(testLeaderCardWhiteMarble2.getId());

        try {
            testGame.takeResourceFromMarket("Row", 2,whiteMarbleCardChoice);
            assertEquals(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getWhiteMarbleCardChoice(), whiteMarbleCardChoice);
        } catch (LeaderCardNotFound | LeaderCardNotActivated problems) {
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

    /** Method moveResourceTest tests Game method moveResource. */
    @Test
    @DisplayName("moveResourceTest test")
    void moveResourceTest() throws DepositHasReachedMaxLimit, DepositHasAnotherResource, EmptyDeposit, AnotherDepositContainsThisResource, InvalidResource {
        //putting 3 coins in a deposit
        Resource resource1 = new Resource(ResourceType.COIN);
        ConcreteStrategyResource concreteStrategyResource = new ConcreteStrategyResource(2,testGame.getActivePlayers().get(0).getBoard(),resource1.getType());
        resource1.setStrategy(concreteStrategyResource);
        for (int j = 0; j < 3; j++) {
            resource1.useResource();
        }
        assertEquals(3,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDeposits().get(2).getQuantity());
        assertEquals(ResourceType.COIN,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDeposits().get(2).getResourcetype());

        //taking them and filling the resourceBuffer using fill buffer
        assertEquals(0,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getResourceBuffer().size());
        testGame.moveResource(2);
        assertEquals(1,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getResourceBuffer().size());
        assertEquals(ResourceType.COIN, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getResourceBuffer().get(0).getType());

        testGame.moveResource(2);
        testGame.moveResource(2);
        assertEquals(3,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getResourceBuffer().size());
        assertEquals(0,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDeposits().get(2).getQuantity());
        assertNull(testGame.getActivePlayers().get(0).getBoard().getDeposits().get(2).getResourcetype());
    }

    /** Method takeResourcesFromMarketTest tests Game method takeResourcesFromMarket. */
    @Test
    @DisplayName("takeResourcesFromMarketTest test")
    void takeResourcesFromMarketTest() throws LeaderCardNotActivated, LeaderCardNotFound {
        testGame.takeResourceFromMarket("row",2,null);
        assertNotEquals(0, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getResourceBuffer().size());
    }

    /** Method takeResourcesFromMarketTest tests Game method takeResourcesFromMarket. */
    @Test
    @DisplayName("takeResourcesFromMarketTestException test")
    void takeResourcesFromMarketTestException() {
        ArrayList<Integer> whiteMarbleCardChoice= new ArrayList<>();
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).addLeaderCard(testLeaderCardWhiteMarble);
        whiteMarbleCardChoice.add(testLeaderCardWhiteMarble.getId());
        try {
            testGame.takeResourceFromMarket("Column", 2, whiteMarbleCardChoice);
            fail();
        }
        catch (LeaderCardNotActivated | LeaderCardNotFound ignored) {}
    }

    /** Method placeResourceTest tests Game method placeResource. */
    @Test
    @DisplayName("placeResourceTest test")
    void placeResourceTest() throws DepositHasReachedMaxLimit, DepositHasAnotherResource, AnotherDepositContainsThisResource, InvalidResource {
        Resource resource1 = new Resource(ResourceType.COIN);
        Resource resource2 = new Resource(ResourceType.COIN);
        Resource resource3 = new Resource(ResourceType.STONE);
        Resource resource4 = new Resource(ResourceType.SERVANT);
        Resource resource5 = new Resource(ResourceType.SERVANT);
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getResourceBuffer().addAll(Arrays.asList(
                resource1,resource3,resource2,resource4,resource5
        ));
        assertEquals(5, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getResourceBuffer().size());

        testGame.placeResource(0,0);
        assertEquals(1,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDeposits().get(0).getQuantity());
        assertEquals(ResourceType.COIN,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDeposits().get(0).getResourcetype());
        assertEquals(4, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getResourceBuffer().size());

        testGame.placeResource(1,0);
        assertEquals(1,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDeposits().get(1).getQuantity());
        assertEquals(ResourceType.STONE,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDeposits().get(1).getResourcetype());
        assertEquals(3, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getResourceBuffer().size());

        testGame.placeResource(2,1);
        testGame.placeResource(2,1);
        assertEquals(2,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDeposits().get(2).getQuantity());
        assertEquals(ResourceType.SERVANT,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getDeposits().get(2).getResourcetype());
        assertEquals(1, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getResourceBuffer().size());

    }

    /** Method useAndChooseProdPowerTest1 tests Game method useAndChooseProdPower without JOLLY resources in resource obtained. */
    @Test
    @DisplayName("useAndChooseProdPowerTest1 test")
    void useAndChooseProdPowerTest1() throws DifferentDimension, TooManyResourcesRequested, EmptyDeposit, DepositDoesntHaveThisResource, NotEnoughChosenResources, EmptyProductionPower {
        HashMap<ResourceType,Integer> resourceNeeded = new HashMap<>();
        HashMap<ResourceType,Integer> resourceObtained = new HashMap<>();
        resourceNeeded.put(ResourceType.COIN, 3);
        resourceNeeded.put(ResourceType.SERVANT, 4);
        resourceNeeded.put(ResourceType.JOLLY, 2);
        resourceObtained.put(ResourceType.SHIELD, 5);
        resourceObtained.put(ResourceType.STONE, 7);
        resourceObtained.put(ResourceType.JOLLY, 0);

        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getStrongbox().replaceAll((key, oldvalue) -> oldvalue + 10);

        ProductionPower productionPower = new ProductionPower(resourceNeeded,resourceObtained);
        ArrayList<ResourceType> resources = new ArrayList<>();
        ArrayList<Warehouse> warehouse = new ArrayList<>();
        for(int i= 0;i<4; i++){
            resources.add(ResourceType.COIN);
            warehouse.add(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox());
        }
        for(int i= 0;i<5; i++){
            resources.add(ResourceType.SERVANT);
            warehouse.add(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox());
        }
        assertEquals(10,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getTotalCoins());
        assertEquals(10,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getTotalStones());
        assertEquals(10,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getTotalServants());
        assertEquals(10,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getTotalShields());

        ArrayList<ResourceType> newResources = new ArrayList<>();

        testGame.useAndChooseProdPower(productionPower,resources,warehouse, newResources);


        assertEquals(6,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getTotalCoins());
        assertEquals(17,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getTotalStones());
        assertEquals(5,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getTotalServants());
        assertEquals(15,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getTotalShields());

    }

    /**  Method useAndChooseProdPowerTest2 tests Game method useAndChooseProdPower2 with JOLLY resources in resource obtained.*/
    @Test
    @DisplayName("useAndChooseProdPowerTest2 test")
    void useAndChooseProdPowerTest2() throws TooManyResourcesRequested, DifferentDimension, EmptyDeposit, DepositDoesntHaveThisResource, NotEnoughChosenResources, EmptyProductionPower {
        HashMap<ResourceType,Integer> resourceNeeded = new HashMap<>();
        HashMap<ResourceType,Integer> resourceObtained = new HashMap<>();
        resourceNeeded.put(ResourceType.COIN, 3);
        resourceNeeded.put(ResourceType.SERVANT, 4);
        resourceNeeded.put(ResourceType.JOLLY, 2);
        resourceObtained.put(ResourceType.SHIELD, 5);
        resourceObtained.put(ResourceType.STONE, 7);
        resourceObtained.put(ResourceType.JOLLY, 4);


        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getStrongbox().replaceAll((key, oldvalue) -> oldvalue + 10);

        ProductionPower productionPower = new ProductionPower(resourceNeeded,resourceObtained);
        ArrayList<ResourceType> resources = new ArrayList<>();
        ArrayList<ResourceType> new_resources = new ArrayList<>();
        ArrayList<Warehouse> warehouse = new ArrayList<>();

        for(int i= 0;i<4; i++){
            new_resources.add(ResourceType.COIN);
        }
        for(int i= 0;i<4; i++){
            resources.add(ResourceType.COIN);
            warehouse.add(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox());
        }
        for(int i= 0;i<5; i++){
            resources.add(ResourceType.SERVANT);
            warehouse.add(testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox());
        }
        assertEquals(10,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getTotalCoins());
        assertEquals(10,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getTotalStones());
        assertEquals(10,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getTotalServants());
        assertEquals(10,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getTotalShields());

        testGame.useAndChooseProdPower(productionPower,resources,warehouse, new_resources);


        assertEquals(10,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getTotalCoins());
        assertEquals(17,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getTotalStones());
        assertEquals(5,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getTotalServants());
        assertEquals(15,testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getBoard().getStrongbox().getTotalShields());
    }

    /**Method endTurnAndIncreaseFaithMarkerTest tests Game methods increaseFaithMarkerOfOtherPlayers and endTurn.*/
    @Test
    @DisplayName("endTurnAndIncreaseFaithMarkerTest test")
    void endGameAndIncreaseFaithMarkerTest() throws LeaderCardNotFound, LeaderCardNotActivated {
        testGame.takeResourceFromMarket("row", 2, null);
        assertNotEquals(0, testGame.getActivePlayers().get(testGame.getCurrentPlayer()).getResourceBuffer().size());
        testGame.getActivePlayers().get(testGame.getCurrentPlayer()).endTurn();
        for(int i = 1; i< 4; i++){
            assertNotEquals(0,testGame.getActivePlayers().get(i).getBoard().getFaithMarker());
        }
    }

    /**
     * Test method checkWinnerFaithMarkers checks the Game's method winner that returns in this case the first player
     * who has the victory points obtained by the faith track
     */
    @Test
    void checkWinnerFaithMarkers() {
        //the first player reaches the last faith track's cell
        for (int j = 0; j < 24; j++) {
            testGame.getActivePlayers().get(0).getBoard().increaseFaithMarker();
        }
        assertEquals(24, testGame.getActivePlayers().get(0).getBoard().getFaithMarker());
        assertTrue(testGame.isEndgame());
     //   assertEquals(testGame.getActivePlayers().get(0).getUsername(), testGame.winner());

    }

    /**
     * Test method checkWinnerLeaderCards checks the Game's method winner that returns in this case the second player
     * because he has more victory points obtained by the leader cards
     */
    @Test
    void checkWinnerLeaderCards(){

        testGame.getActivePlayers().get(0).addLeaderCard(testLeaderCardProdPower);
        testGame.getActivePlayers().get(0).getLeaderCards().get(0).getStrategy().ability();
        testGame.getActivePlayers().get(0).addLeaderCard(testLeaderCardDiscount);
        testGame.getActivePlayers().get(0).getLeaderCards().get(1).getStrategy().ability();
        testGame.getActivePlayers().get(1).addLeaderCard(testLeaderCardExtraDep);
        testGame.getActivePlayers().get(1).getLeaderCards().get(0).getStrategy().ability();
        testGame.getActivePlayers().get(1).addLeaderCard(testLeaderCardWhiteMarble);
        testGame.getActivePlayers().get(1).getLeaderCards().get(1).getStrategy().ability();

        //leader cards are activated
        assertTrue(testGame.getActivePlayers().get(0).getLeaderCards().get(0).getStrategy().isActive());
        assertTrue(testGame.getActivePlayers().get(0).getLeaderCards().get(1).getStrategy().isActive());
        assertTrue(testGame.getActivePlayers().get(1).getLeaderCards().get(0).getStrategy().isActive());
        assertTrue(testGame.getActivePlayers().get(1).getLeaderCards().get(1).getStrategy().isActive());

        //the first two players reach the last faith track's cell
        for (int j = 0; j < 24; j++) {
            testGame.getActivePlayers().get(0).getBoard().increaseFaithMarker();
            testGame.getActivePlayers().get(1).getBoard().increaseFaithMarker();
        }
        assertEquals(24, testGame.getActivePlayers().get(0).getBoard().getFaithMarker());
        assertEquals(24, testGame.getActivePlayers().get(1).getBoard().getFaithMarker());

        assertTrue(testGame.isEndgame());
     //   assertEquals(testGame.getActivePlayers().get(1).getUsername(), testGame.winner());
    }

    @Test
    void checkLeaderCardExceptions(){
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(testLeaderCardWhiteMarble.getId());
        try {
            testGame.takeResourceFromMarket("row", 1, cards);
        } catch (LeaderCardNotFound | LeaderCardNotActivated ignored) {
        }
    }

    @Test
    void checkDisconnection(){
        assertEquals(4, testGame.getActivePlayers().size());
        testGame.disconnectPlayer(testGame.getActivePlayers().get(0).getUsername());
        assertEquals(3, testGame.getActivePlayers().size());
        assertEquals("bea", testGame.getActivePlayers().get(0).getUsername());
    }


    @Test
    void checkReconnection(){
        testGame.disconnectPlayer(testGame.getActivePlayers().get(0).getUsername());
        assertEquals(3, testGame.getActivePlayers().size());
        testGame.reconnectPlayer(testGame.getPlayers().get(0).getUsername());
        assertEquals(4, testGame.getActivePlayers().size());
        assertEquals("fil", testGame.getActivePlayers().get(0).getUsername());
    }

    @Test
    void checkGetIndexOfPlayer(){
        assertEquals(0, testGame.getIndexOfActivePlayer("fil"));
        testGame.disconnectPlayer(testGame.getActivePlayers().get(0).getUsername());
        assertEquals(0, testGame.getIndexOfActivePlayer("bea"));

    }

    @Test
    void checkNextPlayerGivenUsername(){
        assertEquals("bea", testGame.nextPlayerGivenUsername("fil"));
        assertEquals("fil", testGame.nextPlayerGivenUsername("jack"));
        testGame.disconnectPlayer("fil");
        assertNull(testGame.nextPlayerGivenUsername("fil"));
        testGame.reconnectPlayer("fil");
        assertEquals("bea", testGame.nextPlayerGivenUsername("fil"));

    }

    @Test
    void checkNextPlayer(){
        assertEquals(0, testGame.getCurrentPlayer());
        testGame.nextPlayer();
        assertEquals(1, testGame.getCurrentPlayer());
        testGame.nextPlayer();
        assertEquals(2, testGame.getCurrentPlayer());
        testGame.nextPlayer();
        assertEquals(3, testGame.getCurrentPlayer());
        testGame.nextPlayer();
        assertEquals(0, testGame.getCurrentPlayer());
        testGame.disconnectPlayer("fil");
        testGame.nextPlayer();
        assertEquals(1, testGame.getCurrentPlayer());
        testGame.disconnectPlayer("bea");

    }

}