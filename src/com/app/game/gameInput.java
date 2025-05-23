package com.app.game;

import java.util.Scanner;

public class gameInput {
    public static void init() {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while(true) {
                String input = scanner.nextLine();
                System.out.println(gameConsole.get().readLine(input));
            }
        }).start();
    }
}
