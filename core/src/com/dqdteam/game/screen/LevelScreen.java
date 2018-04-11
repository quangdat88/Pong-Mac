package com.dqdteam.game.screen;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Back;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.dqdteam.game.MonsterPong;
import com.dqdteam.game.accessor.TableAccessor;
import com.dqdteam.game.helps.TiledMapStage;
import com.dqdteam.game.objects.Boss;

/**
 * Created by DATDQ on 3/23/2018.
 */

public class LevelScreen implements Screen {
    MonsterPong game;
    Stage stage;
    Table table;
    Screen nextScreen;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    OrthographicCamera camera;
    float elapsedTimeBoss = 0f;
    Boss boss;
    private Animation animationBoss;
    TiledMapTileLayer.Cell cell;
    public String state;

    final String NORMAL_STATE = "normal state";
    final String OUTRO_STATE = "outro state";

    public LevelScreen(MonsterPong g) {
        this.game = g;
        table = new Table();
        table.setFillParent(true);
        state = NORMAL_STATE;
        tiledMap = new TmxMapLoader().load("data/Maps/Sence1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        stage = new TiledMapStage(tiledMap, this);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.width, game.height);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.075f, 0.059f, 0.188f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.tweenManager.update(delta);
        game.batch.setProjectionMatrix(stage.getCamera().combined);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        stage.act();
        stage.draw();
        if (state.equals(OUTRO_STATE)) {
            setOutroTween();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        setTableTween();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
    }

    private void setTableTween() {
        table.setX(-800);
        Tween.to(table, TableAccessor.POSITION_XY, .8f)
                .targetRelative(800, 0)
                .ease(Back.OUT)
                .start(game.tweenManager);

    }

    private void setOutroTween() {
        TweenCallback tweenCallback = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                game.setScreen(new MainPlayScreen(game));
                dispose();
            }
        };
        Tween.to(table, TableAccessor.POSITION_X, .8f)
                .targetRelative(800)
                .ease(Back.IN)
                .setCallback(tweenCallback)
                .start(game.tweenManager);
    }
}
