package com.app.game;

import com.app.engine.engine;
import com.app.engine.graphicsSystem;

import java.awt.Color;
import java.awt.Graphics;

public class gameGraphics {
    private static engine engineInstance = engine.instance();

    private static graphicsSystem.gGraphicsSystem graphics = engineInstance.graphicsSystem.new gGraphicsSystem(engineInstance.graphicsSystem.new gPanel() {
        public void draw(Graphics g) {
            game.videoFramesMetric++;
            game.videoFrames++;
            if(game.videoFrames >= Integer.MAX_VALUE - 1000)
                game.videoFrames = 0;
            if(System.currentTimeMillis() > game.frameMetricTimeMillis) {
                game.frameMetricTimeMillis = System.currentTimeMillis() + 1000;
                game.gameFramesSnapshot = game.gameFramesMetric;
                game.gameFramesMetric = 0;
                game.videoFramesSnapshot = game.videoFramesMetric;
                game.videoFramesMetric = 0;
            }

            g.setColor(Color.WHITE);
            g.drawString("Game Frames: " + game.gameFrames, 0, 25);
            g.drawString("Video Frames: " + game.videoFrames, 0, 50);
            g.drawString("Game FPS: " + game.gameFramesSnapshot, 0, 75);
            g.drawString("Video FPS: " + game.videoFramesSnapshot, 0, 100);
            for(Integer xpos : new int[]{14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1}) {
                g.drawImage(game.gSprites.getFirst().getImage(), (int)(xpos*75+game.radix), 359 - 150, null);
                g.drawImage(game.gSprites.getFirst().getImage(), (int)(xpos*75+game.radix), 359 - 75, null);
                g.drawImage(game.gSprites.getFirst().getImage(), (int)(xpos*75+game.radix), 359, null);
                g.drawImage(game.gSprites.getFirst().getImage(), (int)(xpos*75+game.radix), 359 + 75, null);
                g.drawImage(game.gSprites.getFirst().getImage(), (int)(xpos*75+game.radix), 359 + 150, null);
                g.drawImage(game.gSprites.getFirst().getImage(), (int)(xpos*75+game.radix), 359 + 225, null);
                g.drawImage(game.gSprites.getFirst().getImage(), (int)(xpos*75+game.radix), 359 + 300, null);
            }
            g.drawImage(game.gSprites.get(1).getImage(), (int)game.radix, 359 - 150, null);
            g.drawImage(game.gSprites.get(2).getImage(), 300 + (int)game.radix, 359 - 150, null);
        }
    });

    public static graphicsSystem.gGraphicsSystem get() {
        return graphics;
    }
}
