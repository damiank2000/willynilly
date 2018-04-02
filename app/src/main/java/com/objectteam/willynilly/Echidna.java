package com.objectteam.willynilly;

import com.objectteam.framework.DynamicGameObject;

public class Echidna extends DynamicGameObject {
    public static final float ECHIDNA_WIDTH = 0.7f;
    public static final float ECHIDNA_HEIGHT = 0.5f;
    public static final float ECHIDNA_VELOCITY = 0.5f;
    public static final float ECHIDNA_WALK_DISTANCE = 10f;
    
    float stateTime = 0;
    float startX = 0;
    float endX = 0;
    
    public Echidna(float x, float y) {
        super(x, y, ECHIDNA_WIDTH, ECHIDNA_HEIGHT);
        velocity.set(ECHIDNA_VELOCITY, 0);
        startX = x;
        endX = startX + ECHIDNA_WALK_DISTANCE;
    }
    
    public void update(float deltaTime) {
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        bounds.setPosition(position);
        
        if(position.x < startX ) {
            position.x = startX;
            velocity.x = ECHIDNA_VELOCITY;
        }
        if(position.x > endX) {
            position.x = endX;
            velocity.x = -ECHIDNA_VELOCITY;
        }
        stateTime += deltaTime;
    }
}
