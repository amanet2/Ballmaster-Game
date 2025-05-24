package com.app.game;

import com.app.engine.cVarSystem.gCVar;
import com.app.engine.cVarSystem.gCVarSystem;
import com.app.engine.engine;
import com.app.engine.utils.gDict;

import java.util.Arrays;
import java.util.TreeSet;

public class gameCVars {
    private static engine engineInstance = engine.instance();
    private static final gCVarSystem cVars = engineInstance.gCVarSystem;

    public static void parseLaunchArgs(String[] args) {
        for(int i = 0; i < args.length; i++) {
            System.out.println("LAUNCH ARG: " + args[i]);
            if(cVars.getCVarValue(args[i]) != null && args.length > i+1) {
                System.out.println("LAUNCH ARG VALUE: " + args[i+1]);
                cVars.setCVarValue(args[i], args[i+1]);
                i+=1;
            }
        }
    }

    public static String setCVar(String name, String value) {
        if(cVars.getCVarValue(name) == null)
            return String.format("No cvar found for '%s'%n", name);
        cVars.setCVarValue(name, value);
        return String.format("Set value of cvar '%s' to '%s'%n", name, value);
    }

    public static gCVarSystem get() {
        return cVars;
    }

    public static String[] getCVarList() {
        return new TreeSet<>(cVars.keySet()).toArray(new String[0]);
    }

    public static gDict toDict() {
        gDict dict = new gDict();
        for(String k : cVars.keySet()) {
            dict.put(k, cVars.getCVarValue(k));
        }
        return dict;
    }

    public static void init() {
        cVars.registerCVar(new gCVar("cam_xy", Arrays.toString(gameCamera.getCamera1().getCoords())) {
            @Override
            public void onChange() {
                String[] args = this.getValue().split(",");
                gameCamera.getCamera1().setCoords(new double[]{Double.parseDouble(args[0]), Double.parseDouble(args[1])});
            }
        });
        cVars.registerCVar(new gCVar("cam_zoom", Double.toString(gameCamera.getCamera1().getZoom())) {
            @Override
            public void onChange() {
                gameCamera.getCamera1().setZoom(Double.parseDouble(this.getValue()));
            }
        });
        cVars.registerCVar(new gCVar("com_showcamerainfo", gameSettings.showCameraInfo ? "1" : "0") {
            @Override
            public void onChange() {
                gameSettings.showCameraInfo = this.getValue().equalsIgnoreCase("1");
            }
        });
        cVars.registerCVar(new gCVar("com_showfps", gameSettings.showFps ? "1" : "0") {
            @Override
            public void onChange() {
                gameSettings.showFps = this.getValue().equalsIgnoreCase("1");
            }
        });
        cVars.registerCVar(new gCVar("com_showframeinfo", gameSettings.showFrameInfo ? "1" : "0") {
            @Override
            public void onChange() {
                gameSettings.showFrameInfo = this.getValue().equalsIgnoreCase("1");
            }
        });
        cVars.registerCVar(new gCVar("fs_datapath", gameSettings.fileSystemSpritesPath) {
            @Override
            public void onChange() {
                gameSettings.fileSystemSpritesPath = this.getValue();
            }
        });
        cVars.registerCVar(new gCVar("fs_filespath", gameSettings.fileSystemConfigPath) {
            @Override
            public void onChange() {
                gameSettings.fileSystemConfigPath = this.getValue();
            }
        });
    }
}
