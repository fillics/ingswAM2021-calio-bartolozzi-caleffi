package it.polimi.ingsw.client;

public class CLI implements ViewInterface{
    private ClientModelView clientModelView;

    public CLI(ClientModelView clientModelView) {
        this.clientModelView = clientModelView;
    }

    @Override
    public ClientModelView getClientModelView() {
        return clientModelView;
    }

    @Override
    public void printLeaderCards(){
        System.out.println("Your leader cards:");
        for (int i=0; i<clientModelView.getMyPlayer().getLeaderCards().size();i++){
            clientModelView.getMyPlayer().getLeaderCards().get(i).dump();
        }
        System.out.print("\n");
    }
}
