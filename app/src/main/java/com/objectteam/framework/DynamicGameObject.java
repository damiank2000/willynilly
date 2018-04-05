package com.objectteam.framework;

import com.objectteam.framework.math.Vector2;

public class DynamicGameObject extends GameObject {
    public final Vector2 velocity = new Vector2();
    public final Vector2 accel = new Vector2();
    
    public DynamicGameObject(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
    
    public DynamicGameObject(float x, float y, float width, float height, int boundsShape) {
        super(x, y, width, height, boundsShape);
    }

    public void update(float stateTime) {
        position.add(velocity.x * stateTime, velocity.y * stateTime);
        bounds.setPosition(position);
    }
}
