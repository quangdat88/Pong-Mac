package com.dqdteam.game.accessor;

import aurelienribon.tweenengine.TweenAccessor;
import com.dqdteam.game.objects.Monster;

/**
 * Created by DATDQ on 11/22/2017.
 */

public class MonsterAccessor implements TweenAccessor<Monster> {

    public static final int POSITION_X = 1;
    public static final int POSITION_Y = 2;
    public static final int POSITION_XY = 3;

    @Override
    public int getValues(Monster target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case POSITION_X: returnValues[0] = target.getCenterX(); return 1;
            case POSITION_Y: returnValues[0] = target.getY(); return 1;
            case POSITION_XY:
                returnValues[0] = target.getX();
                returnValues[1] = target.getY();
                return 2;
            default: assert false; return -1;

        }
    }

    @Override
    public void setValues(Monster target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case POSITION_X: target.setCenterX(newValues[0]); break;
            case POSITION_Y: target.setY(newValues[0]); break;
            case POSITION_XY:
                target.setPosition(newValues[0], newValues[1]);
                break;
            default: assert false; break;
        }
    }
}
