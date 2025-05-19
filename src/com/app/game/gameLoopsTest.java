package com.app.game;

import java.util.Scanner;

public class gameLoopsTest {
    public static void inputLoopTest() {
        // INPUT THREAD
        new Thread(() -> {
            while(true) {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                System.out.printf("Game Console Input Read%n");
                System.out.printf("Entered: %s%n", input);
                String result = gameConsoleTest.gConsoleSystem.readLine(input);
                System.out.printf("Result: %s%n", result);
//                System.out.println("Input thread read line: " + input);
//                System.out.println("Use the KeyboardListener for the real game instead.");
            }
        }).start();
    }

    public static void renderLoopTest() {
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
    }

    public static void gameLoopTest() {
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
