package com.app.game;

import com.app.engine.engine;
import com.app.engine.fileSystem.gDirectory;
import com.app.engine.fileSystem.gFile;
import com.app.engine.fileSystem.gFileSystem;

import java.util.HashMap;
import java.util.TreeSet;

public class gameFiles {
    private static engine engineInstance = engine.instance();

    private static gFileSystem fileSystemConfig;
    private static gFileSystem fileSystemScripts;
    private static gFileSystem fileSystemSprites;

    public static HashMap<String, gFile> gFilesConfig = new HashMap<>();
    public static HashMap<String, gFile> gFilesScripts = new HashMap<>();
    public static HashMap<String, gFile> gFilesSprites = new HashMap<>();

    public static void init() {
        fileSystemConfig = engineInstance.fileSystem.new gFileSystem(gameSettings.fileSystemConfigPath);
        parseFiles(gFilesConfig, fileSystemConfig.getRootDirectory());

        fileSystemScripts = engineInstance.fileSystem.new gFileSystem(gameSettings.fileSystemScriptsPath);
        parseFiles(gFilesScripts, fileSystemScripts.getRootDirectory());

        fileSystemSprites = engineInstance.fileSystem.new gFileSystem(gameSettings.fileSystemSpritesPath);
        parseFiles(gFilesSprites, fileSystemSprites.getRootDirectory());
    }

    public static String[] getFilesListConfig() {
        return new TreeSet<>(gFilesConfig.keySet()).toArray(new String[0]);
    }

    public static String[] getFilesListScripts() {
        return new TreeSet<>(gFilesScripts.keySet()).toArray(new String[0]);
    }

    public static String[] getFilesListSprites() {
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
        System.out.println("Reading config file: " + name);
        for(String line : gFilesConfig.get(name).getFileLines()) {
            System.out.println("% " + line);
            System.out.print(gameConsole.get().readLine(line));
        }
        return "";
    }

    public static String scriptFile(String name) {
        System.out.println("Reading script file: " + name);
        System.out.println("TO BE CONTINUED...");
        return "";
    }
}
