package com.company.model;

import java.util.Arrays;

public class Board {
    private static final int DEFAULT_SIZE = 3;

    private final Figure[][] table;

    public Board(int size) {
        table = new Figure[size][size];
        for (Figure[] arr : table) {
            Arrays.fill(arr, Figure.NULL);
        }
    }

    public Board() {
        this(DEFAULT_SIZE);
    }

    public int size() {
        return table.length;
    }

    public void input(Figure figure, int row, int column) {
        verifyInput(figure, row, column);
        table[row][column] = figure;
    }

    public void input(Point point) {
        input(point.figure(), point.row(), point.column());
    }

    public void input(PointStringIndex point) {
        var coord = toIndexes(point.stringIndex());
        input(point.figure(), coord[0], coord[1]);
    }

    private void verifyInput(Figure figure, int row, int column) {
        if (!isCorrectCell(row, column)) {
            String message = "cell (row=%d, column=%d) is incorrect, must be in the range 0...%s  ";
            throw new GameException(message.formatted(row, column, size()));
        }

        if (!isFreeCell(row, column)) {
            String message = "cell %s(row=%d, column=%d) is busy";
            throw new GameException(message.formatted(indexAsString(row, column), row, column));
        }

        if (figure.isNull()) {
            String message = "this character must not be used for a figure: %s ( %s dec. code))";
            throw new GameException(message.formatted(Figure.NULL.getChar(), (int) Figure.NULL.getChar()));
        }
    }

    public Figure get(int row, int column) {
        return table[row][column];
    }

    public String getString(int row, int column) {
        var figure = get(row, column);
        if (!figure.isNull()) {
            return String.valueOf(figure.getChar());
        } else {
            return indexAsString(row, column);
        }
    }

    public boolean isFreeCell(int row, int column) {
        return get(row, column).isNull();
    }

    public boolean isCorrectCell(int row, int column) {
        return (row >= 0 && row < size()) && (column >= 0 && column < size());
    }


    public boolean isFull() {
        for (var row : table) {
            for (var f : row) {
                if ( f.isNull()) {
                    return false;
                }
            }
        }
        return true;
    }

    public Figure[][] toFigureArray() {
        return table.clone();
    }

    private int[] toIndexes(String string) {
        int num = (int) string.charAt(0) - 'a';
        var row = num / size();
        var column = num % size();
        return new int[] {row, column};
    }

    private String indexAsString(int row, int column) {
        return String.valueOf((char) ('a' + row * size() + column));
    }

}
