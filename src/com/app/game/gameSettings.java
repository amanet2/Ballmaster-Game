package com.app.game;

public class gameSettings {
    static String fileSystemConfigPath = "config";
    static String fileSystemScriptsPath = "scripts";
    static String fileSystemSpritesPath = "data";

    static boolean showTimeElapsed = false;
    static boolean showCameraInfo = false;
    static boolean showFrameInfo = false;

    // longtime to get snapshots for ALL metrics
    static long frameMetricTimeMillis = System.currentTimeMillis() + 1000;

    static int gameFrames = 0;
    static int gameFramesPerSecondMetric = 0;
    static int gameFramesPerSecondMetricSnapshot = 0;
    static double gameFrametime = 0;
    static long gameFrametimeLast = 0;
    static double gameFrametimeMetric = 0;
    static double gameFrametimeMetricLowest = 0;
    static double gameFrametimeMetricSnapshotLowest = 0;
    static double gameFrametimeMetricSnapshotAvg = 0;
    static double gameFrametimeMetricHighest = 0;
    static double gameFrametimeMetricSnapshotHighest = 0;

    static double testSpriteX = 0.0;
    static double testSpriteY = 0.0;
    static int dir = 1;

    static long timeStartMillis = 0;
    static long timeElapsedMillis = 0;
}
