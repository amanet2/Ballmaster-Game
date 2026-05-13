package com.app.game;

import java.util.ArrayList;
import java.util.HashMap;

import com.app.engine.entitySystem.gEntity;
import com.app.engine.eventSystem.*;
import com.app.engine.utils.*;

public class gameState {
    public static gEntity ballBoy;

    public static gEventTriggerBounds triggerBounds;
    public static ArrayList<gBounds> collisionBounds;

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

    public static void init() {
        ballBoy = new gEntity();
        ballBoy.setSprite(gameSprites.pinkGuySprite);
        ballBoy.setBounds(new gBounds(new double[]{ 0, -600, 300, 300 }));
        ballBoy.setVec(new double[]{ 0.0, 0.0 });

        // Gonna need the scripting engine to make this be definable in a string/file
        triggerBounds = new gEventTriggerBounds(new gEventTrigger(new gEvent(){
            @Override
            public void doEvent() {
                triggerBounds = null;
            }
        }));
        triggerBounds.setBounds(new gBounds(new double[] { 900, 450, 150, 150 }));

        String dictString = "{collisions=[{x=-1200, y=600, w=2400, h=600},{x=-1800, y=0, w=600, h=600},{x=1200, y=0, w=600, h=600}]}";
        collisionBounds = new ArrayList<>();
        for(Object entry : (ArrayList) new gDict(dictString).get("collisions")) {
            HashMap collision = (HashMap) entry;
            collisionBounds.add(new gBounds(new double[] {
                    Double.parseDouble(collision.get("x").toString()),
                    Double.parseDouble(collision.get("y").toString()),
                    Double.parseDouble(collision.get("w").toString()),
                    Double.parseDouble(collision.get("h").toString())
            }));
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

        if(triggerBounds != null && triggerBounds.getBounds().intersects(ballBoy.getBounds()))
            triggerBounds.doTrigger();
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
