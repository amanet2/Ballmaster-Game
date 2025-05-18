package com.app.game;

import com.app.engine.camera;
import com.app.engine.dict;
import com.app.engine.keyboard;
import com.app.engine.utils;

import java.util.Arrays;

public class gameMiscTest {
    static final com.app.engine.camera camera = new camera();
    static final com.app.engine.keyboard keyboard = new keyboard();

    public static void test() {
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
