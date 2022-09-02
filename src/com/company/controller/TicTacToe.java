package com.company.controller;

import com.company.model.Board;
import com.company.model.Figure;
import com.company.model.Player;
import com.company.view.ConsolePrinter;
import com.company.view.ConsoleReader;

public class TicTacToe {
    public static void main(String[] args) {
        Player[] players = new Player[] {new Player(Figure.X), new Player(Figure.ZERO), new Player(Figure.SHARP)};
        Board board = new Board(6);
        ConsolePrinter printer =  new ConsolePrinter();
        ConsoleReader reader = new ConsoleReader();

        var game = new Game(board, players, printer, reader);
        game.go();
    }
}
