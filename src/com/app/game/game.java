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
import com.app.engine.dictImpl;
import com.app.engine.cmdImpl;
import com.app.engine.consoleImpl;

public class game {
    static final utilsImpl utils = new utilsImpl();
    static final cameraImpl camera = new cameraImpl();
    static final fileMgrImpl files = new fileMgrImpl();
    static final consoleImpl console = new consoleImpl();
    static final spritesImpl sprites = new spritesImpl();
    static final keyboardImpl keyboard = new keyboardImpl();
    static final schedulerImpl scheduler = new schedulerImpl();
    static int roundToTestVal = 36;
    static int roundToTestNearest = 30;

    public static void main(String[] args) {
        long currentTimeMillis = System.currentTimeMillis();

        console.registerCmd("echo", new cmdImpl(){
            @Override
            public String doCmd(String[] args) {
                StringBuilder echoStr = new StringBuilder();
                for(String tok : args) {
                    echoStr.append(tok).append(" ");
                }
                System.out.printf("\n%s", echoStr);
                return echoStr.toString();
            }
        });

        cVarImpl roundToValVar = new cVarImpl("round_to", Integer.toString(roundToTestVal)) {
            public void onUpdate() {
                roundToTestVal = Integer.parseInt(this.getValue());
                System.out.printf("\nSet cvar %s value to: %s", this.getKey(), this.getValue());
                System.out.printf(
                        "\nRounding %s to nearest %d: %d",
                        this.getValue(),
                        roundToTestNearest,
                        utils.roundToNearest(roundToTestVal, roundToTestNearest)
                );
            }
        };

        dictImpl testDict = new dictImpl("{foo=bar,bar=");

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

        String startString = String.format(
                "\nStarted Game w/ scale %d, args: %s",
                settings.nativeScale,
                Arrays.toString(args)
        );
        System.out.print(startString);
        System.out.printf(
                "\nRounding %d to nearest %d: %d",
                roundToTestVal,
                roundToTestNearest,
                utils.roundToNearest(roundToTestVal, roundToTestNearest)
        );
        System.out.printf("\nCam coords: %s", Arrays.toString(camera.getCoords()));
        System.out.printf("\nFiles in /data: %s", Arrays.toString(files.getFilesInDirectory("data")));
        System.out.printf("\nSprite for 'none': %s", sprites.getScaledImage("none", 0, 0));
        System.out.printf("\nKeyboard code for key a: %d", keyboard.getCodeForKey("a"));
        scheduler.doEvents(System.currentTimeMillis()); // prints stuff
        roundToValVar.setValue("62");
        System.out.printf(
                "\nTest state: %s. (keys: %s) (foo value: %s, bar value:%s)",
                testDict,
                testDict.keys(),
                testDict.get("foo"),
                testDict.get("bar")
        );
        console.readLine("echo I am echoing something from the console!");
        System.out.println();
    }
}