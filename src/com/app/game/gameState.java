package com.app.game;

import com.app.engine.entity;

public class gameState {
    public static entity ballBoy;
    public static boolean playerUp = false;
    public static boolean playerDown = false;
    public static boolean playerLeft = false;
    public static boolean playerRight = false;
    public static boolean camUp = false;
    public static boolean camDown = false;
    public static boolean camLeft = false;
    public static boolean camRight = false;

    public static void init() {
        ballBoy = gameEntities.get(gameStrings.BALL_PINK);
    }

    private static void updateCharacters() {
        double vecDx = 0.0;
        double vecDy = 0.0;

        if(playerUp) vecDy -= 0.2;
        if(playerDown) vecDy += 0.2;
        if(playerLeft) vecDx -= 0.2;
        if(playerRight) vecDx += 0.2;

        ballBoy.setVec(new double[]{ vecDx, vecDy });

        double[] vec = ballBoy.getVec();
        double[] coords = ballBoy.getCoords();
        ballBoy.setCoords(new double[]{ coords[0] + vec[0], coords[1] + vec[1]});
    }

    // TODO: make sure camera movement is proportional to player if same vel
    private static void updateCamera() {
        double vecDx = 0.0;
        double vecDy = 0.0;

        if(camUp) vecDy -= 0.2;
        if(camDown) vecDy += 0.2;
        if(camLeft) vecDx -= 0.2;
        if(camRight) vecDx += 0.2;

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
