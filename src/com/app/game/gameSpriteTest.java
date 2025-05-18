package com.app.game;

import com.app.engine.spriteSystem;
import com.app.engine.spriteSystem.gSprite;
import com.app.engine.spriteSystem.gSpriteSystem;

public class gameSpriteTest {
    static spriteSystem spriteSystem = new spriteSystem();

    public static void test() {
        gSpriteSystem gSpriteSystem = spriteSystem. new gSpriteSystem();
        gSprite testSprite1 = gSpriteSystem.getScaledSprite("data/player_pint_03.png", 300, 300);
        gSprite testSprite2 = gSpriteSystem.getScaledSprite("data/player_pint_03.png", 300, 300);
        gSprite testSprite3 = gSpriteSystem.getScaledSprite("data/player_pint_03.png", 450, 450);
        System.out.println("(Should be same as sprite2) Got sprite1: " + testSprite1.getImage());
        System.out.println("(Should be same as sprite1) Got sprite2: " + testSprite2.getImage());
        System.out.println("(Should be unique)          Got sprite3: " + testSprite3.getImage());
    }
}
