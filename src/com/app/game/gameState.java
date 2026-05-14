package com.app.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import com.app.engine.entitySystem.gEntity;
import com.app.engine.eventSystem.*;
import com.app.engine.utils.*;

public class gameState {
    public static gEntity ballBoy;

    public static ArrayList<gBounds> collisionBounds;
    public static ArrayList<gEventTriggerBounds> triggerBounds;

    public static boolean playerUp = false;
    public static boolean playerDown = false;
    public static boolean playerLeft = false;
    public static boolean playerRight = false;

    public static boolean playerJump = false;
    public static long playerCanJumpAtMillis = 0;

    public static boolean camUp = false;
    public static boolean camDown = false;
    public static boolean camLeft = false;
    public static boolean camRight = false;

    public static void init(String filePath) {
//        String filePath = "base/map/test.map";
        String stateString = gameFiles.instance().getFileSystemMaps().getRootDirectory().getFile(filePath).getFileString();

        gDict stateDict = new gDict(stateString);

        ArrayList execStrings = (ArrayList) stateDict.get("exec");
        for(Object execStringObject : execStrings) {
            String execString = execStringObject.toString();

            String output = gameConsole.instance().readLine(execString);
            System.out.print(output + (!output.isEmpty() ? "\n" : ""));
        }

        HashMap ballBoyVars = (HashMap) stateDict.get("ballboy");
        HashMap ballBoyBounds = (HashMap) ballBoyVars.get("bounds");
        ArrayList ballBoyVec = (ArrayList) ballBoyVars.get("vec");

        ballBoy = new gEntity();
        ballBoy.setSprite(gameSprites.pinkGuySprite);
        ballBoy.setBounds(new gBounds(new double[]{
                Double.parseDouble(ballBoyBounds.get("x").toString()),
                Double.parseDouble(ballBoyBounds.get("y").toString()),
                Double.parseDouble(ballBoyBounds.get("w").toString()),
                Double.parseDouble(ballBoyBounds.get("h").toString())
        }));
        ballBoy.setVec(new double[]{
                Double.parseDouble(ballBoyVec.get(0).toString()),
                Double.parseDouble(ballBoyVec.get(1).toString())
        });

        collisionBounds = new ArrayList<>();
        for(Object entry : (ArrayList) stateDict.get("collisions")) {
            HashMap collision = (HashMap) entry;
            collisionBounds.add(new gBounds(new double[] {
                    Double.parseDouble(collision.get("x").toString()),
                    Double.parseDouble(collision.get("y").toString()),
                    Double.parseDouble(collision.get("w").toString()),
                    Double.parseDouble(collision.get("h").toString())
            }));
        }

        // Gonna need the scripting engine to make triggers truly definable in a string/file
        // But for now, we can hard-code different triggers e.g. triggers_superspeed, triggers_superjump
        triggerBounds = new ArrayList<>();
        for(Object entry : (ArrayList) stateDict.get("triggers")) {
            HashMap trigger = (HashMap) entry;

            gEvent event = new gEvent(){
                public void doEvent() {
                    triggerBounds.remove(this.getParentEventTrigger().getParentEventTriggerBounds());
                }
            };

            gEventTrigger eventTrigger = new gEventTrigger(event);
            event.setParentEventTrigger(eventTrigger);

            gEventTriggerBounds eventTriggerBounds = new gEventTriggerBounds(eventTrigger);
            eventTrigger.setParentEventTriggerBounds(eventTriggerBounds);

            eventTriggerBounds.setBounds(new gBounds(new double[]{
                    Double.parseDouble(trigger.get("x").toString()),
                    Double.parseDouble(trigger.get("y").toString()),
                    Double.parseDouble(trigger.get("w").toString()),
                    Double.parseDouble(trigger.get("h").toString())
            }));

            triggerBounds.add(eventTriggerBounds);
        }
    }

    private static void updateCharacters() {
//        double vecDx = 0.0;
//        double vecDy = 0.0;
        double vecDx = 0.0;
        double vecDy = Math.min(ballBoy.getVec()[1] + gameCVars.worldGravity, gameCVars.worldGravity);

//        if(playerUp) vecDy -= playerSpeed;
//        if(playerDown) vecDy += playerSpeed;
        if(playerLeft) vecDx -= gameCVars.playerMaxSpeed;
        if(playerRight) vecDx += gameCVars.playerMaxSpeed;

        // gravity
//        vecDy += gravity;

        if (playerJump) {
            if(playerCanJumpAtMillis < System.currentTimeMillis()) {
                playerCanJumpAtMillis = System.currentTimeMillis() + gameCVars.playerJumpDelay;
                vecDy -= gameCVars.playerJumpForce;
            }
            playerJump = false;
        }

        ballBoy.setVec(new double[]{ vecDx, vecDy });

        double[] vec = ballBoy.getVec();
        gBounds bounds = ballBoy.getBounds();

        double coordsDx = bounds.getX() + vec[0];
        double coordsDy = bounds.getY() + vec[1];

        gBounds ballBoyBoundsDx = new gBounds(new double[] {
                coordsDx,
                bounds.getY(),
                bounds.getWidth(),
                bounds.getHeight()
        });

        gBounds ballBoyBoundsDy = new gBounds(new double[] {
                bounds.getX(),
                coordsDy,
                bounds.getWidth(),
                bounds.getHeight()
        });

        // collisions
        for(gBounds collisionBounds : collisionBounds) {
            if(ballBoyBoundsDx.intersects(collisionBounds)) coordsDx = bounds.getX();
            if(ballBoyBoundsDy.intersects(collisionBounds)) coordsDy = bounds.getY();
        }

        ballBoy.setBounds(new gBounds(new double[]{ coordsDx, coordsDy, bounds.getWidth(), bounds.getHeight() }));

        // triggers
        Queue<gEventTriggerBounds> triggeredEventBounds = new LinkedList<>();
        for(gEventTriggerBounds eventTriggerBounds : triggerBounds) {
            if(eventTriggerBounds.getBounds().intersects(ballBoy.getBounds()))
                triggeredEventBounds.add(eventTriggerBounds);
        }
        while(triggeredEventBounds.peek() != null) {
            gEventTriggerBounds eventTriggerBounds = triggeredEventBounds.remove();
            eventTriggerBounds.doTrigger();
        }
    }

    // TODO: make sure camera movement is proportional to player if same vel
    private static void updateCamera() {
        double vecDx = 0.0;
        double vecDy = 0.0;

        if(camUp) vecDy -= gameCVars.cameraMaxSpeed;
        if(camDown) vecDy += gameCVars.cameraMaxSpeed;
        if(camLeft) vecDx -= gameCVars.cameraMaxSpeed;
        if(camRight) vecDx += gameCVars.cameraMaxSpeed;

        gameCamera.gameCamera.setVec(new double[]{ vecDx, vecDy });

        double[] vec = gameCamera.gameCamera.getVec();
        double[] camCoords = gameCamera.gameCamera.getCoords();
        gameCamera.gameCamera.setCoords(new double[]{ camCoords[0] + vec[0], camCoords[1] + vec[1]});
    }

    public static void update() {
        updateCharacters();
        updateCamera();
    }
}
