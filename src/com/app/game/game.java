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


        // GAME LOOPS TEST
        // INPUT THREAD WILL READ MOUSE AND KEYBOARD INPUTS
        // RENDER THREAD (SHELL) WILL DRAW WORLD, MENUS, ETC
        // GAME LOOP (MAIN THREAD, THIS THREAD) WILL UPDATE WORLD
        gameLoopsTest.inputLoopTest();
        gameLoopsTest.renderLoopTest();
        gameLoopsTest.gameLoopTest();
        System.out.println("Starting game loop, press Ctrl+C to exit...");
    }
}