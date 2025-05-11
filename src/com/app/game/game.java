package com.app.game;

import java.util.Arrays;
import com.app.engine.settings;
import com.app.engine.utilsImpl;
import com.app.engine.cameraImpl;
import com.app.engine.fileMgrImpl;
import com.app.engine.spritesImpl;
import com.app.engine.keyboardImpl;
import com.app.engine.schedulerImpl;
import com.app.engine.doableImpl;

public class game {
    public static String[] launchArgs;
    static final utilsImpl utils = new utilsImpl();
    static final cameraImpl cam = new cameraImpl();
    static final fileMgrImpl files = new fileMgrImpl();
    static final spritesImpl sprites = new spritesImpl();
    static final keyboardImpl keyboard = new keyboardImpl();
    static final schedulerImpl scheduler = new schedulerImpl();
    static final int roundToTestVal = 36;
    static final int roundToTestNearest = 30;

    public static void main(String[] args) {
        launchArgs = args;

        long currentTimeMillis = System.currentTimeMillis();
        scheduler.putEvent(currentTimeMillis, new doableImpl() {
            public void doCommand() {
                System.out.print("\nDid a scheduled event");
            }
        });
        scheduler.putEvent(currentTimeMillis + 5000, new doableImpl() {
            public void doCommand() {
                System.out.print("\nWe should not see this");
            }
        });

        System.out.printf("\nStarted Game w/ scale %d, args: %s", settings.nativeScale, Arrays.toString(launchArgs));
        System.out.printf("\nRounding %d to nearest %d: %d", roundToTestVal, roundToTestNearest, utils.roundToNearest(roundToTestVal, roundToTestNearest));
        System.out.printf("\nCam coords: %s", Arrays.toString(cam.getCoords()));
        System.out.printf("\nFiles in /data: %s", Arrays.toString(files.getFilesInDirectory("data")));
        System.out.printf("\nSprite for 'none': %s", sprites.getScaledImage("none", 0, 0));
        System.out.printf("\nKeyboard code for key a: %d", keyboard.getCodeForKey("a"));
        scheduler.doEvents(System.currentTimeMillis());

        System.out.println();

    }
}