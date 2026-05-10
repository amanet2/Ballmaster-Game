package com.app.game;

import com.app.engine.entity;
import com.app.engine.eventSystem.*;
import com.app.engine.utils.*;

public class gameState {
    public static double gameRate = 1000;

    public static entity ballBoy;
    public static gEventTriggerBounds triggerBounds;

    public static double playerSpeed = 0.4;
    public static boolean playerUp = false;
    public static boolean playerDown = false;
    public static boolean playerLeft = false;
    public static boolean playerRight = false;

    public static double cameraSpeed = 0.2;
    public static boolean camUp = false;
    public static boolean camDown = false;
    public static boolean camLeft = false;
    public static boolean camRight = false;

    public static double gravity = 0.0;

    public static void init() {
        ballBoy = gameEntities.get(gameStrings.BALL_PINK);

        triggerBounds = new gEventTriggerBounds(new gEventTrigger(new gEvent(){
            @Override
            public void doEvent() {
                System.out.println("FOOBAR");
            }
        }));
        triggerBounds.setBounds(new gBounds(new double[] { -600, -600, 150, 150 }));
    }

    private static void updateCharacters() {
        double vecDx = 0.0;
        double vecDy = 0.0;

        if(playerUp) vecDy -= playerSpeed;
        if(playerDown) vecDy += playerSpeed;
        if(playerLeft) vecDx -= playerSpeed;
        if(playerRight) vecDx += playerSpeed;

        // gravity
        vecDy += gravity;

        ballBoy.setVec(new double[]{ vecDx, vecDy });

        double[] vec = ballBoy.getVec();
        gBounds bounds = ballBoy.getBounds();

        double coordsDx = bounds.getX() + vec[0];
        double coordsDy = bounds.getY() + vec[1];

        if(coordsDx >= 450 || coordsDx <= -450) coordsDx = bounds.getX(); // fake collisons
        if(coordsDy >= 450 || coordsDy <= -450) coordsDy = bounds.getY(); // fake collisons

        ballBoy.setBounds(new gBounds(new double[]{ coordsDx, coordsDy, ballBoy.getBounds().getWidth(), ballBoy.getBounds().getHeight() }));

        checkIntersection();
    }

    private static void checkIntersection() {
        gBounds bounds = triggerBounds.getBounds();
        gBounds ballBoyBounds = ballBoy.getBounds();

        if (
                bounds.getX() > ballBoyBounds.getX() + ballBoyBounds.getWidth()/2
                        || ballBoyBounds.getX() - ballBoyBounds.getWidth()/2 > bounds.getX() + bounds.getWidth()
        )
            return;

        if (
                bounds.getY() + bounds.getHeight() < ballBoyBounds.getY() - ballBoyBounds.getHeight()/2
                        || ballBoyBounds.getY() + ballBoyBounds.getHeight()/2 < bounds.getY()
        )
            return;

        triggerBounds.doTrigger();
    }

    // TODO: make sure camera movement is proportional to player if same vel
    private static void updateCamera() {
        double vecDx = 0.0;
        double vecDy = 0.0;

        if(camUp) vecDy -= cameraSpeed;
        if(camDown) vecDy += cameraSpeed;
        if(camLeft) vecDx -= cameraSpeed;
        if(camRight) vecDx += cameraSpeed;

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
