package com.app.game;

import com.app.engine.engine;
import com.app.engine.fileSystem;

public class gameFiles {
    private static engine engineInstance = engine.instance();

    private static fileSystem.gFileSystem fileSystem;

    public static fileSystem.gFileSystem get() {
        return fileSystem;
    }

    public static void init() {
        fileSystem = engineInstance.fileSystem.new gFileSystem(gameSettings.basePath);
    }
}
