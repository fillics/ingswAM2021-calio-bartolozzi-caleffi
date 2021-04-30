package it.polimi.ingsw.controller.packets;

import it.polimi.ingsw.controller.ActionType;
import java.io.Serializable;

public class PacketDoAction {

    private final String to;
    private final ActionType actionType;

    public PacketDoAction(String to, ActionType actionType) {
        this.to = to;
        this.actionType = actionType;
    }

    public String getTo() {
        return to;
    }

    public ActionType getActionType() {
        return actionType;
    }
}
