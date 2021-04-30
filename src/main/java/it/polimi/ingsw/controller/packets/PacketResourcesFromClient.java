package it.polimi.ingsw.controller.packets;

import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.board.resources.ResourceType;

import java.io.Serializable;
import java.util.ArrayList;

public class PacketResourcesFromClient {

    private String to; //persona alla quale prendere le risorse
    private ArrayList<ResourceType> availableResources;
}
