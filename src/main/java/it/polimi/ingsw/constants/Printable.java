package it.polimi.ingsw.constants;

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
    QUESTION_MARK("\uFFFD"),

    RED_CROSS("\u2717"),
    CHECK("\u2713"),
    CROSS ("\u2020"),
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

    Printable(String print) {
        this.print = print;
    }

    public String print() {return print; }
}
