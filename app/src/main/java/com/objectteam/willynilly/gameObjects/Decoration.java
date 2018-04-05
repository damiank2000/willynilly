package com.objectteam.willynilly.gameObjects;

import com.objectteam.framework.DynamicGameObject;
import com.objectteam.willynilly.World;

public class Decoration extends DynamicGameObject {
    public static final float PLATFORM_WIDTH = 2.0f;
    public static final float PLATFORM_HEIGHT = 1.0f;
    public static final int PLATFORM_MOTION_TYPE_STATIC = 0;
    public static final int PLATFORM_MOTION_TYPE_MOVING = 1;
    public static final int PLATFORM_STATE_NORMAL = 0;
    public static final int PLATFORM_STATE_PULVERIZING = 1;
    public static final float PLATFORM_PULVERIZE_TIME = 0.2f * 4;
    public static final float PLATFORM_VELOCITY = 2;
    public static final int DECORATION_TYPE_TUNNEL_BACKGROUND = 5;
    public static final int DECORATION_TYPE_TUNNEL_ENTRANCE_LEFT = 6;
    public static final int DECORATION_TYPE_TUNNEL_WALL = 7;
    public static final int DECORATION_TYPE_TUNNEL_EXIT_RIGHT = 8;
	public static final int DECORATION_TYPE_ROCK_TUNNEL_LEFT = 9;
	public static final int DECORATION_TYPE_ROCK_TUNNEL_CENTRE = 10;
	public static final int DECORATION_TYPE_ROCK_TUNNEL_RIGHT = 11;
    
    int decorationType;
    int motionType;
    int state;
    float stateTime;
    final World world;
    
    public Decoration(int decorationType, int motionType, float x, float y, World world) {
        super(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        this.decorationType = decorationType;
        this.motionType = motionType;
        this.state = PLATFORM_STATE_NORMAL;
        this.stateTime = 0;
        if(motionType == PLATFORM_MOTION_TYPE_MOVING) {
            velocity.y = PLATFORM_VELOCITY;
        }
        this.world = world;
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

    public int getDecorationType() {
        return decorationType;
    }
}
