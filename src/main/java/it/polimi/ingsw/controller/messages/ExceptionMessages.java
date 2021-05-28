package it.polimi.ingsw.controller.messages;

public enum ExceptionMessages {
    DEVELOPMENTCARDNOTFOURND("Development card not found"),
    LEADERCARDNOTFOUND("Leader card not found"),
    NOTENOUGHREQUIREMENTS("Not enough requirements"),
    DEVCARDNOTPLACEABLE("You can't place this development card here"),
    NOTENOUGHRESOURCES("You don't have enough resources"),
    WRONGCHOSENRESOURCES("The resources you chose can't make this production"),
    DIFFERENTDIMENSION("The number of resources and the places in which you take them are not equals"),
    EMPTYDEPOSIT("The deposit is empty"),
    DEPOSITDOESNTHAVETHISRESOURCE("A deposit you chose doesn't have the resource you requested"),
    DISCOUNTCANNOTBEACTIVATED("The discount can't be activated"),
    DEPOSITHASREACHEDMAXLIMIT("This deposit has reached is max limit"),
    DEPOSITHASANOTHERRSOURCE("This deposit has already another type of resource"),
    LEADERCARDNOTACTIVATED("The leader card you chose isn't active"),
    TOOMANYRESOURCESREQUESTED("The warehouse doesn't have sufficient resources"),
    ANOTHERDEPOSITCONTAINSTHISRESOURCE("Another deposit already has this resource"),
    NOTENOUGHCHOSENRESOURCES("The resources you selected aren't enough"),
    INVALIDRESOURCE("You can't place this resource in here, this deposit is special"),
    EMPTYPRODPOWER("You didn't select any production power, try again"),
    USERNAMENOTEXISTING("The username chosen is not playing in this game or not exists, TRY AGAIN");


    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
