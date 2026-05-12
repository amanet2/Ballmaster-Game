package com.app.game;

import com.app.engine.fileSystem.gBaseFileSystem;
import com.app.engine.engine;

public class gameFiles {
    public static gBaseFileSystem instance() {
        return engine.instance().gBaseFileSystem;
    }

    public static void init() {

    }
}
