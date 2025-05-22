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
        String testCVarName = "test_cvar";
        cVarSystem.gCVar testCVar = engineInstance.cVarSystem. new gCVar(testCVarName, "foo") {
            @Override
            public void onUpdate() {
                System.out.println(testCVarName + " value was updated!");
            }
            @Override
            public void onChange() {
                System.out.println(testCVarName + " value was changed!");
            }
        };
        cVars.registerCVar(testCVar);
        System.out.println("set up cvars!");
    }
}
