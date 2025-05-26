package com.app.game;

public class game {
    // TODO: need to use interfaces as headers for game files too
    // TODO: e.g. we need an interface for console to list out commands
    private static void updateGame() {
        if(gameSettings.dir > 0)
            gameSettings.testSpriteX += 0.2;
        if(gameSettings.dir < 1)
            gameSettings.testSpriteX -= 0.2;
        if(gameSettings.testSpriteX > 300)
            gameSettings.dir = -1;
        if(gameSettings.testSpriteX < -300)
            gameSettings.dir = 1;
    }

    public static void main(String[] args) {
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

                gameMetrics.getGameMetrics();
            }

            gameScheduler.instance().doEvents(System.currentTimeMillis());

            gameGraphics.instance().update();
        }
    }
}
