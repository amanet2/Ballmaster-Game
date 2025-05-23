package com.app.game;

import com.app.engine.engine;
import com.app.engine.spriteSystem.gSprite;
import com.app.engine.spriteSystem.gSpriteSystem;

import java.util.ArrayList;

public class gameSprites {
    private static engine engineInstance = engine.instance();

    private static gSpriteSystem gSpriteSystem = engineInstance.spriteSystem.new gSpriteSystem();

    public static ArrayList<gSprite> gSprites = new ArrayList<>();

    public static gSpriteSystem get() {
        return gSpriteSystem;
    }

    public static void init() {
        String basePath = gameSettings.dataPath.endsWith("/") ? gameSettings.dataPath : gameSettings.dataPath + "/";
        String testSpritePath = String.format("%scharacters/player_pink/a03.png", basePath);
        gSprite testSprite1 = gSpriteSystem.getScaledSprite(testSpritePath, 150, 150);
        gSprite testSprite2 = gSpriteSystem.getScaledSprite(testSpritePath, 300, 300);
        gSprite testSprite3 = gSpriteSystem.getScaledSprite(testSpritePath, 600, 600);
        gSprites.add(testSprite1);
        gSprites.add(testSprite2);
        gSprites.add(testSprite3);
    }
}
