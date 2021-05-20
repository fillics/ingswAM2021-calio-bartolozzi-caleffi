module it.polimi.ingsw {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.google.gson;


    exports it.polimi.ingsw;
    exports it.polimi.ingsw.client;
    exports it.polimi.ingsw.GUI;
    exports it.polimi.ingsw.model;
    exports it.polimi.ingsw.model.board;
    exports it.polimi.ingsw.model.board.faithtrack;
    exports it.polimi.ingsw.model.board.resources;
    exports it.polimi.ingsw.model.board.storage;
    exports it.polimi.ingsw.model.cards;
    exports it.polimi.ingsw.model.cards.developmentcards;
    exports it.polimi.ingsw.model.cards.leadercards;
    exports it.polimi.ingsw.model.marbles;
    exports it.polimi.ingsw.model.singleplayer;
    exports it.polimi.ingsw.exceptions;
    exports it.polimi.ingsw.controller;
    exports it.polimi.ingsw.controller.client_packets;
    exports it.polimi.ingsw.controller.server_packets;
    exports it.polimi.ingsw.constants;
    exports it.polimi.ingsw.localgame;
    exports it.polimi.ingsw.server;


    opens it.polimi.ingsw;
    opens it.polimi.ingsw.client;
    opens it.polimi.ingsw.GUI;
    opens it.polimi.ingsw.model;
    opens it.polimi.ingsw.model.board;
    opens it.polimi.ingsw.model.board.faithtrack;
    opens it.polimi.ingsw.model.board.resources;
    opens it.polimi.ingsw.model.board.storage;
    opens it.polimi.ingsw.model.cards;
    opens it.polimi.ingsw.model.cards.developmentcards;
    opens it.polimi.ingsw.model.cards.leadercards;
    opens it.polimi.ingsw.model.marbles;
    opens it.polimi.ingsw.model.singleplayer;
    opens it.polimi.ingsw.exceptions;
    opens it.polimi.ingsw.controller;
    opens it.polimi.ingsw.controller.client_packets;
    opens it.polimi.ingsw.controller.server_packets;
    opens it.polimi.ingsw.constants;
    opens it.polimi.ingsw.localgame;
    opens it.polimi.ingsw.server;
}