package com.objectteam.willynilly.screens;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.objectteam.framework.Game;
import com.objectteam.framework.Input.TouchEvent;
import com.objectteam.framework.gl.Camera2D;
import com.objectteam.framework.gl.SpriteBatcher;
import com.objectteam.framework.impl.GLScreen;
import com.objectteam.willynilly.Assets;
import com.objectteam.willynilly.Settings;

public class GameOverScreen extends GLScreen {
    private Camera2D guiCam;
    private SpriteBatcher batcher;

    private static final int SCREEN_WIDTH = 480;
    private static final int SCREEN_HEIGHT = 320;
    private static final float SCREEN_DISPLAY_DURATION = 3f;
    private float stateTime = 0;
    private boolean soundPlayed;
    private String scoreString;
    
    GameOverScreen(Game game, String scoreString) {
        super(game);
        guiCam = new Camera2D(glGraphics, SCREEN_WIDTH, SCREEN_HEIGHT);
        batcher = new SpriteBatcher(glGraphics, 100);
        soundPlayed = false;
        this.scoreString = scoreString;
    }       
    
    @Override
    public void update(float deltaTime) {
        if (!soundPlayed && Settings.soundEnabled) {
        	soundPlayed = true;
        	Assets.playSound(Assets.gameOverSound);
        }
    	
    	List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        
        stateTime += deltaTime;
        
        if (stateTime >= SCREEN_DISPLAY_DURATION || touchedScreen(touchEvents)) {
            if(Settings.soundEnabled) {
                Assets.backgroundMusic.stop();
                Assets.titleMusic.play();
            }
   	    	game.setScreen(new MainMenuScreen(game));
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
	    batcher.drawSprite((SCREEN_WIDTH / 2), SCREEN_HEIGHT / 2, 160, 96, Assets.gameOver);        
	    float scoreWidth = Assets.font.glyphWidth * scoreString.length();
	    Assets.font.drawText(batcher, scoreString, (SCREEN_WIDTH / 2) - scoreWidth / 2, SCREEN_HEIGHT -20);
        batcher.endBatch();
                
        gl.glDisable(GL10.GL_BLEND);
    }
    
    @Override
    public void pause() {        
        Settings.save(game.getFileIO());
    }

    @Override
    public void resume() {        
    }       

    @Override
    public void dispose() {        
    }
}
