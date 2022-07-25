package com.company.controller;

import com.company.model.*;


import com.company.view.MyPrinter;
import com.company.view.MyReader;

import java.util.InputMismatchException;

public class Game {

    private final Player[] players;
    private final Board board;
    private Player currentPlayer;
    private final MyPrinter printer;
    private final MyReader reader;

    public Game(Board board, Player[] players, MyPrinter printer, MyReader reader) {
        this.board = board;
        this.players = players;
        this.printer = printer;
        this.reader = reader;
    }

    public void go() {
        currentPlayer = changePlayer();

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
            return players[0];
        }

        for (int i = 0; i < players.length; i++) {
            if (currentPlayer == players[i]) {
                return (i < players.length - 1) ? players[i + 1] : players[0];
            }
        }
        throw new GameException("unknown player");
    }

    private PointStringIndex getPoint(Player player) {
        printer.show("input cell : ");
        var index = reader.readString();

        return new PointStringIndex(player.getFigure(), index);
    }

    private int readIntWithLabel(int max, String message) {
        int num;
        while (true) {
            printer.show(message);
            try {
                num = reader.readInt();
                if (num >= 0 || num < max) {
                    return num;
                }

            } catch (InputMismatchException e) {
                printer.showLn("invalid input, please try again");
            }
        }
    }

    private boolean inputToBoard(PointStringIndex point) {
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
        var win = new WinController();
        return win.getWinOrEmpty(board) != Figure.NULL;
    }

    private void winAction() {
        String message = "Game over, WINNER IS: %s".formatted(getWinPlayer().getName());
        printer.showLn(message);
    }

    private void drawAction() {
        printer.showLn("The game ended in a draw...");
    }


    private Player getPlayerByFigure(Figure figure) {
        for (Player player : players) {
            if (player.getFigure() == figure) {
                return player;
            }
        }
        throw new GameException("unknown player's figure");
    }

    public Player getWinPlayer() {
        var win = new WinController();
        var figure = win.getWinOrEmpty(board);
        if (figure.isNull()) {
            throw new GameException("you want to get who won, but there is no winner yet");
        }
        return getPlayerByFigure(figure);
    }


}
