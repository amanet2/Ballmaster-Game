package com.app.game;

import com.app.engine.engine;
import com.app.engine.graphicsSystem.gGraphicsSystem;
import com.app.engine.settings;
import com.app.engine.utils.gDate;
import com.app.engine.utils.gMath;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class gameGraphics {
    private static engine engineInstance = engine.instance();

    private static gGraphicsSystem graphics;

    public static gGraphicsSystem get() {
        return graphics;
    }

    private static void getGameMetrics() {
        long currentTimeMillis = System.currentTimeMillis();

        if(currentTimeMillis > gameSettings.frameMetricTimeMillis) {
            gameSettings.frameMetricTimeMillis = currentTimeMillis + 1000;

            gameSettings.gameFramesPerSecondMetricSnapshot = gameSettings.gameFramesPerSecondMetric;

            gameSettings.gameFramesPerSecondMetric = 0;

            gameSettings.gameFrametimeMetricSnapshotLowest = gameSettings.gameFrametimeMetricLowest;
            gameSettings.gameFrametimeMetricSnapshotAvg = gameSettings.gameFrametimeMetric/1000;
            gameSettings.gameFrametimeMetricSnapshotHighest = gameSettings.gameFrametimeMetricHighest;

            gameSettings.gameFrametimeMetric = 0;
            gameSettings.gameFrametimeMetricLowest = 0;
            gameSettings.gameFrametimeMetricHighest = 0;
        }
    }

    private static void transformWorldCameraAndScale(Graphics g) {
//        g.translate(graphics.getWidth() / 2, graphics.getHeight() / 2);
//        ((Graphics2D) g).scale(gameCamera.getCamera1().getZoom(), gameCamera.getCamera1().getZoom());
//        g.translate(- graphics.getWidth() / 2, - graphics.getHeight() / 2);


        double scaleFactor = gMath.scaleDoubleToWindowHeight(1.0, gameSettings.gameScale, gameSettings.screenHeight);
        ((Graphics2D) g).scale(scaleFactor, scaleFactor);

        g.translate(-(int)gameCamera.getCamera1().getCoords()[0], -(int)gameCamera.getCamera1().getCoords()[1]);

    }

    private static void resetWorldCameraAndScale(Graphics g) {
        g.translate((int)gameCamera.getCamera1().getCoords()[0], (int)gameCamera.getCamera1().getCoords()[1]);

//        g.translate(graphics.getWidth() / 2, graphics.getHeight() / 2);
//        ((Graphics2D) g).scale(1.0/gameCamera.getCamera1().getZoom(), 1.0/gameCamera.getCamera1().getZoom());

//        double scaleFactor = gMath.unscaleDoubleToWindowHeight(1.0, gameSettings.gameScale, gameSettings.screenHeight);
//        ((Graphics2D) g).scale(scaleFactor, scaleFactor);

//        g.translate(- graphics.getWidth() / 2, - graphics.getHeight() / 2);
    }

    private static void drawWorld(Graphics g) {
        int spriteId = 2;
        int spriteWorldX = 300 + (int)gameSettings.radix;
        int spriteWorldY = 0;
        if(gameSprites.gSprites.size() > spriteId)
            g.drawImage(gameSprites.gSprites.get(spriteId).getImage(), spriteWorldX, spriteWorldY,null);
    }

    private static void drawUI(Graphics g) {
        g.setColor(Color.WHITE);
        int debugInfoY = settings.showMetricsVideo ? 125 : 0;
        if(gameSettings.showTimeElapsed) {
            g.drawString("Time Elapsed: " + gDate.getTimerString(gameSettings.timeElapsedMillis), 0, debugInfoY + 25);
            debugInfoY += 25;
        }
        if(gameSettings.showFrameInfo) {
            g.drawString("Game FPS: " + gameSettings.gameFramesPerSecondMetricSnapshot, 0, debugInfoY + 25);
            g.drawString("Game Frames: " + gameSettings.gameFrames, 0, debugInfoY + 50);
            g.drawString("Game Frametime AVG: " + gameSettings.gameFrametimeMetricSnapshotAvg, 0, debugInfoY + 75);
            g.drawString("Game Frametime Lowest: " + gameSettings.gameFrametimeMetricSnapshotLowest, 0, debugInfoY + 100);
            g.drawString("Game Frametime Highest: " + gameSettings.gameFrametimeMetricSnapshotHighest, 0, debugInfoY + 125);
            debugInfoY += 125;
        }
        if(gameSettings.showCameraInfo) {
            double[] camCoords = gameCamera.getCamera1().getCoords();
            g.drawString("Camera Coords: " + camCoords[0] + ", " + camCoords[1], 0, debugInfoY + 25);
            g.drawString("Camera Scale: " + gameCamera.getCamera1().getZoom(), 0, debugInfoY + 50);
            debugInfoY += 50;
        }
    }

    public static void init() {
        // TODO: window needs to be resizable for cVars to work
        graphics = engineInstance.graphicsSystem.new gGraphicsSystem(
                engineInstance.graphicsSystem.new gPanel() {
                    public void draw(Graphics g) {
                        try {
                            super.draw(g);  // required to collect video metrics
                            getGameMetrics();

                            transformWorldCameraAndScale(g);
                            drawWorld(g);
                            resetWorldCameraAndScale(g);

                            drawUI(g);
                        }
                        catch (Exception e) {
                            System.out.println("EXCEPTION IN gameGraphics.draw()");
                            e.printStackTrace();
                        }
                    }
                },
                gameSettings.screenWidth,
                gameSettings.screenHeight
        );
    }
}
