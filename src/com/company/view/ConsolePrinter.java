package com.company.view;

import com.company.model.Board;

public class ConsolePrinter implements MyPrinter {

    @Override
    public void showBoard(Board board) {
        final var template = " %c ";
        final var sep = '|';
        var arr = board.toCharArray();

        System.out.println();

        for (int i = 0; i < arr.length; i++) {
            var line = arr[i];
            for (int j = 0; j < line.length; j++) {
                System.out.print(template.formatted(line[j]));
                if (j < line.length - 1) {
                    System.out.print(sep);
                }
            }
            System.out.println();

            if(i >= arr.length - 1) {
                break;
            }

            for (int j = 0; j < line.length; j++) {
                System.out.print("-".repeat(template.length() - 1));
                if(j < line.length - 1) {
                    System.out.print("+");
                }

            }
            System.out.println();
        }
        System.out.println();
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




