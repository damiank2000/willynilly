package com.objectteam.willynilly;

import com.objectteam.framework.DynamicGameObject;

public class Bat extends DynamicGameObject {
    public static final float BAT_WIDTH = 0.25f;
    public static final float BAT_HEIGHT = 0.15f;
    public static final float BAT_VELOCITY = 3f;
    
    float stateTime = 0;
    World world;
    
    public Bat(float x, float y, World world) {
        super(x, y, BAT_WIDTH, BAT_HEIGHT);
        velocity.set(0, BAT_VELOCITY);
        this.world = world;
    }
    
    public void update(float deltaTime) {
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        bounds.setPosition(position);
        
        if(position.y < BAT_HEIGHT ) {
            position.y = BAT_HEIGHT;
            velocity.y = BAT_VELOCITY;
        }
        if(position.y > world.getWorldHeight() - BAT_HEIGHT) {
            position.y = world.getWorldHeight() - BAT_HEIGHT;
            velocity.y = -BAT_VELOCITY;
        }
        stateTime += deltaTime;
    }
}
