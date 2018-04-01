package com.objectteam.willynilly;

import java.util.List;
import java.util.Timer;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

import com.objectteam.framework.Game;
import com.objectteam.framework.Input.TouchEvent;
import com.objectteam.framework.gl.Animation;
import com.objectteam.framework.gl.Camera2D;
import com.objectteam.framework.gl.FPSCounter;
import com.objectteam.framework.gl.SpriteBatcher;
import com.objectteam.framework.gl.TextureRegion;
import com.objectteam.framework.impl.GLScreen;
import com.objectteam.framework.math.OverlapTester;
import com.objectteam.framework.math.Rectangle;
import com.objectteam.framework.math.Vector2;

public class GameScreen extends GLScreen {
    static final int GAME_READY = 0;    
    static final int GAME_RUNNING = 1;
    static final int GAME_PAUSED = 2;
    static final int GAME_LEVEL_END = 3;
    static final int GAME_LOST_LIFE = 4;
    static final int GAME_OVER = 5;
    
    static final int SCREEN_WIDTH = 480;
    static final int SCREEN_HEIGHT = 320;
    
    static final float SCREEN_LOST_LIFE_DISPLAY_DURATION = 3f;
  
    int state;
    Camera2D guiCam;
    Vector2 touchPoint;
    SpriteBatcher batcher;    
    World world;
    WorldListener worldListener;
    WorldRenderer renderer;    
    Rectangle resumeBounds;
    Rectangle quitBounds;
    int lastScore;
    int lastLives;
    String scoreString;   
    String livesString;
    FPSCounter fpsCounter;
    float stateTime = 0;
    float lifeStateTime = 0;
    
    public GameScreen(Game game, World world) {
        super(game);
        this.world = world;
        setState(GAME_RUNNING);
        guiCam = new Camera2D(glGraphics, SCREEN_WIDTH, SCREEN_HEIGHT);
        touchPoint = new Vector2();
        batcher = new SpriteBatcher(glGraphics, 5000);
        renderer = new WorldRenderer(glGraphics, batcher, world);
        resumeBounds = new Rectangle((SCREEN_WIDTH / 2) - 96, SCREEN_HEIGHT / 2, 192, 36);
        quitBounds = new Rectangle((SCREEN_WIDTH / 2) - 96, (SCREEN_HEIGHT / 2) - 36, 192, 36);
        lastScore = 0;
        scoreString = "score: 0";
        livesString = "lives: " + world.willy.lives;
        lifeStateTime = 0;
        fpsCounter = new FPSCounter();
    }

	@Override
	public void update(float deltaTime) {
	    if(deltaTime > 0.1f)
	        deltaTime = 0.1f;
	    
	    stateTime += deltaTime;
	    lifeStateTime += deltaTime;
	    
	    switch(state) {
	    case GAME_READY:
	        updateReady();
	        break;
	    case GAME_RUNNING:
	        updateRunning(deltaTime);
	        break;
	    case GAME_PAUSED:
	        updatePaused();
	        break;
	    case GAME_LEVEL_END:
	        updateLevelEnd(deltaTime);
	        break;
	    case GAME_LOST_LIFE:
	        updateLostLife();
	        break;
	    case GAME_OVER:
	        updateGameOver();
	        break;
	    }
	}
	
	private void setState(int state) {
		this.state = state;
		stateTime = 0;
		lifeStateTime = 0;
		if (state == GAME_RUNNING) {
			Assets.playMusic(Assets.backgroundMusic);
		}
		else {
	        Assets.stopMusic(Assets.backgroundMusic);
		}
	}

	private void updateReady() {
        Assets.stopMusic(Assets.backgroundMusic);
    	game.setScreen(new LevelStartScreen(game, world));
	}
	
	private void updateRunning(float deltaTime) {

	    List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	    if (world.willy.state == Willy.WILLY_STATE_RUN) {
		    int len = touchEvents.size();
		    for(int i = 0; i < len; i++) {
		        TouchEvent event = touchEvents.get(i);
		        if (event.type == TouchEvent.TOUCH_DOWN) {
		        	world.willy.jump();
		        	Assets.playSound(Assets.boingSound);
		        	return;
		        }

		    }
	    }
	    
	    world.update(deltaTime, game.getInput().getAccelY());
	    if(world.score != lastScore) {
	        lastScore = world.score;
	        scoreString = "score: " + lastScore;
	    }
	    if(world.willy.lives != lastLives) {
	    	lastLives = world.willy.lives;
	    	livesString = "lives: " + lastLives;
	    }
	    if(world.state == World.WORLD_STATE_NEXT_LEVEL) {
	        setState(GAME_LEVEL_END);        
	    }
	    if(world.state == World.WORLD_STATE_LOST_LIFE) {
	        setState(GAME_LOST_LIFE);
	    }

	}
	
	private void updatePaused() {
	    List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	    int len = touchEvents.size();
	    for(int i = 0; i < len; i++) {
	        TouchEvent event = touchEvents.get(i);
	        if(event.type != TouchEvent.TOUCH_UP)
	            continue;
	        
	        touchPoint.set(event.x, event.y);
	        guiCam.touchToWorld(touchPoint);
	        
	        if(OverlapTester.pointInRectangle(resumeBounds, touchPoint)) {
	            Assets.playSound(Assets.clickSound);
	            setState(GAME_RUNNING);
	            return;
	        }
	        
	        if(OverlapTester.pointInRectangle(quitBounds, touchPoint)) {
	            Assets.playSound(Assets.clickSound);
	            Assets.stopMusic(Assets.backgroundMusic);
	            game.setScreen(new MainMenuScreen(game));
	            return;
	        
	        }
	    }
	}
	
