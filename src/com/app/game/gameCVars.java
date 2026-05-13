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

    static double worldTickRate = 1000;

    static double playerMaxSpeed = 4.0;
    static long playerJumpDelay = 500;
    static double playerJumpForce = 72.0;

    static double cameraMaxSpeed = 0.2;

    static double worldGravity = 0.3;

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

    static gCVar cVarCamSpeed = new gCVar(Double.toString(cameraMaxSpeed)) {
        @Override
        public void onChange() {
            cameraMaxSpeed =  Double.parseDouble(this.getValue());
        }
    };

    static gCVar cVarPlayerSpeed = new gCVar(Double.toString(playerMaxSpeed)) {
        @Override
        public void onChange() {
            playerMaxSpeed =  Double.parseDouble(this.getValue());
        }
    };

    static gCVar cVarGameRate = new gCVar(Double.toString(worldTickRate)) {
        @Override
        public void onChange() {
            worldTickRate =  Double.parseDouble(this.getValue());
        }
    };

    static gCVar cVarGravity = new gCVar(Double.toString(worldGravity)) {
        @Override
        public void onChange() {
            worldGravity =  Double.parseDouble(this.getValue());
        }
    };

    static gCVar cVarJumpDelay = new gCVar(Long.toString(playerJumpDelay)) {
        @Override
        public void onChange() {
            playerJumpDelay =  Long.parseLong(this.getValue());
        }
    };

    static gCVar cVarPlayerJumpForce = new gCVar(Double.toString(playerJumpForce)) {
        @Override
        public void onChange() {
            playerJumpForce =  Double.parseDouble(this.getValue());
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
        instance().registerCVar("g_jumpForce", cVarPlayerJumpForce);
    }
}
