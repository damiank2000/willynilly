package com.objectteam.framework;

import com.objectteam.framework.math.Vector2;

public class DynamicGameObject extends GameObject {
    public final Vector2 velocity;
    public final Vector2 accel;
    
    public DynamicGameObject(float x, float y, float width, float height) {
        super(x, y, width, height);
        velocity = new Vector2();
        accel = new Vector2();
    }
    
    public DynamicGameObject(float x, float y, float width, float height, int boundsShape) {
        super(x, y, width, height, boundsShape);
        velocity = new Vector2();
        accel = new Vector2();
    }
}
