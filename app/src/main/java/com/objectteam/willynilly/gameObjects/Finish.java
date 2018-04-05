package com.objectteam.willynilly.gameObjects;

import com.objectteam.framework.DynamicGameObject;
import com.objectteam.framework.GameObject;

public class Finish extends DynamicGameObject {
    public static float FINISH_WIDTH = 1.7f;
    public static float FINISH_HEIGHT = 1.7f;

    float stateTime = 0;

    public Finish(float x, float y) {
        super(x, y, FINISH_WIDTH, FINISH_HEIGHT);
    }
        
    public void update(float deltaTime) {
        stateTime += deltaTime;
    }

    public float getStateTime() {
        return stateTime;
    }
}

