package com.app.game;

import com.app.engine.cVarSystem.gCVar;
import com.app.engine.cVarSystem.gCVarSystem;
import com.app.engine.engine;
import com.app.engine.settings;
import com.app.engine.utils.gDict;

import java.util.Arrays;
import java.util.TreeSet;

public class gameCVars {
    private static final gCVarSystem cVars = engine.instance().gCVarSystem;

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
        gCVar cVarCamXY = new gCVar(Arrays.toString(gameCamera.getCamera1().getCoords())) {
            @Override
            public void onChange() {
                String[] args = this.getValue().split(",");
                gameCamera.getCamera1().setCoords(new double[]{Double.parseDouble(args[0]), Double.parseDouble(args[1])});
            }
        };

        gCVar cVarCamZoon = new gCVar(Double.toString(gameCamera.getCamera1().getZoom())) {
            @Override
            public void onChange() {
                gameCamera.getCamera1().setZoom(Double.parseDouble(this.getValue()));
            }
        };

        gCVar cVarShowCamInfo = new gCVar(gameSettings.showCameraInfo ? "1" : "0") {
            @Override
            public void onChange() {
                gameSettings.showCameraInfo = this.getValue().equalsIgnoreCase("1");
            }
        };

        gCVar cVarShowFps = new gCVar(settings.showMetricsVideo ? "1" : "0") {
            @Override
            public void onChange() {
                settings.showMetricsVideo = this.getValue().equalsIgnoreCase("1");
            }
        };

        gCVar cVarShowFrameInfo = new gCVar(gameSettings.showFrameInfo ? "1" : "0") {
            @Override
            public void onChange() {
                gameSettings.showFrameInfo = this.getValue().equalsIgnoreCase("1");
            }
        };

        gCVar cVarComShowTimeElapsed = new gCVar(gameSettings.showTimeElapsed ? " 1" : "0") {
            @Override
            public void onChange() {
                gameSettings.showTimeElapsed =  this.getValue().equalsIgnoreCase("1");
            }
        };

        gCVar cVarFsSpriteFilesPath = new gCVar(gameSettings.fileSystemSpritesPath) {
            @Override
            public void onChange() {
                gameSettings.fileSystemSpritesPath = this.getValue();
            }
        };

        gCVar cVarFsCfgFilesPath = new gCVar(gameSettings.fileSystemConfigPath) {
            @Override
            public void onChange() {
                gameSettings.fileSystemConfigPath = this.getValue();
            }
        };

        gCVar cVarRCustomHeight = new gCVar(Integer.toString(gameSettings.screenHeight)) {
            @Override
            public void onChange() {
                gameSettings.screenHeight = Integer.parseInt(this.getValue());
            }
        };

        gCVar cVarRCustomWidth = new gCVar(Integer.toString(gameSettings.screenWidth)) {
            @Override
            public void onChange() {
                gameSettings.screenWidth = Integer.parseInt(this.getValue());
            }
        };

        cVars.registerCVar("cam_xy", cVarCamXY);
        cVars.registerCVar("cam_zoom", cVarCamZoon);
        cVars.registerCVar("com_showcamerainfo", cVarShowCamInfo);
        cVars.registerCVar("com_showfps", cVarShowFps);
        cVars.registerCVar("com_showframeinfo", cVarShowFrameInfo);
        cVars.registerCVar("com_showtimeelapsed", cVarComShowTimeElapsed);
        cVars.registerCVar("fs_spritespath", cVarFsSpriteFilesPath);
        cVars.registerCVar("fs_cfgpath", cVarFsCfgFilesPath);
        cVars.registerCVar("r_customHeight", cVarRCustomHeight);
        cVars.registerCVar("r_customWidth", cVarRCustomWidth);

        System.out.println("----------------");
        System.out.println("CREATED CVARMAP: " + toDict());
//        String testDictString = "{}";
//        String testDictString = "{foo={}}";
//        String testDictString = "{foo={}, bar={baz=qaz}}";
        String testDictString = "{foo=bar, baz={foo=bar^} bzy, qaz={yaz=^pzaz}}, zaz={abz=bzaz}}";
        gDict testDict = new gDict(testDictString);
        System.out.println("TEST DICT STRING: " + testDictString);
        System.out.println("TEST DICT FROM STRING: " + testDict);
        System.out.println("TEST DICT GET(baz.foo): " + ((gDict) testDict.get("baz")).get("foo"));
        System.out.println("----------------");
    }
}
