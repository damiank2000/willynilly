package com.objectteam.willynilly;

import com.objectteam.framework.GameObject;

public class Fan extends GameObject {
    public static final float FAN_WIDTH = 1f;
    public static final float FAN_HEIGHT = 10f;

    float stateTime;
    public Fan(float x, float y) {
        super(x, y, FAN_WIDTH, FAN_HEIGHT);
        stateTime = 0;
    }
    
    public void update(float deltaTime) {
        stateTime += deltaTime;
    }
}
