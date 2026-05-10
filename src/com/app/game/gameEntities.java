package com.app.game;

import com.app.engine.entity;
import com.app.engine.utils.gBounds;

import java.util.HashMap;

public class gameEntities {
    private static final HashMap<String, entity> entities = new HashMap<>();

    public static entity get(String entityKey) {
        return entities.get(entityKey);
    }

    public static void init() {
        entity ballBoy = new entity();
        ballBoy.setSprite(gameSprites.gSprites.get(gameStrings.BALL_PINK));
        ballBoy.setBounds(new gBounds(new double[]{ 0, 0, 300, 300 }));
        ballBoy.setVec(new double[]{ 0.2, 0.0 });

        entities.put(gameStrings.BALL_PINK, ballBoy);
    }
}
