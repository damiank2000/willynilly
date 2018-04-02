package com.objectteam.framework.math;

public class OverlapTester {
    public static boolean overlapCircles(Circle c1, Circle c2) {
        float distance = c1.center.distSquared(c2.center);
        float radiusSum = c1.radius + c2.radius;
        return distance <= radiusSum * radiusSum;
    }
        
    public static boolean overlapRectangles(Rectangle r1, Rectangle r2) {
        if(r1.getLowerLeft().x < r2.getLowerLeft().x + r2.getWidth() &&
           r1.getLowerLeft().x + r1.getWidth() > r2.getLowerLeft().x &&
           r1.getLowerLeft().y < r2.getLowerLeft().y + r2.getHeight() &&
           r1.getLowerLeft().y + r1.getHeight() > r2.getLowerLeft().y)
            return true;
        else
            return false;
    }
    
    public static boolean overlapCircleRectangle(Circle c, Rectangle r) {
        float closestX = c.center.x;
        float closestY = c.center.y;
        
        if(c.center.x < r.getLowerLeft().x) {
            closestX = r.getLowerLeft().x; 
        } 
        else if(c.center.x > r.getLowerLeft().x + r.getWidth()) {
            closestX = r.getLowerLeft().x + r.getWidth();
        }
          
        if(c.center.y < r.getLowerLeft().y) {
            closestY = r.getLowerLeft().y;
        } 
        else if(c.center.y > r.getLowerLeft().y + r.getHeight()) {
            closestY = r.getLowerLeft().y + r.getHeight();
        }
        
        return c.center.distSquared(closestX, closestY) < c.radius * c.radius;           
    }
    
    public static boolean pointInCircle(Circle c, Vector2 p) {
        return c.center.distSquared(p) < c.radius * c.radius;
    }
    
    public static boolean pointInCircle(Circle c, float x, float y) {
        return c.center.distSquared(x, y) < c.radius * c.radius;
    }
    
    public static boolean pointInRectangle(Rectangle r, Vector2 p) {
        return r.getLowerLeft().x <= p.x && r.getLowerLeft().x + r.getWidth() >= p.x &&
               r.getLowerLeft().y <= p.y && r.getLowerLeft().y + r.getHeight() >= p.y;
    }
    
    public static boolean pointInRectangle(Rectangle r, float x, float y) {
        return r.getLowerLeft().x <= x && r.getLowerLeft().x + r.getWidth() >= x &&
               r.getLowerLeft().y <= y && r.getLowerLeft().y + r.getHeight() >= y;
    }
}