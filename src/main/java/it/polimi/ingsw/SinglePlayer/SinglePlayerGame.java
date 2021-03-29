package it.polimi.ingsw.SinglePlayer;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;
import it.polimi.ingsw.Cards.LeaderCards.LeaderCard;
import it.polimi.ingsw.Game;
import it.polimi.ingsw.Player;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the class used when a player wants to play against Lorenzo il Magnifico.
 */

public class SinglePlayerGame extends Game {

    ArrayList<SoloActionToken> deckSoloActionToken;
    ArrayList<SoloActionToken> deletedSoloActionToken;
    private SoloActionTokenType token;
    private int blackCross;

    public SinglePlayerGame(ArrayList<Player> players, ArrayList<LeaderCard> leaderDeck, ArrayList<ArrayList<DevelopmentCard>> developmentDeck, ArrayList<SoloActionToken> deckSoloActionToken, ArrayList<SoloActionToken> deletedSoloActionToken, SoloActionTokenType token, int blackCross) {
        super(players, leaderDeck, developmentDeck);
        this.deckSoloActionToken = deckSoloActionToken;
        this.deletedSoloActionToken = deletedSoloActionToken;
        this.token = token;
        this.blackCross = blackCross;
    }

    public int getBlackCross() {
        return blackCross;
    }

    public ArrayList<SoloActionToken> getDeckSoloActionToken() {
        return deckSoloActionToken;
    }

    public ArrayList<SoloActionToken> getDeletedSoloActionToken() {
        return deletedSoloActionToken;
    }

    public int addBlackCross(int amount, int blackCross){
        return blackCross + amount;
    }

    /**
     * To shuffle the tokens when the game starts.
     */
    public ArrayList<SoloActionToken> shuffleSoloActionToken(){
        Collections.shuffle(deckSoloActionToken);
        return deckSoloActionToken;
    }

    /**
     * To reveal and apply the effect of the token.
     */
    public void useSoloActionToken(){
        SoloActionToken token = deckSoloActionToken.get(deckSoloActionToken.size() - 1);
        token.applyEffect();
        deletedSoloActionToken.add(token);

    }

    @Override
    public void setup(){
        System.out.println("Setup Single Player finito");
    }
}
