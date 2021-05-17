package it.polimi.ingsw.client;

public class CLI implements ViewInterface{

    public void printDiProva(){
        System.out.println(Color.ANSI_BLUE.escape()
                + Printable.DOUBLE_LINE + Color.RESET
                + Color.ANSI_PURPLE.escape() + Printable.BLOCK + Color.RESET
                + Color.ANSI_BLUE.escape() +Printable.DOUBLE_LINE + "\n"
                + Printable.DOUBLE_LINE + Color.RESET
                + Color.ANSI_RED.escape() + Printable.CROSS + Color.RESET
                + Color.ANSI_BLUE.escape() + Printable.DOUBLE_LINE + "\n"+ Color.RESET);
        System.out.println(Color.ANSI_YELLOW.escape() + "  "+ Printable.CIRCLE+ Color.RESET + "\n"
                + Color.ANSI_PURPLE.escape() + " "+ Printable.CIRCLE + " "+ Printable.CIRCLE + Color.RESET + "\n"
                + Color.ANSI_BLUE.escape()+ Printable.CIRCLE + " "+ Printable.CIRCLE + " " + Printable.CIRCLE +Color.RESET );
    }
    @Override
    public void choiceGameType() {
    }
}
