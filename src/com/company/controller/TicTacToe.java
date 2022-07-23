package com.company.controller;

import com.company.view.ConsolePrinter;
import com.company.view.ConsoleReader;

public class TicTacToe {
    public static void main(String[] args) {
        var game = new Game(new ConsolePrinter(), new ConsoleReader());
        game.go();
    }
}
