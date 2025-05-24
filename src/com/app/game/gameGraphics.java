package com.app.game;

import com.app.engine.engine;
import com.app.engine.graphicsSystem.gGraphicsSystem;
import com.app.engine.utils.gDate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class gameGraphics {
    private static engine engineInstance = engine.instance();

    private static gGraphicsSystem graphics;

    public static gGraphicsSystem get() {
        return graphics;
    }

    private static void drawUI(Graphics g) {
        g.translate(graphics.getWidth() / 2, graphics.getHeight() / 2);
        ((Graphics2D) g).scale(1.0/gameCamera.getCamera1().getZoom(), 1.0/gameCamera.getCamera1().getZoom());
        g.translate(- graphics.getWidth() / 2, - graphics.getHeight() / 2);

        g.setColor(Color.WHITE);
        int debugInfoY = 0;
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
        if(gameSettings.showFps) {
            g.drawString("Video FPS: " + gameSettings.videoFramesPerSecondMetricSnapshot, 0, debugInfoY + 25);
            g.drawString("Video Frames: " + gameSettings.videoFrames, 0, debugInfoY + 50);
            g.drawString("Video Frametime AVG: " + gameSettings.videoFrametimeMetricSnapshotAvg + "ms", 0, debugInfoY + 75);
            g.drawString("Video Frametime Lowest: " + gameSettings.videoFrametimeMetricSnapshotLowest + "ms", 0, debugInfoY + 100);
            g.drawString("Video Frametime Highest: " + gameSettings.videoFrametimeMetricSnapshotHighest + "ms", 0, debugInfoY + 125);
            debugInfoY += 125;
        }
        if(gameSettings.showCameraInfo) {
            double[] camCoords = gameCamera.getCamera1().getCoords();
            g.drawString("Camera Coords: " + camCoords[0] + ", " + camCoords[1], 0, debugInfoY + 25);
            g.drawString("Camera Scale: " + gameCamera.getCamera1().getZoom(), 0, debugInfoY + 50);
            debugInfoY += 50;
        }
    }

    private static void drawWorld(Graphics g) {
        g.translate(graphics.getWidth() / 2, graphics.getHeight() / 2);
        ((Graphics2D) g).scale(gameCamera.getCamera1().getZoom(), gameCamera.getCamera1().getZoom());
        g.translate(- graphics.getWidth() / 2, - graphics.getHeight() / 2);

        // scale to world
        ((Graphics2D) g).scale(
                1.0 / gameSettings.gameScale * (double) gameSettings.screenHeight,
                1.0 / gameSettings.gameScale * (double) gameSettings.screenHeight
        );

        AffineTransform originalTransform = ((Graphics2D) g).getTransform();
        g.translate(-(int)gameCamera.getCamera1().getCoords()[0], -(int)gameCamera.getCamera1().getCoords()[1]);

        if(gameSprites.gSprites.size() > 2)
            g.drawImage(
                    gameSprites.gSprites.get(2).getImage(),
                    graphics.getWidth()/2 - 300 + (int)gameSettings.radix,
                    graphics.getHeight()/2 - 300,
                    null
            );

        //reset scale and transform
        ((Graphics2D) g).scale(
                gameSettings.gameScale / (double) gameSettings.screenHeight,
                gameSettings.gameScale / (double) gameSettings.screenHeight
        );
        ((Graphics2D) g).setTransform(originalTransform);
    }

    private static void getVideoMetrics() {
//        long currentTimeNanos = System.nanoTime();  // TODO: Use this for video frametime measurements
        long currentTimeMillis = System.currentTimeMillis();

        gameSettings.videoFramesPerSecondMetric++;
        gameSettings.videoFrames++;

        if(gameSettings.videoFrames >= Integer.MAX_VALUE - 1000)
            gameSettings.videoFrames = 0;

        gameSettings.videoFrametime = currentTimeMillis - gameSettings.videoFrametimeLast;
        gameSettings.videoFrametimeLast = currentTimeMillis;
        gameSettings.videoFrametimeMetric += gameSettings.videoFrametime;

        if(gameSettings.videoFrametime > gameSettings.videoFrametimeMetricHighest)
            gameSettings.videoFrametimeMetricHighest = gameSettings.videoFrametime;
        if(gameSettings.videoFrametime < gameSettings.videoFrametimeMetricLowest)
            gameSettings.videoFrametimeMetricLowest = gameSettings.videoFrametime;

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

            gameSettings.videoFramesPerSecondMetricSnapshot = gameSettings.videoFramesPerSecondMetric;

            gameSettings.videoFramesPerSecondMetric = 0;

            gameSettings.videoFrametimeMetricSnapshotLowest = gameSettings.videoFrametimeMetricLowest;
            gameSettings.videoFrametimeMetricSnapshotAvg = gameSettings.videoFrametimeMetric/1000;
            gameSettings.videoFrametimeMetricSnapshotHighest = gameSettings.videoFrametimeMetricHighest;

            gameSettings.videoFrametimeMetric = 0;
            gameSettings.videoFrametimeMetricLowest = 0;
            gameSettings.videoFrametimeMetricHighest = 0;
        }
    }

    public static void init() {
        graphics = engineInstance.graphicsSystem.new gGraphicsSystem(
                engineInstance.graphicsSystem.new gPanel() {
                    public void draw(Graphics g) {
                        try {
                            getVideoMetrics();
                            drawWorld(g);
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