	private void updateLevelEnd(float deltaTime) {
	    world.update(deltaTime, game.getInput().getAccelX());
	    List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	    int len = touchEvents.size();
	    for(int i = 0; i < len; i++) {                   
	        TouchEvent event = touchEvents.get(i);
	        if(event.type != TouchEvent.TOUCH_UP)
	            continue;
	        world.newLevel();
	        renderer = new WorldRenderer(glGraphics, batcher, world);
	        world.score = lastScore;
	        setState(GAME_READY);
	    }
	}
	
	private void updateLostLife() {
    	if (stateTime >= SCREEN_LOST_LIFE_DISPLAY_DURATION) {
    		if (world.willy.lives <= 0) {
    			setState(GAME_OVER);
    		}
    		else {
    			setState(GAME_READY);
    		}
    	}
	}

	private void updateGameOver() {
        if(lastScore >= Settings.highscores[4]) 
            scoreString = "new highscore: " + lastScore;
        else
            scoreString = "score: " + lastScore;
        Settings.addScore(lastScore);
        Settings.save(game.getFileIO());
        Assets.stopMusic(Assets.backgroundMusic);
    	game.setScreen(new GameOverScreen(game, world, scoreString));
	}

	@Override
	public void present(float deltaTime) {
	    GL10 gl = glGraphics.getGL();
	    gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    gl.glEnable(GL10.GL_TEXTURE_2D);
	    
	    renderer.render();
	    
	    guiCam.setViewportAndMatrices();
	    gl.glEnable(GL10.GL_BLEND);
	    gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
	    batcher.beginBatch(Assets.items);
	    switch(state) {
	    case GAME_READY:
	        presentReady();
	        break;
	    case GAME_RUNNING:
	        presentRunning(deltaTime);
	        break;
	    case GAME_PAUSED:
	        presentPaused();
	        break;
	    case GAME_LEVEL_END:
	        presentLevelEnd();
	        break;
	    case GAME_LOST_LIFE:
	        presentLostLife();
	        break;
	    case GAME_OVER:
	        presentGameOver();
	        break;
	    }
	    batcher.endBatch();
	    gl.glDisable(GL10.GL_BLEND);
	    fpsCounter.logFrame();
	}
	
	private void presentReady() {
	    batcher.drawSprite(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 192, 32, Assets.ready);
	}
	
	private void presentRunning(float deltaTime) {
	    Assets.font.drawText(batcher, scoreString, 16, SCREEN_HEIGHT-20);
	    presentRemainingLives();
	}

	
	private void presentLostLife() {
	    Assets.font.drawText(batcher, scoreString, 16, SCREEN_HEIGHT-20);
	    presentRemainingLives();
    	TextureRegion keyFrame = Assets.lifeRemoving.getKeyFrame(lifeStateTime, Animation.ANIMATION_LOOPING);
    	batcher.drawSprite(SCREEN_WIDTH - 150 + (world.willy.lives * 40), SCREEN_HEIGHT - 20, 25, 25, keyFrame);
	}
	
	private void presentRemainingLives() {
		for (int f=0; f < world.willy.lives; f++) {
	    	TextureRegion keyFrame = Assets.life.getKeyFrame(lifeStateTime, Animation.ANIMATION_LOOPING);
	    	batcher.drawSprite(SCREEN_WIDTH - 150 + (f * 40), SCREEN_HEIGHT - 20, 25, 25, keyFrame);
	    }
	}

	private void presentPaused() {        
	    batcher.drawSprite((SCREEN_WIDTH / 2), (SCREEN_HEIGHT / 2), 192, 96, Assets.pauseMenu);
	    Assets.font.drawText(batcher, scoreString, 16, SCREEN_HEIGHT-20);
	}
	
	private void presentLevelEnd() {
	    String topText = "Level " + (world.level-1) + " complete!";
	    String timeTakenText = "In " + world.timer + " seconds!";
	    String bottomText = "Ready for the next level...";
	    float topWidth = Assets.font.glyphWidth * topText.length();
	    float timeTakenWidth = Assets.font.glyphWidth * timeTakenText.length();
	    float bottomWidth = Assets.font.glyphWidth * bottomText.length();
	    Assets.font.drawText(batcher, topText, (SCREEN_WIDTH / 2) - topWidth / 2, SCREEN_HEIGHT - 40);
	    Assets.font.drawText(batcher, bottomText, (SCREEN_WIDTH / 2) - bottomWidth / 2, 40);
	    if (Settings.debugMode)
	    	Assets.font.drawText(batcher, timeTakenText, (SCREEN_WIDTH / 2) - timeTakenWidth / 2, SCREEN_HEIGHT / 2);
	}


	private void presentGameOver() {
	    batcher.drawSprite((SCREEN_WIDTH / 2), SCREEN_HEIGHT / 2, 160, 96, Assets.gameOver);        
	    float scoreWidth = Assets.font.glyphWidth * scoreString.length();
	    Assets.font.drawText(batcher, scoreString, (SCREEN_WIDTH / 2) - scoreWidth / 2, SCREEN_HEIGHT -20);
	}

	@Override
    public void pause() {
        if(state == GAME_RUNNING)
            setState(GAME_PAUSED);
    }

    @Override
    public void resume() {        
    }

    @Override
    public void dispose() {       
    }
}
