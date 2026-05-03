package com.app.game;

import com.app.engine.engine;
import com.app.engine.spriteSystem.gSprite;
import com.app.engine.spriteSystem.gSpriteSystem;

import java.util.ArrayList;

public class gameSprites {
    private static gSpriteSystem gSpriteSystem = engine.instance().gSpriteSystem;

    public static ArrayList<gSprite> gSprites = new ArrayList<>();

    public static gSpriteSystem instance() {
        return gSpriteSystem;
    }

    public static void init() {
        String pinkGuyPath = gameFiles.gFilesSprites.get(
                gameSettings.fileSystemSpritesPath + "/characters/player_pink/a03.png"
        ).getName();

        gSprite pinkGuySprite = gSpriteSystem.getScaledSprite(pinkGuyPath, 300, 300);

        gSprites.add(pinkGuySprite);

        System.out.println("SPRITES SYSTEM INITIALIZED");
    }
}
