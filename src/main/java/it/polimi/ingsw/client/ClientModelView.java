package it.polimi.ingsw.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.model.singleplayer.SoloActionToken;

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

    public void setSingleGame(boolean singleGame) {
        isSingleGame = singleGame;
    }

    public SoloActionToken getSoloActionToken() {
        return soloActionToken;
    }

    public void setSoloActionToken(SoloActionToken soloActionToken) {
        this.soloActionToken = soloActionToken;
    }
}
