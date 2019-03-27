package ru.otus.boljshoj.service;

public interface IOService {
    String getStringFromUser();
    void printMessage(String example, Object... params);
}
