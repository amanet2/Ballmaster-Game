package com.app.game;
import java.util.Arrays;
import com.app.engine.settings;
import com.app.engine.utilsImpl;
import com.app.engine.cameraImpl;

public class game {
    public static String[] launchArgs;
    static final utilsImpl utils = new utilsImpl();
    static final cameraImpl cam = new cameraImpl();
    static final int roundToTestVal = 36;
    static final int roundToTestNearest = 30;

    public static void main(String[] args) {
        launchArgs = args;
        System.out.printf("Started Game w/ scale %d, args: %s%n", settings.nativeScale, Arrays.toString(launchArgs));
        System.out.printf("Rounding %d to nearest %d: %d", roundToTestVal, roundToTestNearest, utils.roundToNearest(roundToTestVal, roundToTestNearest));
    }
}