package com.app.game;
import com.app.engine.spriteSystem.gSprite;

import java.awt.*;

public class entity {
    private gSprite sprite;
    private double x;
    private double y;
    private double w;
    private double h;

    public entity() {

    }

    public gSprite getSprite() {
        return this.sprite;
    }

    public void setSprite(gSprite sprite) {
        this.sprite = sprite;
    }

    public void draw(Graphics g) {
        this.getSprite().draw(
                g,
                (int) (this.x - this.w/2.0),
                (int) (this.y - this.h/2.0),
                (int) this.w,
                (int) this.h
        );
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getW() {
        return this.w;
    }

    public double getH() {
        return this.h;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setW(double w) {
        this.w = w;
    }

    public void setH(double h) {
        this.h = h;
    }

}
