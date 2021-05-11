package it.polimi.ingsw.controller.server_packets;

import it.polimi.ingsw.client.ClientModelView;

//inviato al client come pacchetto finale per permettere il riconoscimento della fine dell'update
public class PacketEndofUpdate implements ServerPacketHandler{
    @Override
    public void execute(ClientModelView clientModelView) {

    }
}
