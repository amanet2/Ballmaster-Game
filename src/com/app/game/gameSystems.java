package com.app.game;

import com.app.engine.utils.gDict;

public class gameSystems {
    public static void init(String[] args) {
        System.out.println("----------------");
        System.out.println("INITIALIZING GAME SYSTEMS...");

        System.out.print("INITIALIZING CONSOLE...");
        gameConsole.init();
        System.out.println("DONE.");
        System.out.print("INITIALIZING CVARS...");
        gameCVars.init();
        System.out.println("DONE.");
        System.out.print("INITIALIZING FILESYSTEM...");
        gameFiles.init();
        System.out.println("DONE.");
        System.out.print("READING AUTOEXEC...");
        gameFiles.execCfgFile("config/autoexec.cfg");
        System.out.println("DONE.");
        System.out.print("READING LAUNCH ARGS...");
        gameCVars.instance().parseArgs(args);
        System.out.println("DONE.");
        System.out.print("INITIALIZING SCHEDULER...");
        gameScheduler.init();
        System.out.println("DONE.");
        System.out.print("INITIALIZING GRAPHICS...");
        gameGraphics.init();
        System.out.println("DONE.");
        System.out.print("INITIALIZING SPRITES...");
        gameSprites.init();
        System.out.println("DONE.");
        System.out.print("INITIALIZING METRICS...");
        gameMetrics.init();
        System.out.println("DONE.");

        System.out.println("----------------");
        System.out.print("INITIALIZING GAME STATE...");

        String testString = "{foo=bar, baz={foo=bar, qaz={yaz=p\\}zaz}}, zaz={abz=bzaz}, laz=[1, 2, 3, 4]}";
        gDict testDict = new gDict(testString);
        System.out.println("TEST GDICT STRING: " + testString);
        System.out.println("TEST GDICT: " + testDict);

        gameState.init();
        System.out.println("DONE.");

        System.out.println("----------------");
        System.out.println("----------------");
        System.out.println("STARTED GAME SUCCESSFULLY!");
        System.out.println("YOU MAY BEGIN ENTERING CONSOLE COMMANDS");
        System.out.println("----------------");

        gameInput.init();
    }
}
