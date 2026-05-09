package com.app.game;

import com.app.engine.entity;

public class gameState {
    public static entity ballBoy;
    public static boolean camUp = false;
    public static boolean camDown = false;
    public static boolean camLeft = false;
    public static boolean camRight = false;

    public static void init() {
        ballBoy = gameEntities.get(gameStrings.BALL_PINK);
    }

    private static void updateCharacters() {
        if(ballBoy.getX() >= 450)
            ballBoy.setDx(-0.2);
        if(ballBoy.getX() <= -450)
            ballBoy.setDx(0.2);

        ballBoy.setX(ballBoy.getX() + ballBoy.getDx());
    }

    private static void updateCamera() {
        double vecDx = 0.0;
        double vecDy = 0.0;

        if(camUp) vecDy -= 0.2;
        if(camDown) vecDy += 0.2;
        if(camLeft) vecDx -= 0.2;
        if(camRight) vecDx += 0.2;

        gameCamera.gameCamera.setVec(new double[]{ vecDx, vecDy });
    }

    public static void update() {
        updateCharacters();
        updateCamera();

        double[] vec = gameCamera.gameCamera.getVec();
        double[] camCoords = gameCamera.gameCamera.getCoords();
        gameCamera.gameCamera.setCoords(new double[]{ camCoords[0] + vec[0], camCoords[1] + vec[1]});
    }
}
