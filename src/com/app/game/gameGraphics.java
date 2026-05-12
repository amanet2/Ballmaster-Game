package com.app.game;

import com.app.engine.engine;
import com.app.engine.graphicsSystem.gCanvas;
import com.app.engine.graphicsSystem.gGraphicsSystem;
import com.app.engine.utils.gDate;

import java.awt.*;

public class gameGraphics {
    public static gGraphicsSystem instance() {
        return engine.instance().gGraphicsSystem;
    }

    private static void drawWorld() {
        instance().setCameraTransform(gameCamera.gameCamera, false);
        Graphics g = instance().getGraphics();

        g.setColor(Color.WHITE);
        g.drawRect(
                (int) gameState.bigBox1.getX(),
                (int) gameState.bigBox1.getY(),
                (int) gameState.bigBox1.getWidth(),
                (int) gameState.bigBox1.getHeight()
        );
        g.drawRect(
                (int) gameState.bigBox2.getX(),
                (int) gameState.bigBox2.getY(),
                (int) gameState.bigBox2.getWidth(),
                (int) gameState.bigBox2.getHeight()
        );
        g.drawRect(
                (int) gameState.bigBox3.getX(),
                (int) gameState.bigBox3.getY(),
                (int) gameState.bigBox3.getWidth(),
                (int) gameState.bigBox3.getHeight()
        );

        g.setColor(Color.PINK);
        g.drawRect(
                (int) gameState.ballBoy.getBounds().getX(),
                (int) gameState.ballBoy.getBounds().getY(),
                (int) gameState.ballBoy.getBounds().getWidth(),
                (int) gameState.ballBoy.getBounds().getHeight()
        );

        g.setColor(Color.GREEN);
        g.drawRect(
                (int)gameState.triggerBounds.getBounds().getX(),
                (int)gameState.triggerBounds.getBounds().getY(),
                (int)gameState.triggerBounds.getBounds().getWidth(),
                (int)gameState.triggerBounds.getBounds().getHeight()
        );

        gameState.ballBoy.draw(g);
    }

    private static void drawUI() {
        instance().setCameraTransform(gameCamera.uiCamera, true);
        Graphics g = instance().getGraphics();

        g.setColor(Color.WHITE);
        int debugInfoY = 0;
        int offsetY = 20;
        if(gameCVars.showVideoInfo) {
            for(String k : instance().getVideoMetrics()) {
                g.drawString(k, 0, debugInfoY += offsetY);
            }
        }
        if(gameCVars.showFrameInfo) {
            String[] metrics = {
                    "Game FPS: %d".formatted(gameMetrics.getGameFramesPerSecondMetricSnapshot()),
                    "Game Frames: %d".formatted(gameMetrics.getGameFrames()),
                    "Game Frametime Average: %fms".formatted(gameMetrics.getGameFrametimeMetricSnapshotAvg()),
                    "Game Frametime Lowest: %fms".formatted(gameMetrics.getGameFrametimeMetricSnapshotLowest()),
                    "Game Frametime Highest: %fms".formatted(gameMetrics.getGameFrametimeMetricSnapshotHighest()),
            };
            for(String metric : metrics) {
                g.drawString(metric, 0, debugInfoY += offsetY);
            }
        }
        if(gameCVars.showCameraInfo) {
            double[] camCoords = gameCamera.gameCamera.getCoords();
            double[] camVec = gameCamera.gameCamera.getVec();
            String[] metrics = {
                    "Cam XY: [%f, %f]".formatted(camCoords[0], camCoords[1]),
                    "Cam Vec: [%f, %f]".formatted(camVec[0], camVec[1]),
                    "Cam Scale: %g".formatted(gameCamera.gameCamera.getZoom()),
            };
            for(String metric : metrics) {
                g.drawString(metric, 0, debugInfoY += offsetY);
            }
        }
        if(gameCVars.showTimeElapsed)
            g.drawString(
                    "Time Elapsed: %s".formatted(gDate.getTimerString(gameMetrics.getTimeElapsedMillis())),
                    0,
                    debugInfoY += offsetY
            );
        g.drawString(
                "Player XY: [%f, %f]".formatted(gameState.ballBoy.getBounds().getX(), gameState.ballBoy.getBounds().getY()),
                0,
                debugInfoY += offsetY
        );
        g.drawString(
                "Player Vec: [%f, %f]".formatted(gameState.ballBoy.getDx(), gameState.ballBoy.getDy()),
                0,
                debugInfoY += offsetY
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
    }
}
