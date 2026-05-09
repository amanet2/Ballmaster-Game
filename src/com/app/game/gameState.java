package com.app.game;

import com.app.engine.entity;

public class gameState {
    public static entity ballBoy;

    public static void init() {
        ballBoy = gameEntities.get(gameStrings.BALL_PINK);
    }

    public static void update() {
        if(ballBoy.getX() >= 450)
            ballBoy.setDx(-0.2);
        if(ballBoy.getX() <= -450)
            ballBoy.setDx(0.2);

        ballBoy.setX(ballBoy.getX() + ballBoy.getDx());

        double[] camCoords = gameCamera.gameCamera.getCoords();
        double[] camVec = gameCamera.gameCamera.getVec();
        gameCamera.gameCamera.setCoords(new double[]{ camCoords[0] + camVec[0], camCoords[1] + camVec[1]});
    }
}
