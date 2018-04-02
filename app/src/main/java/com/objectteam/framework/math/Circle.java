package com.objectteam.framework.math;

public class Circle implements BoundsShape {
    public final Vector2 center = new Vector2();
    public float radius;

    public Circle(float x, float y, float radius) {
        this.center.set(x,y);
        this.radius = radius;
    }
    
    public void setPosition(Vector2 position) {
    	this.center.set(position);
    }
    
    public Vector2 getLowerLeft() {
    	return center.sub(radius, radius);
    }
    
    public float getWidth() {
    	return radius;
    }
    
    public float getHeight() {
    	return radius;
    }
}
