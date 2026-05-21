package com.app.game;

import com.app.engine.engine;
import com.app.engine.graphicsSystem.gCanvas;
import com.app.engine.graphicsSystem.gGraphicsSystem;
import com.app.engine.eventSystem.gEventTriggerBounds;
import com.app.engine.utils.gDate;
import com.app.engine.utils.gBounds;

import java.awt.*;

public class gameGraphics {
    public static gGraphicsSystem instance() {
        return engine.instance().gGraphicsSystem;
    }

    private static void drawWorld() {
        instance().setCameraTransform(gameCamera.gameCamera, false);
        Graphics g = instance().getGraphics();

        ((Graphics2D) g).setPaint(gameTextures.wallTexture.getTexturePaint());
        for(gBounds collisionBounds : gameState.collisionBounds) {
            g.fillRect(
                    (int) collisionBounds.getX(),
                    (int) collisionBounds.getY(),
                    (int) collisionBounds.getWidth(),
                    (int) collisionBounds.getHeight()
            );
        }

        g.setColor(Color.PINK);
        g.drawRect(
                (int) gameState.ballBoy.getBounds().getX(),
                (int) gameState.ballBoy.getBounds().getY(),
                (int) gameState.ballBoy.getBounds().getWidth(),
                (int) gameState.ballBoy.getBounds().getHeight()
        );

        g.setColor(Color.GREEN);
        for(gEventTriggerBounds eventTriggerBounds : gameState.triggerBounds) {
            g.drawRect(
                    (int)eventTriggerBounds.getBounds().getX(),
                    (int)eventTriggerBounds.getBounds().getY(),
                    (int)eventTriggerBounds.getBounds().getWidth(),
                    (int)eventTriggerBounds.getBounds().getHeight()
            );
        }

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
        if(gameCVars.showMouseInfo) {
            int[] mouseCoords = gameInput.instance().getMouse().getCoordinates();
            g.drawString(
                    "Mouse XY: [%d, %d]".formatted(mouseCoords[0], mouseCoords[1]),
                    0,
                    debugInfoY += offsetY
            );
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

        // MOUSE COORDS
        int[] mouseXY = gameInput.instance().getMouse().getCoordinates();
        int[] windowXY = instance().getWindowXY();
        int[] screenXY = { mouseXY[0] - windowXY[0], mouseXY[1] - windowXY[1] };

        g.setColor(Color.CYAN);
        int[] uiMouseXY = getUIXYFromScreenXY(new int[]{ screenXY[0], screenXY[1]});

        g.drawString(
                "Mouse XY (CANVAS UI): [%d, %d]".formatted(uiMouseXY[0], uiMouseXY[1]),
                0,
                debugInfoY += offsetY
        );

        double[] scaleXY = {
                ((double) uiMouseXY[0] * (480.0/(double) instance().getRenderH())),
                ((double) uiMouseXY[1] * (480.0/(double) instance().getRenderH())),
        };
        g.drawLine((int) scaleXY[0], 0, (int) scaleXY[0], instance().getWindowH());
        g.drawLine(0, (int) scaleXY[1], instance().getWindowW(), (int) scaleXY[1]);


        g.setColor(Color.PINK);
        double[] worldMouseXY = getWorldXYFromScreenXY(new int[]{ uiMouseXY[0], uiMouseXY[1]});
//        double[] worldMouseXY = getWorldXYFromScreenXY(new int[]{ screenXY[0], screenXY[1]});

        g.drawString(
                "Mouse XY (WORLD): [%f, %f]".formatted(worldMouseXY[0], worldMouseXY[1]),
                0,
                debugInfoY += offsetY
        );

//        double[] worldScaleUI = {((double) worldMouseXY[0]), ((double) worldMouseXY[1]),};
//        g.drawLine((int) worldScaleUI[0], 0, (int) worldScaleUI[0], instance().getWindowH());
//        g.drawLine(0, (int) worldScaleUI[1], instance().getWindowW(), (int) worldScaleUI[1]);

        // EVENTS
        gameScheduler.instance().doEventsGraphics(System.currentTimeMillis(), g);
    }

    public static int[] getUIXYFromScreenXY(int[] xy) {
        double[] canvasXY = {
                (double) xy[0] * ((double) instance().getRenderW() / (double) instance().getWindowW()),
                (double) xy[1] * ((double) instance().getRenderH() / (double) instance().getWindowH()),
        };

        return new int[]{ (int) (canvasXY[0]), (int) (canvasXY[1]) };
    }

    // TODO: works fine for all situations except when render is above 640x480 and cam coords are not at 0,0
    public static double[] getWorldXYFromScreenXY(int[] xy) {
        return new double[]{
                ((((double) xy[0] + gameCamera.gameCamera.getCoords()[0] - (double) instance().getRenderW()/2) / gameCamera.gameCamera.getZoom()))*(480.0/instance().getRenderH()),
                ((((double) xy[1] + gameCamera.gameCamera.getCoords()[1] - (double) instance().getRenderH()/2) / gameCamera.gameCamera.getZoom()))*(480.0/instance().getRenderH()),
        };
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
