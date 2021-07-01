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
    QUESTION_MARK("?"),

    RED_CROSS("X"),
    CHECK("V"),
    CROSS ("†"), //does not work
    CIRCLE ("●"),
    SQUARE("■"),
    WHITE_MARBLE ("●"),
    WHITE_SQUARE(" "),

    ONE_POINT("¹"),
    TWO_POINTS("²"),
    THREE_POINTS("³"), //fixare questa riga di dev cards

    ARROW_RIGHT("←"),
    ARROW_BOTTOM("↑");

    private String print;

    Printable(String print) {
        this.print = print;
    }

    public String print() {return print; }
}
