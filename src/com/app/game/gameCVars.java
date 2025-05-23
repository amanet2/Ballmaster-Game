package com.app.game;

import com.app.engine.cVarSystem;
import com.app.engine.engine;

public class gameCVars {
    private static engine engineInstance = engine.instance();

    private static final cVarSystem.gCVarSystem cVars = engineInstance.cVarSystem. new gCVarSystem();

    public static cVarSystem.gCVarSystem get() {
        return cVars;
    }

    public static void init() {
        cVars.registerCVar(engineInstance.cVarSystem. new gCVar("basepath", gameSettings.basePath) {
            @Override
            public void onChange() {
                gameSettings.basePath = this.getValue();
            }
        });
        cVars.registerCVar(engineInstance.cVarSystem. new gCVar("showfps", gameSettings.showfps ? "1" : "0") {
            @Override
            public void onChange() {
                gameSettings.showfps = this.getValue().equalsIgnoreCase("1");
            }
        });
        cVars.registerCVar(engineInstance.cVarSystem. new gCVar("showdebug", gameSettings.showdebug ? "1" : "0") {
            @Override
            public void onChange() {
                gameSettings.showdebug = this.getValue().equalsIgnoreCase("1");
            }
        });
    }
}
