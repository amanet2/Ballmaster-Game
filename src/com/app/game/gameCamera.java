package com.app.game;

import com.app.engine.cameraSystem.gCamera;

public class gameCamera {
    public static final gCamera[] gameCameras = {new gCamera(), new gCamera()};

    public static gCamera gameCamera = gameCameras[0];
    public static gCamera uiCamera = new gCamera();
}
