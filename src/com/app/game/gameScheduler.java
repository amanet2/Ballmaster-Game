package com.app.game;

import com.app.engine.schedulerSystem.gSchedulerEvent;
import com.app.engine.schedulerSystem.gSchedulerSystem;
import com.app.engine.engine;

public class gameScheduler {
    private static engine engineInstance = engine.instance();

    private static final gSchedulerSystem scheduler = engineInstance.schedulerSystem. new gSchedulerSystem();

    public static gSchedulerSystem get() {
        return scheduler;
    }

    static void init() {
        gSchedulerEvent infiniteEvent = engineInstance.schedulerSystem. new gSchedulerEvent(){
            gSchedulerEvent parentEvent = this;
            public void doEvent() {
                gameCamera.getCamera1().snapToWorldCoords(new int[]{-75, -75});
                scheduler.addEvent(
                    System.currentTimeMillis() + 5000,
                    engineInstance.schedulerSystem. new gSchedulerEvent(){
                        public void doEvent() {
                            gameCamera.getCamera1().snapToWorldCoords(new int[]{0, 0});
                            scheduler.addEvent(System.currentTimeMillis() + 5000, parentEvent);
                        }
                    }
                );
            }
        };

        scheduler.addEvent(System.currentTimeMillis() + 5000, infiniteEvent);
    }
}
