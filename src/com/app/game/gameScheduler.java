package com.app.game;

import com.app.engine.schedulerSystem.gSchedulerSystem;
import com.app.engine.eventSystem.gEvent;
import com.app.engine.engine;

public class gameScheduler {
    private static final gSchedulerSystem scheduler = engine.instance().gSchedulerSystem;

    public static gSchedulerSystem instance() {
        return scheduler;
    }

    static void init() {
        // DO NOT REMOVE: a template for an infinitely-recurring event
        scheduler.addEvent(System.currentTimeMillis() + 30000, new gEvent(){
            gEvent infiniteEvent = this;
            public void doEvent() {
                System.out.print("echo 30 seconds elapsed.\n% ");
                scheduler.addEvent(
                        System.currentTimeMillis() + 30000,
                        new gEvent(){
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
