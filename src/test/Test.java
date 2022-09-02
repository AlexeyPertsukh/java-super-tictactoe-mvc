package test;

import com.company.controller.WinController;
import com.company.model.Board;
import com.company.model.Figure;
import com.company.model.PointStringIndex;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Test {

    @org.junit.jupiter.api.Test
    void testBoardWinVertical() {
        var figure = Figure.SHARP;
        var stringsV0 = new String[]{"aa", "ba", "ca"};
        var stringsV1 = new String[]{"bb", "cb", "db"};
        var stringsV2 = new String[]{"df", "ef", "ff"};
        var arr = new String[][]{stringsV0, stringsV1, stringsV2};

        for (var line : arr) {
            var board = new Board(6);
            for (var idx : line) {
                board.input(new PointStringIndex(figure, idx));
            }
            var win = (new WinController()).getWinOrEmpty(board);
            assertEquals(figure, win);
        }
    }

    @org.junit.jupiter.api.Test
    void testBoardWinHorizontal() {
        var figure = Figure.X;
        var stringsV0 = new String[]{"aa", "ab", "ac"};
        var stringsV1 = new String[]{"fd", "fe", "ff"};
        var stringsV2 = new String[]{"cd", "ce", "cf"};
        var arr = new String[][]{stringsV0, stringsV1, stringsV2};

        for (var line : arr) {
            var board = new Board(6);
            for (var idx : line) {
                board.input(new PointStringIndex(figure, idx));
            }
            var win = (new WinController()).getWinOrEmpty(board);
            assertEquals(figure, win);
        }
    }

    @org.junit.jupiter.api.Test
    void testBoardWinDiagonal() {
        var figure = Figure.X;
        var stringsV0 = new String[]{"aa", "bb", "cc"};
        var stringsV1 = new String[]{"dd", "ee", "ff"};
        var stringsV2 = new String[]{"af", "be", "cd"};
        var stringsV3 = new String[]{"dc", "eb", "fa"};
        var stringsV4 = new String[]{"bd", "cc", "db"};
        var arr = new String[][]{stringsV0, stringsV1, stringsV2, stringsV3, stringsV4};

        for (var line : arr) {
            var board = new Board(6);
            for (var idx : line) {
                board.input(new PointStringIndex(figure, idx));
            }
            var win = (new WinController()).getWinOrEmpty(board);
            assertEquals(figure, win);
        }
    }

}