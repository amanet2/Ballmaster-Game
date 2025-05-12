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
import com.app.engine.cVarImpl;

public class game {
    static final utilsImpl utils = new utilsImpl();
    static final cameraImpl camera = new cameraImpl();
    static final fileMgrImpl files = new fileMgrImpl();
    static final spritesImpl sprites = new spritesImpl();
    static final keyboardImpl keyboard = new keyboardImpl();
    static final schedulerImpl scheduler = new schedulerImpl();
    static int roundToTestVal = 36;
    static int roundToTestNearest = 30;

    public static void main(String[] args) {
        long currentTimeMillis = System.currentTimeMillis();

        cVarImpl roundToValVar = new cVarImpl("round_to", Integer.toString(roundToTestVal)) {
            public void onUpdate() {
                roundToTestVal = Integer.parseInt(this.getValue());
                System.out.printf("\nRounding %s to nearest %d: %d", this.getValue(), roundToTestNearest, utils.roundToNearest(roundToTestVal, roundToTestNearest));
            }
        };

        scheduler.putEvent(currentTimeMillis, new doableImpl() {
            public void doCommand() {
                System.out.print("\nDid a scheduled event");
            }
        });
        scheduler.putEvent(currentTimeMillis, new doableImpl() {
            public void doCommand() {
                System.out.print("\nDid another scheduled event");
            }
        });
        scheduler.putEvent(currentTimeMillis + 5000, new doableImpl() {
            public void doCommand() {
                System.out.print("\nWe should not see this");
            }
        });

        String startString = String.format("\nStarted Game w/ scale %d, args: %s", settings.nativeScale, Arrays.toString(args));
        System.out.print(startString);
        System.out.printf("\nRounding %d to nearest %d: %d", roundToTestVal, roundToTestNearest, utils.roundToNearest(roundToTestVal, roundToTestNearest));
        System.out.printf("\nCam coords: %s", Arrays.toString(camera.getCoords()));
        System.out.printf("\nFiles in /data: %s", Arrays.toString(files.getFilesInDirectory("data")));
        System.out.printf("\nSprite for 'none': %s", sprites.getScaledImage("none", 0, 0));
        System.out.printf("\nKeyboard code for key a: %d", keyboard.getCodeForKey("a"));
        scheduler.doEvents(System.currentTimeMillis()); // prints stuff
        roundToValVar.setValue("62");
        System.out.println();

    }
}