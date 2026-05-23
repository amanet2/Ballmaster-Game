package com.app.game;

import com.app.engine.inputSystem.gImpulse;

public class gameImpulses {
    public static gImpulse impulsePauseGame = new gImpulse() {
        public void keyPressed() {
            gameState.paused = !gameState.paused;
        }
    };

    public static gImpulse impulseCameraUp = new gImpulse() {
        public void keyPressed() {
            gameState.camUp = true;
        }

        public void keyReleased() {
            gameState.camUp = false;
        }
    };

    public static gImpulse impulseCameraDown = new gImpulse() {
        public void keyPressed() {
            gameState.camDown = true;
        }

        public void keyReleased() {
            gameState.camDown = false;
        }
    };

    public static gImpulse impulseCameraLeft = new gImpulse() {
        public void keyPressed() {
            gameState.camLeft = true;
        }

        public void keyReleased() {
            gameState.camLeft = false;
        }
    };

    public static gImpulse impulseCameraRight = new gImpulse() {
        public void keyPressed() {
            gameState.camRight = true;
        }

        public void keyReleased() {
            gameState.camRight = false;
        }
    };

    public static gImpulse impulsePlayerUp = new gImpulse() {
        public void keyPressed() {
            gameState.playerUp = true;
        }

        public void keyReleased() {
            gameState.playerUp = false;
        }
    };

    public static gImpulse impulsePlayerDown = new gImpulse() {
        public void keyPressed() {
            gameState.playerDown = true;
        }

        public void keyReleased() {
            gameState.playerDown = false;
        }
    };

    public static gImpulse impulsePlayerLeft = new gImpulse() {
        public void keyPressed() {
            gameState.playerLeft = true;
        }

        public void keyReleased() {
            gameState.playerLeft = false;
        }
    };

    public static gImpulse impulsePlayerRight = new gImpulse() {
        public void keyPressed() {
            gameState.playerRight = true;
        }

        public void keyReleased() {
            gameState.playerRight = false;
        }
    };

    public static gImpulse impulsePlayerJump = new gImpulse() {
        public void keyPressed() {
            gameState.playerJump = true;
        }

        public void keyReleased() {
        }
    };

    public static gImpulse impulseCameraZoomIn = new gImpulse() {
        public void keyPressed() {
            gameCamera.gameCamera.setZoom(Math.min(gameCamera.gameCamera.getZoom() + 0.01, 1.0));
        }

        public void keyReleased() {
        }
    };

    public static gImpulse impulseCameraZoomOut = new gImpulse() {
        public void keyPressed() {
            gameCamera.gameCamera.setZoom(Math.max(gameCamera.gameCamera.getZoom() - 0.01, 0.01));
        }

        public void keyReleased() {
        }
    };
}
