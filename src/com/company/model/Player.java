package com.company.model;

public class Player {
    private final String name;
    private final char token;

    public Player(String name, char token) {
        this.name = name;
        this.token = token;
    }

    public Player(String name) {
        this(name, name.charAt(0));
    }

    public String getName() {
        return name;
    }

    public char getToken() {
        return token;
    }
}
