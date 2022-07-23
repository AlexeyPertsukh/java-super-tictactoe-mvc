package com.company.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleReader implements MyReader {

    @Override
    public int readInt() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

}

