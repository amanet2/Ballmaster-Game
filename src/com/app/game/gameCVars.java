package com.app.game;

import com.app.engine.cVarSystem.gCVar;
import com.app.engine.cVarSystem.gCVarSystem;
import com.app.engine.engine;

import java.util.Arrays;

public class gameCVars {
    public static gCVarSystem instance() {
        return engine.instance().gCVarSystem;
    }

    static boolean showTimeElapsed = false;
    static boolean showCameraInfo = false;
    static boolean showFrameInfo = false;
    static boolean showVideoInfo = false;

    static long jumpDelay = 500;

    static gCVar cVarCamXY = new gCVar(Arrays.toString(gameCamera.gameCamera.getCoords())) {
        @Override
        public void onChange() {
            String[] args = this.getValue().split(",");
            gameCamera.gameCamera.setCoords(new double[]{Double.parseDouble(args[0]), Double.parseDouble(args[1])});
        }
    };

    static gCVar cVarCamZoom = new gCVar(Double.toString(gameCamera.gameCamera.getZoom())) {
        @Override
        public void onChange() {
            gameCamera.gameCamera.setZoom(Double.parseDouble(this.getValue()));
        }
    };

    static gCVar cVarShowCamInfo = new gCVar(showCameraInfo ? "1" : "0") {
        @Override
        public void onChange() {
            showCameraInfo = this.getValue().equalsIgnoreCase("1");
        }
    };

    static gCVar cVarShowFrameInfo = new gCVar(showFrameInfo ? "1" : "0") {
        @Override
        public void onChange() {
            showFrameInfo = this.getValue().equalsIgnoreCase("1");
        }
    };

    static gCVar cVarShowVideoInfo = new gCVar(showVideoInfo ? "1" : "0") {
        @Override
        public void onChange() {
            showVideoInfo = this.getValue().equalsIgnoreCase("1");
        }
    };

    static gCVar cVarComShowTimeElapsed = new gCVar(showTimeElapsed ? " 1" : "0") {
        @Override
        public void onChange() {
            showTimeElapsed =  this.getValue().equalsIgnoreCase("1");
        }
    };

    static gCVar cVarCamSpeed = new gCVar(Double.toString(gameState.cameraSpeed)) {
        @Override
        public void onChange() {
            gameState.cameraSpeed =  Double.parseDouble(this.getValue());
        }
    };

    static gCVar cVarPlayerSpeed = new gCVar(Double.toString(gameState.playerSpeed)) {
        @Override
        public void onChange() {
            gameState.playerSpeed =  Double.parseDouble(this.getValue());
        }
    };

    static gCVar cVarGameRate = new gCVar(Double.toString(gameState.gameRate)) {
        @Override
        public void onChange() {
            gameState.gameRate =  Double.parseDouble(this.getValue());
        }
    };

    static gCVar cVarGravity = new gCVar(Double.toString(gameState.gravity)) {
        @Override
        public void onChange() {
            gameState.gravity =  Double.parseDouble(this.getValue());
        }
    };

    static gCVar cVarJumpDelay = new gCVar(Long.toString(jumpDelay)) {
        @Override
        public void onChange() {
            jumpDelay =  Long.parseLong(this.getValue());
        }
    };

    public static void init() {
        instance().registerCVar("com_showframeinfo", cVarShowFrameInfo);
        instance().registerCVar("com_showvideoinfo", cVarShowVideoInfo);
        instance().registerCVar("com_showtimeelapsed", cVarComShowTimeElapsed);
        instance().registerCVar("g_showcamerainfo", cVarShowCamInfo);
        instance().registerCVar("g_camXY", cVarCamXY);
        instance().registerCVar("g_camZoom", cVarCamZoom);
        instance().registerCVar("g_camSpeed", cVarCamSpeed);
        instance().registerCVar("g_playerSpeed", cVarPlayerSpeed);
        instance().registerCVar("g_gameRate", cVarGameRate);
        instance().registerCVar("g_gravity", cVarGravity);
        instance().registerCVar("g_jumpDelay", cVarJumpDelay);
    }
}
