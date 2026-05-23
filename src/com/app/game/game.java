package com.app.game;

public class game {
    public static void main(String[] args) {
        gameSystems.init(args);

        long snapshotTimeNanos = System.nanoTime();  // use nano for game timer in case we want >1000 fps
        long tickTimeNanos = snapshotTimeNanos;

        // GAME LOOP
        while (true) {
            snapshotTimeNanos = System.nanoTime();

            // update state
            while (tickTimeNanos < snapshotTimeNanos) {
                tickTimeNanos += (long) (1000000000.0 / gameCVars.worldTickRate);

                gameState.update();

//                gameMetrics.update();
            }

            gameScheduler.instance().doEvents(System.currentTimeMillis());

            gameGraphics.instance().update();
        }
    }
}
