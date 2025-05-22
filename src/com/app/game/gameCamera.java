package com.app.game;

import com.app.engine.engine;
import com.app.engine.camera;

public class gameCamera {
    private static engine engineInstance = engine.instance();
    private static final camera camera = engineInstance.camera;

    public static camera get() {
        return camera;
    }
}
