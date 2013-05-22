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
	
	public TextureRegion player;
	public TextureRegion lightning;
	
	private Game game;
	int frame = 0;
	
	public float x;
	public float y;
	public int width = 50;
	public int height = 50;
	
	public boolean redDown = false;
	public boolean greenDown = false;
	public boolean blueDown = false;
	public boolean lightDown = false;
	public boolean darkDown = false;
	
	public boolean isDead = false;
	
	public float red;
	public float green;
	public float blue;
	
	public volatile boolean setColor = false;
	
	public volatile boolean isShooting = false;
	
	private Sound death;
	private Sound shoot;
	
	public Player(){
		this.game = Main.instance;
		create();
	}
	
	public void create(){
		x = 50;
		y = 300;
		isDead = false;
		player = Util.getFromSpriteSheet(0, 0, 50, 50);
		lightning = Util.getFromSpriteSheet(100, 49, 1, 1);
		death = Gdx.audio.newSound(Gdx.files.internal("res/player_dead_drain.ogg"));
		shoot = Gdx.audio.newSound(Gdx.files.internal("res/shoot.wav"));
	}
	
	public void render(SpriteBatch batch){
		update();
		batch.enableBlending();
		batch.setColor(new Color(red, green, blue, 1));
		if(isShooting){
			makeLightning();
			batch.draw(lightning, x + width, y + height/2, 0, 0, 1, 1, Main.WIDTH - x, 3, 0);
		}
		batch.draw(player, x, y, width, height);
		batch.setColor(1, 1, 1, 1);
	}
	
	private void update(){
		if(!setColor){
			setColor();
		}
	}
	
	public synchronized void shoot(){
		if(!isShooting){
			new Thread(new Runnable(){
	
				@Override
				public void run() {
					isShooting = true;
					shoot.play(game.volume);
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
	
	private void makeLightning(){
		if(frame % 5 == 0){
			for(int i = 0; i < 30; i++){
				int rotation = new Random().nextInt(45);
				game.batch.draw(lightning, x + width + i*20, y + height/2, 0, 0, 1, 1, 100 - i*3, 1, rotation);
				game.batch.draw(lightning, x + width + i*20 + 7, y + height/2, 0, 0, 1, 1, 100 - i*3, 1, -rotation);
			}
		}
		frame++;
	}
	
	private void setColor(){
		if(darkDown ^ lightDown){
			if(redDown) red = Util.boolToFloat(redDown) * (Util.boolToFloat(lightDown)/2 + .5f);
			if(greenDown) green = Util.boolToFloat(greenDown) * (Util.boolToFloat(lightDown)/2 + .5f);
			if(blueDown) blue = Util.boolToFloat(blueDown) * (Util.boolToFloat(lightDown)/2 + .5f);
		}
	}
	
	public void die(){
		if(!isDead){
			death.play(game.volume);
			isDead = true;
		}
	}
	
	@Override
	public void dispose(){
		death.dispose();
		shoot.dispose();
	}
}
