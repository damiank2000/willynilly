package com.objectteam.framework.gl;

public class TextureRegion {    
    public float u1, v1;
    public float u2, v2;
    public final Texture texture;
    private float width;
    private float height;
    
    public TextureRegion(Texture texture, float x, float y, float width, float height) {
        this.u1 = x / texture.width;
        this.v1 = y / texture.height;
        this.width = width;
        this.height = height;
        this.u2 = this.u1 + this.width / texture.width;
        this.v2 = this.v1 + this.height / texture.height;        
        this.texture = texture;
    }
    
    public void setX(float x) {
        this.u1 = x / this.texture.width;
        this.u2 = this.u1 + this.width / this.texture.width;    	
    }
}
