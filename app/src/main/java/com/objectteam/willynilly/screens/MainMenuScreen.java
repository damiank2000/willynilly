package com.objectteam.willynilly.screens;

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
import com.objectteam.framework.math.Vector2;
import com.objectteam.willynilly.Assets;
import com.objectteam.willynilly.PushButton;
import com.objectteam.willynilly.Settings;
import com.objectteam.willynilly.World;
import com.objectteam.willynilly.WorldListener;
import com.objectteam.willynilly.gameObjects.DemoWilly;
import com.objectteam.willynilly.screens.ChooseLevelScreen;
import com.objectteam.willynilly.screens.HighscoresScreen;
import com.objectteam.willynilly.screens.LevelStartScreen;

public class MainMenuScreen extends GLScreen {
    private Camera2D guiCam;
    private SpriteBatcher batcher;
    private PushButton soundButton;
    private PushButton playButton;
    private PushButton highScoresButton;
    private PushButton debugButton;
    private PushButton levelUpButton;
    private Vector2 touchPoint;
    private DemoWilly[] willies;
    private Random random = new Random();
    
    private static final int SCREEN_WIDTH = 480;
    private static final int SCREEN_HEIGHT = 320;
    private static final int MOVING_WILLIES = 10;

    private int startingLevel = 1;
    
    public MainMenuScreen(Game game) {
        super(game);
        guiCam = new Camera2D(glGraphics, SCREEN_WIDTH, SCREEN_HEIGHT);
        batcher = new SpriteBatcher(glGraphics, 100);
        soundButton = new PushButton(0, 0, 64, 64);
        playButton = new PushButton((SCREEN_WIDTH / 2) - 150, 130, 300, 72);
        highScoresButton = new PushButton((SCREEN_WIDTH / 2) - 150, 130 - 18 - 36, 300, 36);
        debugButton = new PushButton((SCREEN_WIDTH / 2) - 150, 130 - 18 - 36 - 36, 300, 36);
        levelUpButton = new PushButton((SCREEN_WIDTH - 64), 0, 64, 64);
        touchPoint = new Vector2();
        willies = new DemoWilly[MOVING_WILLIES];
        createWillies();
    }       

    private void createWillies() {
    	for (int i=0; i < MOVING_WILLIES; i++) {
    		float x = random.nextFloat() * guiCam.frustumWidth;
    		float y = random.nextFloat() * guiCam.frustumHeight;
    		float xVelocity = (random.nextFloat() - 0.5f) * 500;
    		float yVelocity = (random.nextFloat() - 0.5f) * 500;
    		willies[i] = new DemoWilly(x, y, guiCam.frustumWidth, guiCam.frustumHeight);
    		willies[i].velocity.set(xVelocity, yVelocity);
    		willies[i].startJumpingState();
    	}
    }
    
    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        
    	for (int i=0; i < MOVING_WILLIES; i++) {
    		willies[i].update(deltaTime);
    	}
    	
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);                        
            if(event.type == TouchEvent.TOUCH_UP) {
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);
                
                if(playButton.pushed(touchPoint)) {
                	Assets.playSound(Assets.clickSound);
                    game.setScreen(new ChooseLevelScreen(game));
                    return;
                }
                if(highScoresButton.pushed(touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    game.setScreen(new HighscoresScreen(game));
                    return;
                }
                if(levelUpButton.pushed(touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    startingLevel++;
                    if (startingLevel > 20) startingLevel = 1;
                    return;
                }
                if(soundButton.pushed(touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if(Settings.soundEnabled) 
                        Assets.titleMusic.play();
                    else
                        Assets.titleMusic.pause();
                }
                if (debugButton.pushed(touchPoint)) 
                {
                    //Assets.playSound(Assets.clickSound);
                    //Settings.debugMode = !Settings.debugMode;                
                }
            }
        }
    }

	private void startNewGame() {
		Assets.playSound(Assets.clickSound);
		Assets.titleMusic.stop();
        WorldListener worldListener = new WorldListener() {
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

        World world = new World(worldListener, startingLevel);
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
               
		for (int i=0; i < MOVING_WILLIES; i++) {
			drawWilly(willies[i]);
		}
        batcher.drawSprite((SCREEN_WIDTH / 2), SCREEN_HEIGHT - 10 - 71, 274, 142, Assets.logo);
        batcher.drawSprite((SCREEN_WIDTH / 2), 130, 200, 110, Assets.mainMenu);
        batcher.drawSprite(32, 32, 64, 64, Settings.soundEnabled?Assets.soundOn:Assets.soundOff);
        if (Settings.debugMode) {
        	String levelText = "L" + startingLevel;
	    	float levelTextWidth = Assets.font.glyphWidth * levelText.length();
	    	Assets.font.drawText(batcher, levelText, (SCREEN_WIDTH) - levelTextWidth, 40);
        }
	    batcher.endBatch();
        
        gl.glDisable(GL10.GL_BLEND);
    }
    
    private void drawWilly(DemoWilly willy) {
        TextureRegion keyFrame;
        switch(willy.getState()) {
        case Falling:
            keyFrame = Assets.fallingWilly.getKeyFrame(willy.getStateTime(), Animation.ANIMATION_LOOPING);
            break;
        case Jumping:
            keyFrame = Assets.runningWilly.getKeyFrame(willy.getStateTime(), Animation.ANIMATION_LOOPING);
            break;
        case Hit:
        default:
        	keyFrame = Assets.runningWilly.getKeyFrame(willy.getStateTime(), Animation.ANIMATION_LOOPING);
        }
        
        float side = willy.velocity.x < 0? -1: 1;        
        batcher.drawSprite(willy.position.x, willy.position.y, side * 64, 48, keyFrame);        
        if (Settings.debugMode) {
        	batcher.drawSprite(willy.position.x, willy.position.y, side * 64, 48, Assets.blankRegion);	
        }
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
