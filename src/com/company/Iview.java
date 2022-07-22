package com.company;

public interface Iview {

    void showField(Field field);

    void showPlayerMessage(String message);

    void showMessage(String... strings);

    Point getPoint(char token, String messageX, String messageY);

}
