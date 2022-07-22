package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView implements Iview {

    @Override
    public void showField(Field field) {
        final var template = " %c ";
        final var board = '|';
        var arr = field.toCharArray();

        System.out.println();

        for (int i = 0; i < arr.length; i++) {
            var line = arr[i];
            for (int j = 0; j < line.length; j++) {
                System.out.print(template.formatted(line[j]));
                if (j < line.length - 1) {
                    System.out.print(board);
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
    public void showPlayerMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showMessage(String... strings) {
        for (var s : strings) {
            System.out.println(s);
        }
    }

    @Override
    public Point getPoint(char token, String messageX, String messageY) {
        var column = nextIntWithLabel(messageX);
        var row = nextIntWithLabel(messageY);
        return new Point(token, row, column);
    }

    private int nextIntWithLabel(String label) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print(label);
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("invalid input, please try again\n");
            }
        }
    }

}

