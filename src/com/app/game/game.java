package com.app.game;

public class game {
    private static void init(String[] args) {
        System.out.println("----------------");
        System.out.println("INITIALIZING GAME SYSTEMS...");
        System.out.println("----------------");

        gameConsole.init();
        gameCVars.init();
        gameFiles.init();
        gameFiles.execCfgFile("config/autoexec.cfg");
        gameCVars.instance().parseArgs(args);
        gameScheduler.init();
        gameGraphics.init();
        gameInput.init();
        gameSprites.init();
        gameMetrics.init();

        System.out.println("----------------");
        System.out.println("STARTED GAME SUCCESSFULLY!");
        System.out.println("YOU MAY BEGIN ENTERING CONSOLE COMMANDS");
        System.out.println("----------------");
        System.out.print("% ");
    }

    public static void main(String[] args) {
        init(args);

        int internalGameRate = 1000;
        long snapshotTimeNanos = System.nanoTime();  // use nano for game timer in case we want >1000 fps
        long tickTimeNanos = snapshotTimeNanos;

        // GAME LOOP
        while(true) {
            snapshotTimeNanos = System.nanoTime();

            // update state
            while (tickTimeNanos < snapshotTimeNanos) {
                tickTimeNanos += (1000000000 / internalGameRate);

                gameState.update();

                gameMetrics.update();
            }

            gameScheduler.instance().doEvents(System.currentTimeMillis());

            gameGraphics.instance().update();
        }
    }
}
