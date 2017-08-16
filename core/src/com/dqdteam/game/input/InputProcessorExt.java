package com.dqdteam.game.input;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Sine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.dqdteam.game.MonsterPong;
import com.dqdteam.game.accessor.PaddleAccessor;
import com.dqdteam.game.objects.Controller;
import com.dqdteam.game.objects.Paddle;

public class InputProcessorExt implements InputProcessor {
	private final Vector3 tmpV = new Vector3();
	private final float Time = 1f;
	Controller leftCtr;
	Controller rightCtr;
	Controller middleCtr;
	Paddle paddle1;
	Paddle paddle2;
	MonsterPong game;
	Camera camera;
	boolean touch = false;
	

	public InputProcessorExt(Controller[] aController, Paddle[] aPaddle,
			MonsterPong game, Camera camera) {
		this.leftCtr = aController[0];
		this.middleCtr = aController[1];
		this.rightCtr = aController[2];
		this.paddle1 = aPaddle[0];
		this.paddle2 = aPaddle[1];
		this.game = game;
		this.camera = camera;
	}

	public void setPaddleLocationForTouchDown(Vector3 pos) {
		if (pos.x >= leftCtr.x && pos.x <= (leftCtr.x + leftCtr.width)
				&& pos.y >= leftCtr.y && pos.y <= (leftCtr.y + leftCtr.height)) {
			leftCtr.pressController();
			Vector3 posmove = new Vector3();
			//posmove.x = paddle1.getCenterX() - 20;			
			posmove.x = 0;
			if (leftCtr.isPress)
				paddleMoveTween(paddle1, posmove,Time);
		}

		if (pos.x >= rightCtr.x && pos.x <= (rightCtr.x + rightCtr.width)
				&& pos.y >= rightCtr.y
				&& pos.y <= (rightCtr.y + rightCtr.height)) {
			rightCtr.pressController();
			Vector3 posmove = new Vector3();
			posmove.x = game.width;			
			if (rightCtr.isPress)
				paddleMoveTween(paddle1, posmove,Time);
		}

	}

	private void paddleMoveTween(final Paddle paddle, Vector3 pos,float time) {

		TweenCallback tweenCallback = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				paddle.setTweening(false);
			}
		};

		paddle.setTweening(true);
		Tween.to(paddle, PaddleAccessor.POSITION_X, time).ease(Sine.INOUT)
				.target(pos.x).setCallback(tweenCallback)
				.start(game.tweenManager);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {	
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		setPaddleLocationForTouchDown(camera.unproject(tmpV.set(screenX,
				screenY, 0)));
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
