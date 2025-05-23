package com.app.game;

import java.io.File;
import java.util.Arrays;

public class game {
    static void parseLaunchArgs(String[] args) {
        for(int i = 0; i < args.length; i++) {
            System.out.println("LAUNCH ARG: " + args[i]);
            if(gameCVars.get().getCVarValue(args[i]) != null && args.length > i+1) {
                System.out.println("LAUNCH ARG VALUE: " + args[i+1]);
                gameCVars.get().setCVarValue(args[i], args[i+1]);
                i+=1;
            }
        }
    }

    static void updateGame() {
        if(gameSettings.dir > 0)
            gameSettings.radix+=0.1;
        if(gameSettings.dir < 1)
            gameSettings.radix-=0.1;
        if(gameSettings.radix > 75)
            gameSettings.dir = -1;
        if(gameSettings.radix < -75)
            gameSettings.dir = 1;
    }

    public static void main(String[] args) {
        System.out.println("----------------");
        System.out.println("INITIALIZING GAME SYSTEMS...");
        System.out.println("----------------");

        gameCVars.init();
        gameFiles.init();
        // TODO: set cvars from cfg file(s) before launch args
        parseLaunchArgs(args);
        gameGraphics.init();
        gameConsole.init();
        gameScheduler.init();
        gameSprites.init();
        gameInput.init();

        System.out.println("----------------");
        System.out.println("STARTED GAME SUCCESSFULLY!");
        System.out.println("YOU MAY BEGIN ENTERING CONSOLE COMMANDS");
        System.out.println("----------------");

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

                gameSettings.gameFrames++;
                gameSettings.gameFramesMetric++;
                if(gameSettings.gameFrames >= Integer.MAX_VALUE - 1000)
                    gameSettings.gameFrames = 0;
            }

            gameScheduler.get().doEvents(System.currentTimeMillis());

            gameGraphics.get().update();
        }
    }
}
