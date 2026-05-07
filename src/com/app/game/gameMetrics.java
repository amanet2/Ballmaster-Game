package com.app.game;

public class gameMetrics {
    private static long timeStartedMillis;
    private static long currentTimeMillis;
    private static long frameMetricTimeMillis;
    private static long timeElapsedMillis;

    private static int gameFrames = 0;
    private static int gameFramesPerSecondMetric = 0;
    private static int gameFramesPerSecondMetricSnapshot = 0;
    private static double gameFrametime = 0;
    private static long gameFrametimeLast = 0;
    private static double gameFrametimeMetric = 0;
    private static double gameFrametimeMetricLowest = 0;
    private static double gameFrametimeMetricSnapshotLowest = 0;
    private static double gameFrametimeMetricSnapshotAvg = 0;
    private static double gameFrametimeMetricHighest = 0;
    private static double gameFrametimeMetricSnapshotHighest = 0;

    public static void init() {
        timeStartedMillis = System.currentTimeMillis();
        currentTimeMillis = timeStartedMillis;
        frameMetricTimeMillis = currentTimeMillis + 1000;
        timeElapsedMillis = 0;
    }

    public static long getTimeElapsedMillis() {
        return timeElapsedMillis;
    }

    public static int getGameFrames() {
        return gameFrames;
    }

    public static int getGameFramesPerSecondMetricSnapshot() {
        return gameFramesPerSecondMetricSnapshot;
    }

    public static double getGameFrametimeMetricSnapshotAvg() {
        return gameFrametimeMetricSnapshotAvg;
    }

    public static double getGameFrametimeMetricSnapshotLowest() {
        return gameFrametimeMetricSnapshotLowest;
    }

    public static double getGameFrametimeMetricSnapshotHighest() {
        return gameFrametimeMetricSnapshotHighest;
    }

    public static void update() {
        currentTimeMillis = System.currentTimeMillis();

        timeElapsedMillis = currentTimeMillis - timeStartedMillis;

        gameFrames++;
        gameFramesPerSecondMetric++;
        if(gameFrames >= Integer.MAX_VALUE - 1000)
            gameFrames = 0;

        gameFrametime = currentTimeMillis - gameFrametimeLast;
        gameFrametimeLast = currentTimeMillis;
        gameFrametimeMetric += gameFrametime;

        if(gameFrametime > gameFrametimeMetricHighest)
            gameFrametimeMetricHighest = gameFrametime;
        if(gameFrametime < gameFrametimeMetricLowest)
            gameFrametimeMetricLowest = gameFrametime;

        if(currentTimeMillis > frameMetricTimeMillis) {
            frameMetricTimeMillis = currentTimeMillis + 1000;

            gameFramesPerSecondMetricSnapshot = gameFramesPerSecondMetric;

            gameFramesPerSecondMetric = 0;

            gameFrametimeMetricSnapshotLowest = gameFrametimeMetricLowest;
            gameFrametimeMetricSnapshotAvg = gameFrametimeMetric/1000;
            gameFrametimeMetricSnapshotHighest = gameFrametimeMetricHighest;

            gameFrametimeMetric = 0;
            gameFrametimeMetricLowest = 0;
            gameFrametimeMetricHighest = 0;
        }
    }
}
