package com.objectteam.framework.math;

public class Rectangle implements BoundsShape {
    private final Vector2 lowerLeft;
    private float width, height;
    
    public Rectangle(float x, float y, float width, float height) {
        this.lowerLeft = new Vector2(x,y);
        this.width = width;
        this.height = height;
    }
    
    public void setPosition(Vector2 position) {
    	this.lowerLeft.set(position).sub(this.width / 2, this.height / 2);
    }
    
    public Vector2 getLowerLeft() {
    	return lowerLeft;
    }
    
    public float getWidth() {
    	return width;
    }
    
    public float getHeight() {
    	return height;
    }
}
