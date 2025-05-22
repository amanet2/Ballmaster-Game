package com.app.game;

import com.app.engine.camera;

public class gameCamera {
    private static final camera camera1 = new camera();

    public static camera getCamera1() {
        return camera1;
    }
}
