package com.app.game;

import com.app.engine.engine;
import com.app.engine.graphicsSystem.gGraphicsSystem;

import java.awt.Color;
import java.awt.Graphics;

public class gameGraphics {
    private static engine engineInstance = engine.instance();

    private static gGraphicsSystem graphics;

    public static gGraphicsSystem get() {
        return graphics;
    }

    public static void init() {
        graphics = engineInstance.graphicsSystem.new gGraphicsSystem(engineInstance.graphicsSystem.new gPanel() {
            public void draw(Graphics g) {
                gameSettings.videoFramesMetric++;
                gameSettings.videoFrames++;
                if(gameSettings.videoFrames >= Integer.MAX_VALUE - 1000)
                    gameSettings.videoFrames = 0;
                if(System.currentTimeMillis() > gameSettings.frameMetricTimeMillis) {
                    gameSettings.frameMetricTimeMillis = System.currentTimeMillis() + 1000;
                    gameSettings.gameFramesSnapshot = gameSettings.gameFramesMetric;
                    gameSettings.gameFramesMetric = 0;
                    gameSettings.videoFramesSnapshot = gameSettings.videoFramesMetric;
                    gameSettings.videoFramesMetric = 0;
                }

                //UI text
                g.setColor(Color.WHITE);
                if(gameSettings.showdebug) {
                    g.drawString("Game Frames: " + gameSettings.gameFrames, 0, 25);
                    g.drawString("Video Frames: " + gameSettings.videoFrames, 0, 50);
                    g.drawString("Game FPS: " + gameSettings.gameFramesSnapshot, 0, 75);
                }
                if(gameSettings.showfps)
                    g.drawString("Video FPS: " + gameSettings.videoFramesSnapshot, 0, gameSettings.showdebug ? 100 : 25);

                //WORLD
                g.translate(-(int)gameCamera.getCamera1().getCoords()[0], -(int)gameCamera.getCamera1().getCoords()[1]);

                if(gameSprites.gSprites.size() < 3)
                    return;
                for(Integer xpos : new int[]{14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1}) {
                    g.drawImage(gameSprites.gSprites.getFirst().getImage(), (int)(xpos*75+gameSettings.radix), 359 - 150, null);
                    g.drawImage(gameSprites.gSprites.getFirst().getImage(), (int)(xpos*75+gameSettings.radix), 359 - 75, null);
                    g.drawImage(gameSprites.gSprites.getFirst().getImage(), (int)(xpos*75+gameSettings.radix), 359, null);
                    g.drawImage(gameSprites.gSprites.getFirst().getImage(), (int)(xpos*75+gameSettings.radix), 359 + 75, null);
                    g.drawImage(gameSprites.gSprites.getFirst().getImage(), (int)(xpos*75+gameSettings.radix), 359 + 150, null);
                    g.drawImage(gameSprites.gSprites.getFirst().getImage(), (int)(xpos*75+gameSettings.radix), 359 + 225, null);
                    g.drawImage(gameSprites.gSprites.getFirst().getImage(), (int)(xpos*75+gameSettings.radix), 359 + 300, null);
                }
                g.drawImage(gameSprites.gSprites.get(1).getImage(), (int)gameSettings.radix, 359 - 150, null);
                g.drawImage(gameSprites.gSprites.get(2).getImage(), 300 + (int)gameSettings.radix, 359 - 150, null);
            }
        });
    }
}
