package com.app.game;

import com.app.engine.engine;
import com.app.engine.inputSystem.gInputSystem;
import com.app.engine.inputSystem.impulse;

import java.util.Scanner;

public class gameInput {
    public static gInputSystem instance() {
        return engine.instance().gInputSystem;
    }

    public static void init() {
        instance().init();

        gameGraphics.instance().getCanvas().addKeyListener(instance().getKeyboard());

        // Thread to listen to terminal window inputs
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while(true) {
                System.out.print("% ");
                String input = scanner.nextLine();
                String output = gameConsole.instance().readLine(input);
                System.out.print(output + (!output.isEmpty() ? "\n" : ""));
            }
        }).start();
    }
}
