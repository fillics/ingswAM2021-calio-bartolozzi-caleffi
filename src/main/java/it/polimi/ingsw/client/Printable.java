package it.polimi.ingsw.client;

public enum Printable {
    BLOCK ("█"),

    DOUBLE_LINE( "║"),
    LINE("\u258D"),
    UPPER_BOX ("╔════════╗"),
    BOTTOM_BOX ("╚════════╝"),
    UNDER_LINE("\u2581"),

    NORD_OVEST("╔"),
    NORD_EST("╗"),
    SUD_OVEST("╚"),
    SUD_EST("╝"),
    MIDDLE("═"),

    CROSS ("\u2020"),
    BLACK_CROSS("\u271D"),
    CIRCLE ("\u25CF"),
    SQUARE("\u25A0"),
    WHITE_MARBLE ("\u25EF"),
    WHITE_SQUARE("\u25A1"),

    ONE_POINT("\u2219"),
    TWO_POINTS("\u2236"),
    THREE_POINTS("\u22EE"),

    ARROW_RIGHT("\u2B05"),
    ARROW_BOTTOM("\u2B06");

    private String print;
    private char printChar;

    Printable(String print) {
        this.print = print;
    }

    Printable(char printChar){
        this.printChar=printChar;
    }

    public char printChar(){
        return printChar;
    }

    public String print() {return print; }
}
