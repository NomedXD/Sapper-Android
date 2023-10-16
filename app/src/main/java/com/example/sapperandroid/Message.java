package com.example.sapperandroid;

public class Message {
    private final String text;
    private final int color;

    public Message(String text, int color) {
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public int getColor() {
        return color;
    }
}
