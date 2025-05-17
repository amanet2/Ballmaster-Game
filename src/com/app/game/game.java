package com.app.game;

import java.util.Arrays;
import com.app.engine.settings;
import com.app.engine.utils;
import com.app.engine.camera;
import com.app.engine.sprites;
import com.app.engine.keyboard;
import com.app.engine.dict;

public class game {
    static final utils utils = new utils();
    static final camera camera = new camera();
    static final sprites sprites = new sprites();
    static final keyboard keyboard = new keyboard();

    static int roundToTestVal = 36;
    static int roundToTestNearest = 30;

    public static void main(String[] args) {
        System.out.printf("Started Game w/ scale %d, args: %s%n", settings.nativeScale, Arrays.toString(args));

        gameCVarTest.test();
        gameConsoleTest.test();
        gameSchedulerTest.test();
        gameFileSystemTest.test();

        dict testDict = new dict("{foo=bar,bar=");

        System.out.printf(
                "Rounding %d to nearest %d: %d%n",
                roundToTestVal,
                roundToTestNearest,
                utils.roundToNearest(roundToTestVal, roundToTestNearest)
        );
        System.out.printf("Cam coords: %s%n", Arrays.toString(camera.getCoords()));
        System.out.printf("Sprite for 'none': %s%n", sprites.getScaledImage("none", 0, 0));
        System.out.printf("Keyboard code for key a: %d%n", keyboard.getCodeForKey("a"));
        System.out.printf(
                "Test state: %s. (keys: %s) (foo value: %s, bar value:%s)%n",
                testDict,
                testDict.keys(),
                testDict.get("foo"),
                testDict.get("bar")
        );
    }
}