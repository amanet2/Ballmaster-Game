package com.app.game;

import java.util.Arrays;
import com.app.engine.settings;

public class game {
    public static void main(String[] args) {
        System.out.printf("Started Game w/ scale %d, args: %s%n", settings.nativeScale, Arrays.toString(args));

        gameMiscTest.test();
        gameCVarTest.test();
        gameSpriteTest.test();  // TODO: why does this test make the run hang for a few seconds at the end?
        gameConsoleTest.test();
        gameSchedulerTest.test();
        gameFileSystemTest.test();
    }
}