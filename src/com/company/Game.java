package com.company;

public class Game {

    private final Player player1 = new Player("X");
    private final Player player2 = new Player("0");
    private Player currentPlayer = player1;
    Field field = new Field();
    Iview view;

    public Game(Iview view) {
        this.view = view;
    }

    public void go() {
        while (!isGameOver()) {
            view.showField(field);
            view.showPlayerMessage("Current player: %s".formatted(currentPlayer.getName()));

            var point = getPoint(currentPlayer);
            var result = inputToField(point);
            if (result) {
                currentPlayer = changePlayer();
            }
        }

        view.showField(field);

        if (isWin()) {
            winAction();
        } else {
            drawAction();
        }
    }

    private Player changePlayer() {
        if (currentPlayer == null) {
            return player1;
        }
        return (currentPlayer == player1) ? player2 : player1;
    }

    private Point getPoint(Player player) {
        return view.getPoint(player.getToken(), "move to x : ", "move to y : ");
    }

    private boolean inputToField(Point point) {
        try {
            field.input(point);
            return true;
        } catch (GameException e) {
            String message = "!!! " + e.getMessage();
            view.showMessage(message);
            return false;
        }
    }

    public boolean isGameOver() {
        return field.isFull() || isWin();
    }

    public boolean isWin() {
        return getWinTokenOrEmpty() != Field.EMPTY;
    }

    private void winAction() {
        String message = "Game over, WINNER IS: %s".formatted(getWinPlayer().getName());
        view.showMessage(message);
    }

    private void drawAction() {
        view.showMessage("The game ended in a draw...");
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
        if (token == Field.EMPTY) {
            throw new GameException("you want to get who won, but there is no winner yet");
        }
        return getPlayerByToken(token);
    }

    //хитрый метод нахождения выигравшего- получаем из field
    //массив всех возможных линий(верт., гориз., диаг.)
    //медленней, чем обычная проверка, но меньше кода в контроллере и красивее
    private char getWinTokenOrEmpty() {
        var array = field.getAllStraightLines();
        char win;
        for (var line : array) {
            win = line[0];

            for (var c : line) {
                if(c == Field.EMPTY || c != win) {
                    win = Field.EMPTY;
                    break;
                }
            }

            if(win != Field.EMPTY) {
                return win;
            }
        }
        return Field.EMPTY;
    }

}
