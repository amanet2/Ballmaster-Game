package com.app.game;

public class gameState {
    private static double dir = 0.2;  // TODO: make an entity member, vector horiz and vector vert
    public static entity ballBoy = new entity();

    public static void init() {
        ballBoy.setSprite(gameSprites.gSprites.getFirst());
        ballBoy.setX(0);
        ballBoy.setY(0);
        ballBoy.setW(300);
        ballBoy.setH(300);
    }

    public static void update() {
        if(ballBoy.getX() >= 450)
            dir = -0.2;
        if(ballBoy.getX() <= -450)
            dir = 0.2;

        ballBoy.setX(ballBoy.getX() + dir);
    }
}
