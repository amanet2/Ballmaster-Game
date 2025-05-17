package com.app.game;

import com.app.engine.cVarSystem;
import com.app.engine.cVarSystem.gCVar;
import com.app.engine.cVarSystem.gCVarSystem;

public class gameCVarTest {
    static cVarSystem cVarSystem = new cVarSystem();  // TODO: IDE rec -> extract method?

    public static void test() {
        gCVarSystem gCVarSystem = cVarSystem. new gCVarSystem();
        gCVar testCVar = cVarSystem. new gCVar("test_cvar", "foo") {
            @Override
            public void onUpdate() {
                System.out.println("test_cvar value was updated!");
            }

            @Override
            public void onChange() {
                System.out.println("test_cvar value was changed!");
            }
        };
        gCVarSystem.registerCVar(testCVar);

        System.out.printf("test_cvar value: %s%n", gCVarSystem.getCVarValue("test_cvar"));
        gCVarSystem.setCVarValue("test_cvar", "bar");
        System.out.printf("test_cvar value: %s%n", gCVarSystem.getCVarValue("test_cvar"));
        gCVarSystem.setCVarValue("test_cvar", "bar");
        System.out.printf("test_cvar value: %s%n", gCVarSystem.getCVarValue("test_cvar"));
    }
}
