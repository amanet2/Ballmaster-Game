package com.app.game;

import com.app.engine.cmd;
import com.app.engine.console;

public class gameConsoleTest {
    public static void test() {
        console console = new console();
        console.registerCmd("echo", new cmd() {
            @Override
            public String doCmd(String[] args) {
                StringBuilder echoStr = new StringBuilder();
                for(String tok : args) {
                    echoStr.append(tok).append(" ");
                }
                System.out.printf("%s%n", echoStr);
                return echoStr.toString();
            }
        });

        console.registerCmd("add", new cmd() {
            @Override
            public String doCmd(String[] args) {
                try {
                    return Integer.toString(Integer.parseInt(args[0]) + Integer.parseInt(args[1]));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return "null";
            }
        });

        console.readLine("echo I am echoing something from the console!");
        String addCom = "add 2 2";
        String addResult = console.readLine(addCom);
        console.readLine(String.format("echo I am echoing the result of command '%s' -> %s", addCom, addResult));
    }

}
