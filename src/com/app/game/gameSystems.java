package com.app.game;

public class gameSystems {
    public static void init(String[] args) {
        System.out.println("----------------");
        System.out.println("INITIALIZING GAME SYSTEMS...");
        System.out.println("----------------");
        System.out.print("INITIALIZING CONSOLE...");
        gameConsole.init();
        System.out.println("DONE.");
        System.out.print("INITIALIZING CVARS...");
        gameCVars.init();
        System.out.println("DONE.");
        System.out.print("INITIALIZING FILESYSTEM...");
        gameFiles.init();
        System.out.println("DONE.");
        System.out.println("READING AUTOEXEC...");
        // TODO: want to get to the point where we call getFileSystemConfig.getFile("autoexec.cfg")
        gameConsole.instance().execCfgFile(
                gameFiles.instance().getFileSystemConfig().getRootDirectory().getFile("base/config/autoexec.cfg")
        );
        System.out.println("DONE.");
        System.out.print("READING LAUNCH ARGS...");
        gameConsole.instance().parseLaunchArgs(args);
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
        System.out.print("INITIALIZING TEXTURES...");
        gameTextures.init();
        System.out.println("DONE.");
        System.out.print("INITIALIZING METRICS...");
        gameMetrics.init();
        System.out.println("DONE.");
        System.out.print("INITIALIZING INPUTS...");
        gameInput.init();
        System.out.println("DONE.");
        System.out.print("INITIALIZING GAME STATE...");
        gameState.init("base/map/test.map");
        System.out.println("DONE.");

        System.out.println("----------------");
        System.out.println("----------------");
        System.out.println("STARTED GAME SUCCESSFULLY!");
        System.out.println("YOU MAY BEGIN ENTERING CONSOLE COMMANDS");
        System.out.println("----------------");
    }
}
