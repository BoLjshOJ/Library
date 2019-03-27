package ru.otus.boljshoj.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {
    private Scanner sc = new Scanner(System.in);

    @Override
    public String getStringFromUser() {
        return sc.nextLine();
    }

    @Override
    public void printMessage(String example, Object... params) {
        System.out.printf(example, params);
        System.out.println();
    }
}