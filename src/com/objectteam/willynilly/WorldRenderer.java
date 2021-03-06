package com.objectteam.willynilly;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

import com.objectteam.framework.GameObject;
import com.objectteam.framework.gl.Animation;
import com.objectteam.framework.gl.Camera2D;
import com.objectteam.framework.gl.SpriteBatcher;
import com.objectteam.framework.gl.TextureRegion;
import com.objectteam.framework.impl.GLGraphics;

public class WorldRenderer {
    static final float FRUSTUM_WIDTH = 15;
    static final float FRUSTUM_HEIGHT = 10;    
    GLGraphics glGraphics;
    World world;
    Camera2D cam;
    SpriteBatcher batcher;    
    
    public WorldRenderer(GLGraphics glGraphics, SpriteBatcher batcher, World world) {
        this.glGraphics = glGraphics;
        this.world = world;
        this.cam = new Camera2D(glGraphics, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        this.batcher = batcher;        
    }
    
    public void render() {
    	if (world.state == World.WORLD_STATE_RUNNING) {
    		cam.position.x = world.willy.position.x + (cam.frustumWidth/2) - (Willy.WILLY_WIDTH * 2);
    		cam.position.y = Math.max(world.willy.position.y, cam.frustumHeight/2);
    	}
        cam.setViewportAndMatrices();
        renderBackground();
        renderObjects();        
    }
    
    public void renderBackground() {
    	float backgroundX = cam.position.x * 3;
        batcher.beginBatch(Assets.background);
        Assets.backgroundRegion.setX(backgroundX);
        batcher.drawSprite(cam.position.x, cam.position.y,
                           FRUSTUM_WIDTH, FRUSTUM_HEIGHT, 
                           Assets.backgroundRegion);
        batcher.endBatch();
    }
    
    public void renderObjects() {
        GL10 gl = glGraphics.getGL();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        
        batcher.beginBatch(Assets.items);
        renderDecoration(world.backgroundDecoration);
        renderPlatforms();
        renderWilly();
        renderItems();
        renderBats();
        renderWaftyBirds();
        renderEchidnas();
        renderFinish();
        renderDecoration(world.foregroundDecoration);
        batcher.endBatch();
        gl.glDisable(GL10.GL_BLEND);
    }
    
    private void renderWilly() {
        TextureRegion keyFrame;
        switch(world.willy.state) {
        case Willy.WILLY_STATE_FALL:
            keyFrame = Assets.fallingWilly.getKeyFrame(world.willy.stateTime, Animation.ANIMATION_LOOPING);
            break;
        case Willy.WILLY_STATE_JUMP:
            keyFrame = Assets.jumpingWilly;
            break;
        case Willy.WILLY_STATE_RUN:
        case Willy.WILLY_STATE_RUN_EXIT:
            keyFrame = Assets.runningWilly.getKeyFrame(world.willy.stateTime, Animation.ANIMATION_LOOPING);
            break;
        case Willy.WILLY_STATE_HIT:
        case Willy.WILLY_STATE_STUNNED:
        default:
            keyFrame = Assets.stunnedWilly.getKeyFrame(world.willy.stateTime, Animation.ANIMATION_LOOPING);
        }
        
        float side = world.willy.velocity.x < 0? -1: 1;        
        batcher.drawSprite(world.willy.position.x, world.willy.position.y, side * 2, 1.33f, keyFrame);
        if (Settings.debugMode)
        	drawBounds(world.willy);
    }
    
    private void renderPlatforms() {
        int len = world.platforms.size();
        for(int i = 0; i < len; i++) {
            Platform platform = world.platforms.get(i);
            TextureRegion keyFrame;
            switch (platform.platformType) {
            case 1:
                keyFrame = Assets.platform1;
                break;
            case 2:
                keyFrame = Assets.rockTopLeft;
                break;
            case 3:
                keyFrame = Assets.rockTopCentre;
                break;
            case 4:
                keyFrame = Assets.rockTopRight;
                break;
            default:
                keyFrame = Assets.platform1;
                break;
            }
            if(platform.state == Platform.PLATFORM_STATE_PULVERIZING) {                
                keyFrame = Assets.brakingPlatform.getKeyFrame(platform.stateTime, Animation.ANIMATION_NONLOOPING);
            }            
                                   
            batcher.drawSprite(platform.position.x, platform.position.y, 2, 1, keyFrame);
            if (Settings.debugMode)
            	drawBounds(platform);
        }
    }
    
	private void renderDecoration(List<Decoration> decorations) {
		int len = decorations.size();
        for(int i = 0; i < len; i++) {
            Decoration decoration = decorations.get(i);
            TextureRegion keyFrame;
            switch (decoration.decorationType) {
	            case Decoration.DECORATION_TYPE_TUNNEL_BACKGROUND:
	            	keyFrame = Assets.rockTunnelBackground;
	            	break;
	            case Decoration.DECORATION_TYPE_TUNNEL_ENTRANCE_LEFT:
	            	keyFrame = Assets.rockTunnelLeft;
	            	break;
	            case Decoration.DECORATION_TYPE_TUNNEL_EXIT_RIGHT:
	            	keyFrame = Assets.rockTunnelRight;
	            	break;
	            case Decoration.DECORATION_TYPE_TUNNEL_WALL:
	            	keyFrame = Assets.rockTopCentre;
	            	break;
	            case Decoration.DECORATION_TYPE_ROCK_TUNNEL_LEFT:
	            	keyFrame = Assets.rockTopLeft;
	            	break;
	            case Decoration.DECORATION_TYPE_ROCK_TUNNEL_CENTRE:
	            	keyFrame = Assets.rockTopCentre;
	            	break;
	            case Decoration.DECORATION_TYPE_ROCK_TUNNEL_RIGHT:
	            	keyFrame = Assets.rockTopRight;
	            	break;
	            default:
	                keyFrame = Assets.tunnel;
	                break;
            }
            batcher.drawSprite(decoration.position.x, decoration.position.y, 2, 1, keyFrame);
        }
	}

    private void renderItems() {
        int len = world.wombatSigns.size();
        for(int i = 0; i < len; i++) {
            GameObject wombatSign = world.wombatSigns.get(i);
            TextureRegion keyFrame = Assets.staticWombatSign;
            batcher.drawSprite(wombatSign.position.x, wombatSign.position.y, 2, 3, keyFrame);
        }

        len = world.opals.size();
        for(int i = 0; i < len; i++) {
            Opal opal = world.opals.get(i);
            TextureRegion keyFrame;
            switch (opal.state) {
            case Opal.WORM_STATE_WIGGLING:
            	keyFrame = Assets.opalAnim.getKeyFrame(opal.stateTime, Animation.ANIMATION_LOOPING);
            	break;
            case Opal.WORM_STATE_EATEN:
            	keyFrame = Assets.scoreAnim.getKeyFrame(opal.stateTime, Animation.ANIMATION_NONLOOPING);
            	break;
            default:
            	keyFrame = Assets.opalAnim.getKeyFrame(opal.stateTime, Animation.ANIMATION_LOOPING);
            	break;
            }
            batcher.drawSprite(opal.position.x, opal.position.y, 1, 1, keyFrame);
            if (Settings.debugMode)
            	drawBounds(opal);
        }

        len = world.fans.size();
        for(int i = 0; i < len; i++) {
            Fan fan = world.fans.get(i);
            TextureRegion keyFrame = Assets.fan.getKeyFrame(fan.stateTime, Animation.ANIMATION_LOOPING);
            batcher.drawSprite(fan.position.x, fan.position.y, 1, 1, keyFrame);
        }
    }
    
    private void renderBats() {
        int len = world.bats.size();
        for(int i = 0; i < len; i++) {
            Bat bat = world.bats.get(i);
            TextureRegion keyFrame = Assets.bat.getKeyFrame(bat.stateTime, Animation.ANIMATION_LOOPING);
            float side = bat.velocity.x < 0?-1:1;
            batcher.drawSprite(bat.position.x, bat.position.y, side * 1, 1, keyFrame);
            if (Settings.debugMode)
            	drawBounds(bat);
        }
    }
    
    private void renderWaftyBirds() {
        int len = world.waftyBirds.size();
        for(int i = 0; i < len; i++) {
            WaftyBird waftyBird = world.waftyBirds.get(i);
            TextureRegion keyFrame = Assets.waftyBird.getKeyFrame(waftyBird.stateTime, Animation.ANIMATION_LOOPING);
            float side = waftyBird.velocity.x < 0?-1:1;
            batcher.drawSprite(waftyBird.position.x, waftyBird.position.y, side * 1, 1, keyFrame);
            if (Settings.debugMode)
            	drawBounds(waftyBird);
        }
    }

    private void renderEchidnas() {
        int len = world.echidnas.size();
        for(int i = 0; i < len; i++) {
            Echidna echidna = world.echidnas.get(i);
            TextureRegion keyFrame = Assets.walkingEchidna.getKeyFrame(echidna.stateTime, Animation.ANIMATION_LOOPING);
            float side = echidna.velocity.x < 0?-1:1;
            batcher.drawSprite(echidna.position.x, echidna.position.y, side * 2, 1.33f, keyFrame);
            if (Settings.debugMode)
            	drawBounds(echidna);
        }
    }

    private void renderFinish() {
    	int len = world.finishes.size();
        for(int i = 0; i < len; i++) {
        	Finish nest = world.finishes.get(i);
        	TextureRegion finish;
        	if (world.state == World.WORLD_STATE_NEXT_LEVEL) {
        		finish = Assets.wombatSign.getKeyFrame(nest.stateTime, Animation.ANIMATION_LOOPING);
        	}
        	else {
        		finish = Assets.staticWombatSign;
        	}
        	batcher.drawSprite(nest.position.x, nest.position.y, 2, 3, finish);
        }
    }
    
    private void drawBounds(GameObject object) {
    	batcher.drawSprite(object.position.x, object.position.y, object.bounds.getWidth(), object.bounds.getHeight(), Assets.boundingRectangleRegion);
    }
}
