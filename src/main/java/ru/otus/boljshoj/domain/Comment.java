package ru.otus.boljshoj.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Comment {

    private String text;

    public Comment(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}