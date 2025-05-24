package com.app.game;

public class gameSettings {
    static int gameScale = 2160;
    static String fileSystemDataPath = "data";
    static String fileSystemFilesPath = "config";
    static boolean showCameraInfo = false;
    static boolean showFps = false;
    static boolean showFrameInfo = false;

    static long frameMetricTimeMillis = System.currentTimeMillis() + 1000;
    static int gameFrames = 0;
    static int gameFramesMetric = 0;
    static int gameFramesSnapshot = 0;
    static int videoFrames = 0;
    static int videoFramesMetric = 0;
    static int videoFramesSnapshot = 0;
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
