package com.app.game;

import com.app.engine.engine;
import com.app.engine.graphicsSystem;
import com.app.engine.graphicsSystem.gCanvas;
import com.app.engine.graphicsSystem.gPanel;
import com.app.engine.graphicsSystem.gGraphicsSystem;
import com.app.engine.utils.gDate;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

public class gameGraphics {
    private static engine engineInstance = engine.instance();

    private static gGraphicsSystem graphics = engineInstance.gGraphicsSystem;

    public static gGraphicsSystem instance() {
        return graphics;
    }

    private static void drawWorld(Graphics g) {
        int spriteId = 1;
        int spriteWidth = 300;
        int spriteWorldCoordX = (int) (0.0 - spriteWidth/2.0) + (int) gameState.testSpriteX;
        int spriteWorldCoordY = (int) (0.0 - spriteWidth/2.0) + (int) gameState.testSpriteY;
        if(gameSprites.gSprites.size() > spriteId)
            g.drawImage(gameSprites.gSprites.get(spriteId).getImage(), spriteWorldCoordX, spriteWorldCoordY,null);

        g.setColor(Color.YELLOW);
        g.drawLine(600, -1000, 600, 1000);
        g.drawLine(-600, -1000, -600, 1000);
    }

    private static void drawUI(Graphics g) {
        g.setColor(Color.WHITE);
        int debugInfoY = 0;
        if(gameSettings.showVideoInfo) {
            HashMap<String, Number> videoMetrics = engineInstance.gGraphicsSystem.getVideoMetrics();
            g.drawString("Video FPS: " + videoMetrics.get("videoFramesPerSecondMetricSnapshot"), 0, debugInfoY + 25);
            g.drawString("Video Frames: " + videoMetrics.get("videoFrames"), 0, debugInfoY + 50);
            g.drawString("Video Frametime AVG: " + videoMetrics.get("videoFrametimeMetricSnapshotAvg") + "ms", 0, debugInfoY + 75);
            g.drawString("Video Frametime Lowest: " + videoMetrics.get("videoFrametimeMetricSnapshotLowest") + "ms", 0, debugInfoY + 100);
            g.drawString("Video Frametime Highest: " + videoMetrics.get("videoFrametimeMetricSnapshotHighest") + "ms", 0, debugInfoY + 125);
            debugInfoY += 125;
        }
        if(gameSettings.showFrameInfo) {
            g.drawString("Game FPS: " + gameMetrics.getGameFramesPerSecondMetricSnapshot(), 0, debugInfoY + 25);
            g.drawString("Game Frames: " + gameMetrics.getGameFrames(), 0, debugInfoY + 50);
            g.drawString("Game Frametime AVG: " + gameMetrics.getGameFrametimeMetricSnapshotAvg(), 0, debugInfoY + 75);
            g.drawString("Game Frametime Lowest: " + gameMetrics.getGameFrametimeMetricSnapshotLowest(), 0, debugInfoY + 100);
            g.drawString("Game Frametime Highest: " + gameMetrics.getGameFrametimeMetricSnapshotHighest(), 0, debugInfoY + 125);
            debugInfoY += 125;
        }
        if(gameSettings.showCameraInfo) {
            double[] camCoords = gameCamera.gameCamera.getCoords();
            g.drawString("Camera Coords: " + camCoords[0] + ", " + camCoords[1], 0, debugInfoY + 25);
            g.drawString("Camera Scale: " + gameCamera.gameCamera.getZoom(), 0, debugInfoY + 50);
            debugInfoY += 50;
        }
        if(gameSettings.showTimeElapsed) {
            g.drawString("Time Elapsed: " + gDate.getTimerString(gameMetrics.getTimeElapsedMillis()), 0, debugInfoY + 25);
            debugInfoY += 25;
        }
    }

    public static void init() {
        engineInstance.gGraphicsSystem.init(new gCanvas() {
            public void render() {
                super.draw();
                Graphics g = this.getGraphics();
                drawUI(g);

                super.render();
            }
        });
//        engineInstance.gGraphicsSystem.setPanel(new gPanel() {
//                public void draw(Graphics g) {
//                    try {
//                        // TODO make it so I dont have to call restoreTransform
//                        super.draw(g);  // required to collect video metric
//
//                        this.setCameraTransform(g, gameCamera.gameCamera);
//                        drawWorld(g);
//
//                        this.restoreScaledTransform(g);
//
//                        this.setCameraTransform(g, gameCamera.uiCamera);
//                        drawUI(g);
//                    }
//                    catch (Exception e) {
//                        System.out.println("EXCEPTION IN gameGraphics.draw()");
//                        e.printStackTrace();
//                    }
//                }
//        });
        System.out.println("GRAPHICS SYSTEM INITIALIZED");
    }
}
