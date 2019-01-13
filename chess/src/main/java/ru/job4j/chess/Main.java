package ru.job4j.chess;

import javafx.application.Application;

public class Main {
    private Main() {

    }

    public static void main(
            final String[] args) {
        /*
         * Non-modular applications must specify the application class explicitly rather
         * than allowing the launcher to use reflection to try to instantiate and start
         * an application.
         */

        Application.launch(Chess.class, args);
    }
}
