package com.company.model;

public class Player {
    private final String name;
    private final Figure figure;

    public Player(String name, Figure figure) {
        this.name = name;
        this.figure = figure;
    }

    public Player(Figure figure) {
        this(String.valueOf(figure.getSymbol()), figure);
    }

    public String getName() {
        return name;
    }

    public Figure getFigure() {
        return figure;
    }
}
