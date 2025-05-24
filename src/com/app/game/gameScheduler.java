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
//        scheduler.addEvent(System.currentTimeMillis() + 5000, engineInstance.schedulerSystem. new gSchedulerEvent(){
//            gSchedulerEvent grandFatherEvent = this;
//            public void doEvent() {
//                gameCamera.getCamera1().snapToWorldCoords(new int[]{-75, -75});
//                gameCamera.getCamera1().setZoom(2.0);
//                scheduler.addEvent(
//                        System.currentTimeMillis() + 5000,
//                        engineInstance.schedulerSystem. new gSchedulerEvent(){
//                            gSchedulerEvent parentEvent = this;
//                            public void doEvent() {
//                                gameCamera.getCamera1().snapToWorldCoords(new int[]{0, 0});
//                                gameCamera.getCamera1().setZoom(1.0);
//                                scheduler.addEvent(System.currentTimeMillis() + 5000, grandFatherEvent);
//                            }
//                        }
//                );
//            }
//        });
    }
}
