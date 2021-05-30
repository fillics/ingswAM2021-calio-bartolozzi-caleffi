package it.polimi.ingsw.client.gui;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.ClientOperationHandler;
import it.polimi.ingsw.client.SocketClientConnection;
import it.polimi.ingsw.client.ViewInterface;
import it.polimi.ingsw.model.board.resources.ResourceType;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class GUIOperationHandler implements ClientOperationHandler {

    private SocketClientConnection socketClientConnection;
    private ClientModelView clientModelView;
    private ViewInterface viewInterface;


    public GUIOperationHandler(SocketClientConnection socketClientConnection, ClientModelView clientModelView, ViewInterface viewInterface) {
        this.socketClientConnection = socketClientConnection;
        this.clientModelView = clientModelView;
        this.viewInterface = viewInterface;

    }

    @Override
    public void handleOperation(String input) {

    }

    @Override
    public void chooseInitialResources(int resources) {

    }

    @Override
    public void chooseLeaderCardToRemove() {

    }

    @Override
    public int scannerChooseDeposit(BufferedReader bf) {
        return 0;
    }

    @Override
    public ResourceType scannerChooseResources(BufferedReader bf) {
        return null;
    }

    @Override
    public void activateLeaderCard() throws IOException {

    }

    @Override
    public void buyDevCard() {

    }

    @Override
    public void chooseDiscount() throws IOException {

    }

    @Override
    public void discardLeaderCard() {

    }

    @Override
    public void moveResource() {

    }

    @Override
    public void placeResource() {

    }

    @Override
    public void takeResourceFromMarket() {

    }

    @Override
    public void endTurn() throws JsonProcessingException {

    }

    @Override
    public void setClientModelView(ClientModelView clientModelView) {

    }

    @Override
    public ViewInterface getViewInterface() {
        return null;
    }

    @Override
    public void setViewInterface(ViewInterface viewInterface) {
        this.viewInterface = viewInterface;
    }
}
