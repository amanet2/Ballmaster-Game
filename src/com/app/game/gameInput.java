package com.app.game;

import java.util.Scanner;

public class gameInput {
    public static void init() {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while(true) {
                System.out.print("% ");
                String input = scanner.nextLine();
                System.out.print(gameConsole.get().readLine(input));
            }
        }).start();
    }
}
