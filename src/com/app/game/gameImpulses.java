package com.app.game;

import com.app.engine.inputSystem.impulse;

public class gameImpulses {
    public static impulse impulseCameraUp = new impulse() {
        public void keyPressed() {
            gameState.camUp = true;
        }

        public void keyReleased() {
            gameState.camUp = false;
        }
    };

    public static impulse impulseCameraDown = new impulse() {
        public void keyPressed() {
            gameState.camDown = true;
        }

        public void keyReleased() {
            gameState.camDown = false;
        }
    };

    public static impulse impulseCameraLeft = new impulse() {
        public void keyPressed() {
            gameState.camLeft = true;
        }

        public void keyReleased() {
            gameState.camLeft = false;
        }
    };

    public static impulse impulseCameraRight = new impulse() {
        public void keyPressed() {
            gameState.camRight = true;
        }

        public void keyReleased() {
            gameState.camRight = false;
        }
    };
}
