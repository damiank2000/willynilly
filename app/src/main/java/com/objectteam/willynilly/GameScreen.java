package com.objectteam.willynilly;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

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

    private static final int SCREEN_WIDTH = 480;
    private static final int SCREEN_HEIGHT = 320;
    
    private static final float SCREEN_LOST_LIFE_DISPLAY_DURATION = 3f;
  
    private GameState state;
    private Camera2D guiCam;
    private Vector2 touchPoint;
    private SpriteBatcher batcher;
    private World world;
    private WorldListener worldListener;
    private WorldRenderer renderer;
    private Rectangle resumeBounds;
    private Rectangle quitBounds;
    private int lastScore;
    private int lastLives;
    private String scoreString;
    private String livesString;
    private FPSCounter fpsCounter;
    private float stateTime = 0;
    private float lifeStateTime;

    GameScreen(Game game, World world) {
        super(game);
        this.world = world;
        setState(GameState.Running);
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
	    case Ready:
	        updateReady();
	        break;
	    case Running:
	        updateRunning(deltaTime);
	        break;
	    case Paused:
	        updatePaused();
	        break;
	    case LevelEnd:
	        updateLevelEnd(deltaTime);
	        break;
	    case LostLife:
	        updateLostLife();
	        break;
	    case GameOver:
	        updateGameOver();
	        break;
	    }
	}
	
	private void setState(GameState state) {
		this.state = state;
		stateTime = 0;
		lifeStateTime = 0;
		if (state == GameState.Running) {
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
	    if (world.willy.canControl()) {
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
	        setState(GameState.LevelEnd);
	    }
	    if(world.state == World.WORLD_STATE_LOST_LIFE) {
	        setState(GameState.LostLife);
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
	            setState(GameState.Running);
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
	        setState(GameState.Ready);
	    }
	}
	
	private void updateLostLife() {
    	if (stateTime >= SCREEN_LOST_LIFE_DISPLAY_DURATION) {
    		if (world.willy.lives <= 0) {
    			setState(GameState.GameOver);
    		}
    		else {
    			setState(GameState.Ready);
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
	    case Ready:
	        presentReady();
	        break;
	    case Running:
	        presentRunning(deltaTime);
	        break;
	    case Paused:
	        presentPaused();
	        break;
	    case LevelEnd:
	        presentLevelEnd();
	        break;
	    case LostLife:
	        presentLostLife();
	        break;
	    case GameOver:
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
        if(state == GameState.Running)
            setState(GameState.Paused);
    }

    @Override
    public void resume() {        
    }

    @Override
    public void dispose() {       
    }
}
