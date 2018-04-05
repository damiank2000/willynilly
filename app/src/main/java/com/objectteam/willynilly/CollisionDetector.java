package com.objectteam.willynilly;

import java.util.ArrayList;

import com.objectteam.framework.math.OverlapTester;
import com.objectteam.framework.math.Rectangle;
import com.objectteam.willynilly.gameObjects.Bat;
import com.objectteam.willynilly.gameObjects.Echidna;
import com.objectteam.willynilly.gameObjects.Fan;
import com.objectteam.willynilly.gameObjects.Finish;
import com.objectteam.willynilly.gameObjects.JetPack;
import com.objectteam.willynilly.gameObjects.Opal;
import com.objectteam.willynilly.gameObjects.Platform;
import com.objectteam.willynilly.gameObjects.WaftyBird;
import com.objectteam.willynilly.gameObjects.Willy;

public class CollisionDetector {

	public void checkCollisions(World world) {
	    checkPlatformCollisions(world);
	    if (world.state == World.WORLD_STATE_RUNNING) {
	    	if (world.willy.canCollide()) {
			    checkBatCollisions(world);
			    checkWaftyBirdCollisions(world);
			    checkEchidnaCollisions(world);
			    checkOpalCollisions(world);
			    checkFanCollisions(world);
			    checkFinishCollisions(world);
			    checkJetPackCollisions(world);
	    	}
	    }
	}
	
	private void checkPlatformCollisions(World world) {

		ArrayList<Platform> candidates = new ArrayList<Platform>(world.platforms);
	    Platform onPlatform = null;
	    
		if (world.willy.velocity.y <= 0) {
		    for (Platform candidate : candidates) {
		        if (candidate.canLandOnPlatform && ((world.willy.position.y - (Willy.HEIGHT / 2)) > candidate.position.y )
		        		&& world.willy.position.x > (candidate.position.x - (Platform.PLATFORM_WIDTH / 2))) {
		            if (OverlapTester.overlapRectangles((Rectangle)world.willy.bounds, (Rectangle)candidate.bounds)) {
		    	        onPlatform = candidate;
		                break;
		            	}
		        }
		    }
		}
	    
	    if (onPlatform != null) {
		    candidates.remove(onPlatform);
			world.willy.onPlatform(onPlatform);
            }
        else {
			world.willy.notOnPlatform();
	    }

	    Platform hitPlatform = null;
	    for (Platform candidate : candidates) {
	        if (!candidate.canPassThroughPlatform && OverlapTester.overlapRectangles(
	        		(Rectangle)world.willy.bounds, 
	        		(Rectangle)candidate.bounds)) {
				hitPlatform = candidate;
				break;
			}
	    }
	    
	    if (hitPlatform != null) {
			world.willy.hitPlatform();
		}
	}
	
	private void checkBatCollisions(World world) {
	    int len = world.bats.size();
	    for (int i = 0; i < len; i++) {
	        Bat bat = world.bats.get(i);
	        if (OverlapTester.overlapRectangles((Rectangle)world.willy.bounds, (Rectangle)bat.bounds)) {
	        	world.willy.hitEnemy();
	        	world.listener.hit();
	        }
	    }
	}
	
	private void checkWaftyBirdCollisions(World world) {
	    int len = world.waftyBirds.size();
	    for (int i = 0; i < len; i++) {
	        WaftyBird waftyBird = world.waftyBirds.get(i);
	        if (OverlapTester.overlapRectangles((Rectangle)world.willy.bounds, (Rectangle)waftyBird.bounds)) {
	        	world.willy.hitEnemy();
	        	world.listener.hit();
	        }
	    }
	}

	private void checkEchidnaCollisions(World world) {
	    int len = world.echidnas.size();
	    for (int i = 0; i < len; i++) {
	        Echidna echidna = world.echidnas.get(i);
	        if (OverlapTester.overlapRectangles((Rectangle)world.willy.bounds, (Rectangle)echidna.bounds)) {
	        	world.willy.hitEnemy();
	        	world.listener.hit();
	        }
	    }
	}

	private void checkOpalCollisions(World world) {
	    int len = world.opals.size();
	    for (int i = 0; i < len; i++) {
	        Opal opal = world.opals.get(i);
	        if (opal.state == CollectableState.Collectable
	        		&& OverlapTester.overlapRectangles((Rectangle)world.willy.bounds, (Rectangle)opal.bounds)) {
	        	opal.collect();
	            len = world.opals.size();
	            world.listener.coin();
	            world.score += Opal.SCORE;
	        }
	    }
	}

	private void checkJetPackCollisions(World world) {
		int len = world.jetPacks.size();
		for (int i = 0; i < len; i++) {
			JetPack jetPack = world.jetPacks.get(i);
			if (jetPack.state == CollectableState.Collectable
					&& OverlapTester.overlapRectangles((Rectangle)world.willy.bounds, (Rectangle)jetPack.bounds)) {
				jetPack.collect();
				world.willy.collectedJetPack();
				len = world.jetPacks.size();
			}
		}
	}

	private void checkFanCollisions(World world) {
	    int len = world.fans.size();
	    for (int i = 0; i < len; i++) {
	        Fan fan = world.fans.get(i);
	        if (world.willy.position.y > fan.position.y) {
	            if (OverlapTester.overlapRectangles((Rectangle)world.willy.bounds, (Rectangle)fan.bounds)) {
	                world.willy.blownUp();
	                world.listener.highJump();
	            }
	        }
	    }
	}
	
	private void checkFinishCollisions(World world) {
	    int len = world.finishes.size();
	    for (int i = 0; i < len; i++) {
	        Finish finish = world.finishes.get(i);
	        if (OverlapTester.overlapRectangles((Rectangle)world.willy.bounds, (Rectangle)finish.bounds)) {
	        	world.finishLevel();
	        	return;
	        }
	    }
	}
}
