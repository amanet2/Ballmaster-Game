package com.app.game;

import com.app.engine.consoleSystem;
import com.app.engine.consoleSystem.gConsoleCommand;
import com.app.engine.consoleSystem.gConsoleSystem;

public class gameConsoleTest {
    static consoleSystem consoleSystem = new consoleSystem();

    public static void test() {
        gConsoleSystem gConsoleSystem = consoleSystem. new gConsoleSystem();

        gConsoleCommand gConsoleCommandEcho = consoleSystem. new gConsoleCommand() {
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

        gConsoleCommand gConsoleCommandAdd = consoleSystem. new gConsoleCommand() {
            @Override
            public String doCommand(String[] args) {
                try {
                    return Integer.toString(Integer.parseInt(args[0]) + Integer.parseInt(args[1]));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return "null";
            }
        };

        gConsoleSystem.registerCmd("echo", gConsoleCommandEcho);
        gConsoleSystem.registerCmd("add", gConsoleCommandAdd);

        String echoStr = "echo I am echoing something from the console!";
        String addStr = "add 2 2";

        String addResult = gConsoleSystem.readLine(addStr);

        String addResultEchoStr = String.format("echo I am echoing the result of command '%s' -> %s", addStr, addResult);

        gConsoleSystem.readLine(echoStr);
        gConsoleSystem.readLine(addResultEchoStr);
    }
}
