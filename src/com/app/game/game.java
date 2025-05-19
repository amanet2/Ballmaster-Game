package com.app.game;

import java.util.Arrays;
import java.util.Scanner;

import com.app.engine.settings;
import com.app.engine.consoleSystem;
import com.app.engine.consoleSystem.gConsoleCommand;
import com.app.engine.consoleSystem.gConsoleSystem;

import com.app.engine.schedulerSystem;
import com.app.engine.schedulerSystem.gSchedulerEvent;
import com.app.engine.schedulerSystem.gSchedulerSystem;


public class game {
    static consoleSystem consoleSystem = new consoleSystem();
    static gConsoleSystem gConsoleSystem = consoleSystem. new gConsoleSystem();

    static schedulerSystem schedulerSystem = new schedulerSystem();
    static gSchedulerSystem gSchedulerSystem = schedulerSystem. new gSchedulerSystem();

    public static void createInputThread() {
        // INPUT THREAD
        // TODO: Use KeyboardListener instead (see old game)
        new Thread(() -> {
            while(true) {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                System.out.println("----------------");
                System.out.printf("Game Console Input Read%n");
                System.out.printf("Entered: %s%n", input);
                String result = gConsoleSystem.readLine(input);
                System.out.printf("Result: %s%n", result);
            }
        }).start();
    }

    static void registerConsoleCommands() {
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
    }

    static void registerEvents() {
        final long eventTime = System.currentTimeMillis();

        gSchedulerEvent event1 = schedulerSystem. new gSchedulerEvent(){
            public void doEvent() {
                System.out.println("----------------");
                System.out.printf("Did an event scheduled for %d @ %d%n", eventTime, System.currentTimeMillis());
            }
        };

        gSchedulerEvent event2 = schedulerSystem. new gSchedulerEvent(){
            public void doEvent() {
                System.out.println("----------------");
                System.out.printf("Did another event scheduled for %d @ %d%n", eventTime, System.currentTimeMillis());
            }
        };

        gSchedulerEvent event3 = schedulerSystem. new gSchedulerEvent(){
            public void doEvent() {
                System.out.println("----------------");
                System.out.printf("Did an event scheduled for %d @ %d%n", eventTime + 5000, System.currentTimeMillis());
            }
        };

        gSchedulerEvent event4 = schedulerSystem. new gSchedulerEvent(){
            public void doEvent() {
                System.out.println("----------------");
                System.out.printf("Did an event scheduled for %d @ %d%n", eventTime + 10000, System.currentTimeMillis());
            }
        };

        gSchedulerSystem.addEvent(eventTime, event1);
        gSchedulerSystem.addEvent(eventTime, event2);
        gSchedulerSystem.addEvent(eventTime + 5000, event3);
        gSchedulerSystem.addEvent(eventTime + 10000, event4);
    }

    public static void main(String[] args) {
        System.out.printf("Started Game w/ scale %d, args: %s%n", settings.nativeScale, Arrays.toString(args));

        System.out.println("Testing Game Systems...");
        gameMiscTest.test();
        gameCVarTest.test();
        gameSpriteTest.test();
        gameFileSystemTest.test();

        System.out.println("(Ctrl+C to exit) Starting game loop...");
        registerConsoleCommands();
        registerEvents();

        int gameFrames = 0;
        int internalGameRate = 1000;
        long snapshotTimeNanos = System.nanoTime();  // use nano for game timer
        long tickTimeNanos = snapshotTimeNanos;

        long frameMetricTimeMillis = System.currentTimeMillis() + 1000;
        int framesTotal = 0;
        int framesMetric = 0;

        // GAME LOOP
        while(true) {
            snapshotTimeNanos = System.nanoTime();

            //game update
            while (tickTimeNanos < snapshotTimeNanos) {
                tickTimeNanos += (1000000000 / (long) internalGameRate);
                //update game stuff, move players, execute scheduled events, etc
                gameFrames++;
                if(gameFrames >= Integer.MAX_VALUE - 1000000000)
                    gameFrames = 0;
            }

            //do scheduled events
            gSchedulerSystem.doEvents(System.currentTimeMillis());

            //game render
            framesMetric++;
            framesTotal++;
            if(System.currentTimeMillis() > frameMetricTimeMillis) {
                frameMetricTimeMillis = System.currentTimeMillis() + 1000;
                System.out.println("----------------");
                System.out.println("Time: " + System.currentTimeMillis());
                System.out.println("Ticks: " + gameFrames);
                System.out.println("Frames: " + framesTotal);
                System.out.println("FPS: " + framesMetric);
                System.out.println("(Ctrl+C to exit) Enter your command: ");
                if(framesTotal >= Integer.MAX_VALUE - 1000000000)
                    framesTotal = 0;
                framesMetric = 0;
            }
        }
    }
}