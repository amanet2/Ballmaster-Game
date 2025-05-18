package com.app.game;

import com.app.engine.fileSystem;
import com.app.engine.fileSystem.gFile;
import com.app.engine.fileSystem.gDirectory;
import java.util.Arrays;

public class gameFileSystemTest {
    static fileSystem fileSystem = new fileSystem();

    public static void test() {
        gDirectory gDirectory = fileSystem. new gDirectory("data");
        System.out.printf("Filenames in /%s: %s%n", gDirectory.getPath(), Arrays.toString(gDirectory.getFileNames()));
        for(gFile file : gDirectory.getFiles()) {
            System.out.printf("File obj in /%s: %s%n", gDirectory.getPath(), file.getFullPath());
        }
    }
}
