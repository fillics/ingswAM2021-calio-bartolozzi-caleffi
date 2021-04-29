package it.polimi.ingsw.controller.packets;

import java.io.Serializable;

public class PacketUsername implements Serializable {

    // TODO: 29/04/2021 aggiungere id serializzazione

    private final String username;

    public PacketUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}