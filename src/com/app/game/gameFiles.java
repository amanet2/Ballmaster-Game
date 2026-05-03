package com.app.game;

import com.app.engine.fileSystem.gDirectory;
import com.app.engine.fileSystem.gFile;
import com.app.engine.fileSystem.gFileSystem;

import java.util.HashMap;
import java.util.TreeSet;

public class gameFiles {
    private static gFileSystem fileSystemConfig;
    private static gFileSystem fileSystemScripts;
    private static gFileSystem fileSystemSprites;

    public static HashMap<String, gFile> gFilesConfig = new HashMap<>();
    public static HashMap<String, gFile> gFilesScripts = new HashMap<>();
    public static HashMap<String, gFile> gFilesSprites = new HashMap<>();

    public static void init() {
        fileSystemConfig = new gFileSystem(gameSettings.fileSystemConfigPath);
        parseFiles(gFilesConfig, fileSystemConfig.getRootDirectory());

        fileSystemScripts = new gFileSystem(gameSettings.fileSystemScriptsPath);
        parseFiles(gFilesScripts, fileSystemScripts.getRootDirectory());

        fileSystemSprites = new gFileSystem(gameSettings.fileSystemSpritesPath);
        parseFiles(gFilesSprites, fileSystemSprites.getRootDirectory());

        System.out.println("FILE SYSTEM INITIALIZED");
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

    public static String execCfgFile(String name) {
        // TODO: apply lexing & parsing so comments '#' can be anywhere in the line
        System.out.println("Reading config file: " + name);
        for(String line : gFilesConfig.get(name).getFileLines()) {
            if(!line.trim().startsWith((";")) || !line.trim().startsWith("#")) {
                System.out.println("% " + line);
                System.out.println(gameConsole.instance().readLine(line));
            }
        }
        return "";
    }

    public static String scriptFile(String name) {
        System.out.println("Reading script file: " + name);
        System.out.println("TO BE CONTINUED...");
        return "";
    }
}
