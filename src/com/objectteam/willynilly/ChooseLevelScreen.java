package com.objectteam.willynilly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import com.objectteam.framework.Game;
import com.objectteam.framework.Input.TouchEvent;
import com.objectteam.framework.gl.Animation;
import com.objectteam.framework.gl.Camera2D;
import com.objectteam.framework.gl.SpriteBatcher;
import com.objectteam.framework.gl.TextureRegion;
import com.objectteam.framework.impl.GLScreen;
import com.objectteam.framework.math.OverlapTester;
import com.objectteam.framework.math.Rectangle;
import com.objectteam.framework.math.Vector2;

public class ChooseLevelScreen extends GLScreen {
    Camera2D guiCam;
    SpriteBatcher batcher;
    ArrayList<PushButton> levelButtons;
    PushButton backButton;
    Vector2 touchPoint;
    
    static final int SCREEN_WIDTH = 480;
    static final int SCREEN_HEIGHT = 320;
    
    World world;
    WorldListener worldListener;
    int startingLevel = 1;
    
    public ChooseLevelScreen(Game game) {
        super(game);
        guiCam = new Camera2D(glGraphics, SCREEN_WIDTH, SCREEN_HEIGHT);
        batcher = new SpriteBatcher(glGraphics, 100);
        backButton = new PushButton(0, 0, 64, 64);
        levelButtons = new ArrayList<PushButton>();
        for (int y=0; y<=3; y++) {
        	for (int x=0; x<=4; x++) {
        		int levelNumber = ((y * 5) + x + 1);
        		if (Settings.unlockedLevel >= levelNumber) {
        			levelButtons.add(new PushButton((x * 50) + 50, (SCREEN_HEIGHT - (y * 50) - 40), 48, 40));
        		}
        	}
        }        
        touchPoint = new Vector2();
    }       

    
    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
            	
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);                        
            if(event.type == TouchEvent.TOUCH_UP) {
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);
                
                if(backButton.pushed(touchPoint)) {
                	Assets.playSound(Assets.clickSound);
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
                
                for (int j = 0; j < levelButtons.size(); j++) {
                	if (levelButtons.get(j).pushed(touchPoint)) {
                		startNewGame(j + 1);
                		return;
                	}
                }

            }
        }
    }

	private void startNewGame(int level) {
		Assets.playSound(Assets.clickSound);
		Assets.titleMusic.stop();
		worldListener = new WorldListener() {
		    @Override
		    public void jump() {            
		        Assets.playSound(Assets.jumpSound);
		    }

		    @Override
		    public void highJump() {
		        Assets.playSound(Assets.fanSound);
		    }

		    @Override
		    public void hit() {
		        Assets.playSound(Assets.hitSound);
		    }

		    @Override
		    public void coin() {
		        Assets.playSound(Assets.collectSound);
		    }                      

		    @Override
		    public void boing() {
		        Assets.playSound(Assets.boingSound);
		    }                      

		    @Override
		    public void gameOver() {
		        Assets.playSound(Assets.gameOverSound);
		    }
		    
		    @Override
		    public void complete() {
		    	Assets.backgroundMusic.stop();
		        Assets.playSound(Assets.completeSound);
		    }

		};
		
		world = new World(worldListener, level);
		game.setScreen(new LevelStartScreen(game, world));
	}

    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGL();        
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();
        guiCam.position.x = (guiCam.frustumWidth / 2);
        
        gl.glEnable(GL10.GL_TEXTURE_2D);
        
        batcher.beginBatch(Assets.background);
        batcher.drawSprite((SCREEN_WIDTH / 2), (SCREEN_HEIGHT / 2), SCREEN_WIDTH, SCREEN_HEIGHT, Assets.backgroundRegion);
        batcher.endBatch();
        
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);               
        
        batcher.beginBatch(Assets.items);
        for (int y=0; y<=3; y++) {
        	for (int x=0; x<=4; x++) {
        		int levelNumber = ((y * 5) + x + 1);
        		batcher.drawSprite((x * 50) + 50 + 24, (SCREEN_HEIGHT - (y * 50) - 40), 48, 40, Assets.levelChoiceBackground);
    			String levelString = "" + levelNumber;
    			int levelStringWidth = Assets.font.glyphWidth * levelString.length();
        		Assets.font.drawText(batcher, levelString , (x * 50) + 80 - (levelStringWidth / 2), (SCREEN_HEIGHT - (y * 50) - 40));
        		if (Settings.unlockedLevel < levelNumber) {
        			batcher.drawSprite((x * 50) + 50 + 24, (SCREEN_HEIGHT - (y * 50) - 40), 48, 40, Assets.lock);
        		}
        	}
        }

        batcher.drawSprite(32, 32, 64, 64, Assets.arrow);
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
