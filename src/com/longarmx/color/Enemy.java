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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy {
	
	private float red;
	private float green;
	private float blue;
	private float x = Main.WIDTH;
	private Random random;
	private Game game;
	public boolean isDead = false;
	
	public static float SPEED = 1.0f;
	
	private TextureRegion texture;
	
	private boolean staticColor = false;
	
	public Enemy(){
		this.game = Main.instance;
		create();
	}
	
	public Enemy(float r, float g, float b){
		this.game = Main.instance;
		this.red = r;
		this.green = g;
		this.blue = b;
		staticColor = true;
		create();
	}
	
	public void create(){
		if(!staticColor) random = new Random();
		if(!staticColor) switchColor();
		texture = Util.getFromSpriteSheet(50, 0, 50, 50);
	}
	
	public void render(SpriteBatch batch){
		batch.enableBlending();
		batch.setColor(red, green, blue, 1);
		batch.draw(texture, x, 300);
		batch.setColor(1, 1, 1, 1);
	}
	
	public void update(){
		if(!game.player.isDead){
			x-=SPEED;
			if(x <= game.player.x + 50 + 10){
				game.player.die();
			}
		}
		if(game.player.isShooting){
			if(game.player.red == red && game.player.green == green && game.player.blue == blue){
				isDead = true;
			}
		}
	}
	
	public void switchColor(){
		if(Game.level >= 2){
			float dark = Util.boolToFloat(random.nextBoolean())/2 + .5f;
			red = Util.boolToFloat(random.nextBoolean()) * dark;
			green = Util.boolToFloat(random.nextBoolean()) * dark;
			blue = Util.boolToFloat(random.nextBoolean()) * dark;
		}else{
			red = Util.boolToFloat(random.nextBoolean());
			green = Util.boolToFloat(random.nextBoolean());
			blue = Util.boolToFloat(random.nextBoolean());
		}
	}

}
