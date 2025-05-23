package com.app.game;

import com.app.engine.cVarSystem;
import com.app.engine.engine;

public class gameCVars {
    private static engine engineInstance = engine.instance();

    private static final cVarSystem.gCVarSystem cVars = engineInstance.cVarSystem. new gCVarSystem();

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
            return String.format("No cvar found for '%s'", name);
        cVars.setCVarValue(name, value);
        return String.format("Set value of cvar '%s' to '%s'", name, value);
    }

    public static cVarSystem.gCVarSystem get() {
        return cVars;
    }

    public static void init() {
        cVars.registerCVar(engineInstance.cVarSystem. new gCVar("basepath", gameSettings.dataPath) {
            @Override
            public void onChange() {
                gameSettings.dataPath = this.getValue();
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
