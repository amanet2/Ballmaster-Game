package com.app.game;

import java.util.Scanner;

public class gameLoopsTest {
    public static void inputLoopTest() {
        // INPUT THREAD
        // TODO: Use KeyboardListener instead (see old game)
        new Thread(() -> {
            while(true) {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                System.out.println("----------------");
                System.out.printf("Game Console Input Read%n");
                System.out.printf("Entered: %s%n", input);
                String result = gameConsoleTest.gConsoleSystem.readLine(input);
                System.out.printf("Result: %s%n", result);
            }
        }).start();
    }

    public static void updateAndRenderTest() {
        // GAME AND RENDER LOOP
        int gameFrames = 0;
        int internalGameRate = 1000;
        long snapshotTimeNanos = System.nanoTime();  // use nano for game timer
        long tickTimeNanos = snapshotTimeNanos;

        long frameMetricTimeMillis = System.currentTimeMillis() + 1000;
        int framesTotal = 0;
        int framesMetric = 0;

        while(true) {
            snapshotTimeNanos = System.nanoTime();

            //game update
            while (tickTimeNanos < snapshotTimeNanos) {
                tickTimeNanos += (1000000000 / (long) internalGameRate);
                //update game stuff, move players, execute scheduled events, etc
                gameFrames++;
                if(gameFrames >= Integer.MAX_VALUE - 1000000000)
                    gameFrames = 0;
            }

            //game render
            framesMetric++;
            framesTotal++;
            if(System.currentTimeMillis() > frameMetricTimeMillis) {
                frameMetricTimeMillis = System.currentTimeMillis() + 1000;
                System.out.println("----------------");
                System.out.println("Time: " + System.currentTimeMillis());
                System.out.println("Ticks: " + gameFrames);
                System.out.println("Frames: " + framesTotal);
                System.out.println("FPS: " + framesMetric);
                System.out.println("(Ctrl+C to exit) Enter your command: ");
                if(framesTotal >= Integer.MAX_VALUE - 1000000000)
                    framesTotal = 0;
                framesMetric = 0;
            }
        }
    }
}
