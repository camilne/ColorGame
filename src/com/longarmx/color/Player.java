/*
 * Copyright 2013 Longarmx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.longarmx.color;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class Player implements Disposable{
	
	// The graphics of the player and its lighting bolt.
	public TextureRegion player;
	public TextureRegion lightning;
	
	//The game instance used by this class.
	private Game game;
	
	int frame = 0;
	
	// The player's dimensions
	public float x;
	public float y;
	public int width = 50;
	public int height = 50;
	
	// Whether or not the buttons are down that choose the colors.
	public boolean redDown = false;
	public boolean greenDown = false;
	public boolean blueDown = false;
	public boolean lightDown = false;
	public boolean darkDown = false;
	
	// Tells whether the player is dead.
	public boolean isDead = false;
	
	// Holds the red, green, and blue elements of the player's color.
	public float red;
	public float green;
	public float blue;
	
	public volatile boolean setColor = false;
	
	// Whether the player is shooting or not.
	public volatile boolean isShooting = false;
	
	// The sounds for the player's actions
	private Sound death;
	private Sound shoot;
	
	/**
	 *  The Constructor.
	 */
	public Player(){
		this.game = Main.instance;
		create();
	}
	
	/**
	 *  Where the initialization happens.
	 */
	public void create(){
		x = 50;
		y = 300;
		isDead = false;
		player = Util.getFromSpriteSheet(0, 0, 50, 50);
		lightning = Util.getFromSpriteSheet(100, 49, 1, 1);
		death = Gdx.audio.newSound(Gdx.files.internal("res/player_die.ogg"));
		shoot = Gdx.audio.newSound(Gdx.files.internal("res/shoot.wav"));
	}
	
	/**
	 *  Where the rendering happens.
	 *  @param batch The active Spritebatch. Must be drawing.
	 */
	public void render(SpriteBatch batch){
		update();
		batch.enableBlending();
		batch.setColor(0, 0, 0, 1);
		batch.draw(player, x + 1, y, width, height);
		batch.setColor(new Color(red, green, blue, 1));
		if(isShooting){
			makeLightning(batch);
			batch.draw(lightning, x + width, y + height/2, 0, 0, 1, 1, Main.ORIGINAL_WIDTH - x, 3, 0);
		}
		batch.draw(player, x, y, width, height);
		batch.setColor(1, 1, 1, 1);
	}
	
	/**
	 *  Where the updates happen.
	 */
	private void update(){
		if(!setColor){
			setColor();
		}
	}
	
	/**
	 *  Called whenever the player presses the space bar.
	 */
	public synchronized void shoot(){
		if(!isShooting){
			new Thread(new Runnable(){
	
				@Override
				public void run() {
					isShooting = true;
					checkEnemiesForMultiplier();
					shoot.play(game.masterSound);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					isShooting = false;
					setColor = false;
					red = 0;
					green = 0;
					blue = 0;
				}
				
			}).start();
		}
	}
	
	/**
	 *  Determines whether the multiplier should continue.
	 */
	private synchronized void checkEnemiesForMultiplier(){
		
		// Whether or not the player's shot was meaningless or not. Determines whether the multiplier should reset.
		boolean meaninglessShot = true;
		
		for(Enemy enemy : game.enemies){
			
			// Checks the players color and compares it to the enemies color.
			if(enemy.getColor().equals(new Color(this.red, this.green, this.blue, 1))){
				meaninglessShot = false;
				break;
			}
			
		}
		
		if(meaninglessShot){
			// Resets the multiplier because no enemies were killed.
			game.multiplier = 1;
		}else if(game.multiplier < 20){
			// Increases the multiplier because the player killed at least one enemy.
			game.multiplier++;
		}
	}
	
	/**
	 *  Creates and renders the lightning effect whenever the player shoots.
	 */
	private void makeLightning(SpriteBatch batch){
		if(frame % 5 == 0){
			for(int i = 0; i < 30; i++){
				int rotation = new Random().nextInt(45);
				batch.draw(lightning, x + width + i*20, y + height/2, 0, 0, 1, 1, 100 - i*3, 1, rotation);
				batch.draw(lightning, x + width + i*20 + 7, y + height/2, 0, 0, 1, 1, 100 - i*3, 1, -rotation);
			}
		}
		frame++;
	}
	
	/**
	 *  Determines what color elements should change based on the buttons pressed down.
	 */
	private void setColor(){
		if(darkDown ^ lightDown){
			if(redDown) red = Util.boolToInt(lightDown)/2f + .5f;
			if(greenDown) green = Util.boolToInt(lightDown)/2f + .5f;
			if(blueDown) blue = Util.boolToInt(lightDown)/2f + .5f;
		}
	}
	
	/**
	 *  Called when the player dies.
	 */
	public void die(){
		if(!isDead){
			death.play(game.masterSound);
			isDead = true;
			game.highscore.score(game.score);
		}
	}
	
	/**
	 *  Disposes all of the resources used by the player class.
	 */
	@Override
	public void dispose(){
		death.dispose();
		shoot.dispose();
	}
}
