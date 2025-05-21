package com.app.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Scanner;

import com.app.engine.engine;
import com.app.engine.settings;
import com.app.engine.consoleSystem.gConsoleSystem;
import com.app.engine.consoleSystem.gConsoleCommand;
import com.app.engine.cVarSystem.gCVar;
import com.app.engine.cVarSystem.gCVarSystem;
import com.app.engine.graphicsSystem.gGraphicsSystem;
import com.app.engine.schedulerSystem.gSchedulerEvent;
import com.app.engine.schedulerSystem.gSchedulerSystem;
import com.app.engine.spriteSystem.gSprite;
import com.app.engine.spriteSystem.gSpriteSystem;


public class game {
    static long frameMetricTimeMillis = System.currentTimeMillis() + 1000;
    static int gameFrames = 0;
    static int gameFramesMetric = 0;
    static int gameFramesSnapshot = 0;
    static int videoFrames = 0;
    static int videoFramesMetric = 0;
    static int videoFramesSnapshot = 0;
    // TODO: make baseGamePath an arg passed by run.sh ($game_home) to main(args[]). Will need to rearrage things
    static String baseGamePath = "/Users/Stallion/Code/Ballmaster-Game";
    static String testSpritePath = String.format("%s/data/player_pink_03.png", baseGamePath);
    static String testCVarName = "test_cvar";

    static engine engine = new engine();

    static gConsoleSystem gConsoleSystem = engine.consoleSystem. new gConsoleSystem();

    static gCVarSystem gCVarSystem = engine.cVarSystem. new gCVarSystem();

    static gSchedulerSystem gSchedulerSystem = engine.schedulerSystem. new gSchedulerSystem();

    static gSpriteSystem gSpriteSystem = engine.spriteSystem. new gSpriteSystem();
    static gSprite testSprite1 = gSpriteSystem.getScaledSprite(testSpritePath, 150, 150);
    static gSprite testSprite2 = gSpriteSystem.getScaledSprite(testSpritePath, 300, 300);
    static gSprite testSprite3 = gSpriteSystem.getScaledSprite(testSpritePath, 600, 600);


    static gGraphicsSystem gGraphicsSystem = engine.graphicsSystem.new gGraphicsSystem(engine.graphicsSystem.new gPanel() {
        public void draw(Graphics g) {
            videoFramesMetric++;
            videoFrames++;
            if(videoFrames >= Integer.MAX_VALUE - 1000)
                videoFrames = 0;
            if(System.currentTimeMillis() > frameMetricTimeMillis) {
                frameMetricTimeMillis = System.currentTimeMillis() + 1000;
                gameFramesSnapshot = gameFramesMetric;
                gameFramesMetric = 0;
                videoFramesSnapshot = videoFramesMetric;
                videoFramesMetric = 0;
            }

            g.setColor(Color.WHITE);
            g.drawString("Game Frames: " + gameFrames, 0, 25);
            g.drawString("Video Frames: " + videoFrames, 0, 50);
            g.drawString("Game FPS: " + gameFramesSnapshot, 0, 75);
            g.drawString("Video FPS: " + videoFramesSnapshot, 0, 100);
            for(Integer xpos : new int[]{14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1}) {
                g.drawImage(testSprite1.getImage(), xpos*75, 359 - 150, null);
                g.drawImage(testSprite1.getImage(), xpos*75, 359 - 75, null);
                g.drawImage(testSprite1.getImage(), xpos*75, 359, null);
                g.drawImage(testSprite1.getImage(), xpos*75, 359 + 75, null);
                g.drawImage(testSprite1.getImage(), xpos*75, 359 + 150, null);
                g.drawImage(testSprite1.getImage(), xpos*75, 359 + 225, null);
                g.drawImage(testSprite1.getImage(), xpos*75, 359 + 300, null);
            }
            g.drawImage(testSprite2.getImage(), 0, 359 - 150, null);
            g.drawImage(testSprite3.getImage(), 300, 359 - 150, null);
        }
    });

    public static void createInputThread() {
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

    static void registerCVars() {
        gCVar testCVar = engine.cVarSystem. new gCVar(testCVarName, "foo") {
            @Override
            public void onUpdate() {
                System.out.println(testCVarName + " value was updated!");
            }
            @Override
            public void onChange() {
                System.out.println(testCVarName + " value was changed!");
            }
        };
        gCVarSystem.registerCVar(testCVar);
    }

    static void registerConsoleCommands() {
        gConsoleCommand gConsoleCommandEcho = engine.consoleSystem. new gConsoleCommand() {
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
        gConsoleCommand gConsoleCommandAdd = engine.consoleSystem. new gConsoleCommand() {
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

        gSchedulerEvent event1 = engine.schedulerSystem. new gSchedulerEvent(){
            public void doEvent() {
                System.out.println("----------------");
                System.out.printf("Did an event scheduled for %d @ %d%n", eventTime, System.currentTimeMillis());
                gCVarSystem.setCVarValue("test_cvar", "bar");
            }
        };
        gSchedulerEvent event2 = engine.schedulerSystem. new gSchedulerEvent(){
            public void doEvent() {
                System.out.println("----------------");
                System.out.printf("Did another event scheduled for %d @ %d%n", eventTime, System.currentTimeMillis());
                gCVarSystem.setCVarValue("test_cvar", "foo");
            }
        };
        gSchedulerEvent event3 = engine.schedulerSystem. new gSchedulerEvent(){
            public void doEvent() {
                System.out.println("----------------");
                System.out.printf("Did an event scheduled for %d @ %d%n", eventTime + 5000, System.currentTimeMillis());
                gCVarSystem.setCVarValue("test_cvar", "bar");
                gConsoleSystem.readLine("echo Penultimate Scheduled Event Just Finished!");
            }
        };
        gSchedulerEvent event4 = engine.schedulerSystem. new gSchedulerEvent(){
            public void doEvent() {
                System.out.println("----------------");
                System.out.printf("Did an event scheduled for %d @ %d%n", eventTime + 10000, System.currentTimeMillis());
                gCVarSystem.setCVarValue("test_cvar", "bar");
                String result = gConsoleSystem.readLine("add 2 2");
                gConsoleSystem.readLine("echo Last Scheduled Event Just Finished! 2 + 2 is... " + result + "!");
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
        gameFileSystemTest.test();

        registerCVars();
        registerConsoleCommands();
        registerEvents();
        createInputThread();

        int internalGameRate = 1000;
        long snapshotTimeNanos = System.nanoTime();  // use nano for game timer
        long tickTimeNanos = snapshotTimeNanos;

        // GAME LOOP
        while(true) {
            snapshotTimeNanos = System.nanoTime();

            // update state
            while (tickTimeNanos < snapshotTimeNanos) {
                tickTimeNanos += (1000000000 / internalGameRate);
                gameFrames++;
                gameFramesMetric++;
                if(gameFrames >= Integer.MAX_VALUE - 1000)
                    gameFrames = 0;
            }

            gSchedulerSystem.doEvents(System.currentTimeMillis());

            gGraphicsSystem.update();
        }
    }
}
