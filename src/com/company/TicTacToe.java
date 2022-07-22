package com.company;

public class TicTacToe {
    public static void main(String[] args) {
        var game = new Game(new ConsoleView());
        game.go();
    }
}
