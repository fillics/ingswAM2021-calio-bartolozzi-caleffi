package it.polimi.ingsw.client;

public enum Color {

    ANSI_RED("\u001B[31m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_YELLOW("\u001b[33;1m"),
    ANSI_BLUE("\u001B[34m"),
    ANSI_PURPLE("\u001B[35m"),
    ANSI_GREY ("\u001b[37;1m"),
    BACKGROUND_GREEN ("\u001B[42m"),
    BACKGROUND_YELLOW ("\u001B[43m"),
    BACKGROUND_BLUE ("\u001B[44m"),
    BACKGROUND_PURPLE ("\u001B[45m"),
    ;

    public static final String RESET = "\u001B[0m";
    private String escape;

    Color(String escape) {
        this.escape = escape;
    }

    public String escape() {
        return escape;
    }
}
