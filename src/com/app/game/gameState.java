package com.app.game;

public class gameState {
    private static double dir = 0.2;
    public static double testSpriteX = 0;
    public static double testSpriteY = 0;

    public static void update() {
        if(testSpriteX >= 450)
            dir = -0.2;
        if(testSpriteX <= -450)
            dir = 0.2;

        testSpriteX += (dir);
    }
}
