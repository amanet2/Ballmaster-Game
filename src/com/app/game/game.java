package com.app.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Scanner;

import com.app.engine.settings;

import com.app.engine.consoleSystem;
import com.app.engine.consoleSystem.gConsoleCommand;
import com.app.engine.consoleSystem.gConsoleSystem;

import com.app.engine.schedulerSystem;
import com.app.engine.schedulerSystem.gSchedulerEvent;
import com.app.engine.schedulerSystem.gSchedulerSystem;

import com.app.engine.graphicsSystem;
import com.app.engine.graphicsSystem.gGraphicsSystem;

import com.app.engine.spriteSystem;
import com.app.engine.spriteSystem.gSprite;
import com.app.engine.spriteSystem.gSpriteSystem;



public class game {
    static consoleSystem consoleSystem = new consoleSystem();
    static gConsoleSystem gConsoleSystem = consoleSystem. new gConsoleSystem();

    static schedulerSystem schedulerSystem = new schedulerSystem();
    static gSchedulerSystem gSchedulerSystem = schedulerSystem. new gSchedulerSystem();

    static spriteSystem spriteSystem = new spriteSystem();
    static gSpriteSystem gSpriteSystem = spriteSystem. new gSpriteSystem();
    static gSprite testSprite1 = gSpriteSystem.getScaledSprite("data/player_pink_03.png", 150, 150);
//    static gSprite testSprite2 = gSpriteSystem.getScaledSprite("data/player_pink_03.png", 300, 300);
//    static gSprite testSprite3 = gSpriteSystem.getScaledSprite("data/player_pink_03.png", 600, 600);

    static graphicsSystem graphicsSystem = new graphicsSystem();
    static gGraphicsSystem gGraphicsSystem = graphicsSystem.new gGraphicsSystem(graphicsSystem.new gPanel() {
        int framesTotal = 0;
        long frameMetricTimeMillis = System.currentTimeMillis() + 1000;
        int fpsMetric = 0;
        int fpsSnapshot = 0;

        public void draw(Graphics g) {
            fpsMetric++;
            framesTotal++;
            if(System.currentTimeMillis() > frameMetricTimeMillis) {
                frameMetricTimeMillis = System.currentTimeMillis() + 1000;
                if(framesTotal >= Integer.MAX_VALUE - 1000)
                    framesTotal = 0;
                fpsSnapshot = fpsMetric;
                fpsMetric = 0;
            }
            g.setColor(Color.WHITE);
            g.drawString("Time: " + System.currentTimeMillis(), 0, 50);
            g.drawString("Frames: " + framesTotal, 0, 75);
            g.drawString("FPS: " + fpsSnapshot, 0, 100);
            for(Integer xpos : new int[]{14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1}) {
                g.drawImage(testSprite1.getImage(), xpos*75, 359 - 150, null);
                g.drawImage(testSprite1.getImage(), xpos*75, 359 - 75, null);
                g.drawImage(testSprite1.getImage(), xpos*75, 359, null);
                g.drawImage(testSprite1.getImage(), xpos*75, 359 + 75, null);
                g.drawImage(testSprite1.getImage(), xpos*75, 359 + 150, null);
                g.drawImage(testSprite1.getImage(), xpos*75, 359 + 225, null);
                g.drawImage(testSprite1.getImage(), xpos*75, 359 + 300, null);
//                g.fill3DRect(xpos*150, 359 + 75, 150, 150, true);  // NOTE: this lowers fps considerably
            }
        }
    });

    public static void createInputThread() {
        // INPUT THREAD
        // TODO: Use KeyboardListener instead (see old game)
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while(true) {
                System.out.println("----------------");
                System.out.print("(Ctrl+C to Exit) Enter command: ");
                String input = scanner.nextLine();
                System.out.printf("%nEntered: %s%n", input);
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
        gameFileSystemTest.test();

        registerConsoleCommands();
        registerEvents();
        createInputThread();

        int gameFrames = 0;
        int internalGameRate = 1000;
        long snapshotTimeNanos = System.nanoTime();  // use nano for game timer
        long tickTimeNanos = snapshotTimeNanos;

        // GAME LOOP
        while(true) {
            snapshotTimeNanos = System.nanoTime();

            //game update
            while (tickTimeNanos < snapshotTimeNanos) {
                tickTimeNanos += (1000000000 / (long) internalGameRate);
                //update game stuff, move players, execute scheduled events, etc
                gameFrames++;
                if(gameFrames >= Integer.MAX_VALUE - 1000)
                    gameFrames = 0;
            }
            //do scheduled events
            gSchedulerSystem.doEvents(System.currentTimeMillis());
            //game render
            gGraphicsSystem.update();
        }
    }
}
