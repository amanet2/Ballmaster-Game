package com.app.game;

import com.app.engine.spriteSystem;

import java.util.ArrayList;

public class gameSettings {
    static int gameScale = 2160;
    static String dataPath = "data";
    static String filesPath = "config";
    static boolean showfps = false;
    static boolean showdebug = false;

    static long frameMetricTimeMillis = System.currentTimeMillis() + 1000;
    static int gameFrames = 0;
    static int gameFramesMetric = 0;
    static int gameFramesSnapshot = 0;
    static int videoFrames = 0;
    static int videoFramesMetric = 0;
    static int videoFramesSnapshot = 0;

    static double radix = 0.0;
    static int dir = 1;

    static ArrayList<spriteSystem.gSprite> gSprites = new ArrayList<>();
}
