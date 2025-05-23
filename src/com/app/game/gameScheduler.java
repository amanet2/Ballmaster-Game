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
        final long initTime = System.currentTimeMillis();

        schedulerSystem.gSchedulerEvent event1 = engineInstance.schedulerSystem. new gSchedulerEvent(){
            public void doEvent() {
                gameCamera.getCamera1().snapToWorldCoords(new int[]{-75, -75});
            }
        };
        schedulerSystem.gSchedulerEvent event2 = engineInstance.schedulerSystem. new gSchedulerEvent(){
            public void doEvent() {
                gameCamera.getCamera1().snapToWorldCoords(new int[]{0, 0});
            }
        };
        scheduler.addEvent(initTime + 5000, event1);
        scheduler.addEvent(initTime + 10000, event2);
    }
}
