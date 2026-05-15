package com.app.game;

import com.app.engine.engine;
import com.app.engine.graphicsSystemI;

public class gameTextures {
    public static graphicsSystemI.gTextureSystem instance() {
        return engine.instance().gGraphicsSystem.getTextureSystem();
    }

    static graphicsSystemI.gTexture wallTexture;

    public static void init() {
        // TODO: filesystem path should not be passed by game lib
        String path = "base/textures/wall.png";
        String file = gameFiles.instance().getFileSystemTextures().getRootDirectory().getFile(path).getName();
        wallTexture = instance().getTexture(file);
    }
}
