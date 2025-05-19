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


        // GAME LOOPS TEST
        // INPUT THREAD WILL READ MOUSE AND KEYBOARD INPUTS
        // RENDER THREAD (SHELL) WILL DRAW WORLD, MENUS, ETC
        // GAME LOOP (MAIN THREAD, THIS THREAD) WILL UPDATE WORLD
        System.out.println("Started game loop, press Ctrl+C to exit...");

        // INPUT THREAD
        new Thread(() -> {
            while(true) {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                String result = gameConsoleTest.gConsoleSystem.readLine(input);
                System.out.printf("Game Console Input Read%n");
                System.out.printf("Entered: %s%n", input);
                System.out.printf("Result: %s%n", result);
//                System.out.println("Input thread read line: " + input);
//                System.out.println("Use the KeyboardListener for the real game instead.");
            }
        }).start();

        // RENDER THREAD
        Thread renderThread = new Thread() {
            long frameMetricTimeMillis = System.currentTimeMillis() + 1000;
            int frames = 0;

            public void run() {
                while(true) {
                    frames++;
                    if(System.currentTimeMillis() > frameMetricTimeMillis) {
                        frameMetricTimeMillis = System.currentTimeMillis() + 1000;
                        System.out.println("FPS: " + frames);
                        frames = 0;
                    }
                }
            }
        };
        renderThread.start();

        // GAME LOOP
        int gameFrames = 0;
        int internalGameRate = 1000;
        long snapshotTimeNanos = System.nanoTime();  // use nano for game timer
        long tickTimeNanos = snapshotTimeNanos;
        long nextFrameTimeNanos;

        while(true) {
            snapshotTimeNanos = System.nanoTime();
            nextFrameTimeNanos = snapshotTimeNanos + (1000000000 / (long) internalGameRate);

            while (tickTimeNanos < snapshotTimeNanos) {
                tickTimeNanos += (1000000000 / (long) internalGameRate);
                //update game stuff, move players, execute scheduled events, etc
                gameFrames++;
                if(gameFrames >= Integer.MAX_VALUE - 1000000000)
                    gameFrames = 0;
            }

            while (nextFrameTimeNanos > System.nanoTime()) {  // wait for next main loop
                //do nothing
            }
        }
    }
}