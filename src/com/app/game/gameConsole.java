package com.app.game;

import com.app.engine.consoleSystem.gConsoleCommand;
import com.app.engine.consoleSystem.gConsoleSystem;
import com.app.engine.engine;

import java.awt.event.KeyEvent;

public class gameConsole {
    public static gConsoleSystem instance() {
        return engine.instance().gConsoleSystem;
    }

    static gConsoleCommand gConsoleCommandUseCamera = new gConsoleCommand("change active camera") {
        @Override
        public String doCommand(String[] args) {
            if(args.length < 1 || args[0].trim().isEmpty())
                return "Usage: useCam NUMBER";
            int index = Integer.parseInt(args[0]);
            if(index < 0 || gameCamera.gameCameras.length < index)
                return "camera does not exist";
            gameCamera.gameCamera = gameCamera.gameCameras[index];
            return "using camera %d".formatted(index);
        }
    };

    public static void init() {
        instance().registerCmd("useCam", gConsoleCommandUseCamera);

        gameInput.instance().setBind(KeyEvent.VK_UP, gameImpulses.impulseCameraUp);
        gameInput.instance().setBind(KeyEvent.VK_DOWN, gameImpulses.impulseCameraDown);
        gameInput.instance().setBind(KeyEvent.VK_LEFT, gameImpulses.impulseCameraLeft);
        gameInput.instance().setBind(KeyEvent.VK_RIGHT, gameImpulses.impulseCameraRight);

        gameInput.instance().setBind(KeyEvent.VK_W, gameImpulses.impulsePlayerUp);
        gameInput.instance().setBind(KeyEvent.VK_S, gameImpulses.impulsePlayerDown);
        gameInput.instance().setBind(KeyEvent.VK_A, gameImpulses.impulsePlayerLeft);
        gameInput.instance().setBind(KeyEvent.VK_D, gameImpulses.impulsePlayerRight);

        gameInput.instance().setBind(KeyEvent.VK_SPACE, gameImpulses.impulsePlayerJump);

        gameInput.instance().setBind(KeyEvent.VK_EQUALS, gameImpulses.impulseCameraZoomIn);
        gameInput.instance().setBind(KeyEvent.VK_MINUS, gameImpulses.impulseCameraZoomOut);
    }
}
