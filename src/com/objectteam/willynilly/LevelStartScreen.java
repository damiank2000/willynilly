package com.objectteam.willynilly;

import java.util.List;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import com.objectteam.framework.Game;
import com.objectteam.framework.Input.TouchEvent;
import com.objectteam.framework.Sound;
import com.objectteam.framework.gl.Camera2D;
import com.objectteam.framework.gl.SpriteBatcher;
import com.objectteam.framework.gl.TextureRegion;
import com.objectteam.framework.impl.GLScreen;

public class LevelStartScreen extends GLScreen {
    Camera2D guiCam;
    SpriteBatcher batcher;
    Random random = new Random();
    
    static final int SCREEN_WIDTH = 480;
    static final int SCREEN_HEIGHT = 320;
    static final float SCREEN_DISPLAY_DURATION = 3f;
    float stateTime = 0;
    boolean soundPlayed = false;
    World world;
    String message1;
    String message2;
    TextureRegion graphic;
    Sound sound;
    
    public LevelStartScreen(Game game, World world) {
        super(game);
        this.world = world;
        this.world.reset();
        guiCam = new Camera2D(glGraphics, SCREEN_WIDTH, SCREEN_HEIGHT);
        batcher = new SpriteBatcher(glGraphics, 100);
        soundPlayed = false;
        message1 = "LEVEL " + world.level;
        message2 = world.levelName;
        graphic = Assets.ready;
        sound = Assets.levelStartSound;
    }    
    
    @Override
    public void update(float deltaTime) {
        if (!soundPlayed) {
        	soundPlayed = true;
        	Assets.playSound(sound);
        }
    	
    	List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        
        stateTime += deltaTime;
        
        if (stateTime >= SCREEN_DISPLAY_DURATION || touchedScreen(touchEvents)) {
            game.setScreen(new GameScreen(game, world));
            return;
        }
        
    }

	private boolean touchedScreen(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);                        
            if(event.type == TouchEvent.TOUCH_UP) {
                return true;
            }
        }
        return false;
	}

    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGL();        
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();
        guiCam.position.x = (guiCam.frustumWidth / 2);
        
        gl.glEnable(GL10.GL_TEXTURE_2D);      
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);               
        
        batcher.beginBatch(Assets.items);                 
        batcher.drawSprite((SCREEN_WIDTH / 2), (SCREEN_HEIGHT / 2), SCREEN_WIDTH, SCREEN_HEIGHT, Assets.blankRegion);
	    batcher.drawSprite(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 192, 32, graphic);
	    float message1Width = Assets.font.glyphWidth * message1.length();
	    Assets.font.drawText(batcher, message1, (SCREEN_WIDTH / 2) - message1Width / 2, SCREEN_HEIGHT -20);
	    float message2Width = Assets.font.glyphWidth * message2.length();
	    Assets.font.drawText(batcher, message2, (SCREEN_WIDTH / 2) - message2Width / 2, SCREEN_HEIGHT -60);
	    batcher.endBatch();
        
        gl.glDisable(GL10.GL_BLEND);
    }
    
    @Override
    public void pause() {        
    }

    @Override
    public void resume() {        
    }       

    @Override
    public void dispose() {        
    }
}
