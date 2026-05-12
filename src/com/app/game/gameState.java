package com.app.game;

import com.app.engine.entitySystem.gEntity;
import com.app.engine.eventSystem.*;
import com.app.engine.utils.*;

public class gameState {
    public static double gameRate = 1000;

    public static gEntity ballBoy;
    public static gEventTriggerBounds triggerBounds;
    public static gBounds bigBox1;
    public static gBounds bigBox2;
    public static gBounds bigBox3;

    public static double playerSpeed = 0.4;
    public static boolean playerUp = false;
    public static boolean playerDown = false;
    public static boolean playerLeft = false;
    public static boolean playerRight = false;

    public static boolean playerJump = false;
    public static long playerCanJumpAtMillis = 0;

    public static double cameraSpeed = 0.2;
    public static boolean camUp = false;
    public static boolean camDown = false;
    public static boolean camLeft = false;
    public static boolean camRight = false;

    public static double gravity = 3.0;

    public static void init() {
        ballBoy = new gEntity();
        ballBoy.setSprite(gameSprites.pinkGuySprite);
        ballBoy.setBounds(new gBounds(new double[]{ 0, -600, 300, 300 }));
        ballBoy.setVec(new double[]{ 0.0, 0.0 });

        triggerBounds = new gEventTriggerBounds(new gEventTrigger(new gEvent(){
            @Override
            public void doEvent() {
                triggerBounds = null;
            }
        }));
        triggerBounds.setBounds(new gBounds(new double[] { 900, 450, 150, 150 }));

        bigBox1 = new gBounds(new double[] { -1200, 600, 2400, 600});
        bigBox2 = new gBounds(new double[] { -1800, 0, 600, 600 });
        bigBox3 = new gBounds(new double[] { 1200, 0, 600, 600 });
    }

    private static void updateCharacters() {
//        double vecDx = 0.0;
//        double vecDy = 0.0;
        double vecDx = 0.0;
        double vecDy = Math.min(ballBoy.getVec()[1] + gravity, gravity);

//        if(playerUp) vecDy -= playerSpeed;
//        if(playerDown) vecDy += playerSpeed;
        if(playerLeft) vecDx -= playerSpeed;
        if(playerRight) vecDx += playerSpeed;

        // gravity
//        vecDy += gravity;

        if (playerJump && playerCanJumpAtMillis < System.currentTimeMillis()) {
            System.out.println("JUMP");
            playerCanJumpAtMillis = System.currentTimeMillis() + 1000;
            playerJump = false;

            vecDy -= 72.0;
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
        if(ballBoyBoundsDx.intersects(bigBox1)) coordsDx = bounds.getX();
        if(ballBoyBoundsDy.intersects(bigBox1)) coordsDy = bounds.getY();
        if(ballBoyBoundsDx.intersects(bigBox2)) coordsDx = bounds.getX();
        if(ballBoyBoundsDy.intersects(bigBox2)) coordsDy = bounds.getY();
        if(ballBoyBoundsDx.intersects(bigBox3)) coordsDx = bounds.getX();
        if(ballBoyBoundsDy.intersects(bigBox3)) coordsDy = bounds.getY();

        ballBoy.setBounds(new gBounds(new double[]{ coordsDx, coordsDy, bounds.getWidth(), bounds.getHeight() }));

        if(triggerBounds != null && triggerBounds.getBounds().intersects(ballBoy.getBounds()))
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
