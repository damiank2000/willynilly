package com.objectteam.willynilly;

import com.objectteam.framework.DynamicGameObject;

public class Platform extends DynamicGameObject {
    public static final float PLATFORM_WIDTH = 2.0f;
    public static final float PLATFORM_HEIGHT = 1.0f;
    public static final int PLATFORM_MOTION_TYPE_STATIC = 0;
    public static final int PLATFORM_MOTION_TYPE_MOVING = 1;
    public static final int PLATFORM_STATE_NORMAL = 0;
    public static final int PLATFORM_STATE_PULVERIZING = 1;
    public static final float PLATFORM_PULVERIZE_TIME = 0.2f * 4;
    public static final float PLATFORM_VELOCITY = 2;
    public static final int PLATFORM_TYPE_GRASSY_ROCK = 1;
    public static final int PLATFORM_TYPE_ROCK_TUNNEL_LEFT = 2;
    public static final int PLATFORM_TYPE_ROCK_TUNNEL_CENTER = 3;
    public static final int PLATFORM_TYPE_ROCK_TUNNEL_RIGHT = 4;
    
    int platformType;
    int motionType;
    int state;
    float stateTime;
	public boolean canLandOnPlatform;
	public boolean canPassThroughPlatform;
	final World world;
    
    public Platform(int platformType, int motionType, float x, float y, World world) {
        super(x, y, GetPlatformWidth(platformType), GetPlatformHeight(platformType));
        this.platformType = platformType;
        this.motionType = motionType;
        this.state = PLATFORM_STATE_NORMAL;
        this.stateTime = 0;
        if(motionType == PLATFORM_MOTION_TYPE_MOVING) {
            velocity.y = PLATFORM_VELOCITY;
        }
        this.canLandOnPlatform = true;
        switch (this.platformType){
        case Platform.PLATFORM_TYPE_GRASSY_ROCK:
        	this.canPassThroughPlatform = true;
        	break;
        default:
        	this.canPassThroughPlatform = true;
        	break;
        }
        this.world = world;
    }
    
    public static float GetPlatformWidth(int platformType) {
        switch (platformType){
        case Platform.PLATFORM_TYPE_GRASSY_ROCK:
        	return PLATFORM_WIDTH;
        default:
        	return PLATFORM_WIDTH;
        }
    }
    
    public static float GetPlatformHeight(int platformType) {
        switch (platformType){
        case Platform.PLATFORM_TYPE_GRASSY_ROCK:
        	return PLATFORM_HEIGHT;
        default:
        	return PLATFORM_HEIGHT;
        }
    }
    
    public void update(float deltaTime) {
        if(motionType == PLATFORM_MOTION_TYPE_MOVING) {
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
                
        stateTime += deltaTime;        
    }
    
    public void pulverize() {
        state = PLATFORM_STATE_PULVERIZING;
        stateTime = 0;
        velocity.x = 0;
    }
}
