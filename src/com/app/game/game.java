package com.app.game;

import java.util.Arrays;

import com.app.engine.settings;

public class game {
    public static void main(String[] args) {
        System.out.printf("Started Game w/ scale %d, args: %s%n", settings.nativeScale, Arrays.toString(args));

        gameMiscTest.test();
        gameCVarTest.test();
        gameSpriteTest.test();
        gameConsoleTest.test();
        gameSchedulerTest.test();
        gameFileSystemTest.test();


        // GAME LOOPS TEST
        gameLoopsTest.inputLoopTest();
        gameLoopsTest.updateAndRenderTest();
        System.out.println("Starting game loop, press Ctrl+C to exit...");
    }
}