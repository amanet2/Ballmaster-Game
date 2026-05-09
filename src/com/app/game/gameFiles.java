package com.app.game;

import com.app.engine.fileSystem.gBaseFileSystem;
import com.app.engine.engine;

public class gameFiles {
    private static final gBaseFileSystem baseFileSystem = engine.instance().gBaseFileSystem;

    public static gBaseFileSystem instance() {
        return baseFileSystem;
    }

    public static void init() {

    }
}
