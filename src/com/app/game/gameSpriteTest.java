package com.app.game;

import com.app.engine.spriteSystem;
import com.app.engine.spriteSystem.gSprite;
import com.app.engine.spriteSystem.gSpriteSystem;

public class gameSpriteTest {
    static spriteSystem spriteSystem = new spriteSystem();

    public static void test() {
        gSpriteSystem gSpriteSystem = spriteSystem. new gSpriteSystem();
        gSprite testSprite = gSpriteSystem.getScaledSprite("data/player_pint_03.png", 300, 300);
        System.out.println("Got sprite " + testSprite.getImage());
    }
}
