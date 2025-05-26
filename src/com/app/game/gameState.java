package com.app.game;

public class gameState {
    private static int dir = 1;
    public static double testSpriteX = 0;
    public static double testSpriteY = 0;

    public static void update() {
        if(testSpriteX >= 300)
            dir = -1;
        if(testSpriteX <= -300)
            dir = 1;

        testSpriteX += (dir * 0.2);
    }
}
