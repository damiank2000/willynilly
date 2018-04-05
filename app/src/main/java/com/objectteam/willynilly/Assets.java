package com.objectteam.willynilly;

import com.objectteam.framework.Music;
import com.objectteam.framework.Sound;
import com.objectteam.framework.gl.Animation;
import com.objectteam.framework.gl.Font;
import com.objectteam.framework.gl.Texture;
import com.objectteam.framework.gl.TextureRegion;
import com.objectteam.framework.impl.GLGame;

public class Assets {
    public static Texture background;
    public static TextureRegion backgroundRegion;
    public static Texture mainMenuBackground;
    public static TextureRegion mainMenuBackgroundRegion;
    
    public static Texture items;        
    public static TextureRegion blankRegion;
    public static TextureRegion boundingRectangleRegion;
    public static TextureRegion mainMenu;
    public static TextureRegion pauseMenu;
    public static TextureRegion ready;
    public static TextureRegion gameOver;
    public static TextureRegion highScoresRegion;
    public static TextureRegion logo;
    public static TextureRegion soundOn;
    public static TextureRegion soundOff;
    public static TextureRegion arrow;
    public static TextureRegion pause;    
    public static TextureRegion spring;
    public static TextureRegion levelChoiceBackground;
    public static TextureRegion lock;
    public static Animation nest;
    public static TextureRegion staticWombatSign;
    public static Animation wombatSign;
    public static Animation fan;
    public static Animation scoreAnim;
    public static Animation runningWilly;
    public static TextureRegion jumpingWilly;
    public static Animation flyingWilly;
    public static Animation opalAnim;
    public static Animation walkingEchidna;
    public static Animation fallingWilly;
    public static Animation stunnedWilly;
    public static Animation bat;
    public static Animation waftyBird;
    public static Animation life;
    public static Animation lifeRemoving;
    public static TextureRegion jetPack;
    public static TextureRegion platform1;
    public static TextureRegion platform2;
    public static TextureRegion platform3;
    public static TextureRegion platform4;
    public static TextureRegion platform5;
    public static TextureRegion tunnel;
    public static TextureRegion rockTopLeft;
    public static TextureRegion rockTopCentre;
    public static TextureRegion rockTopRight;
    public static TextureRegion rockTunnelBackground;
    public static TextureRegion rockTunnelLeft;
    public static TextureRegion rockTunnelRight;
    public static Animation brakingPlatform;    
    public static Font font;
    
    public static Music titleMusic;
    public static Music backgroundMusic;
    public static Sound jumpSound;
    public static Sound fanSound;
    public static Sound hitSound;
    public static Sound eatSound;
    public static Sound clickSound;
    public static Sound boingSound;
    public static Sound gameOverSound;
    public static Sound collectSound;
    public static Sound completeSound;
    public static Sound levelStartSound;

    public static void load(GLGame game) {
        background = new Texture(game, "background.png");
        backgroundRegion = new TextureRegion(background, 0, 0, 480, 320);      
        mainMenuBackground = new Texture(game, "mainmenubackground.png");
        mainMenuBackgroundRegion = new TextureRegion(mainMenuBackground, 0, 0, 480, 320);      
        	
        items = new Texture(game, "items.png");        
        levelChoiceBackground = new TextureRegion(items, 124, 12, 48, 40);
        lock = new TextureRegion(items, 136, 80, 29, 35);
        blankRegion = new TextureRegion(items, 0, 500, 2, 2);
        boundingRectangleRegion = new TextureRegion(items, 18, 499, 27, 14);
        mainMenu = new TextureRegion(items, 0, 224, 300, 110);
        pauseMenu = new TextureRegion(items, 224, 128, 192, 96);
        ready = new TextureRegion(items, 320, 224, 192, 32);
        gameOver = new TextureRegion(items, 352, 256, 160, 96);
        highScoresRegion = new TextureRegion(Assets.items, 0, 298, 300, 38);
        logo = new TextureRegion(items, 0, 352, 274, 142);
        soundOff = new TextureRegion(items, 0, 0, 64, 64);
        soundOn = new TextureRegion(items, 64, 0, 64, 64);
        arrow = new TextureRegion(items, 0, 64, 64, 64);
        pause = new TextureRegion(items, 64, 64, 64, 64);
        staticWombatSign = new TextureRegion(items, 320, 416, 64, 96); 
        wombatSign = new Animation(0.1f,
	        new TextureRegion(items, 320, 416, 64, 96),
	        new TextureRegion(items, 384, 416, 64, 96),
	        new TextureRegion(items, 448, 416, 64, 96),
	        new TextureRegion(items, 512, 416, 64, 96));
        nest =  new Animation(0.2f,
    	        new TextureRegion(items, 320, 416, 64, 96),
    	        new TextureRegion(items, 384, 416, 64, 96),
    	        new TextureRegion(items, 448, 416, 64, 96),
    	        new TextureRegion(items, 512, 416, 64, 96));
        life = new Animation(0.1f,
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 33, 194, 31, 30));
        lifeRemoving = new Animation(0.4f,
        		new TextureRegion(items, 2, 194, 31, 30),
        		new TextureRegion(items, 64, 195, 31, 29));

