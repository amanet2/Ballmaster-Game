package com.app.game;

import com.app.engine.engine;
import com.app.engine.graphicsSystem.gCanvas;
import com.app.engine.graphicsSystem.gGraphicsSystem;
import com.app.engine.utils.gDate;

import java.awt.*;
import java.util.HashMap;

public class gameGraphics {
    public static gGraphicsSystem instance() {
        return engine.instance().gGraphicsSystem;
    }

    private static void drawWorld() {
        instance().setCameraTransform(gameCamera.gameCamera);
        Graphics g = instance().getGraphics();

        int spriteWidth = 300;
        int spriteWorldCoordX = (int) (0.0 - spriteWidth/2.0) + (int) gameState.testSpriteX;
        int spriteWorldCoordY = (int) (0.0 - spriteWidth/2.0) + (int) gameState.testSpriteY;
        g.drawImage(gameSprites.gSprites.getFirst().getImage(), spriteWorldCoordX, spriteWorldCoordY,null);

        g.setColor(Color.YELLOW);
        g.drawLine(600, -1000, 600, 1000);
        g.drawLine(-600, -1000, -600, 1000);

        instance().restoreTransform();
    }

    private static void drawUI() {
        instance().setCameraTransform(gameCamera.uiCamera);
        Graphics g = instance().getGraphics();

        g.setColor(Color.WHITE);
        int debugInfoY = 25;
        if(gameSettings.showVideoInfo) {
            HashMap<String, Number> videoMetrics = instance().getVideoMetrics();

            g.drawString(
                    "Video Render: [%d, %d]".formatted(
                            videoMetrics.get("videoRenderW"),
                            videoMetrics.get("videoRenderH")
                    ),
                    0,
                    debugInfoY
            );
            debugInfoY += 25;

            g.drawString(
                    "Video Window: [%d, %d]".formatted(
                            videoMetrics.get("videoWindowW"),
                            videoMetrics.get("videoWindowH")
                    ),
                    0,
                    debugInfoY
            );
            debugInfoY += 25;

            g.drawString(
                    "Video FPS: " + videoMetrics.get("videoFramesPerSecondMetricSnapshot"),
                    0,
                    debugInfoY
            );
            debugInfoY += 25;

            g.drawString(
                    "Video Frames: " + videoMetrics.get("videoFrames"),
                    0,
                    debugInfoY
            );
            debugInfoY += 25;

            g.drawString(
                    "Video Frametime AVG: " + videoMetrics.get("videoFrametimeMetricSnapshotAvg") + "ms",
                    0,
                    debugInfoY
            );
            debugInfoY += 25;

            g.drawString(
                    "Video Frametime Lowest: " + videoMetrics.get("videoFrametimeMetricSnapshotLowest") + "ms",
                    0,
                    debugInfoY
            );
            debugInfoY += 25;

            g.drawString(
                    "Video Frametime Highest: " + videoMetrics.get("videoFrametimeMetricSnapshotHighest") + "ms",
                    0,
                    debugInfoY
            );
            debugInfoY += 25;
        }
        if(gameSettings.showFrameInfo) {
            g.drawString("Game FPS: " + gameMetrics.getGameFramesPerSecondMetricSnapshot(), 0, debugInfoY);
            debugInfoY += 25;

            g.drawString("Game Frames: " + gameMetrics.getGameFrames(), 0, debugInfoY);
            debugInfoY += 25;

            g.drawString("Game Frametime AVG: " + gameMetrics.getGameFrametimeMetricSnapshotAvg(), 0, debugInfoY);
            debugInfoY += 25;

            g.drawString("Game Frametime Lowest: " + gameMetrics.getGameFrametimeMetricSnapshotLowest(), 0, debugInfoY);
            debugInfoY += 25;

            g.drawString("Game Frametime Highest: " + gameMetrics.getGameFrametimeMetricSnapshotHighest(), 0, debugInfoY);
            debugInfoY += 25;
        }
        if(gameSettings.showCameraInfo) {
            double[] camCoords = gameCamera.gameCamera.getCoords();
            g.drawString("Camera Coords: " + camCoords[0] + ", " + camCoords[1], 0, debugInfoY);
            debugInfoY += 25;

            g.drawString("Camera Scale: " + gameCamera.gameCamera.getZoom(), 0, debugInfoY);
            debugInfoY += 25;
        }
        if(gameSettings.showTimeElapsed) {
            g.drawString("Time Elapsed: " + gDate.getTimerString(gameMetrics.getTimeElapsedMillis()), 0, debugInfoY);
            debugInfoY += 25;
        }
    }

    public static void init() {
        instance().init(new gCanvas() {
            public void render() {
                try {
                    super.clear();

                    drawWorld();

                    drawUI();

                    super.render();
                }
                catch(Exception e) {
                    System.out.println("Exception in gameGraphics.render");
                    e.printStackTrace();
                }
            }
        });
        System.out.println("GRAPHICS SYSTEM INITIALIZED");
    }
}
