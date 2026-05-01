package com.app.game;

import com.app.engine.camera;

public class gameCamera {
    public static final camera[] cameras = {new camera(), new camera()};

    public static camera activeCamera = cameras[0];
}
