package com.dqdteam.game.helps;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dqdteam.game.screen.LevelScreen;

/**
 * Created by DATDQ on 3/27/2018.
 */

public class TiledMapStage extends Stage {
    private static Group group = new Group();
    private TiledMap tiledMap;
    Screen nextScreen;
    LevelScreen level;

    public TiledMapStage(TiledMap tiledMap, LevelScreen level) {
        this.level =level;
        this.tiledMap = tiledMap;
        group.setVisible(true);
        for (MapLayer layer : tiledMap.getLayers()) {
            TiledMapTileLayer tiledLayer = (TiledMapTileLayer)layer;
            if(tiledLayer.getName().equals("boss"))
                createActorsForLayer(tiledLayer);
        }
    }

    private void createActorsForLayer(TiledMapTileLayer tiledLayer) {
        for (int x = 0; x < tiledLayer.getWidth(); x++) {
            for (int y = 0; y < tiledLayer.getHeight(); y++) {
                    TiledMapTileLayer.Cell cell = tiledLayer.getCell(x, y);
                    if (cell != null && cell.getTile().getProperties().containsKey("phonix")) {
                        TiledMapActor actor = new TiledMapActor(tiledMap, tiledLayer, cell);
                        actor.setBounds(x * tiledLayer.getTileWidth(), y * tiledLayer.getTileHeight(), tiledLayer.getTileWidth() * 3,
                                tiledLayer.getTileHeight());
                        group.addActor(actor);
                        actor.addListener(new TiledMapClickListener(actor , level));
                }
            }
        }
        this.addActor(group);
    }
}