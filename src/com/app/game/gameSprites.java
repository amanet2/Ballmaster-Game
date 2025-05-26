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

        gSprite pinkGuySprite = gSpriteSystem.getScaledSprite(pinkGuyPath, 150, 150);
        gSprite pinkGuySpriteBig = gSpriteSystem.getScaledSprite(pinkGuyPath, 300, 300);
        gSprite pinkGuySpriteHuge = gSpriteSystem.getScaledSprite(pinkGuyPath, 600, 600);

        gSprites.add(pinkGuySprite);
        gSprites.add(pinkGuySpriteBig);
        gSprites.add(pinkGuySpriteHuge);
    }
}
