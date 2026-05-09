package com.app.game;

import com.app.engine.consoleSystem.gConsoleCommand;
import com.app.engine.consoleSystem.gConsoleSystem;
import com.app.engine.engine;

public class gameConsole {
    // TODO: need to use interfaces as headers for game files too
    // TODO: e.g. we need an interface for console to list out commands
    private static final gConsoleSystem console = engine.instance().gConsoleSystem;

    public static gConsoleSystem instance() {
        return console;
    }

    public static void init() {
        gConsoleCommand gConsoleCommandUseCamera = new gConsoleCommand("change active camera") {
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

        console.registerCmd("useCam", gConsoleCommandUseCamera);

        gameInput.instance().bind(38, gameImpulses.impulseCameraUp);
        gameInput.instance().bind(40, gameImpulses.impulseCameraDown);
        gameInput.instance().bind(37, gameImpulses.impulseCameraLeft);
        gameInput.instance().bind(39, gameImpulses.impulseCameraRight);
    }
}
