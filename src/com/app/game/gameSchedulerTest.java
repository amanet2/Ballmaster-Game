package com.app.game;

import com.app.engine.schedulerSystem;
import com.app.engine.schedulerSystem.gSchedulerEvent;
import com.app.engine.schedulerSystem.gSchedulerSystem;

public class gameSchedulerTest {
    static schedulerSystem schedulerSystem = new schedulerSystem();
    static gSchedulerSystem gSchedulerSystem = schedulerSystem. new gSchedulerSystem();

    public static void test() {
        long currentTimeMillis = System.currentTimeMillis();

        gSchedulerEvent event1 = schedulerSystem. new gSchedulerEvent(){
            public void doEvent() {
                System.out.printf("Did an event scheduled for %d @ %d%n", currentTimeMillis, System.currentTimeMillis());
            }
        };

        gSchedulerEvent event2 = schedulerSystem. new gSchedulerEvent(){
            public void doEvent() {
                System.out.printf("Did another event scheduled for %d @ %d%n", currentTimeMillis, System.currentTimeMillis());
            }
        };

        gSchedulerEvent event3 = schedulerSystem. new gSchedulerEvent(){
            public void doEvent() {
                System.out.println("We should not see this scheduled event happen.");
            }
        };

        gSchedulerSystem.addEvent(currentTimeMillis, event1);
        gSchedulerSystem.addEvent(currentTimeMillis, event2);
        gSchedulerSystem.addEvent(currentTimeMillis + 5000, event3);

        gSchedulerSystem.doEvents(System.currentTimeMillis());
    }
}
