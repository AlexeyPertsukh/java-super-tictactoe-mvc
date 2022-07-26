package com.company.model;

import java.util.Arrays;
import java.util.Locale;

public class Board {
    private static final int DEFAULT_SIZE = 3;
    private static final int MAX_SIZE = 'z' - 'a';

    private final Figure[][] table;

    public Board(int size) {
        verify(size);
        table = new Figure[size][size];
        for (Figure[] arr : table) {
            Arrays.fill(arr, Figure.NULL);
        }
    }

    public Board() {
        this(DEFAULT_SIZE);
    }

    private void verify(int size) {
        if(size > MAX_SIZE) {
            String message = "board size too big: %s, may be < %s";
            throw new GameException(message.formatted(size, MAX_SIZE));
        }
    }


    public int size() {
        return table.length;
    }

    public void input(Figure figure, int row, int column) {
        verifyInput(figure, row, column);
        table[row][column] = figure;
    }

    public void input(PointStringIndex point) {
        var indexes = toIndexes(point.stringIndex());
        input(point.figure(), indexes[0], indexes[1]);
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
        string = string.toLowerCase();
        verifyToIndex(string);
        var row = string.charAt(0) - 'a';
        var column = string.charAt(1) - 'a';
        return new int[] {row, column};
    }

    private static void verifyToIndex(String string) {
        if(string.length() != 2) {
            String message = "illegal length string index for input to board: %s, must be 2";
            throw new GameException(message.formatted(string.length()));
        }
        for (int i = 0; i < string.length(); i++) {
            if(!isLowerCase(string.charAt(i))) {
                String message = "illegal string index: %s";
                throw new GameException(message.formatted(string));
            }
        }

    }

    private static String indexAsString(int row, int column) {
        return "%c%c".formatted('a' + row, 'a' + column);
    }

    private static boolean isLowerCase(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

}
