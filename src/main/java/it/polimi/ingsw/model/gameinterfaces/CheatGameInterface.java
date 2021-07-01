package it.polimi.ingsw.model.gameinterfaces;

/**
 * Interface CheatGameInterface represents the interface that contains the callable Game's cheats methods by a player
 */
public interface CheatGameInterface extends GameInterface{
    void cheatResourcesStrongbox(String username);
    void cheatFaithMarker(String username);
}
