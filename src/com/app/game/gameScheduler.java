package com.app.game;

import com.app.engine.schedulerSystem.gSchedulerSystem;
import com.app.engine.event;
import com.app.engine.engine;

public class gameScheduler {
    private static final gSchedulerSystem scheduler = engine.instance().gSchedulerSystem;

    public static gSchedulerSystem instance() {
        return scheduler;
    }

    static void init() {
        // DO NOT REMOVE: a template for an infinitely-recurring event
        scheduler.addEvent(System.currentTimeMillis() + 30000, new event(){
            event infiniteEvent = this;
            public void doEvent() {
                System.out.print("echo 30 seconds elapsed.\n% ");
                scheduler.addEvent(
                        System.currentTimeMillis() + 30000,
                        new event(){
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
