package com.company.controller;

import com.company.model.Player;
import com.company.model.Board;
import com.company.model.Point;
import com.company.model.GameException;


import com.company.view.MyPrinter;
import com.company.view.MyReader;

import java.util.InputMismatchException;

public class Game {

    private final Player player1 = new Player("X");
    private final Player player2 = new Player("0");
    private Player currentPlayer = player1;
    Board board = new Board();
    MyPrinter printer;
    MyReader reader;

    public Game(MyPrinter printer, MyReader reader) {
        this.printer = printer;
        this.reader = reader;
    }

    public void go() {
        while (!isGameOver()) {
            showBeforeGameStep();

            var point = getPoint(currentPlayer);
            var result = inputToBoard(point);
            if (result) {
                currentPlayer = changePlayer();
            }
        }

        printer.showBoard(board);

        if (isWin()) {
            winAction();
        } else {
            drawAction();
        }
    }

    private void showBeforeGameStep() {
        printer.showBoard(board);
        printer.showLn("Current player: %s".formatted(currentPlayer.getName()));
    }

    private Player changePlayer() {
        if (currentPlayer == null) {
            return player1;
        }
        return (currentPlayer == player1) ? player2 : player1;
    }

    private Point getPoint(Player player) {
        var column = readIntWithLabel(board.getSize(), "move to x : ");
        var row = readIntWithLabel(board.getSize(), "move to y : ");

        return new Point (player.getToken(), row, column);
    }

    private int readIntWithLabel(int max, String message) {
        int num;
        while (true) {
            printer.show(message);
            try{
                num = reader.readInt();
                if(num >= 0 || num < max) {
                    return num;
                }

            } catch (InputMismatchException e) {
                printer.showLn("invalid input, please try again");
            }

        }


    }

    private boolean inputToBoard(Point point) {
        try {
            board.input(point);
            return true;
        } catch (GameException e) {
            String message = "!!! " + e.getMessage();
            printer.showLn(message);
            return false;
        }
    }

    public boolean isGameOver() {
        return board.isFull() || isWin();
    }

    public boolean isWin() {
        return getWinTokenOrEmpty() != Board.EMPTY;
    }

    private void winAction() {
        String message = "Game over, WINNER IS: %s".formatted(getWinPlayer().getName());
        printer.showLn(message);
    }

    private void drawAction() {
        printer.showLn("The game ended in a draw...");
    }


    private Player getPlayerByToken(char token) {
        var player = (player1.getToken() == token) ? player1 : (player2.getToken() == token) ? player2 : null;
        if (player == null) {
            throw new GameException("unknown player's token");
        }
        return player;
    }

    public Player getWinPlayer() {
        char token = getWinTokenOrEmpty();
        if (token == Board.EMPTY) {
            throw new GameException("you want to get who won, but there is no winner yet");
        }
        return getPlayerByToken(token);
    }

    //хитрый метод нахождения выигравшего- получаем из field
    //массив всех возможных линий(верт., гориз., диаг.)
    //медленней, чем обычная проверка, но меньше кода в контроллере и красивее
    private char getWinTokenOrEmpty() {
        var array = board.getAllStraightLines();
        char win;
        for (var line : array) {
            win = line[0];

            for (var c : line) {
                if(c == Board.EMPTY || c != win) {
                    win = Board.EMPTY;
                    break;
                }
            }

            if(win != Board.EMPTY) {
                return win;
            }
        }
        return Board.EMPTY;
    }

}
