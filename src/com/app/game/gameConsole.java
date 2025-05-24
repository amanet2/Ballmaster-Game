package com.app.game;

import com.app.engine.consoleSystem.gConsoleCommand;
import com.app.engine.consoleSystem.gConsoleSystem;
import com.app.engine.engine;

public class gameConsole {
    // TODO: look into static classes to make this easier e.g. utils, cvarSystem
    private static engine engineInstance = engine.instance();

    private static final gConsoleSystem console = engineInstance.gConsoleSystem;

    public static gConsoleSystem get() {
        return console;
    }

    public static void init() {
        gConsoleCommand gConsoleCommandClear = engineInstance.consoleSystem. new gConsoleCommand("clears the console") {
            @Override
            public String doCommand(String[] args) {
                System.out.print("\033\143");
                return "";
            }
        };
        gConsoleCommand gConsoleCommandEcho = engineInstance.consoleSystem. new gConsoleCommand("prints text") {
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
        gConsoleCommand gConsoleCommandExec = engineInstance.consoleSystem. new gConsoleCommand("executes a config file") {
            @Override
            public String doCommand(String[] args) {
                if(args.length < 1 || args[0].trim().isEmpty())
                    return "For executing a cfg file. Usage: exec CFG_FILE";
                return gameFiles.execFile(args[0]);
            }
        };
        gConsoleCommand gConsoleCommandListCmds = engineInstance.consoleSystem. new gConsoleCommand("lists commands") {
            @Override
            public String doCommand(String[] args) {
                String[] names = console.listCmds();
                System.out.println("total " + names.length);
                for(String name : names) {
                    System.out.println(name + " -> " + console.getCmd(name).getDescription());
                }
                return "";
            }
        };
        gConsoleCommand gConsoleCommandListCVars = engineInstance.consoleSystem. new gConsoleCommand("lists Cvars") {
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
        gConsoleCommand gConsoleCommandListFiles = engineInstance.consoleSystem. new gConsoleCommand("lists cfg files") {
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
        gConsoleCommand gConsoleCommandListFilesScripts = engineInstance.consoleSystem. new gConsoleCommand("lists script files") {
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
        gConsoleCommand gConsoleCommandListSprites = engineInstance.consoleSystem. new gConsoleCommand("lists sprite files") {
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
        gConsoleCommand gConsoleCommandQuit = engineInstance.consoleSystem. new gConsoleCommand("quits the game") {
            @Override
            public String doCommand(String[] args) {
                System.exit(0);
                return "You will never see this la la la!";
            }
        };
        gConsoleCommand gConsoleCommandScript = engineInstance.consoleSystem. new gConsoleCommand("executes a line of script") {
            @Override
            public String doCommand(String[] args) {
                if(args.length < 1 || args[0].trim().isEmpty())
                    return "Usage: script LINE OF SCRIPT";
                return gameFiles.scriptFile(args[0]);
            }
        };
        gConsoleCommand gConsoleCommandScriptFile = engineInstance.consoleSystem. new gConsoleCommand("executes a script file") {
            @Override
            public String doCommand(String[] args) {
                if(args.length < 1 || args[0].trim().isEmpty())
                    return "Usage: script SCRIPT_FILE";
                return gameFiles.scriptFile(args[0]);
            }
        };
        gConsoleCommand gConsoleCommandSet = engineInstance.consoleSystem. new gConsoleCommand("sets a cvar") {
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
        console.registerCmd("listFilesCfg", gConsoleCommandListFiles);
        console.registerCmd("listFilesScripts", gConsoleCommandListFilesScripts);
        console.registerCmd("listFilesSprites", gConsoleCommandListSprites);
        console.registerCmd("quit", gConsoleCommandQuit);
        console.registerCmd("script", gConsoleCommandScript);
        console.registerCmd("scriptFile", gConsoleCommandScriptFile);
        console.registerCmd("set", gConsoleCommandSet);
    }
}
