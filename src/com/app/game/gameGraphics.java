package com.app.game;

import com.app.engine.engine;
import com.app.engine.graphicsSystem.gPanel;
import com.app.engine.graphicsSystem.gGraphicsSystem;
import com.app.engine.utils.gDate;

import java.awt.Color;
import java.awt.Graphics;

public class gameGraphics {
    private static engine engineInstance = engine.instance();

    private static gGraphicsSystem graphics = engineInstance.gGraphicsSystem;

    public static gGraphicsSystem instance() {
        return graphics;
    }

    private static void drawWorld(Graphics g) {
        int spriteId = 2;
        int spriteWidth = 600;
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
        int debugInfoY = engine.showMetricsVideo ? 150 : 0;
        int debugInfoSeparation = 25;
        if(gameSettings.showFrameInfo) {
            g.drawString("Game FPS: " + gameMetrics.getGameFramesPerSecondMetricSnapshot(), 0, debugInfoY + debugInfoSeparation);
            g.drawString("Game Frames: " + gameMetrics.getGameFrames(), 0, debugInfoY + debugInfoSeparation*2);
            g.drawString("Game Frametime AVG: " + gameMetrics.getGameFrametimeMetricSnapshotAvg(), 0, debugInfoY + debugInfoSeparation*3);
            g.drawString("Game Frametime Lowest: " + gameMetrics.getGameFrametimeMetricSnapshotLowest(), 0, debugInfoY + debugInfoSeparation*4);
            g.drawString("Game Frametime Highest: " + gameMetrics.getGameFrametimeMetricSnapshotHighest(), 0, debugInfoY + debugInfoSeparation*5);
            debugInfoY += debugInfoSeparation*5;
        }
        if(gameSettings.showCameraInfo) {
            double[] camCoords = gameCamera.camera1.getCoords();
            g.drawString("Camera Coords: " + camCoords[0] + ", " + camCoords[1], 0, debugInfoY + debugInfoSeparation);
            g.drawString("Camera Scale: " + gameCamera.camera1.getZoom(), 0, debugInfoY + debugInfoSeparation*2);
            debugInfoY += debugInfoSeparation*2;
        }
        if(gameSettings.showTimeElapsed) {
            g.drawString("Time Elapsed: " + gDate.getTimerString(gameMetrics.getTimeElapsedMillis()), 0, debugInfoY + debugInfoSeparation);
            debugInfoY += debugInfoSeparation;
        }
    }

    public static void init() {
        engineInstance.gGraphicsSystem.setPanel(new gPanel() {
                public void draw(Graphics g) {
                    try {
                        super.draw(g);  // required to collect video metrics

                        this.setCameraTransform(g, gameCamera.camera1);

                        drawWorld(g);

                        this.restoreScaledTransform(g);

                        drawUI(g);
                    }
                    catch (Exception e) {
                        System.out.println("EXCEPTION IN gameGraphics.draw()");
                        e.printStackTrace();
                    }
                }
        });
        System.out.println("GRAPHICS SYSTEM INITIALIZED");
    }
}
