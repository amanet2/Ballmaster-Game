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
        String basePath = game.basePath.isEmpty() ? game.basePath : game.basePath + "/";
        String testSpritePath = String.format("%sdata/player_pink_03.png", basePath);
        spriteSystem.gSprite testSprite1 = sprites.getScaledSprite(testSpritePath, 150, 150);
        spriteSystem.gSprite testSprite2 = sprites.getScaledSprite(testSpritePath, 300, 300);
        spriteSystem.gSprite testSprite3 = sprites.getScaledSprite(testSpritePath, 600, 600);
        game.gSprites.add(testSprite1);
        game.gSprites.add(testSprite2);
        game.gSprites.add(testSprite3);
    }
}
