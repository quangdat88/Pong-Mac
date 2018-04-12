package com.dqdteam.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.dqdteam.game.StatusBall;
import com.dqdteam.game.StatusCollision;

public class Controller extends Rectangle {

    public Texture button;
    public String name;
    public boolean isPress = false;
    StatusCollision statusCollision = StatusCollision.NONE;

    public Controller(Texture button, String name, float posX, float posY) {
        this.button = button;
        this.name = name;
        this.x = posX;
        this.y = posY;
        this.width = button.getWidth();
        this.height = button.getHeight();
    }

    public void pressController() {
        this.isPress = true;
    }

    public void setStatusController(StatusCollision status) {
        this.statusCollision = status;
    }

    public StatusCollision getStatusController() {
        return this.statusCollision;
    }


    public void upController() {
        this.isPress = false;
    }

    public void dispose() {
        button.dispose();
    }

    public void effectBall(Ball ball) {
        if (this.statusCollision == StatusCollision.PERFECH) {
            ball.status = StatusBall.X2_BALL;
            ball.yVel *= -2;
            ball.xVel *= 2;
        } else if (this.statusCollision == StatusCollision.NORMAL) {
            ball.xVel = ball.xVel > 0 ? ball.xVel + 200f : ball.xVel - 200f;
            ball.yVel = ball.yVel > 0 ? ball.yVel + 200f : ball.yVel - 200f;
            ball.status = StatusBall.A200_BALL;
        } else if (this.statusCollision == StatusCollision.BAD) {
            ball.xVel = ball.xVel > 0 ? ball.xVel + 100f : ball.xVel - 100f;
            ball.yVel = ball.yVel > 0 ? ball.yVel + 100f : ball.yVel - 100f;
            ball.status = StatusBall.A100_BALL;
        }
    }
}
