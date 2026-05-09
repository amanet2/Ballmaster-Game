package com.app.game;

import com.app.engine.camera;

public class gameCamera {
    public static final camera[] gameCameras = {new camera(), new camera()};

    public static camera gameCamera = gameCameras[0];
    public static camera uiCamera = new camera();
}