        spring = new TextureRegion(items, 128, 0, 32, 32);
        opalAnim = new Animation(0.1f,
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 865, 412, 64, 64),
        		new TextureRegion(items, 934, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 412, 64, 64),
        		new TextureRegion(items, 795, 340, 64, 64),
        		new TextureRegion(items, 865, 340, 64, 64),
        		new TextureRegion(items, 934, 340, 64, 64));
        scoreAnim = new Animation(0.2f,                                 
                new TextureRegion(items, 320, 346, 32, 32),
                new TextureRegion(items, 352, 346, 32, 32),
                new TextureRegion(items, 384, 346, 32, 32),
                new TextureRegion(items, 416, 346, 32, 32),
                new TextureRegion(items, 448, 346, 32, 32)
                );
        runningWilly = new Animation(0.05f,
                                new TextureRegion(items, 523, 43, 64, 32),
                                new TextureRegion(items, 523, 80, 64, 32),
                                new TextureRegion(items, 523, 117, 64, 32),
                                new TextureRegion(items, 523, 154, 64, 32),
                                new TextureRegion(items, 523, 191, 64, 32),
                                new TextureRegion(items, 523, 229, 64, 32),
                                new TextureRegion(items, 523, 267, 64, 32));
        fallingWilly = new Animation(0.2f,
                new TextureRegion(items, 523, 80, 64, 32),
                new TextureRegion(items, 523, 117, 64, 32));
        jumpingWilly = new TextureRegion(items, 523, 117, 64, 32);
        stunnedWilly = new Animation(0.2f,
        		new TextureRegion(items, 523, 305, 64, 32),
        		new TextureRegion(items, 523, 343, 64, 32),
        		new TextureRegion(items, 523, 381, 64, 32));
        flyingWilly = new Animation(0.2f,
                new TextureRegion(items, 606, 305, 64, 32),
                new TextureRegion(items, 606, 343, 64, 32));
        bat = new Animation(0.2f, 
                                    new TextureRegion(items, 0, 160, 32, 18),
                                    new TextureRegion(items, 32, 160, 32, 18));
        waftyBird = new Animation(0.2f, 
                new TextureRegion(items, 64, 160, 32, 32),
                new TextureRegion(items, 96, 160, 32, 32));
        walkingEchidna = new Animation(0.15f,
                new TextureRegion(items, 606, 43, 64, 32),
                new TextureRegion(items, 606, 80, 64, 32),
                new TextureRegion(items, 606, 117, 64, 32),
                new TextureRegion(items, 606, 154, 64, 32),
                new TextureRegion(items, 606, 191, 64, 32),
                new TextureRegion(items, 606, 229, 64, 32),
                new TextureRegion(items, 606, 267, 64, 32));
        platform1 = new TextureRegion(items, 740, 0, 64, 32);
        platform2 = new TextureRegion(items, 740, 40, 64, 32);
        platform3 = new TextureRegion(items, 740, 80, 64, 32);
        platform4 = new TextureRegion(items, 740, 120, 64, 32);
        platform5 = new TextureRegion(items, 740, 160, 64, 32);
        tunnel = new TextureRegion(items, 131, 159, 64, 64);
        brakingPlatform = new Animation(0.2f,
                                     new TextureRegion(items, 64, 160, 64, 16),
                                     new TextureRegion(items, 64, 176, 64, 16),
                                     new TextureRegion(items, 64, 192, 64, 16),
                                     new TextureRegion(items, 64, 208, 64, 16));
        fan = new Animation(0.1f,
                new TextureRegion(items, 320, 384, 32, 32),
                new TextureRegion(items, 352, 384, 32, 32),
                new TextureRegion(items, 384, 384, 32, 32),
                new TextureRegion(items, 416, 384, 32, 32));
        jetPack = new TextureRegion(items, 450, 384, 32, 32);
        rockTopLeft = new TextureRegion(items, 592, 417, 64, 32);
        rockTopCentre = new TextureRegion(items, 656, 417, 64, 32);
        rockTopRight = new TextureRegion(items, 720, 417, 64, 32);
        rockTunnelBackground  = new TextureRegion(items, 592, 449, 64, 32);
        rockTunnelLeft = new TextureRegion(items, 592, 481, 64, 32);
        rockTunnelRight = new TextureRegion(items, 656, 481, 64, 32);
        
        
        font = new Font(items, 224, 0, 16, 16, 20);
        
        titleMusic = game.getAudio().newMusic("music.ogg");
        titleMusic.setLooping(true);
        titleMusic.setVolume(0.5f);
        if(Settings.soundEnabled)
            titleMusic.play();
        backgroundMusic = game.getAudio().newMusic("backgroundmusic.ogg");
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
        jumpSound = game.getAudio().newSound("jump.ogg");
        fanSound = game.getAudio().newSound("fan.ogg");
        hitSound = game.getAudio().newSound("hit.ogg");
        eatSound = game.getAudio().newSound("eat.ogg");
        clickSound = game.getAudio().newSound("click.ogg");
        boingSound = game.getAudio().newSound("boing.ogg");
        gameOverSound = game.getAudio().newSound("gameover.ogg");
        collectSound = game.getAudio().newSound("collect.ogg");
        completeSound = game.getAudio().newSound("complete.ogg");
        levelStartSound = game.getAudio().newSound("levelstart.ogg");
    }       
    
    public static void reload() {
        background.reload();
        items.reload();
        if(Settings.soundEnabled)
            titleMusic.play();
    }
    
    public static void playSound(Sound sound) {
        if(Settings.soundEnabled)
            sound.play(1);
    }

    public static void playMusic(Music music) {
        if(Settings.soundEnabled)
            music.play();
    }

    public static void stopMusic(Music music) {
        if(Settings.soundEnabled)
            music.stop();
    }
}
