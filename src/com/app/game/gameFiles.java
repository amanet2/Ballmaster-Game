package com.app.game;

import com.app.engine.engine;
import com.app.engine.fileSystem;
import com.app.engine.fileSystem.gDirectory;
import com.app.engine.fileSystem.gFile;

import java.util.HashMap;

public class gameFiles {
    private static engine engineInstance = engine.instance();

    private static fileSystem.gFileSystem fileSystem; // TODO: can we do filesystems for assets, mapfiles, scripts, etc

    public static HashMap<String, gFile> gFiles = new HashMap<>();

    public static fileSystem.gFileSystem get() {
        return fileSystem;
    }

    public static void init() {
        fileSystem = engineInstance.fileSystem.new gFileSystem(gameSettings.fileSystemFilesPath);
        parseFiles(fileSystem.getRootDirectory());
    }

    private static void parseFiles(gDirectory gDir) {
        for(gFile file: gDir.getFiles()) {
            gFiles.putIfAbsent(file.getName(), file);
        }
        for(gDirectory dir : gDir.getSubDirectories()) {
            parseFiles(dir);
        }
    }

    public static String execFile(String name) {
        System.out.println("Reading file: " + name);
        for(String line : gFiles.get(name).getFileLines()) {
            System.out.println("% " + line);
            System.out.println(gameConsole.get().readLine(line));
        }
        return "";
    }
}
