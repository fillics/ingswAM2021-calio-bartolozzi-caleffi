package it.polimi.ingsw.client;

import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.cards.leadercards.*;

import java.util.ArrayList;

public class LitePlayer {
    private  String username;
    private int idClient;
    private int totalVictoryPoint;
    private ArrayList<LeaderCard> leaderCards;
    private ArrayList<Resource> resourceBuffer;
    private LiteBoard board;
    private int posInGame;
    //private int chosenResource;
    private ArrayList<Integer> whiteMarbleCardChoice;

    public LitePlayer(String username, int idPlayer,int posInGame, int totalVictoryPoint, ArrayList<LeaderCard> leaderCards, ArrayList<Resource> resourceBuffer, LiteBoard board, ArrayList<Integer> whiteMarbleCardChoice) {
        this.username = username;
        this.idClient = idPlayer;
        this.posInGame = posInGame;
        this.board = board;
        this.totalVictoryPoint = totalVictoryPoint;
        this.leaderCards = leaderCards;
        this.resourceBuffer = resourceBuffer;
        this.whiteMarbleCardChoice = whiteMarbleCardChoice;
    }

    public int getPosInGame() {
        return posInGame;
    }

    public String getUsername() {
        return username;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setTotalVictoryPoint(int totalVictoryPoint) {
        this.totalVictoryPoint = totalVictoryPoint;
    }

    public void setLeaderCards(ArrayList<LeaderCard> leaderCards) {
        this.leaderCards = leaderCards;
    }

    public void setResourceBuffer(ArrayList<Resource> resourceBuffer) {
        this.resourceBuffer = resourceBuffer;
    }

    public void setWhiteMarbleCardChoice(ArrayList<Integer> whiteMarbleCardChoice) {
        this.whiteMarbleCardChoice = whiteMarbleCardChoice;
    }

    public ArrayList<Resource> getResourceBuffer() {
        return resourceBuffer;
    }

    public ArrayList<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
