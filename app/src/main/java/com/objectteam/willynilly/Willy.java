package com.objectteam.willynilly;

import com.objectteam.framework.DynamicGameObject;
import com.objectteam.framework.GameObject;

public class Willy extends DynamicGameObject{
    public static final float JUMP_VELOCITY = 5.5f;
    public static final float MAX_RUN_VELOCITY = 10f;
    public static final float WIDTH = 1.8f;
    public static final float HEIGHT = 1.2f;
    public static final float RUN_EXIT_LAG_TIME = 0.5f;
    public static final float STUN_TIME = 2f;
    public static final float ACCELERATION = 1f;

    public static final float JET_PACK_VELOCITY = 20f;
    public static final float JET_PACK_DURATION = 2f;
    
    public int lives = 0;
    
    CharacterState state;
    float stateTime;    

    public Willy(float x, float y) {
        super(x, y, WIDTH, HEIGHT, GameObject.BOUNDS_SHAPE_RECTANGLE);
        startFallingState();   
    }

    @Override
    public void update(float deltaTime) {

        super.update(deltaTime);

    	if (state == CharacterState.Stunned)
    		if (stateTime > STUN_TIME)
    			startFallingState();
    	
        if (Willy.canFall(state)) {
    		velocity.add(World.gravity.x * deltaTime, World.gravity.y * deltaTime);
    	}
    	if (Willy.canAccelerate(state)) {
    		velocity.add(ACCELERATION /deltaTime, 0);
    		if (velocity.x > MAX_RUN_VELOCITY) {
    		    velocity.x = MAX_RUN_VELOCITY;
            }
    	}
    	
        if(state == CharacterState.Flying && stateTime > JET_PACK_DURATION) {
            startRunningState();
        }
                
        if(Willy.canStartFalling(state) && velocity.y < 0) {
        	if ((state == CharacterState.AboutToStopRunning && stateTime > RUN_EXIT_LAG_TIME)
        			|| state == CharacterState.Jumping) {
        		startFallingState();
        	}
        	else if(state == CharacterState.Running) {
        		startRunToFallTransitionState();
            }
        }
               
        stateTime += deltaTime;
    }
    
    public void startRunToFallTransitionState() {
        state = CharacterState.AboutToStopRunning;
        stateTime = 0;    	
    }
    
    public void startFallingState() {
        state = CharacterState.Falling;
        stateTime = 0;    	
    }

    public void startJumpingState() {
        state = CharacterState.Jumping;
        stateTime = 0;    	
    }

    public void startHitState() {
        state = CharacterState.Hit;
        stateTime = 0;    	
    }

    public void startRunningState() {
        state = CharacterState.Running;
        stateTime = 0;    	
    }   
    
    public void startStunnedState() {
    	state = CharacterState.Stunned;
    	stateTime = 0;
    }

    public void startFlyingState() {
        state = CharacterState.Flying;
        stateTime = 0;
    }
    
    public void jump() {
    	velocity.y = JUMP_VELOCITY;
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

    public void collectedJetPack() {
        velocity.set(JET_PACK_VELOCITY, JUMP_VELOCITY);
        startFlyingState();
    }

    private static boolean canAccelerate(CharacterState state) {
    	switch (state) {
            case Jumping:
            case Running:
            case AboutToStopRunning:
            case Falling:
                return true;
            case Hit:
            case Stunned:
            case Flying:
                return false;
            default:
                return true;
        }
    }

    private static boolean canFall(CharacterState state) {
    	switch (state) {
            case Jumping:
            case AboutToStopRunning:
            case Falling:
            case Hit:
            case Stunned:
                return true;
            case Running:
            case Flying:
                return false;
            default:
                return true;
        }
    }

    private static boolean canStartFalling(CharacterState state) {
        switch (state) {
            case Jumping:
            case AboutToStopRunning:
            case Falling:
            case Running:
                return true;
            case Flying:
            case Hit:
            case Stunned:
                return false;
            default:
                return true;
        }
    }

}
