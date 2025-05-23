package com.app.game;

import com.app.engine.engine;
import com.app.engine.fileSystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

public class gameFiles {
    // TODO: current engine fileSystem is useless here
    private static engine engineInstance = engine.instance();

    private static fileSystem.gFileSystem fileSystem;

    public static HashMap<String, File> gFiles = new HashMap<>();

    public static fileSystem.gFileSystem get() {
        return fileSystem;
    }

    public static void init() {
        fileSystem = engineInstance.fileSystem.new gFileSystem(gameSettings.filesPath);
    }

    public static String execFile(String name) {
        try {
            gFiles.putIfAbsent(name, new File(name));
            File execFile = gFiles.get(name);
            String[] lines = Files.readAllLines(execFile.toPath()).toArray(new String[0]);
            System.out.println("Reading file: " + name);
            for(String line : lines) {
                System.out.println("% " + line);
                System.out.println(gameConsole.get().readLine(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
