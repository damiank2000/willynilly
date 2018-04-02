package com.objectteam.willynilly;

import com.objectteam.framework.math.OverlapTester;
import com.objectteam.framework.math.Rectangle;
import com.objectteam.framework.math.Vector2;

public class PushButton {

    Rectangle bounds;
    
    public PushButton(float x, float y, float width, float height) {
    	bounds = new Rectangle(x, y, width, height);	
    }
    
    public boolean pushed(Vector2 touchPoint) {
    	return OverlapTester.pointInRectangle(bounds, touchPoint);
    }
}
