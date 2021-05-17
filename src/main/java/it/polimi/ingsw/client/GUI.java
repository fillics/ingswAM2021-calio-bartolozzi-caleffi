package it.polimi.ingsw.client;

public class GUI implements ViewInterface {
    private ClientModelView clientModelView;

    public GUI(ClientModelView clientModelView) {
        this.clientModelView = clientModelView;
    }

    @Override
    public ClientModelView getClientModelView() {
        return clientModelView;
    }

    @Override
    public void printLeaderCards() {

    }
}
