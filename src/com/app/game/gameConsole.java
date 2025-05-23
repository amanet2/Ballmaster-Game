package com.app.game;

import com.app.engine.consoleSystem;
import com.app.engine.engine;

public class gameConsole {
    private static engine engineInstance = engine.instance();

    private static final consoleSystem.gConsoleSystem console = engineInstance.consoleSystem. new gConsoleSystem();

    public static consoleSystem.gConsoleSystem get() {
        return console;
    }

    public static void init() {
        consoleSystem.gConsoleCommand gConsoleCommandAdd = engineInstance.consoleSystem. new gConsoleCommand() {
            @Override
            public String doCommand(String[] args) {
                return Integer.toString(Integer.parseInt(args[0]) + Integer.parseInt(args[1]));
            }
        };
        consoleSystem.gConsoleCommand gConsoleCommandQuit = engineInstance.consoleSystem. new gConsoleCommand() {
            @Override
            public String doCommand(String[] args) {
                System.exit(0);
                return "Exited Game";
            }
        };

        console.registerCmd("add", gConsoleCommandAdd);
        console.registerCmd("exit", gConsoleCommandQuit);
        console.registerCmd("quit", gConsoleCommandQuit);
    }
}
