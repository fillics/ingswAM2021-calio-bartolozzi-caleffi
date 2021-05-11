package it.polimi.ingsw.client;

public class ClientModelView {
    private LitePlayer MyPlayer;
    private LiteMarketTray marketTray;
    private LiteDevelopmentGrid developmentGrid;
    private LiteBoard liteBoard;

    public LitePlayer getMyPlayer() {
        return MyPlayer;
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
}
