package com.company.model;

import java.util.Arrays;

public class Board {
    private static final int DEFAULT_SIZE = 3;
    public static final char EMPTY = ' ';

    private final char[][] table;

    public Board(int size) {
        table = new char[size][size];
        for (char[] chars : table) {
            Arrays.fill(chars, EMPTY);
        }
    }

    public Board() {
        this(DEFAULT_SIZE);
    }

    public int getSize() {
        return table.length;
    }

    public void input(char token, int row, int column) {
        verifyInput(token, row, column);
        table[row][column] = token;
    }

    public void input(Point point) {
        input(point.token(), point.row(), point.column());
    }

    private void verifyInput(char token, int row, int column) {
        if (!isCorrectCoord(row, column)) {
            String message = "place (x=%d, y=%d) is incorrect, must be in the range 0...%s  ";
            throw new GameException(message.formatted(column, row, getSize()));
        }

        if (!isFreeCoord(row, column)) {
            String message = "place (x=%d, y=%d) is busy";
            throw new GameException(message.formatted(column, row));
        }

        if (token == EMPTY) {
            String message = "this character must not be used for a token: %s ( %s dec. code))";
            throw new GameException(message.formatted(EMPTY, (int) EMPTY));
        }
    }

    public char getToken(int row, int column) {
        return table[row][column];
    }

    public boolean isFreeCoord(int row, int column) {
        return getToken(row, column) == EMPTY;
    }

    public boolean isCorrectCoord(int row, int column) {
        return (row >= 0 && row < getSize()) && (column >= 0 && column < getSize());
    }


    public boolean isFull() {
        for (var row : table) {
            for (var c : row) {
                if (c == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public char[][] toCharArray() {
        return table.clone();
    }

    public char[][] getAllStraightLines() {
        char[][] out = new char[2 * getSize() + 2][getSize()];
        int count = 0;

        for (int i = 0; i < getSize(); i++)  {
            for (int j = 0; j < getSize(); j++) {
                out[count][j] = table[i][j];
                out[count + 1][j] = table[j][i];
            }
            count += 2;
        }
        for (int i = 0; i < getSize(); i++) {
            out[count][i] = table[i][i];
            out[count + 1][i] = table[i][getSize() - i - 1];
        }

        return out;
    }

}
