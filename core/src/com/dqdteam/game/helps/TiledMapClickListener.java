package com.dqdteam.game.helps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.dqdteam.game.screen.LevelScreen;

/**
 * Created by DATDQ on 3/27/2018.
 */

public class TiledMapClickListener extends ClickListener {
    private TiledMapActor actor;
    private LevelScreen level;
    Screen nextScreen;

    final String NORMAL_STATE = "normal state";
    final String OUTRO_STATE = "outro state";

    public TiledMapClickListener(TiledMapActor actor, LevelScreen level) {
        this.actor = actor;
        this.level = level;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        level.state = OUTRO_STATE;
        Gdx.input.setInputProcessor(null);

    }
}
