package it.polimi.ingsw.model.cards.developmentcards;

/**
 * Level of Development Cards.
 */
public enum Level {
    ONE(1), TWO(2), THREE(3);

    public final int value;

    Level(int value) {
        this.value = value;
    }
}
