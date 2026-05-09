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
        String path = "base/data/characters/player_pink/a03.png";
        String file = gameFiles.instance().getFileSystemSprites().getRootDirectory().getFile(path).getName();
        gSprite pinkGuySprite = gSpriteSystem.getSprite(file);

        gSprites.put(gameStrings.BALL_PINK, pinkGuySprite);
    }
}
