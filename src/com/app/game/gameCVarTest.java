package com.app.game;

import com.app.engine.cVarSystem;
import com.app.engine.cVarSystem.gCVar;
import com.app.engine.cVarSystem.gCVarSystem;

public class gameCVarTest {
    static final cVarSystem cVarSystem = new cVarSystem();
    static final gCVarSystem gCVarSystem = cVarSystem. new gCVarSystem();

    public void test() {
        gCVar testCVar = cVarSystem. new gCVar("test_cvar", "foo") {
            @Override
            public void onUpdate() {
                System.out.println("test_cvar value was changed!");
            }
        };
        gCVarSystem.registerCVar(testCVar);

        System.out.printf("test_cvar value: %s%n", gCVarSystem.getCVarValue("test_cvar"));
        gCVarSystem.setCVarValue("test_cvar", "bar");
        System.out.printf("test_cvar value: %s%n", gCVarSystem.getCVarValue("test_cvar"));
    }
}
