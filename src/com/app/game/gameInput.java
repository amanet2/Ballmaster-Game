package com.app.game;

import java.util.Scanner;

public class gameInput {
    public static void init() {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while(true) {
                System.out.println("----------------");
                System.out.print("(Ctrl+C to Exit) Enter command: ");
                String input = scanner.nextLine();
                System.out.printf("%nEntered: %s%n", input);
                String result = gameConsole.get().readLine(input);
                System.out.printf("Result: %s%n", result);
            }
        }).start();
    }
}
