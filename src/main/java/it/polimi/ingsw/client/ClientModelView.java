package it.polimi.ingsw.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.liteclasses.LiteBoard;
import it.polimi.ingsw.client.liteclasses.LiteDevelopmentGrid;
import it.polimi.ingsw.client.liteclasses.LiteMarketTray;
import it.polimi.ingsw.client.liteclasses.LitePlayer;
import it.polimi.ingsw.model.singleplayer.SoloActionToken;

import java.util.ArrayList;

/**
 * contiene il modello lite
 */
public class ClientModelView {
    private LitePlayer myPlayer;
    private LiteMarketTray marketTray;
    private LiteDevelopmentGrid developmentGrid;
    private LiteBoard liteBoard;
    private boolean isSingleGame;
    private SoloActionToken soloActionToken;
    private ArrayList<String> players;

    @JsonCreator
    public ClientModelView(@JsonProperty("player") LitePlayer myPlayer,@JsonProperty("market") LiteMarketTray marketTray,
                           @JsonProperty("devGrid")LiteDevelopmentGrid developmentGrid, @JsonProperty("board") LiteBoard liteBoard) {
        this.myPlayer = myPlayer;
        this.marketTray = marketTray;
        this.developmentGrid = developmentGrid;
        this.liteBoard = liteBoard;
    }

    @JsonCreator
    public ClientModelView() {
        myPlayer = new LitePlayer();
        marketTray = new LiteMarketTray();
        developmentGrid = new LiteDevelopmentGrid();
        liteBoard = new LiteBoard();
        players = new ArrayList<>();
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }

    public LitePlayer getMyPlayer() {
        return myPlayer;
    }

    public LiteMarketTray getMarketTray() {
        return marketTray;
    }

    public LiteDevelopmentGrid getDevelopmentGrid() {
        return developmentGrid;
    }

    public LiteBoard getLiteBoard() {
        return liteBoard;
    }

    public void setMyPlayer(LitePlayer myPlayer) {
        this.myPlayer = myPlayer;
    }

    public void setMarketTray(LiteMarketTray marketTray) {
        this.marketTray = marketTray;
    }

    public void setDevelopmentGrid(LiteDevelopmentGrid developmentGrid) {
        this.developmentGrid = developmentGrid;
    }

    public void setLiteBoard(LiteBoard liteBoard) {
        this.liteBoard = liteBoard;
    }

    public boolean isSingleGame() {
        return isSingleGame;
    }

    public void setSingleGame() {
        isSingleGame = true;
    }

    public SoloActionToken getSoloActionToken() {
        return soloActionToken;
    }

    public void setSoloActionToken(SoloActionToken soloActionToken) {
        this.soloActionToken = soloActionToken;
    }
}
