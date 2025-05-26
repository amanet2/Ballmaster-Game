package com.app.game;

import com.app.engine.cVarSystem.gCVar;
import com.app.engine.cVarSystem.gCVarSystem;
import com.app.engine.engine;

import java.util.Arrays;

public class gameCVars {
    // TODO: much of this functionality can be moved to engine
    // TODO: e.g. parseLaunchArgs can become parseFromArray, setCVar can go in engine and still sys.print errors, etc
    private static engine engineInstance = engine.instance();
    private static final gCVarSystem cVars = engineInstance.gCVarSystem;

    public static gCVarSystem instance() {
        return cVars;
    }

    public static void init() {
        // TODO: register game-specific cvars here
        // TODO: set up relevant cvars to work at engine level
        gCVar cVarCamXY = new gCVar(Arrays.toString(gameCamera.camera1.getCoords())) {
            @Override
            public void onChange() {
                String[] args = this.getValue().split(",");
                gameCamera.camera1.setCoords(new double[]{Double.parseDouble(args[0]), Double.parseDouble(args[1])});
            }
        };

        gCVar cVarCamZoon = new gCVar(Double.toString(gameCamera.camera1.getZoom())) {
            @Override
            public void onChange() {
                gameCamera.camera1.setZoom(Double.parseDouble(this.getValue()));
            }
        };

        gCVar cVarShowCamInfo = new gCVar(gameSettings.showCameraInfo ? "1" : "0") {
            @Override
            public void onChange() {
                gameSettings.showCameraInfo = this.getValue().equalsIgnoreCase("1");
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

        cVars.registerCVar("cam_xy", cVarCamXY);
        cVars.registerCVar("cam_zoom", cVarCamZoon);
        cVars.registerCVar("com_showcamerainfo", cVarShowCamInfo);
        cVars.registerCVar("com_showframeinfo", cVarShowFrameInfo);
        cVars.registerCVar("com_showtimeelapsed", cVarComShowTimeElapsed);
        cVars.registerCVar("fs_spritespath", cVarFsSpriteFilesPath);
        cVars.registerCVar("fs_cfgpath", cVarFsCfgFilesPath);

        System.out.println("----------------");
        System.out.println("CVAR SYSTEM INITIALIZED");
        System.out.println(cVars.toDict());
        System.out.println("----------------");
    }
}
