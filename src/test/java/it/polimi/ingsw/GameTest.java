package it.polimi.ingsw;

import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;
import it.polimi.ingsw.Cards.DevelopmentCards.Level;
import it.polimi.ingsw.Cards.LeaderCards.ConcreteStrategyDiscount;
import it.polimi.ingsw.Cards.LeaderCards.LeaderCard;
import it.polimi.ingsw.Cards.LeaderCards.LeaderCardType;
import it.polimi.ingsw.Cards.LeaderCards.Requirement;
import it.polimi.ingsw.Exceptions.DevelopmentCardNotFound;
import it.polimi.ingsw.Exceptions.LeaderCardNotFound;
import it.polimi.ingsw.Exceptions.NumMaxPlayersReached;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class GameTest tests Game class.
 *
 * @see Game
 */

class GameTest {

    Game testGame;


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
    }

    /**
     * Test method checkSizeArrayPlayer checks if the arrays that contain the players and
     * the active players have the right dimension, after the initialization
     */
    @Test
    void checkSizeArrayPlayer() {
        assertEquals(4, testGame.getActivePlayers().size());
        assertEquals(4, testGame.getPlayers().size());
    }

    /**
     * Method test checkPlayersPosition checks the correct turn's position of the active players
     */
    @Test
    void checkPlayersPosition(){
        assertEquals(1, testGame.getActivePlayers().get(0).getPosition());
        assertEquals(2, testGame.getActivePlayers().get(1).getPosition());
        assertEquals(3, testGame.getActivePlayers().get(2).getPosition());
        assertEquals(4, testGame.getActivePlayers().get(3).getPosition());
    }

    /**
     * Test method checkAdditionalSetup checks if the distribution of resources and faith points to the players
     * at the beginning of the game is correct
     */
    @Test
    void checkAdditionalSetup(){
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
     * Test method checkSizeDevelopmentGrid checks if the method createDevelopmentGrid creates
     * the grid with the correct dimension
     */
    @Test
    void checkSizeDevelopmentGrid() {
        int bound = testGame.getDevelopmentGrid().size();
        IntStream.range(0, bound).forEach(i -> assertEquals(4, testGame.getDevelopmentGrid().get(i).size()));
    }

    /**
     * Test method checkSizeLeaderDeck checks if the method createLeaderDeck creates
     * the deck with the correct dimension
     */
    @Test
    void checkSizeLeaderDeck() {
        testGame.createLeaderDeck();
        assertEquals(16, testGame.getLeaderDeck().size());
    }

    /**
     * Test method checkSizeLeaderDeckAfterDistribute checks the size of the leader deck
     * after the distribution of the leader cards to each player
     */
    @Test
    void checkSizeLeaderDeckAfterDistribute() {
        testGame.setup();
        assertEquals(16-4*testGame.getActivePlayers().size(), testGame.getLeaderDeck().size());
    }

    /**
     * Test method checkRightSortDevelopmentCard checks if the development grid is sorted in the correct way,
     * with the cards in the right position
     */
    @Test
    void checkRightSortDevelopmentCard() {
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
     * Test method checkLeaderCardsDistribution checks if each player receives 4 leader cards at the beginning of the game
     */
    @Test
    void checkLeaderCardsDistribution(){
        testGame.setup();
        for (int i = 0; i < testGame.getActivePlayers().size(); i++) {
            assertEquals(4, testGame.getActivePlayers().get(i).getLeaderCards().size());
        }
    }

    /**
     * Test method checkChooseCardFromDevDeck checks if the Game's method chooseCardFromDevelopmentGrid returns
     * the selected card
     */
    @Test
    void checkChooseCardFromDevDeck(){
        testGame.setup();
        assertEquals(CardColor.BLUE,testGame.chooseCardFromDevelopmentGrid(CardColor.BLUE, Level.ONE).getColor());
        assertEquals(Level.ONE,testGame.chooseCardFromDevelopmentGrid(CardColor.BLUE, Level.ONE).getLevel());
    }

    /**
     * Test method checkRemoveDevCard checks if the removal of the development card from the grid works
     */
    @Test
    void checkRemoveDevCard() throws DevelopmentCardNotFound {
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
     * Test method checkNextPlayer checks if the method nextPlayer works correctly. It tests what happens
     * when the last player ends his turn.
     */
    @Test
    void checkNextPlayer(){
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
     * Test method checkActivationLeaderCard checks if the activation of the leader cards works correctly
     * @throws LeaderCardNotFound if the player has not got the card to activate
     */
    @Test
    void checkActivationLeaderCard() throws LeaderCardNotFound {
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
     * Test method checkDiscardLeaderCard checks if when a player discards one of his leader cards, his faith marker
     * increases correctly
     * @throws LeaderCardNotFound if the player has not got the card to discard
     */
    @Test
    void checkDiscardLeaderCard() throws LeaderCardNotFound {
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
     * Test method checkChooseLeaderCard checks if the Game's method chooseLeaderCards works correctly and if at the end of
     * the method, the player has only two leader cards.
     * @throws LeaderCardNotFound if the player has not got the two chosenCards
     */
    @Test
    void checkChooseLeaderCard() throws LeaderCardNotFound {
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
}