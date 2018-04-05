package com.objectteam.willynilly.gameObjects;

import com.objectteam.framework.DynamicGameObject;
import com.objectteam.framework.GameObject;
import com.objectteam.willynilly.CharacterState;
import com.objectteam.willynilly.World;

public class DemoWilly extends DynamicGameObject{
    public static final float WIDTH = 1.8f;
    public static final float HEIGHT = 1.2f;
    private final float rangeWidth;
    private final float rangeHeight;

    CharacterState state;
    private float stateTime;

    public DemoWilly(float x, float y, float rangeWidth, float rangeHeight) {
        super(x, y, WIDTH, HEIGHT, GameObject.BOUNDS_SHAPE_RECTANGLE);
        this.rangeWidth = rangeWidth;
        this.rangeHeight = rangeHeight;
        startFallingState();   
    }

    @Override
    public void update(float deltaTime) {

        super.update(deltaTime);

        if (position.x > rangeWidth)
            position.x = 0;
        else if (position.x < 0)
            position.x = rangeWidth;
        if (position.y > rangeHeight)
            position.y = 0;
        else if (position.y < 0)
            position.y = rangeHeight;
        stateTime += deltaTime;
               
        stateTime += deltaTime;
    }
    
    public void startFallingState() {
        state = CharacterState.Falling;
        stateTime = 0;    	
    }

    public void startJumpingState() {
        state = CharacterState.Jumping;
        stateTime = 0;    	
    }

    public CharacterState getState() {
        return state;
    }

    public float getStateTime() {
        return stateTime;
    }
}
