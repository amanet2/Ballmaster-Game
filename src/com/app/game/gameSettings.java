package com.app.game;

public class gameSettings {
    static String fileSystemConfigPath = "config";
    static String fileSystemScriptsPath = "scripts";
    static String fileSystemSpritesPath = "data";

    static boolean showCameraInfo = false;
    static boolean showFps = false;
    static boolean showFrameInfo = false;

    static long frameMetricTimeMillis = System.currentTimeMillis() + 1000;
    static int gameFrames = 0;
    static int gameFramesMetric = 0;
    static int gameFramesMetricSnapshot = 0;
    static double gameFrametime = 0;
    static double gameFrametimeLast = 0;
    static double gameFrametimeMetric = 0;
    static double gameFrametimeMetricLowest = 0;
    static double gameFrametimeMetricSnapshotLowest = 0;
    static double gameFrametimeMetricSnapshotAvg = 0;
    static double gameFrametimeMetricHighest = 0;
    static double gameFrametimeMetricSnapshotHighest = 0;

    static int videoFrames = 0;
    static int videoFramesMetric = 0;
    static int videoFramesMetricSnapshot = 0;
    static double videoFrametime = 0;
    static double videoFrametimeLast = 0;
    static double videoFrametimeMetric = 0;
    static double videoFrametimeMetricLowest = 0;
    static double videoFrametimeMetricSnapshotLowest = 0;
    static double videoFrametimeMetricSnapshotAvg = 0;
    static double videoFrametimeMetricHighest = 0;
    static double videoFrametimeMetricSnapshotHighest = 0;

    static double radix = 0.0;
    static int dir = 1;
}
