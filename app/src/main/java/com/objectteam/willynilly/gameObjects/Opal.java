package com.objectteam.willynilly.gameObjects;

import com.objectteam.framework.GameObject;
import com.objectteam.framework.Updatable;
import com.objectteam.willynilly.CollectableState;

public class Opal extends GameObject implements Updatable {
    public static final float WIDTH = 0.5f;
    public static final float HEIGHT = 0.8f;
    public static final int SCORE = 10;

    public CollectableState state = CollectableState.Collectable;
    
    float stateTime;
    public Opal(float x, float y) {
        super(x, y, WIDTH, HEIGHT);
        stateTime = 0;
    }
    
    public void update(float deltaTime) {
        stateTime += deltaTime;
    }
    
    public void collect() {
    	this.state = CollectableState.Collected;
    	stateTime = 0;
    }

    public float getStateTime() {
        return stateTime;
    }
}
