package it.polimi.ingsw.client;

public enum Printable {
    BLOCK ("█"),
    DOUBLE_LINE( "║"),
    UPPER_BOX ("╔════════╗\n"),
    BOTTOM_BOX ("╚════════╝\n"),
    CROSS ("\u2020"),
    CIRCLE ("\u25CF"),
    SQUARE("\u25A0"),
    BOX_LINE("|"),
    WHITE_MARBLE ("\u25EF"),
    WHITE_SQUARE("\u25A1"),
    ONE_POINT("\u2219"),
    TWO_POINTS("\u2236"),
    THREE_POINTS("\u22EE"),
    ARROW_RIGHT("\u2B05"),
    ARROW_BOTTOM("\u2B06");

    private String print;

    Printable(String print) {
        this.print = print;
    }

    public String print() {return print; }
}
