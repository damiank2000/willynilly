package com.objectteam.willynilly;

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

public class MainMenuScreen extends GLScreen {
    Camera2D guiCam;
    SpriteBatcher batcher;
    PushButton soundButton;
    PushButton playButton;
    PushButton highscoresButton;
    PushButton debugButton;
    PushButton levelUpButton;
    Vector2 touchPoint;
    Willy[] willies;
    Random random = new Random();
    
    static final int SCREEN_WIDTH = 480;
    static final int SCREEN_HEIGHT = 320;
    static final int MOVING_WILLIES = 10;
    
    World world;
    WorldListener worldListener;
    int startingLevel = 1;
    
    public MainMenuScreen(Game game) {
        super(game);
        guiCam = new Camera2D(glGraphics, SCREEN_WIDTH, SCREEN_HEIGHT);
        batcher = new SpriteBatcher(glGraphics, 100);
        soundButton = new PushButton(0, 0, 64, 64);
        playButton = new PushButton((SCREEN_WIDTH / 2) - 150, 130, 300, 72);
        highscoresButton = new PushButton((SCREEN_WIDTH / 2) - 150, 130 - 18 - 36, 300, 36);
        debugButton = new PushButton((SCREEN_WIDTH / 2) - 150, 130 - 18 - 36 - 36, 300, 36);
        levelUpButton = new PushButton((SCREEN_WIDTH - 64), 0, 64, 64);
        touchPoint = new Vector2();
        willies = new Willy[MOVING_WILLIES];
        createWillies();
    }       

    private void createWillies() {
    	for (int i=0; i < MOVING_WILLIES; i++) {
    		float x = random.nextFloat() * guiCam.frustumWidth;
    		float y = random.nextFloat() * guiCam.frustumHeight;
    		float xVelocity = (random.nextFloat() - 0.5f) * 500;
    		float yVelocity = (random.nextFloat() - 0.5f) * 500;
    		willies[i] = new Willy(x, y);
    		willies[i].velocity.set(xVelocity, yVelocity);
    		willies[i].state = Willy.WILLY_STATE_JUMP;
    	}
    }
    
    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        
    	for (int i=0; i < MOVING_WILLIES; i++) {
    		moveWilly(willies[i], deltaTime);
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
                if(highscoresButton.pushed(touchPoint)) {
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
		
		world = new World(worldListener, startingLevel);
		game.setScreen(new LevelStartScreen(game, world));
	}

	private void moveWilly(Willy willy, float deltaTime) {
		willy.position.add(willy.velocity.x * deltaTime, willy.velocity.y * deltaTime);
		if (willy.position.x > guiCam.frustumWidth)
			willy.position.x = 0;
		else if (willy.position.x < 0) 
			willy.position.x = guiCam.frustumWidth;
		if (willy.position.y > guiCam.frustumHeight)
			willy.position.y = 0;
		else if (willy.position.y < 0) 
			willy.position.y = guiCam.frustumHeight;
		willy.stateTime += deltaTime;
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
    
    private void drawWilly(Willy willy) {
        TextureRegion keyFrame;
        switch(willy.state) {
        case Willy.WILLY_STATE_FALL:
            keyFrame = Assets.fallingWilly.getKeyFrame(willy.stateTime, Animation.ANIMATION_LOOPING);
            break;
        case Willy.WILLY_STATE_JUMP:
            keyFrame = Assets.runningWilly.getKeyFrame(willy.stateTime, Animation.ANIMATION_LOOPING);
            break;
        case Willy.WILLY_STATE_HIT:
        default:
        	keyFrame = Assets.runningWilly.getKeyFrame(willy.stateTime, Animation.ANIMATION_LOOPING);
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
