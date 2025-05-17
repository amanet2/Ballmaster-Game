package com.app.game;

import java.util.Arrays;
import com.app.engine.settings;
import com.app.engine.utils;
import com.app.engine.camera;
import com.app.engine.fileMgr;
import com.app.engine.sprites;
import com.app.engine.keyboard;
import com.app.engine.scheduler;
import com.app.engine.doable;
import com.app.engine.dict;
import com.app.engine.cmd;
import com.app.engine.console;

public class game {
    static final utils utils = new utils();
    static final camera camera = new camera();
    static final fileMgr dataFiles = new fileMgr("data");
    static final console console = new console();
    static final sprites sprites = new sprites();
    static final keyboard keyboard = new keyboard();
    static final scheduler scheduler = new scheduler();

    static int roundToTestVal = 36;
    static int roundToTestNearest = 30;

    public static void main(String[] args) {
        long currentTimeMillis = System.currentTimeMillis();

        gameCVarTest cVarTest = new gameCVarTest();
        cVarTest.test();

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

        dict testDict = new dict("{foo=bar,bar=");

        scheduler.putEvent(currentTimeMillis, new doable() {
            public void doCommand() {
                System.out.println("Did a scheduled event");
            }
        });
        scheduler.putEvent(currentTimeMillis, new doable() {
            public void doCommand() {
                System.out.println("Did another scheduled event");
            }
        });
        scheduler.putEvent(currentTimeMillis + 5000, new doable() {
            public void doCommand() {
                System.out.println("We should not see this");
            }
        });

        String startString = String.format(
                "Started Game w/ scale %d, args: %s",
                settings.nativeScale,
                Arrays.toString(args)
        );
        System.out.println(startString);
        System.out.printf(
                "Rounding %d to nearest %d: %d%n",
                roundToTestVal,
                roundToTestNearest,
                utils.roundToNearest(roundToTestVal, roundToTestNearest)
        );
        System.out.printf("Cam coords: %s%n", Arrays.toString(camera.getCoords()));
        System.out.printf("Files in /%s: %s%n", dataFiles.dir(), Arrays.toString(dataFiles.getFileSelection()));
        System.out.printf("Sprite for 'none': %s%n", sprites.getScaledImage("none", 0, 0));
        System.out.printf("Keyboard code for key a: %d%n", keyboard.getCodeForKey("a"));
        scheduler.doEvents(System.currentTimeMillis()); // prints stuff
        System.out.printf(
                "Test state: %s. (keys: %s) (foo value: %s, bar value:%s)%n",
                testDict,
                testDict.keys(),
                testDict.get("foo"),
                testDict.get("bar")
        );
        console.readLine("echo I am echoing something from the console!");
        String addCom = "add 1 2";
        System.out.printf("Gonna do this math: %s%n", addCom);
        System.out.printf("%s%n", console.readLine(addCom));
    }
}