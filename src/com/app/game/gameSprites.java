package com.app.game;

import com.app.engine.engine;
import com.app.engine.graphicsSystem.gSprite;
import com.app.engine.graphicsSystem.gSpriteSystem;

import java.util.HashMap;

public class gameSprites {
    private static gSpriteSystem gSpriteSystem = engine.instance().gGraphicsSystem.getSpriteSystem();

    public static HashMap<String, gSprite> gSprites = new HashMap<>();

    public static gSpriteSystem instance() {
        return gSpriteSystem;
    }

    public static void init() {
        // TODO: filesystem path should not be passed by game lib
        String pinkGuyPath = gameFiles.gFilesSprites.get(
                gameSettings.fileSystemSpritesPath + "/characters/player_pink/a03.png"
        ).getName();
        gSprite pinkGuySprite = gSpriteSystem.getScaledSprite(pinkGuyPath, 100, 100);

        gSprites.put(gameStrings.BALL_PINK, pinkGuySprite);
    }
}
