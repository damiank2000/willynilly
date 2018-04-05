package com.objectteam.willynilly;

import com.objectteam.framework.GameObject;

public class JetPack extends GameObject {
    public static float WIDTH = 0.5f;
    public static float HEIGHT = 0.5f;
    public CollectableState state = CollectableState.Collectable;

    public JetPack(float x, float y) {
        super(x, y, WIDTH, HEIGHT);
    }

    public void collect() {
        this.state = CollectableState.Collected;
    }
}
