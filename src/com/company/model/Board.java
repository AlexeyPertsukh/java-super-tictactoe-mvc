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

    public int getSize() {
        return table.length;
    }

    public void input(Figure figure, int row, int column) {
        verifyInput(figure, row, column);
        table[row][column] = figure;
    }

    public void input(Point point) {
        input(point.figure(), point.row(), point.column());
    }

    private void verifyInput(Figure figure, int row, int column) {
        if (!isCorrectCoord(row, column)) {
            String message = "place (x=%d, y=%d) is incorrect, must be in the range 0...%s  ";
            throw new GameException(message.formatted(column, row, getSize()));
        }

        if (!isFreeCoord(row, column)) {
            String message = "place (x=%d, y=%d) is busy";
            throw new GameException(message.formatted(column, row));
        }

        if (figure.isNull()) {
            String message = "this character must not be used for a figure: %s ( %s dec. code))";
            throw new GameException(message.formatted(Figure.NULL.getSymbol(), (int) Figure.NULL.getSymbol()));
        }
    }

    public Figure getFigure(int row, int column) {
        return table[row][column];
    }

    public boolean isFreeCoord(int row, int column) {
        return getFigure(row, column).isNull();
    }

    public boolean isCorrectCoord(int row, int column) {
        return (row >= 0 && row < getSize()) && (column >= 0 && column < getSize());
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

    public Figure[][] getAllStraightLines() {
        Figure[][] out = new Figure[2 * getSize() + 2][getSize()];
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
