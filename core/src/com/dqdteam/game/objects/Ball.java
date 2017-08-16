package com.dqdteam.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.dqdteam.game.MonsterPong;

@SuppressWarnings("serial")
public class Ball extends Rectangle {
    public Texture ballImage;
    public float xVel;
    public float yVel;
    public String name;
    private MonsterPong game;

    public Ball(MonsterPong game) {
    	this.game = game;
        this.name = "ball";        
        ballImage = game.ballImage;
        resetPosition();
        this.width = ballImage.getWidth();
        this.height = ballImage.getHeight();
        this.xVel = -200f;
        this.yVel = -200f;

    }

    public void resetVelocityY(int direction) {
        this.yVel = 200f * direction;
    }

    public float getCombinedVelocity(float delta) {
        double velSquared = Math.pow(xVel, 2) + Math.pow(yVel, 2);
        return (float) Math.sqrt(velSquared) * delta;
    }

    public void moveX(float deltaTime) {
        this.x += this.xVel * deltaTime;
    }

    public void moveY(float deltaTime) {
        this.y -= this.yVel * deltaTime;
    }

    public void dispose() {
        ballImage.dispose();
    }

    public float getTop() {
        return this.y + this.height;
    }

    public void setTop(float posY) {
        this.y = posY - this.height;
    }

    public void setBottom(float posY) {
        this.y = posY;
    }

    public float getRight() {
        return this.x + this.width;
    }

    public void setRight(float posX) {
        this.x = posX - this.width;
    }

    public void resetPosition() {
        this.x = game.width / 2;
        this.y = game.height / 2;
    }

    public void reverseDirectionX() {
        this.xVel *= -1;
    }

    public void reverseDirectionY() {
        this.yVel *= -1;
    }

    public float getBottom() {
        return getY();
    }
}