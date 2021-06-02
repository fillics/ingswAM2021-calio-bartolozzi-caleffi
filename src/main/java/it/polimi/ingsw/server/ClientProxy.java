package it.polimi.ingsw.server;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.ClientStates;

/**
 * Class ClientProxy saves the informations of a player in order to let him continue the game in case of disconnection
 */
public class ClientProxy {
    private ClientStates clientStates;
    private ClientModelView clientModelView;

    @JsonCreator
    public ClientProxy(@JsonProperty("clientmodelview") ClientModelView clientModelView) {
        clientStates=ClientStates.CREATEMODEL;
        this.clientModelView = clientModelView;
    }

    public ClientStates getClientStates() {
        return clientStates;
    }

    public void setClientStates(ClientStates clientStates) {
        this.clientStates = clientStates;
    }

    public ClientModelView getClientModelView() {
        return clientModelView;
    }

    public void setClientModelView(ClientModelView clientModelView) {
        this.clientModelView = clientModelView;
    }

}
