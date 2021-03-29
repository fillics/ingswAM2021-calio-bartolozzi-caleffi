package it.polimi.ingsw;

import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;
import it.polimi.ingsw.Cards.LeaderCards.LeaderCard;

import java.util.ArrayList;

public class Game implements GameInterface{

    ArrayList<Player> players ;
    ArrayList<LeaderCard> leaderDeck;
    ArrayList<ArrayList<DevelopmentCard>> developmentDeck;

    public Game(ArrayList<Player> players, ArrayList<LeaderCard> leaderDeck, ArrayList<ArrayList<DevelopmentCard>> developmentDeck) {
        this.players = players;
        this.leaderDeck = leaderDeck;
        this.developmentDeck = developmentDeck;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setMarketTray(){

    }

    public void setDevelopmentDeck() {
    }

    public void setLeaderDeck() {
    }

    public ArrayList<ArrayList<DevelopmentCard>> removeCardFromDevelopmentDeck(LeaderCard cardToRemove, ArrayList<ArrayList<DevelopmentCard>> deck) {

        return developmentDeck;
    }

    public Player nextPlayer(int i){

        return players.get(i);
    }

    /**
     *  Called when endTurn in Player is true. It controls if the conditions to end the game are satisfied. If so, the method winner is called.
     */

    public boolean endGame(){
        return true;

    }

    public Player winner(int i){
        return getPlayers().get(i);
    }

    public void setBoard(){

    }

    @Override
    public void setup(){
        setMarketTray();
        setDevelopmentDeck();
        setLeaderDeck();
        setBoard();
        System.out.println("Setup finito");
    }

    @Override
    public void buyDevCard() {

    }

    @Override
    public void moveResource() {

    }

    @Override
    public void takeAndPlaceResource() {

    }

    @Override
    public void useAndChooseProdPower() {

    }

    @Override
    public void activateLeaderCard() {

    }

    @Override
    public void discardLeaderCard() {

    }

    @Override
    public void chooseLeaderCard() {

    }
}
