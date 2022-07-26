package com.company.controller;

import com.company.model.Board;
import com.company.model.Figure;

public class WinController {

    private static final int LINE_LNG = 3;

    public Figure getWinOrEmpty(Board board) {
        for (int i = 0; i < board.size() - LINE_LNG + 1; i++) {
            for (int j = 0; j < board.size() - LINE_LNG + 1; j++) {
                var win = getWinOrEmptyFromSquare(board, i, j);
                if (!win.isNull()) {
                    return win;
                }
            }
        }
        return Figure.NULL;
    }


    public Figure getWinOrEmptyFromSquare(Board board, int stRow, int stColumn) {
        var win = getLineWin(board, stRow, stColumn);
        if (!win.isNull()) {
            return win;
        }

        return getDiagonalWin(board, stRow, stColumn);
    }


    public Figure getLineWin(Board board, int stRow, int stColumn) {
        var endRow = stRow + LINE_LNG;
        var endColumn = stColumn + LINE_LNG;

        for (int direction = 0; direction < 2; direction++) {
            for (int i = 0; i < LINE_LNG; i++) {
                var win = (direction == 0)
                        ? board.get(i + stRow, stColumn)
                        : board.get(stRow, i + stColumn);
                for (int j = 0; j < LINE_LNG; j++) {
                    Figure cur = (direction == 0)
                            ? board.get(i + stRow, j + stColumn)
                            : board.get(j + stRow, i + stColumn);
                    if (cur.isNull() || cur != win) {
                        win = Figure.NULL;
                        break;
                    }
                }
                if (!win.isNull()) {
                    return win;
                }
            }
        }
        return Figure.NULL;
    }

    public Figure getDiagonalWin(Board board, int startRow, int startColumn) {
        var endRow = startRow + LINE_LNG;

        for (int direction = 0; direction < 2; direction++) {
            var win = (direction == 0) ? board.get(startRow, startColumn) : board.get(endRow - 1, startColumn);
            for (int i = 0; i < LINE_LNG; i++) {
                var cur = (direction == 0)
                        ? board.get(startRow + i, startColumn + i)
                        : board.get(endRow - i - 1, startColumn + i);
                if (cur.isNull() || cur != win) {
                    win = Figure.NULL;
                    break;
                }
            }
            if (!win.isNull()) {
                return win;
            }
        }
        return Figure.NULL;
    }


}
