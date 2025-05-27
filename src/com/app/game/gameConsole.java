package com.app.game;

import com.app.engine.consoleSystem.gConsoleCommand;
import com.app.engine.consoleSystem.gConsoleSystem;
import com.app.engine.engine;

public class gameConsole {
    // TODO: need to use interfaces as headers for game files too
    // TODO: e.g. we need an interface for console to list out commands
    private static final gConsoleSystem console = engine.instance().gConsoleSystem;

    public static gConsoleSystem instance() {
        return console;
    }

    public static void init() {
        // TODO: register game-specific console commands here
        // TODO: set up gameFiles commands to work at engine level
        gConsoleCommand gConsoleCommandExec = new gConsoleCommand("executes a config file") {
            @Override
            public String doCommand(String[] args) {
                if(args.length < 1 || args[0].trim().isEmpty())
                    return "For executing a cfg file. Usage: exec CFG_FILE";
                return gameFiles.execCfgFile(args[0]);
            }
        };
        gConsoleCommand gConsoleCommandListFiles = new gConsoleCommand("lists cfg files") {
            @Override
            public String doCommand(String[] args) {
                String[] names = gameFiles.getFilesListConfig();
                System.out.println("total " + names.length);
                for(String name : names) {
                    System.out.println(name);
                }
                return "";
            }
        };
        gConsoleCommand gConsoleCommandListFilesScripts = new gConsoleCommand("lists script files") {
            @Override
            public String doCommand(String[] args) {
                String[] names = gameFiles.getFilesListScripts();
                System.out.println("total " + names.length);
                for(String name : names) {
                    System.out.println(name);
                }
                return "";
            }
        };
        gConsoleCommand gConsoleCommandListSprites = new gConsoleCommand("lists sprite files") {
            @Override
            public String doCommand(String[] args) {
                String[] names = gameFiles.getFilesListSprites();
                System.out.println("total " + names.length);
                for(String name : names) {
                    System.out.println(name);
                }
                return "";
            }
        };
        gConsoleCommand gConsoleCommandScript = new gConsoleCommand("executes a line of script") {
            @Override
            public String doCommand(String[] args) {
                if(args.length < 1 || args[0].trim().isEmpty())
                    return "Usage: script LINE OF SCRIPT";
                return gameFiles.scriptFile(args[0]);
            }
        };
        gConsoleCommand gConsoleCommandScriptFile = new gConsoleCommand("executes a script file") {
            @Override
            public String doCommand(String[] args) {
                if(args.length < 1 || args[0].trim().isEmpty())
                    return "Usage: script SCRIPT_FILE";
                return gameFiles.scriptFile(args[0]);
            }
        };

        console.registerCmd("exec", gConsoleCommandExec);
        console.registerCmd("listFilesCfg", gConsoleCommandListFiles);
        console.registerCmd("listFilesScripts", gConsoleCommandListFilesScripts);
        console.registerCmd("listFilesSprites", gConsoleCommandListSprites);
        console.registerCmd("script", gConsoleCommandScript);
        console.registerCmd("scriptFile", gConsoleCommandScriptFile);

        System.out.println("CONSOLE SYSTEM INITIALIZED");
    }
}
