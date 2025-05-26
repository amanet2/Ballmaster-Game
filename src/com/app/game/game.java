package com.app.game;

public class game {
    // TODO: need to use interfaces as headers for game files too
    // TODO: e.g. we need an interface for console to list out commands
    static void updateGame() {
        if(gameSettings.timeStartMillis < 1)
            gameSettings.timeStartMillis = System.currentTimeMillis() ;

        gameSettings.timeElapsedMillis = System.currentTimeMillis() - gameSettings.timeStartMillis;

        if(gameSettings.dir > 0)
            gameSettings.radix+=0.2;
        if(gameSettings.dir < 1)
            gameSettings.radix-=0.2;
        if(gameSettings.radix > 300)
            gameSettings.dir = -1;
        if(gameSettings.radix < -300)
            gameSettings.dir = 1;
    }

    static void getGameMetrics() {
        gameSettings.gameFrames++;
        gameSettings.gameFramesPerSecondMetric++;
        if(gameSettings.gameFrames >= Integer.MAX_VALUE - 1000)
            gameSettings.gameFrames = 0;

        gameSettings.gameFrametime = System.currentTimeMillis() - gameSettings.gameFrametimeLast;
        gameSettings.gameFrametimeLast = System.currentTimeMillis();
        gameSettings.gameFrametimeMetric += gameSettings.gameFrametime;

        if(gameSettings.gameFrametime > gameSettings.gameFrametimeMetricHighest)
            gameSettings.gameFrametimeMetricHighest = gameSettings.gameFrametime;
        if(gameSettings.gameFrametime < gameSettings.gameFrametimeMetricLowest)
            gameSettings.gameFrametimeMetricLowest = gameSettings.gameFrametime;
    }

    public static void main(String[] args) {
        System.out.println("----------------");
        System.out.println("INITIALIZING GAME SYSTEMS...");
        System.out.println("----------------");

        gameConsole.init();
        gameCVars.init();
        gameFiles.init();
        gameFiles.execFile("config/autoexec.cfg");
        gameCVars.parseLaunchArgs(args);
        gameScheduler.init();
        gameGraphics.init();
        gameInput.init();
        gameSprites.init();

        System.out.println("----------------");
        System.out.println("STARTED GAME SUCCESSFULLY!");
        System.out.println("YOU MAY BEGIN ENTERING CONSOLE COMMANDS");
        System.out.println("----------------");
        System.out.print("% ");

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

                getGameMetrics();
            }

            gameScheduler.instance().doEvents(System.currentTimeMillis());

            gameGraphics.instance().update();
        }
    }
}
