package com.app.game;

import java.util.Arrays;
import com.app.engine.settings;
import com.app.engine.utils;
import com.app.engine.camera;
import com.app.engine.keyboard;
import com.app.engine.dict;

public class game {
    static final camera camera = new camera();
    static final keyboard keyboard = new keyboard();

    public static void main(String[] args) {
        System.out.printf("Started Game w/ scale %d, args: %s%n", settings.nativeScale, Arrays.toString(args));

        gameCVarTest.test();
        gameSpriteTest.test();
        gameConsoleTest.test();
        gameSchedulerTest.test();
        gameFileSystemTest.test();

        System.out.printf("Rounding 36 to nearest 30: %d%n", utils.roundToNearest(36, 30));
        System.out.printf("Rounding 66 to nearest 30: %d%n", utils.roundToNearest(66, 30));
        System.out.printf("Cam coords: %s%n", Arrays.toString(camera.getCoords()));
        System.out.printf("Keyboard code for key a: %d%n", keyboard.getCodeForKey("a"));

        dict testDict = new dict("{foo=bar,bar=");
        System.out.printf(
                "Test state: %s. (keys: %s) (foo value: %s, bar value:%s)%n",
                testDict,
                testDict.keys(),
                testDict.get("foo"),
                testDict.get("bar")
        );
    }
}