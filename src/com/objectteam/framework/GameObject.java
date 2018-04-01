package com.objectteam.framework;

import com.objectteam.framework.math.BoundsShape;
import com.objectteam.framework.math.Circle;
import com.objectteam.framework.math.Rectangle;
import com.objectteam.framework.math.Vector2;

public class GameObject {
    public final Vector2 position;
    public final BoundsShape bounds;
    public final static int BOUNDS_SHAPE_RECTANGLE = 1;
    public final static int BOUNDS_SHAPE_CIRCLE = 2;
    public int boundsShape;
    
    public GameObject(float x, float y, float width, float height) {
        this.position = new Vector2(x,y);
        this.boundsShape = BOUNDS_SHAPE_RECTANGLE;
        this.bounds = new Rectangle(x-width/2, y-height/2, width, height);
    }
    
    public GameObject(float x, float y, float width, float height, int boundsShape) {
        this.position = new Vector2(x,y);
        this.boundsShape = boundsShape;
        if (boundsShape == BOUNDS_SHAPE_RECTANGLE) 
        	this.bounds = new Rectangle(x-width/2, y-height/2, width, height);
        else
        	this.bounds = new Circle(x-width/2, y-height/2, width);
    }
    
}
