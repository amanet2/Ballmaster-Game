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
                echoStrBuilder.append("\n");
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
                String[] names = console.listCmds();
                System.out.println("total " + names.length);
                for(String name : names) {
                    System.out.println(name);
                }
                return "";
            }
        };
        gConsoleCommand gConsoleCommandListCVars = engineInstance.consoleSystem. new gConsoleCommand() {
            @Override
            public String doCommand(String[] args) {
                String[] names = gameCVars.getCVarList();
                System.out.println("total " + names.length);
                for(String name : names) {
                    System.out.printf("%s = %s%n", name, gameCVars.get().getCVarValue(name));
                }
                return "";
            }
        };
        gConsoleCommand gConsoleCommandListFiles = engineInstance.consoleSystem. new gConsoleCommand() {
            @Override
            public String doCommand(String[] args) {
                String[] names = gameFiles.getFilesList();
                System.out.println("total " + names.length);
                for(String name : names) {
                    System.out.println(name);
                }
                return "";
            }
        };
        gConsoleCommand gConsoleCommandListSprites = engineInstance.consoleSystem. new gConsoleCommand() {
            @Override
            public String doCommand(String[] args) {
                String[] names = gameFiles.getSpritesFilesList();
                System.out.println("total " + names.length);
                for(String name : names) {
                    System.out.println(name);
                }
                return "";
            }
        };
        gConsoleCommand gConsoleCommandQuit = engineInstance.consoleSystem. new gConsoleCommand() {
            @Override
            public String doCommand(String[] args) {
                System.exit(0);
                return "You will never see this la la la!";
            }
        };
        gConsoleCommand gConsoleCommandSet = engineInstance.consoleSystem. new gConsoleCommand() {
            @Override
            public String doCommand(String[] args) {
                if(args.length < 2)
                    return "Usage: set CVAR_NAME CVAR_VALUE\n";
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
