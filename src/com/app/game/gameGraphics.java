package com.app.game;

import com.app.engine.engine;
import com.app.engine.graphicsSystem.gGraphicsSystem;
import com.app.engine.settings;
import com.app.engine.utils.gDate;
import com.app.engine.utils.gMath;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class gameGraphics {
    private static engine engineInstance = engine.instance();

    private static gGraphicsSystem graphics;

    private static AffineTransform savedTransformation;

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

    private static void transformWorld(Graphics g) {
        savedTransformation = ((Graphics2D) g).getTransform();

        // center the screen over 0,0
        g.translate((int)((double)graphics.getWidth()/2.0), (int)((double)graphics.getHeight()/2.0));

        // scale the world according to screen height
        double scaleFactor = gMath.scaleDoubleToWindowHeight(1.0, gameSettings.gameScale, gameSettings.screenHeight);
        ((Graphics2D) g).scale(scaleFactor, scaleFactor);

        // move world to match camera coords
        g.translate(-(int)gameCamera.getCamera1().getCoords()[0], -(int)gameCamera.getCamera1().getCoords()[1]);

        //zoom in or out depending on camera setting
        double cameraZoom = gameCamera.getCamera1().getZoom();
        ((Graphics2D) g).scale(cameraZoom, cameraZoom);
    }

    private static void resetTransformWorld(Graphics g) {
        ((Graphics2D) g).setTransform(savedTransformation);

        // scale ui text according to window screen height
        double scaleFactor = gMath.scaleDoubleToWindowHeight(1.0, gameSettings.gameScale, gameSettings.screenHeight);
        ((Graphics2D) g).scale(scaleFactor, scaleFactor);
    }

    private static void drawWorld(Graphics g) {
        int spriteId = 2;
        int spriteWidth = 600;
        int spriteWorldCoordX = (int) (0.0 - spriteWidth/2.0) + (int) gameSettings.radix;
        int spriteWorldCoordY = (int) (0.0 - spriteWidth/2.0);
        if(gameSprites.gSprites.size() > spriteId)
            g.drawImage(gameSprites.gSprites.get(spriteId).getImage(), spriteWorldCoordX, spriteWorldCoordY,null);

        g.setColor(Color.YELLOW);
        g.drawLine(600, -1000, 600, 1000);
        g.drawLine(-600, -1000, -600, 1000);
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

                            transformWorld(g);
                            drawWorld(g);
                            resetTransformWorld(g);

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
