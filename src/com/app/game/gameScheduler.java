package com.app.game;

import com.app.engine.schedulerSystem.gSchedulerSystem;
import com.app.engine.schedulerSystem.gSchedulerEvent;
import com.app.engine.engine;

public class gameScheduler {

    private static final gSchedulerSystem scheduler = engine.instance().gSchedulerSystem;

    public static gSchedulerSystem get() {
        return scheduler;
    }

    static void init() {
        scheduler.addEvent(System.currentTimeMillis() + 30000, new gSchedulerEvent(){
            gSchedulerEvent infiniteEvent = this;
            public void doEvent() {
                System.out.print("echo 30 seconds elapsed.\n% ");
                scheduler.addEvent(
                        System.currentTimeMillis() + 30000,
                        new gSchedulerEvent(){
                            public void doEvent() {
                                System.out.print("echo 30 seconds elapsed.\n% ");
                                scheduler.addEvent(System.currentTimeMillis() + 30000, infiniteEvent);
                            }
                        }
                );
            }
        });
    }
}
