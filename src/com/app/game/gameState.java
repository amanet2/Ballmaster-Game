package com.app.game;

import com.app.engine.entity;

public class gameState {
    public static entity ballBoy;

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

    public static void init() {
        ballBoy = gameEntities.get(gameStrings.BALL_PINK);
    }

    private static void updateCharacters() {
        double vecDx = 0.0;
        double vecDy = 0.0;

        if(playerUp) vecDy -= playerSpeed;
        if(playerDown) vecDy += playerSpeed;
        if(playerLeft) vecDx -= playerSpeed;
        if(playerRight) vecDx += playerSpeed;

        ballBoy.setVec(new double[]{ vecDx, vecDy });

        double[] vec = ballBoy.getVec();
        double[] coords = ballBoy.getCoords();

        double coordsDx = coords[0] + vec[0];
        double coordsDy = coords[1] + vec[1];

        if(coordsDx >= 450 || coordsDx <= -450) coordsDx = coords[0]; // fake collisons
        if(coordsDy >= 450 || coordsDy <= -450) coordsDy = coords[1]; // fake collisons

        ballBoy.setCoords(new double[]{ coordsDx, coordsDy });
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
