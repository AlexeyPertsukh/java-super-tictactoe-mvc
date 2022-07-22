package com.company;

import java.util.Arrays;

public class Field {
    public static final int SIZE = 3;
    public static final char EMPTY = ' ';

    private final char[][] table = new char[SIZE][SIZE];

    public Field() {
        for (char[] chars : table) {
            Arrays.fill(chars, EMPTY);
        }
    }

    public void input(char token, int row, int column) {
        verifyInput(token, row, column);
        table[row][column] = token;
    }

    public void input(Point point) {
        input(point.token(), point.row(), point.column());
    }

    private void verifyInput(char token, int row, int column) {
        if (!isCorrectPlace(row, column)) {
            throw new GameException("place (x=%d, y=%d) is incorrect, must be in the range 0...%s  ".formatted(column, row, SIZE - 1));
        }

        if (!isFreePlace(row, column)) {
            throw new GameException("place (x=%d, y=%d) is busy".formatted(column, row));
        }

        if (token == EMPTY) {
            throw new GameException("this character must not be used for a token: %s ( %s dec. code))".formatted(EMPTY, (int) EMPTY));
        }
    }

    public char getToken(int row, int column) {
        return table[row][column];
    }

    public boolean isFreePlace(int row, int column) {
        return getToken(row, column) == EMPTY;
    }

    public boolean isCorrectPlace(int row, int column) {
        return (row >= 0 && row < SIZE) && (column >= 0 && column < SIZE);
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
        char[][] out = new char[2 * SIZE + 2][SIZE];
        int count = 0;

        for (int i = 0; i < SIZE; i++)  {
            for (int j = 0; j < SIZE; j++) {
                out[count][j] = table[i][j];
                out[count + 1][j] = table[j][i];
            }
            count += 2;
        }
        for (int i = 0; i < SIZE; i++) {
            out[count][i] = table[i][i];
            out[count + 1][i] = table[i][SIZE - i - 1];
        }

        return out;
    }

}
