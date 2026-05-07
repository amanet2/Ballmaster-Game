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

    private static void setCameraTransform(camera c) {
        Graphics g = instance().getGraphics();

        // move world to match camera coords
        double[] cCoords = c.getCoords();
        g.translate(-(int)cCoords[0], -(int)cCoords[1]);

        //zoom in or out depending on camera setting
        double cameraZoom = c.getZoom();
        ((Graphics2D) g).scale(cameraZoom, cameraZoom);
    }

    private static void drawWorld() {
        setCameraTransform(gameCamera.gameCamera);
        Graphics g = instance().getGraphics();

        gameState.ballBoy.draw(g);

        g.setColor(Color.YELLOW);
        g.drawLine(600, -600, 600, 600);
        g.drawLine(-600, -600, -600, 600);
        g.drawLine(0, -600, 0, 600);
        g.drawLine(-600, 0, 600, 0);
        g.drawLine(-600, -600, 600, -600);
        g.drawLine(-600, 600, 600, 600);

        instance().restoreTransform();
    }

    private static void drawUI() {
        setCameraTransform(gameCamera.uiCamera);
        Graphics g = instance().getGraphics();

        g.setColor(Color.WHITE);
        int debugInfoY = 0;
        if(gameSettings.showVideoInfo) {
            HashMap<String, Number> videoMetrics = instance().getVideoMetrics();
            String[] metrics = {
                    "Video Render: [%d, %d]".formatted(
                            videoMetrics.get("videoRenderW"),
                            videoMetrics.get("videoRenderH")
                    ),
                    "Video Window: [%d, %d]".formatted(
                            videoMetrics.get("videoWindowW"),
                            videoMetrics.get("videoWindowH")
                    ),
                    "Video FPS: %d".formatted(videoMetrics.get("videoFramesPerSecondMetricSnapshot")),
                    "Video Frames: %d".formatted(videoMetrics.get("videoFrames")),
                    "Video Frametime Average: %fms".formatted(videoMetrics.get("videoFrametimeMetricSnapshotAvg")),
                    "Video Frametime Lowest: %fms".formatted(videoMetrics.get("videoFrametimeMetricSnapshotLowest")),
                    "Video Frametime Highest: %fms".formatted(videoMetrics.get("videoFrametimeMetricSnapshotHighest"))
            };
            for(String metric : metrics) {
                g.drawString(metric, 0, debugInfoY += 25);
            }
        }
        if(gameSettings.showFrameInfo) {
            String[] metrics = {
                    "Game FPS: %d".formatted(gameMetrics.getGameFramesPerSecondMetricSnapshot()),
                    "Game Frames: %d".formatted(gameMetrics.getGameFrames()),
                    "Game Frametime Average: %fms".formatted(gameMetrics.getGameFrametimeMetricSnapshotAvg()),
                    "Game Frametime Lowest: %fms".formatted(gameMetrics.getGameFrametimeMetricSnapshotLowest()),
                    "Game Frametime Highest: %fms".formatted(gameMetrics.getGameFrametimeMetricSnapshotHighest()),
            };
            for(String metric : metrics) {
                g.drawString(metric, 0, debugInfoY += 25);
            }
        }
        if(gameSettings.showCameraInfo) {
            double[] camCoords = gameCamera.gameCamera.getCoords();
            String[] metrics = {
                    "Cam XY: [%f, %f]".formatted(camCoords[0], camCoords[1]),
                    "Cam Scale: %g".formatted(gameCamera.gameCamera.getZoom()),
            };
            for(String metric : metrics) {
                g.drawString(metric, 0, debugInfoY += 25);
            }
        }
        if(gameSettings.showTimeElapsed)
            g.drawString(
                    "Time Elapsed: %s".formatted(gDate.getTimerString(gameMetrics.getTimeElapsedMillis())),
                    0,
                    debugInfoY += 25
            );
        g.drawString(
                "Ballboy: [%f, %f]".formatted(gameState.ballBoy.getX(), gameState.ballBoy.getY()),
                0,
                debugInfoY += 25
        );
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
