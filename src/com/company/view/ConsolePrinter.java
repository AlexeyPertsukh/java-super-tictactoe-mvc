package com.company.view;

import com.company.model.Board;

public class ConsolePrinter implements MyPrinter {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BOLD_GREEN =    "\033[1;32m";
    public static final String ANSI_BOLD_WHITE =    "\033[1;37m";

    public static final String DEFAULT_FIGURE_COLOR = ANSI_BOLD_WHITE;
    public static final String DEFAULT_LINE_COLOR = ANSI_GREEN;
    public static final String DEFAULT_LETTER_COLOR = ANSI_GREEN;


    @Override
    public void showBoard(Board board) {
        showBoard(board, DEFAULT_LETTER_COLOR, DEFAULT_FIGURE_COLOR, DEFAULT_LINE_COLOR);
    }

    public void showBoard(Board board, String letterColor, String figureColor, String lineColor) {
        final var tplFigure = "  %s  ";
        final var tplNull = "%s   ";
        final var sep = '|';

        System.out.println();

        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.size(); j++) {
                var figure = board.get(i, j);
                String string = (figure.isNull()) ? tplNull.formatted(board.getString(i, j)) : tplFigure.formatted(board.getString(i, j));

                if(figure.isNull()) {
                    printColor(string, letterColor);
                } else {
                    printColor(string, figureColor);
                }

                if (j < board.size() - 1) {
                    printColor(sep, lineColor);
                }
            }
            System.out.println();

            if(i >= board.size() - 1) {
                break;
            }

            for (int j = 0; j < board.size(); j++) {
                printColor("-".repeat(tplFigure.length() - 1), lineColor);
                if(j < board.size() - 1) {
                    printColor("+", lineColor);
                }

            }
            System.out.println();
        }
        System.out.println();
    }


    private static void printColor(String message, String color){
        setTextColor(color);
        System.out.print(message);
        resetTextColor();
    }

    private static void printColor(char ch, String color){
        printColor(String.valueOf(ch), color);
    }


    private static void setTextColor(String color){
        System.out.print(color);
    }

    private static void resetTextColor(){
        System.out.print(ANSI_RESET);
    }


    @Override
    public void show(String string) {
            System.out.print(string);
    }

    @Override
    public void showLn(String string) {
        System.out.println(string);
    }
}




