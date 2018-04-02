package com.objectteam.willynilly;

import com.objectteam.framework.DynamicGameObject;

public class WaftyBird extends DynamicGameObject {
    public static final float BIRD_WIDTH = 0.5f;
    public static final float BIRD_HEIGHT = 0.5f;
    public static final float BIRD_SPEED = 3f;
    public static final float BIRD_WAFT = 0f;
    
    float stateTime = 0;
    final float minY;
    final float maxY;
    
    public WaftyBird(float x, float y) {
        super(x, y, BIRD_WIDTH, BIRD_HEIGHT);
        minY = y - 1;
        maxY = y + 1;
        velocity.set(-BIRD_SPEED, BIRD_WAFT);
    }
    
    public void update(float deltaTime) {
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        bounds.setPosition(position);
        
        if (position.x < 0)
        	velocity.set(0, 0);
        if (position.y < minY) {
        	position.y = minY;
        	velocity.set(velocity.x, -velocity.y);
        }
        else if (position.y > maxY) {
        	position.y = maxY;
        	velocity.set(velocity.x, -velocity.y);
        }
        stateTime += deltaTime;
    }
}
