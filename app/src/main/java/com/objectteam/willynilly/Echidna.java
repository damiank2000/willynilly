package com.objectteam.willynilly;

import com.objectteam.framework.DynamicGameObject;

public class Echidna extends DynamicGameObject {
    public static final float WIDTH = 0.7f;
    public static final float HEIGHT = 0.5f;
    public static final float VELOCITY = 0.5f;
    public static final float WALK_DISTANCE = 10f;
    
    float stateTime = 0;
    float startX = 0;
    float endX = 0;
    
    public Echidna(float x, float y) {
        super(x, y, WIDTH, HEIGHT);
        velocity.set(VELOCITY, 0);
        startX = x;
        endX = startX + WALK_DISTANCE;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if(position.x < startX ) {
            position.x = startX;
            velocity.x = VELOCITY;
        }
        if(position.x > endX) {
            position.x = endX;
            velocity.x = -VELOCITY;
        }
        stateTime += deltaTime;
    }
}
