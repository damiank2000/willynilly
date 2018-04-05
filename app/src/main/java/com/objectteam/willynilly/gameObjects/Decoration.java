package com.objectteam.willynilly.gameObjects;

import com.objectteam.framework.DynamicGameObject;
import com.objectteam.willynilly.DecorationType;
import com.objectteam.willynilly.MotionType;
import com.objectteam.willynilly.World;

public class Decoration extends DynamicGameObject {
    private static final float PLATFORM_WIDTH = 2.0f;
    private static final float PLATFORM_HEIGHT = 1.0f;
    private static final float PLATFORM_VELOCITY = 2;

    private DecorationType decorationType;
    private MotionType motionType;
    private final World world;
    
    public Decoration(DecorationType decorationType, MotionType motionType, float x, float y, World world) {
        super(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        this.decorationType = decorationType;
        this.motionType = motionType;
        if(motionType == MotionType.Moving) {
            velocity.y = PLATFORM_VELOCITY;
        }
        this.world = world;
    }

    public void update(float deltaTime) {
        if(motionType == MotionType.Moving) {
            position.add(0, velocity.y * deltaTime);
            bounds.setPosition(position);
            
            if(position.y < PLATFORM_HEIGHT / 2) {
                velocity.y = -velocity.y;
                position.y = PLATFORM_HEIGHT / 2;
            }
            if(position.y > world.getWorldHeight() - PLATFORM_HEIGHT / 2) {
                velocity.y = -velocity.y;
                position.y = world.getWorldHeight() - PLATFORM_HEIGHT / 2;
            }            
        }
    }

    public DecorationType getDecorationType() {
        return decorationType;
    }
}
