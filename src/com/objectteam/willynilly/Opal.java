package com.objectteam.willynilly;

import com.objectteam.framework.GameObject;
import com.objectteam.framework.Updatable;

public class Opal extends GameObject implements Updatable {
    public static final float WORM_WIDTH = 0.5f;
    public static final float WORM_HEIGHT = 0.8f;
    public static final int WORM_SCORE = 10;
    
    public static final int WORM_STATE_WIGGLING = 1;
    public static final int WORM_STATE_EATEN = 2;

    public int state = WORM_STATE_WIGGLING;
    
    float stateTime;
    public Opal(float x, float y) {
        super(x, y, WORM_WIDTH, WORM_HEIGHT);
        stateTime = 0;
    }
    
    public void update(float deltaTime) {
        stateTime += deltaTime;
    }
    
    public void eat() {
    	this.state = WORM_STATE_EATEN;
    	stateTime = 0;
    }
}
