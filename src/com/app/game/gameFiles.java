package com.app.game;

import com.app.engine.engine;
import com.app.engine.fileSystem;
import com.app.engine.fileSystem.gFile;

public class gameFiles {
    private static engine engineInstance = engine.instance();

    private static fileSystem.gFileSystem fileSystem;

    public static fileSystem.gFileSystem get() {
        return fileSystem;
    }

    public static void init() {
        fileSystem = engineInstance.fileSystem.new gFileSystem(gameSettings.filesPath);
    }

    public static String[] getFileLines(String name) {
        for(gFile file : fileSystem.getRootDirectory().getFiles()) {
            System.out.println(name + " vs " + file.getName());
            if(file.getName().equals(name))
                return file.getLines();
        }
        return new String[0];
    }

    public static String execFile(String name) {
        for(String line : getFileLines(name)) {
            System.out.println(line);
            System.out.println(">> " + gameConsole.get().readLine(line));
        }
        return "";
    }
}
