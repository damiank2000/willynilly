package com.objectteam.willynilly;

import com.objectteam.framework.DynamicGameObject;
import com.objectteam.framework.GameObject;

public class Willy extends DynamicGameObject{
    public static final int WILLY_STATE_JUMP = 0;
    public static final int WILLY_STATE_FALL = 1;
    public static final int WILLY_STATE_HIT = 2;
    public static final int WILLY_STATE_RUN = 3;
    public static final int WILLY_STATE_RUN_EXIT = 4;
    public static final int WILLY_STATE_STUNNED = 5;
    public static final float WILLY_JUMP_VELOCITY = 5.5f;
    public static final float WILLY_MOVE_TERMINAL_VELOCITY = 10;
    public static final float WILLY_WIDTH = 1.8f;
    public static final float WILLY_HEIGHT = 1.2f;
    public static final float WILLY_RUN_EXIT_LAG_TIME = 0.5f;
    public static final float WILLY_STUN_TIME = 2f;
    public static final float WILLY_ACCELERATION = 1f;
    
    public int lives = 0;
    
    int state;
    float stateTime;    

    public Willy(float x, float y) {
        super(x, y, WILLY_WIDTH, WILLY_HEIGHT, GameObject.BOUNDS_SHAPE_RECTANGLE);
        startFallingState();   
    }

    public void update(float deltaTime) {   
    	if (state == WILLY_STATE_STUNNED)
    		if (stateTime > WILLY_STUN_TIME)
    			startFallingState();
    	
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);

        if (Willy.canFall(state)) {
    		velocity.add(World.gravity.x * deltaTime, World.gravity.y * deltaTime);
    	}
    	if (Willy.canAccelerate(state)) {
    		velocity.x = Math.min(velocity.x + (WILLY_ACCELERATION/deltaTime), WILLY_MOVE_TERMINAL_VELOCITY);
    	}
    	
        bounds.setPosition(position);
                
        if(velocity.y < 0 && state != WILLY_STATE_HIT && state != WILLY_STATE_STUNNED) {
        	if ((state == WILLY_STATE_RUN_EXIT && stateTime > WILLY_RUN_EXIT_LAG_TIME)
        			|| state == WILLY_STATE_JUMP) {
        		startFallingState();
        	}
        	else if(state == WILLY_STATE_RUN) {
        		startRunToFallTransitionState();
            }
        }
               
        stateTime += deltaTime;
    }
    
    public void startRunToFallTransitionState() {
        state = WILLY_STATE_RUN_EXIT;
        stateTime = 0;    	
    }
    
    public void startFallingState() {
        state = WILLY_STATE_FALL;
        stateTime = 0;    	
    }

    public void startJumpingState() {
        state = WILLY_STATE_JUMP;
        stateTime = 0;    	
    }

    public void startHitState() {
        state = WILLY_STATE_HIT;
        stateTime = 0;    	
    }

    public void startRunningState() {
        state = WILLY_STATE_RUN;
        stateTime = 0;    	
    }   
    
    public void startStunnedState() {
    	state = WILLY_STATE_STUNNED;
    	stateTime = 0;
    }
    
    public void jump() {
    	velocity.y = WILLY_JUMP_VELOCITY;
    	startJumpingState();
    }
    
    public void hitEnemy() {
        velocity.set(0,0);
        startHitState();
    }
    
    public void hitPlatform() {
        velocity.set(0,0);
        startHitState();
    }

    public void blownUp() {
        velocity.y += 1f;
        startJumpingState();   
    }
    
    public boolean noLivesLeft() {
    	return (lives <= 0);
    }
    
    private static boolean canAccelerate(int state) {
    	switch (state) {
        case WILLY_STATE_JUMP:
        case WILLY_STATE_RUN:
        case WILLY_STATE_RUN_EXIT:
        case WILLY_STATE_FALL:
        	return true;
        case WILLY_STATE_HIT:
        case WILLY_STATE_STUNNED:
        	return false;
        default:
        	return true;
    	}
    }

    private static boolean canFall(int state) {
    	switch (state) {
        case WILLY_STATE_JUMP:
        case WILLY_STATE_RUN_EXIT:
        case WILLY_STATE_FALL:
        case WILLY_STATE_HIT:
        case WILLY_STATE_STUNNED:
        	return true;
        case WILLY_STATE_RUN:
        	return false;
        default:
        	return true;
    	}
    }}
