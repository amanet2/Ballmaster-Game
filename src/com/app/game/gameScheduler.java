package com.app.game;

import java.awt.Graphics;

import com.app.engine.schedulerSystem.gSchedulerSystem;
import com.app.engine.eventSystem.gEvent;
import com.app.engine.eventSystem.gEventGraphics;
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

        gEventGraphics eventGraphics = new gEventGraphics(){
            gEventGraphics infiniteEvent = this;
            public void doEvent(Graphics g) {
                g.drawString("10 seconds elapsed.", 320,240);
                scheduler.addEventGraphics(
                        System.currentTimeMillis() + 10000,
                        new gEventGraphics(){
                            public void doEvent(Graphics g) {
                                g.drawString("10 seconds elapsed.", 320,240);
                                scheduler.addEventGraphics(System.currentTimeMillis() + 10000, infiniteEvent, 5000);
                            }
                        },
                        5000
                );
            }
        };
        scheduler.addEventGraphics(System.currentTimeMillis() + 10000, eventGraphics, 5000);
    }
}
