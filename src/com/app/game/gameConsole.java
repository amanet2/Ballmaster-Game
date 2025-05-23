package com.app.game;

import com.app.engine.consoleSystem.gConsoleCommand;
import com.app.engine.consoleSystem.gConsoleSystem;
import com.app.engine.engine;

public class gameConsole {
    private static engine engineInstance = engine.instance();

    private static final gConsoleSystem console = engineInstance.consoleSystem. new gConsoleSystem();

    public static gConsoleSystem get() {
        return console;
    }

    public static void init() {
        gConsoleCommand gConsoleCommandClear = engineInstance.consoleSystem. new gConsoleCommand() {
            @Override
            public String doCommand(String[] args) {
                System.out.print("\033\143");
                return "";
            }
        };
        gConsoleCommand gConsoleCommandEcho = engineInstance.consoleSystem. new gConsoleCommand() {
            @Override
            public String doCommand(String[] args) {
                StringBuilder echoStrBuilder = new StringBuilder();
                for(String tok : args) {
                    echoStrBuilder.append(" ").append(tok);
                }
                return echoStrBuilder.substring(1);
            }
        };
        // executes a specified cfg file
        gConsoleCommand gConsoleCommandExec = engineInstance.consoleSystem. new gConsoleCommand() {
            @Override
            public String doCommand(String[] args) {
                if(args.length < 1 || args[0].trim().isEmpty())
                    return "Usage: exec CFG_FILE";
                return gameFiles.execFile(args[0]);
            }
        };
        gConsoleCommand gConsoleCommandListCmds = engineInstance.consoleSystem. new gConsoleCommand() {
            @Override
            public String doCommand(String[] args) {
                System.out.println();
                String[] cmds = console.listCmds();
                for(String name : cmds) {
                    System.out.println(name);
                }
                return String.format("---\n%d cmds in System", cmds.length);
            }
        };
        gConsoleCommand gConsoleCommandListCVars = engineInstance.consoleSystem. new gConsoleCommand() {
            @Override
            public String doCommand(String[] args) {
                System.out.println();
                String[] cVarNames = gameCVars.getCVarList();
                for(String name : cVarNames) {
                    System.out.printf("%s = %s%n", name, gameCVars.get().getCVarValue(name));
                }
                return String.format("---\n%d CVars in System", cVarNames.length);
            }
        };
        gConsoleCommand gConsoleCommandListFiles = engineInstance.consoleSystem. new gConsoleCommand() {
            @Override
            public String doCommand(String[] args) {
                System.out.println();
                String[] fileNames = gameFiles.getFilesList();
                for(String name : fileNames) {
                    System.out.println(name);
                }
                return String.format("---\n%d Files in System", fileNames.length);
            }
        };
        gConsoleCommand gConsoleCommandListSprites = engineInstance.consoleSystem. new gConsoleCommand() {
            @Override
            public String doCommand(String[] args) {
                System.out.println();
                String[] spriteNames = gameFiles.getSpritesFilesList();
                for(String name : spriteNames) {
                    System.out.println(name);
                }
                return String.format("---\n%d Sprites in System", spriteNames.length);
            }
        };
        gConsoleCommand gConsoleCommandQuit = engineInstance.consoleSystem. new gConsoleCommand() {
            @Override
            public String doCommand(String[] args) {
                System.exit(0);
                return "Exited Game";
            }
        };
        gConsoleCommand gConsoleCommandSet = engineInstance.consoleSystem. new gConsoleCommand() {
            @Override
            public String doCommand(String[] args) {
                if(args.length < 2)
                    return "Usage: set CVAR_NAME CVAR_VALUE";
                return gameCVars.setCVar(args[0], args[1]);
            }
        };

        console.registerCmd("clear", gConsoleCommandClear);
        console.registerCmd("echo", gConsoleCommandEcho);
        console.registerCmd("exec", gConsoleCommandExec);
        console.registerCmd("exit", gConsoleCommandQuit);
        console.registerCmd("listCmds", gConsoleCommandListCmds);
        console.registerCmd("listCVars", gConsoleCommandListCVars);
        console.registerCmd("listFiles", gConsoleCommandListFiles);
        console.registerCmd("listSprites", gConsoleCommandListSprites);
        console.registerCmd("quit", gConsoleCommandQuit);
        console.registerCmd("set", gConsoleCommandSet);
    }
}
