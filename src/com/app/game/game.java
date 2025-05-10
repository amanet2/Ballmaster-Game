package com.app.game;
import java.util.Arrays;
import com.app.engine.settings;
import com.app.engine.utils;

public class game {
    public static String[] launchArgs;

    public static void main(String[] args) {
        launchArgs = args;
        System.out.printf("Started Game w/ scale %d, args: %s%n", settings.nativeScale, Arrays.toString(launchArgs));
        System.out.printf("Rounding 36 to nearest 30: %d", utils.roundTo(36, 30));
    }
}