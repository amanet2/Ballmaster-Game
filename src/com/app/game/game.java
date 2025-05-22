package com.app.game;

import java.util.ArrayList;
import java.util.Arrays;

import com.app.engine.spriteSystem.gSprite;

public class game {
    static String basePath = "";

    static long frameMetricTimeMillis = System.currentTimeMillis() + 1000;
    static int gameFrames = 0;
    static int gameFramesMetric = 0;
    static int gameFramesSnapshot = 0;
    static int videoFrames = 0;
    static int videoFramesMetric = 0;
    static int videoFramesSnapshot = 0;
    static double radix = 0.0;
    static int dir = 1;

    static ArrayList<gSprite> gSprites = new ArrayList<>();

    static void parseLaunchArgs(String[] args) {
        for(int i = 0; i < args.length; i++) {
            String argname = args[i].toLowerCase();
            System.out.println("ARG: " + argname);
            if(argname.equalsIgnoreCase("basepath")) {
                if(args.length > i+1) {
                    basePath = args[i + 1];
                    i += 1;
                }
            }
        }
    }

    static void updateGame() {
        if(dir > 0)
            radix+=0.1;
        if(dir < 1)
            radix-=0.1;
        if(radix > 75)
            dir = -1;
        if(radix < -75)
            dir = 1;
    }

    public static void main(String[] args) {
        System.out.printf("Started Game w/ args: %s%n", Arrays.toString(args));
        parseLaunchArgs(args);
        System.out.printf("Base Path: %s%n", basePath);

        // TODO: these game pointers should be instances initialized in proper order or made singletons
        gameCVars.init();
        gameConsole.init();
        gameScheduler.init();
        gameSprites.init();
        gameInput.init();

        int internalGameRate = 1000;
        long snapshotTimeNanos = System.nanoTime();  // use nano for game timer
        long tickTimeNanos = snapshotTimeNanos;

        // GAME LOOP
        while(true) {
            snapshotTimeNanos = System.nanoTime();

            // update state
            while (tickTimeNanos < snapshotTimeNanos) {
                tickTimeNanos += (1000000000 / internalGameRate);

                updateGame();

                gameFrames++;
                gameFramesMetric++;
                if(gameFrames >= Integer.MAX_VALUE - 1000)
                    gameFrames = 0;
            }

            gameScheduler.get().doEvents(System.currentTimeMillis());

            gameGraphics.get().update();
        }
    }
}
