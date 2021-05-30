package it.polimi.ingsw.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.polimi.ingsw.model.board.resources.ResourceType;

import java.io.BufferedReader;
import java.io.IOException;

public interface ClientOperationHandler {
    void handleOperation(String input) throws IOException;

    void chooseInitialResources(int resources);
    void chooseLeaderCardToRemove();
    int scannerChooseDeposit(BufferedReader bf);
    ResourceType scannerChooseResources(BufferedReader bf);
    void activateLeaderCard() throws IOException;
    void buyDevCard() throws IOException;
    void chooseDiscount() throws IOException;
    void discardLeaderCard();
    void moveResource();
    void placeResource();
    void takeResourceFromMarket();
    void endTurn() throws JsonProcessingException;

    ViewInterface getViewInterface();
    void setViewInterface(ViewInterface viewInterface);

}
