package it.polimi.ingsw.client;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;

import java.util.ArrayList;

public class LitePlayer {
    private final String username;
    private int idPlayer;
    private int totalVictoryPoint;
    private ArrayList<LeaderCard> leaderCards;
    private ArrayList<Resource> resourceBuffer;
    private Board board;
    private int position;
    private int chosenResource;
    private ArrayList<Integer> whiteMarbleCardChoice;

    public LitePlayer(String username, int idPlayer, int totalVictoryPoint, ArrayList<LeaderCard> leaderCards, ArrayList<Resource> resourceBuffer, Board board, int position, int chosenResource, ArrayList<Integer> whiteMarbleCardChoice) {
        this.username = username;
        this.idPlayer = idPlayer;
        this.totalVictoryPoint = totalVictoryPoint;
        this.leaderCards = leaderCards;
        this.resourceBuffer = resourceBuffer;
        this.board = board;
        this.position = position;
        this.chosenResource = chosenResource;
        this.whiteMarbleCardChoice = whiteMarbleCardChoice;
    }

    public Board getBoard() {
        return board;
    }
}
