package it.polimi.ingsw.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.liteclasses.LiteBoard;
import it.polimi.ingsw.client.liteclasses.LiteDevelopmentGrid;
import it.polimi.ingsw.client.liteclasses.LiteMarketTray;
import it.polimi.ingsw.client.liteclasses.LitePlayer;
import it.polimi.ingsw.model.singleplayer.SoloActionToken;

import java.awt.*;
import java.util.ArrayList;

/**
 * ClientModelView contains the lite model of the client
 */
public class ClientModelView {
    private LitePlayer myPlayer;
    private LiteMarketTray marketTray;
    private LiteDevelopmentGrid developmentGrid;
    private LiteBoard liteBoard;
    private boolean isSingleGame;
    private SoloActionToken soloActionToken;
    private ArrayList<String> players;
    private Color colorPlayer;

    /**
     * Class' constructor used to deserialize the ClientModelView
     * @param myPlayer is the Player of the client model view
     * @param marketTray is the market tray of the client model view
     * @param developmentGrid is the development grid of the client model view
     * @param liteBoard is the lite board of the client model view
     */
    @JsonCreator
    public ClientModelView(@JsonProperty("player") LitePlayer myPlayer,@JsonProperty("market") LiteMarketTray marketTray,
                           @JsonProperty("devGrid")LiteDevelopmentGrid developmentGrid, @JsonProperty("board") LiteBoard liteBoard) {
        this.myPlayer = myPlayer;
        this.marketTray = marketTray;
        this.developmentGrid = developmentGrid;
        this.liteBoard = liteBoard;
    }


    /**
     * Class's getter
     * @return the name of all players
     */
    public ArrayList<String> getPlayers() {
        return players;
    }

    /**
     * Class's getter
     * @return the class Player of the client model view
     */
    public LitePlayer getMyPlayer() {
        return myPlayer;
    }

    /**
     * Class's getter
     * @return the market tray of the client model view
     */
    public LiteMarketTray getMarketTray() {
        return marketTray;
    }

    /**
     * Class's getter
     * @return the development grid of the client model view
     */
    public LiteDevelopmentGrid getDevelopmentGrid() {
        return developmentGrid;
    }

    /**
     * Class's getter
     * @return the lte board of the client model view
     */
    public LiteBoard getLiteBoard() {
        return liteBoard;
    }

    /**
     * Class's setter
     * @param myPlayer is the Player of the client model view to set
     */
    public void setMyPlayer(LitePlayer myPlayer) {
        this.myPlayer = myPlayer;
    }

    /**
     * Class's setter
     * @param marketTray is the Market Tray of the client model view to set
     */
    public void setMarketTray(LiteMarketTray marketTray) {
        this.marketTray = marketTray;
    }

    /**
     * Class's setter
     * @param developmentGrid is the Development Grid of the client model view to set
     */
    public void setDevelopmentGrid(LiteDevelopmentGrid developmentGrid) {
        this.developmentGrid = developmentGrid;
    }

    /**
     * Class's setter
     * @param liteBoard is the Lite Board of the client model view to set
     */
    public void setLiteBoard(LiteBoard liteBoard) {
        this.liteBoard = liteBoard;
    }

    /**
     * Class's setter
     * @param players are the name of the players of the client model view to set
     */
    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }

    /**
     * Class's setter
     */
    public void setSingleGame() {
        isSingleGame = true;
    }

    /**
     * Class's setter
     * @param soloActionToken is the token of the client model view to set
     */
    public void setSoloActionToken(SoloActionToken soloActionToken) {
        this.soloActionToken = soloActionToken;
    }

    /**
     * Class' getter
     * @return true if the match is in single player, else false
     */
    @JsonIgnore
    public boolean isSingleGame() {
        return isSingleGame;
    }

    /**
     * Class' getter
     * @return the SoloActionToken of the client model view
     */
    @JsonIgnore
    public SoloActionToken getSoloActionToken() {
        return soloActionToken;
    }

    @JsonIgnore
    public Color getColorPlayer() {
        return colorPlayer;
    }

    public void setColorPlayer(Color colorPlayer) {
        this.colorPlayer = colorPlayer;
    }


    @JsonCreator
    public ClientModelView() {
        myPlayer = new LitePlayer();
        marketTray = new LiteMarketTray();
        developmentGrid = new LiteDevelopmentGrid();
        liteBoard = new LiteBoard();
        players = new ArrayList<>();
    }
}
