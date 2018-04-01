package com.objectteam.framework.math;

public interface BoundsShape {
	Vector2 getLowerLeft();
	float getWidth();
	float getHeight();
	void setPosition(Vector2 position);
}
