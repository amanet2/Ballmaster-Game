package com.app.game;

import java.awt.*;

import com.app.engine.schedulerSystem.gSchedulerSystem;
import com.app.engine.eventSystem.gEvent;
import com.app.engine.eventSystem.gEventGraphics;
import com.app.engine.engine;

public class gameScheduler {
    // TODO: multiple schedulers, one for world time events, one for game (internal) time events
    // TODO: timeScale, advance X game ms for every Y real ms
    //  Example: game scheduler, play time tracking for saved game, weapon cooldowns, ai behaviors
    //  Example: real-world scheduler, timed multiplayer matches, ui effects
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

        // WARNING, calling infinite doEvent() every render loop is adding graphics events in the 1000s
        scheduler.addEventGraphics(
                System.currentTimeMillis() + 5000,
                new gEventGraphics(){
                    public void doEvent(Graphics g) {
                        g.setColor(Color.YELLOW);
                        g.drawString("5 seconds elapsed.", 320, 240);
                    }
                },
                3000
        );
    }
}
