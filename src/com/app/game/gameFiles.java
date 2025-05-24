package com.app.game;

import com.app.engine.engine;
import com.app.engine.fileSystem.gDirectory;
import com.app.engine.fileSystem.gFile;
import com.app.engine.fileSystem.gFileSystem;

import java.util.HashMap;
import java.util.TreeSet;

public class gameFiles {
    private static engine engineInstance = engine.instance();

    private static gFileSystem fileSystem; // TODO: can we do filesystems for assets, mapfiles, scripts, etc
    private static gFileSystem spritesFileSystem;

    public static HashMap<String, gFile> gFiles = new HashMap<>();
    public static HashMap<String, gFile> gFilesSprites = new HashMap<>();

    public static void init() {
        fileSystem = engineInstance.fileSystem.new gFileSystem(gameSettings.fileSystemFilesPath);
        parseFiles(gFiles, fileSystem.getRootDirectory());

        spritesFileSystem = engineInstance.fileSystem.new gFileSystem(gameSettings.fileSystemDataPath);
        parseFiles(gFilesSprites, spritesFileSystem.getRootDirectory());
    }

    public static String[] getFilesList() {
        return new TreeSet<>(gFiles.keySet()).toArray(new String[0]);
    }

    public static String[] getSpritesFilesList() {
        return new TreeSet<>(gFilesSprites.keySet()).toArray(new String[0]);
    }

    private static void parseFiles(HashMap<String, gFile> map, gDirectory gDir) {
        for(gFile file: gDir.getFiles()) {
            map.putIfAbsent(file.getName(), file);
        }
        for(gDirectory dir : gDir.getSubDirectories()) {
            parseFiles(map, dir);
        }
    }

    public static String execFile(String name) {
        System.out.println("Reading file: " + name);
        for(String line : gFiles.get(name).getFileLines()) {
            System.out.println("% " + line);
            System.out.print(gameConsole.get().readLine(line));
        }
        return "";
    }
}
