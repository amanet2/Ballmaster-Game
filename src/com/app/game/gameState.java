package com.app.game;

public class gameState {
    public static entity ballBoy;

    public static void init() {
        ballBoy = new entity();
        ballBoy.setSprite(gameSprites.gSprites.getFirst());
        ballBoy.setCoords(new double[]{ 0, 0 });
        ballBoy.setDims(new double[]{ 300.0, 300.0 });
        ballBoy.setVec(new double[]{ 0.2, 0.0 });
    }

    public static void update() {
        if(ballBoy.getX() >= 450)
            ballBoy.setDx(-0.2);
        if(ballBoy.getX() <= -450)
            ballBoy.setDx(0.2);

        ballBoy.setX(ballBoy.getX() + ballBoy.getDx());
    }
}
