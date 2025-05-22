package com.app.game;

import com.app.engine.schedulerSystem;
import com.app.engine.engine;

public class gameScheduler {
    private static engine engineInstance = engine.instance();

    private static final schedulerSystem.gSchedulerSystem scheduler = engineInstance.schedulerSystem. new gSchedulerSystem();

    public static schedulerSystem.gSchedulerSystem get() {
        return scheduler;
    }

    static void init() {
        final long eventTime = System.currentTimeMillis();

        schedulerSystem.gSchedulerEvent event1 = engineInstance.schedulerSystem. new gSchedulerEvent(){
            public void doEvent() {
                System.out.println("----------------");
                System.out.printf("Did an event scheduled for %d @ %d%n", eventTime, System.currentTimeMillis());
                gameCVars.get().setCVarValue("test_cvar", "bar");
            }
        };
        schedulerSystem.gSchedulerEvent event2 = engineInstance.schedulerSystem. new gSchedulerEvent(){
            public void doEvent() {
                System.out.println("----------------");
                System.out.printf("Did another event scheduled for %d @ %d%n", eventTime, System.currentTimeMillis());
                gameCVars.get().setCVarValue("test_cvar", "foo");
            }
        };
        schedulerSystem.gSchedulerEvent event3 = engineInstance.schedulerSystem. new gSchedulerEvent(){
            public void doEvent() {
                System.out.println("----------------");
                System.out.printf("Did an event scheduled for %d @ %d%n", eventTime + 5000, System.currentTimeMillis());
                gameCVars.get().setCVarValue("test_cvar", "bar");
                gameConsole.get().readLine("echo Penultimate Scheduled Event Just Finished!");
            }
        };
        schedulerSystem.gSchedulerEvent event4 = engineInstance.schedulerSystem. new gSchedulerEvent(){
            public void doEvent() {
                System.out.println("----------------");
                System.out.printf("Did an event scheduled for %d @ %d%n", eventTime + 10000, System.currentTimeMillis());
                gameCVars.get().setCVarValue("test_cvar", "bar");
                String result = gameConsole.get().readLine("add 2 2");
                gameConsole.get().readLine("echo Last Scheduled Event Just Finished! 2 + 2 is... " + result + "!");
            }
        };
        scheduler.addEvent(eventTime, event1);
        scheduler.addEvent(eventTime, event2);
        scheduler.addEvent(eventTime + 5000, event3);
        scheduler.addEvent(eventTime + 10000, event4);
    }
}
