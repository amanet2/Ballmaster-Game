package com.app.game;

import com.app.engine.engine;
import com.app.engine.inputSystem.gKeyboard;
import com.app.engine.inputSystem.gMouse;

import java.util.Scanner;

public class gameInput {
    private static engine engineInstance = engine.instance();

    private static final gKeyboard keyboard = engineInstance.gKeyboard;
    private static final gMouse mouse =  engineInstance.gMouse;

    public gKeyboard getKeyboard() {
        return keyboard;
    }

    public gMouse getMouse() {
        return mouse;
    }

    public static void init() {
        // Thread to listen to terminal window inputs
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
