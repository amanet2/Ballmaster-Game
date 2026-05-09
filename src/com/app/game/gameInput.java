package com.app.game;

import com.app.engine.engine;
import com.app.engine.inputSystem.gInputSystem;
import com.app.engine.inputSystem.impulse;

import java.util.Scanner;

public class gameInput {
    public static gInputSystem instance() {
        return engine.instance().gInputSystem;
    }

    public static void init() {
        instance().init();

        // TODO: Java is acting like a text editor
        //  e.g. holding RIGHT, hold LEFT = STOP, THEN START TO MOVE LEFT!!!

        // UP
        instance().bind(38, new impulse() {
            public void keyPressed() {
                double[] vec = gameCamera.gameCamera.getVec();
                gameCamera.gameCamera.setVec(new double[]{ vec[0], Math.max(-0.2, vec[1] - 0.2) });
            }

            public void keyReleased() {
                double[] vec = gameCamera.gameCamera.getVec();
                gameCamera.gameCamera.setVec(new double[]{ vec[0], Math.min(0.0, vec[1] + 0.2) });
            }
        });
        // DOWN
        instance().bind(40, new impulse() {
            public void keyPressed() {
                double[] vec = gameCamera.gameCamera.getVec();
                gameCamera.gameCamera.setVec(new double[]{ vec[0], Math.min(0.2, vec[1] + 0.2) });
            }

            public void keyReleased() {
                double[] vec = gameCamera.gameCamera.getVec();
                gameCamera.gameCamera.setVec(new double[]{ vec[0], Math.max(0.0, vec[1] - 0.2) });
            }
        });

        // LEFT
        instance().bind(37, new impulse() {
            public void keyPressed() {
                double[] vec = gameCamera.gameCamera.getVec();
                gameCamera.gameCamera.setVec(new double[]{ Math.max(-0.2, vec[0] - 0.2), vec[1] });
            }

            public void keyReleased() {
                double[] vec = gameCamera.gameCamera.getVec();
                gameCamera.gameCamera.setVec(new double[]{ Math.min(0.0, vec[0] + 0.2), vec[1] });
            }
        });
        // RIGHT
        instance().bind(39, new impulse() {
            public void keyPressed() {
                double[] vec = gameCamera.gameCamera.getVec();
                gameCamera.gameCamera.setVec(new double[]{ Math.min(0.2, vec[0] + 0.2), vec[1] });
            }

            public void keyReleased() {
                double[] vec = gameCamera.gameCamera.getVec();
                gameCamera.gameCamera.setVec(new double[]{ Math.max(0.0, vec[0] - 0.2), vec[1] });
            }
        });

        gameGraphics.instance().getCanvas().addKeyListener(instance().getKeyboard());


        // Thread to listen to terminal window inputs
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while(true) {
                System.out.print("% ");
                String input = scanner.nextLine();
                String output = gameConsole.instance().readLine(input);
                System.out.print(output + (!output.isEmpty() ? "\n" : ""));
            }
        }).start();
    }
}
