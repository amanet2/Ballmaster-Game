package com.app.game;

import com.app.engine.engine;
import com.app.engine.spriteSystem;

public class gameSprites {
    private static engine engineInstance = engine.instance();

    private static spriteSystem.gSpriteSystem sprites = engineInstance.spriteSystem.new gSpriteSystem();

    public static spriteSystem.gSpriteSystem get() {
        return sprites;
    }

    public static void init() {
        String basePath = gameSettings.basePath.endsWith("/") ? gameSettings.basePath : gameSettings.basePath + "/";
        String testSpritePath = String.format("%scharacters/player_pink/a03.png", basePath);
        spriteSystem.gSprite testSprite1 = sprites.getScaledSprite(testSpritePath, 150, 150);
        spriteSystem.gSprite testSprite2 = sprites.getScaledSprite(testSpritePath, 300, 300);
        spriteSystem.gSprite testSprite3 = sprites.getScaledSprite(testSpritePath, 600, 600);
        gameSettings.gSprites.add(testSprite1);
        gameSettings.gSprites.add(testSprite2);
        gameSettings.gSprites.add(testSprite3);
    }
}
