package com.app.game;

import com.app.engine.inputSystem.gKeyboard;
import com.app.engine.inputSystem.gMouse;

import java.awt.event.KeyEvent;
import java.util.Scanner;

public class gameInput {
    // TODO: some sort of init() for key/mouse creation

    public static gKeyboard keyboard = new gKeyboard() {
        public void keyPressed(KeyEvent e) {
            System.out.println("Pressed Key: " + e.getKeyCode());
        }

        public void keyReleased(KeyEvent e) {
            System.out.println("Released Key: " + e.getKeyCode());
        }
    };

    public static void init() {
        // TODO: work with both these inputs for now
        gameGraphics.instance().getCanvas().addKeyListener(keyboard);

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
