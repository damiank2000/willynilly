package com.objectteam.willynilly;

import java.util.ArrayList;
import java.util.List;
import com.objectteam.framework.GameObject;
import com.objectteam.framework.Updatable;
import com.objectteam.framework.math.Vector2;
import com.objectteam.willynilly.gameObjects.Bat;
import com.objectteam.willynilly.gameObjects.Decoration;
import com.objectteam.willynilly.gameObjects.Echidna;
import com.objectteam.willynilly.gameObjects.Fan;
import com.objectteam.willynilly.gameObjects.Finish;
import com.objectteam.willynilly.gameObjects.JetPack;
import com.objectteam.willynilly.gameObjects.Opal;
import com.objectteam.willynilly.gameObjects.Platform;
import com.objectteam.willynilly.gameObjects.Spring;
import com.objectteam.willynilly.gameObjects.WaftyBird;
import com.objectteam.willynilly.gameObjects.Willy;

public class World {
    public static final float DEFAULT_WORLD_HEIGHT = 20;
    public static final int WORLD_STATE_RUNNING = 0;
    public static final int WORLD_STATE_NEXT_LEVEL = 1;
    public static final int WORLD_STATE_LOST_LIFE = 3;
    public static final int FINISH_LEVEL_SCORE = 100;
    public static final int MAX_LEVEL = 20;
    public static final Vector2 gravity = new Vector2(0, -8);

    public Willy willy;
    public List<Platform> platforms;
    public List<Decoration> backgroundDecoration;
    public List<Decoration> foregroundDecoration;
    public List<Spring> springs;
    public List<Bat> bats;
    public List<WaftyBird> waftyBirds;
    public List<Opal> opals;
    public List<Fan> fans;
    public List<Echidna> echidnas;
    public List<GameObject> wombatSigns;
    public List<Finish> finishes;
    public List<JetPack> jetPacks;
    public WorldListener listener;

    public float widthSoFar;
    public int score;
    public int state;
    public int livesRemaining;
    public int level;
    public float timer;
    public String levelName;
    
    private LevelGenerator levelGenerator = new LevelGenerator();
    private CollisionDetector collisionDetector = new CollisionDetector();
    private float worldHeight;

    public World(WorldListener listener, int startingLevel) {
        this.score = 0;
        this.livesRemaining = 3;
        this.level = startingLevel;
        this.listener = listener;
        this.worldHeight = DEFAULT_WORLD_HEIGHT;
    }

    public void reset() {
        this.willy = new Willy(1, 17);
        this.willy.lives = livesRemaining;
        this.platforms = new ArrayList<Platform>();
        this.backgroundDecoration = new ArrayList<Decoration>();
        this.foregroundDecoration = new ArrayList<Decoration>();
        this.springs = new ArrayList<Spring>();
        this.bats = new ArrayList<Bat>();
        this.waftyBirds = new ArrayList<WaftyBird>();
        this.opals = new ArrayList<Opal>();
        this.fans = new ArrayList<Fan>();
        this.echidnas = new ArrayList<Echidna>();
        this.wombatSigns = new ArrayList<GameObject>();
        this.finishes = new ArrayList<Finish>();
        this.jetPacks = new ArrayList<JetPack>();
        this.timer = 0;
        newLevel();
    }
    
    public void newLevel() {
        levelGenerator.generateSavedLevel(this, level);
        this.widthSoFar = 0;
        this.state = WORLD_STATE_RUNNING;   
    }
    
	public void update(float deltaTime, float accelY) {
	    updateWilly(deltaTime, accelY);
	    updatePlatforms(deltaTime);
	    updateBats(deltaTime);
	    updateWaftyBirds(deltaTime);
	    updateOpals(this.opals, deltaTime);
	    updateFans(deltaTime);
	    updateEchidnas(deltaTime);
	    updateFinishes(deltaTime);
	    if (willy.canCollide())
	        collisionDetector.checkCollisions(this);
	    if (state == WORLD_STATE_RUNNING) {
			timer += deltaTime;
	    	checkLostLife();
	    	livesRemaining = willy.lives;
	    }
	}
	
	public void finishLevel() {
		listener.complete();
    	state = World.WORLD_STATE_NEXT_LEVEL;
    	level++;
    	if (level > MAX_LEVEL) {
    		level = 1;
    	}
    	if (level > Settings.unlockedLevel) {
    		Settings.unlockedLevel = level;
    	}
    	score += World.FINISH_LEVEL_SCORE;
	}
	
	private void updateWilly(float deltaTime, float accelY) {
	    willy.update(deltaTime);
	    widthSoFar = Math.max(willy.position.x, widthSoFar);
	}
	
	private void updatePlatforms(float deltaTime) {
	    int len = platforms.size();
	    for (int i = 0; i < len; i++) {
	        Platform platform = platforms.get(i);
	        platform.update(deltaTime);
	        if (platform.hasCompletedPulverizing()) {
	            platforms.remove(platform);
	            len = platforms.size();
	        }
	    }
	}
	
	private void updateBats(float deltaTime) {
	    int len = bats.size();
	    for (int i = 0; i < len; i++) {
	        Bat bat = bats.get(i);
	        bat.update(deltaTime);
	    }
	}

	private void updateWaftyBirds(float deltaTime) {
	    int len = waftyBirds.size();
	    for (int i = 0; i < len; i++) {
	        WaftyBird waftyBird = waftyBirds.get(i);
	        waftyBird.update(deltaTime);
	    }
	}

	private void updateOpals(List<Opal> objects, float deltaTime) {
	    int len = objects.size();
	    for (int i = 0; i < len; i++) {
	        Updatable object = objects.get(i);
	        object.update(deltaTime);
	    }
	}
	
	private void updateEchidnas(float deltaTime) {
	    int len = echidnas.size();
	    for (int i = 0; i < len; i++) {
	        Echidna echidna = echidnas.get(i);
	        echidna.update(deltaTime);
	    }
	}

	private void updateFans(float deltaTime) {
	    int len = fans.size();
	    for (int i = 0; i < len; i++) {
	        Fan fan = fans.get(i);
	        fan.update(deltaTime);
	    }
	}

	private void updateFinishes(float deltaTime) {
	    int len = finishes.size();
	    for (int i = 0; i < len; i++) {
	        Finish finish = finishes.get(i);
	        finish.update(deltaTime);
	    }
	}

	private void checkLostLife() {
        if (willy.position.y + (Willy.HEIGHT /2)< 0) {
        	willy.lives--;
            state = WORLD_STATE_LOST_LIFE;
        }
	}

	public void setWorldHeight(float value) {
		this.worldHeight = value;
	}

	public float getWorldHeight() {
		return this.worldHeight;
	}
	

}