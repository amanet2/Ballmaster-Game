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
        consoleSystem.gConsoleCommand gConsoleCommandEcho = engineInstance.consoleSystem. new gConsoleCommand() {
            @Override
            public String doCommand(String[] args) {
                StringBuilder echoStrBuilder = new StringBuilder();
                for(String tok : args) {
                    echoStrBuilder.append(" ").append(tok);
                }
                String echoString = echoStrBuilder.substring(1);
                System.out.printf("%s%n", echoString);
                return echoString;
            }
        };
        consoleSystem.gConsoleCommand gConsoleCommandAdd = engineInstance.consoleSystem. new gConsoleCommand() {
            @Override
            public String doCommand(String[] args) {
                return Integer.toString(Integer.parseInt(args[0]) + Integer.parseInt(args[1]));
            }
        };
        console.registerCmd("echo", gConsoleCommandEcho);
        console.registerCmd("add", gConsoleCommandAdd);
        gConsoleCommandEcho.doCommand(new String[]{"set up console!"});
    }
}
