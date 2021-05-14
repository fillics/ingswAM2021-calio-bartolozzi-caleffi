package it.polimi.ingsw.client;

public class ClientModelView {
    private LitePlayer MyPlayer;
    private LiteMarketTray marketTray;
    private LiteDevelopmentGrid developmentGrid;
    private LiteBoard liteBoard;
    private int numOfPlayers;
    private int firstPosition;

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

    public void setMyPlayer(LitePlayer myPlayer) {
        MyPlayer = myPlayer;
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

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public int getFirstPosition() {
        return firstPosition;
    }

    public void setFirstPosition(int firstPosition) {
        this.firstPosition = firstPosition;
    }
}
