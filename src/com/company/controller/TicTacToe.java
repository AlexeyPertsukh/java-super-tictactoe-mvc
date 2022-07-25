package com.company.controller;

import com.company.model.Board;
import com.company.model.Figure;
import com.company.model.Player;
import com.company.view.ConsolePrinter;
import com.company.view.ConsoleReader;

public class TicTacToe {
    public static void main(String[] args) {
        Player[] players = new Player[] {new Player(Figure.X), new Player(Figure.ZERO), new Player(Figure.SHARP)};
        var game = new Game(new Board(5), players, new ConsolePrinter(), new ConsoleReader());
        game.go();
    }
}
