package com.app.game;

import java.util.Arrays;
import java.util.Scanner;

import com.app.engine.settings;

public class game {
    public static void main(String[] args) {
        System.out.printf("Started Game w/ scale %d, args: %s%n", settings.nativeScale, Arrays.toString(args));

        gameMiscTest.test();
        gameCVarTest.test();
        gameSpriteTest.test();  // TODO: why does this test make the run hang for a few seconds at the end?
        gameConsoleTest.test();
        gameSchedulerTest.test();
        gameFileSystemTest.test();

        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press enter key to exit");
            scanner.nextLine();
            System.exit(0);
        });
        inputThread.start();

        long currentTimeNanos = System.nanoTime();  // use nano for game timer
        long currentTimeMillis = System.currentTimeMillis() + 1000;  // use millis for timing fps
        int frames = 0;
        while(true) {
            frames++;
            if(System.currentTimeMillis() > currentTimeMillis) {
                currentTimeMillis = System.currentTimeMillis() + 1000;
                System.out.println("Frames last second: " + frames);
                frames = 0;
            }
        }
    }
}