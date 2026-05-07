package com.app.game;

import com.app.engine.cVarSystem.gCVar;
import com.app.engine.cVarSystem.gCVarSystem;
import com.app.engine.engine;

import java.util.Arrays;

public class gameCVars {
    private static engine engineInstance = engine.instance();
    private static final gCVarSystem cVars = engineInstance.gCVarSystem;

    public static gCVarSystem instance() {
        return cVars;
    }

    public static void init() {
        // TODO: register game-specific cvars here
        gCVar cVarCamXY = new gCVar(Arrays.toString(gameCamera.gameCamera.getCoords())) {
            @Override
            public void onChange() {
                String[] args = this.getValue().split(",");
                gameCamera.gameCamera.setCoords(new double[]{Double.parseDouble(args[0]), Double.parseDouble(args[1])});
            }
        };

        gCVar cVarCamZoon = new gCVar(Double.toString(gameCamera.gameCamera.getZoom())) {
            @Override
            public void onChange() {
                gameCamera.gameCamera.setZoom(Double.parseDouble(this.getValue()));
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

        gCVar cVarShowVideoInfo = new gCVar(gameSettings.showVideoInfo ? "1" : "0") {
            @Override
            public void onChange() {
                gameSettings.showVideoInfo = this.getValue().equalsIgnoreCase("1");
            }
        };

        gCVar cVarComShowTimeElapsed = new gCVar(gameSettings.showTimeElapsed ? " 1" : "0") {
            @Override
            public void onChange() {
                gameSettings.showTimeElapsed =  this.getValue().equalsIgnoreCase("1");
            }
        };

        gCVar cVarFsScriptFilesPath = new gCVar(gameSettings.fileSystemScriptsPath) {
            @Override
            public void onChange() {
                gameSettings.fileSystemScriptsPath = this.getValue();
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

        cVars.registerCVar("com_showframeinfo", cVarShowFrameInfo);
        cVars.registerCVar("com_showvideoinfo", cVarShowVideoInfo);
        cVars.registerCVar("com_showtimeelapsed", cVarComShowTimeElapsed);
        cVars.registerCVar("fs_scriptspath", cVarFsScriptFilesPath);
        cVars.registerCVar("fs_spritespath", cVarFsSpriteFilesPath);
        cVars.registerCVar("fs_cfgpath", cVarFsCfgFilesPath);
        cVars.registerCVar("g_showcamerainfo", cVarShowCamInfo);
        cVars.registerCVar("g_camXY", cVarCamXY);
        cVars.registerCVar("g_camZoom", cVarCamZoon);
    }
}
