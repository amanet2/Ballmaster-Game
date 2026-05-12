package com.app.game;

import com.app.engine.engine;
import com.app.engine.graphicsSystem.gSprite;
import com.app.engine.graphicsSystem.gSpriteSystem;

import java.util.HashMap;

public class gameSprites {
    public static gSpriteSystem instance() {
        return engine.instance().gGraphicsSystem.getSpriteSystem();
    }

    static gSprite pinkGuySprite;

    public static void init() {
        // TODO: filesystem path should not be passed by game lib
        String path = "base/data/characters/player_pink/a03.png";
        String file = gameFiles.instance().getFileSystemSprites().getRootDirectory().getFile(path).getName();
        pinkGuySprite = instance().getSprite(file);
    }
}
