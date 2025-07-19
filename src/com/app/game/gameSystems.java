package com.app.game;

import com.app.engine.utils.gDict;

import java.util.ArrayList;

public class gameSystems {
    public static void init(String[] args) {
        System.out.println("----------------");
        System.out.println("INITIALIZING GAME SYSTEMS...");

        gameConsole.init();
        gameCVars.init();
        gameFiles.init();
        gameFiles.execCfgFile("config/autoexec.cfg");
        gameCVars.instance().parseArgs(args);
        gameScheduler.init();
        gameGraphics.init();
        gameSprites.init();
        gameMetrics.init();

        String testString = "{foo=bar, baz={foo=bar, qaz={yaz=p\\}zaz}}, zaz={abz=bzaz}, laz=[1, 2, 3, 4]}";
        gDict testDict = new gDict(testString);
        System.out.println("TEST GDICT STRING: " + testString);
        System.out.println("TEST GDICT: " + testDict);
        System.out.println(((ArrayList<String>) testDict.get("laz")).get(1));

        System.out.println("----------------");
        System.out.println("----------------");
        System.out.println("STARTED GAME SUCCESSFULLY!");
        System.out.println("YOU MAY BEGIN ENTERING CONSOLE COMMANDS");
        System.out.println("----------------");

        gameInput.init();
    }
}
