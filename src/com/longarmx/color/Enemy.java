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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy {
	
	private float red = 1.0f;
	private float green = 1.0f;
	private float blue = 1.0f;
	private float x = Main.WIDTH;
	private Random random;
	private Game game;
	public boolean isDead = false;
	public volatile boolean forDestruction = false;
	
	public static float SPEED = 1.0f;
	
	private TextureRegion textureTop;
	private TextureRegion textureBottom;
	private volatile float yTop = 325;
	private volatile float yBottom = 300;
	private volatile float rotation = 0;
	
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
		textureTop = Util.getFromSpriteSheet(50, 0, 50, 25);
		textureBottom = Util.getFromSpriteSheet(50, 25, 50, 25);
	}
	
	public void render(SpriteBatch batch){
		batch.enableBlending();
		batch.setColor(red, green, blue, 1);
		batch.draw(textureTop, x, yTop, 0, 0, 50, 25, 1, 1, -rotation);
		batch.draw(textureBottom, x, yBottom, 0, 0, 50, 25, 1, 1, rotation);
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
				die();
			}
		}
	}
	
	public void switchColor(){
		if(game.level >= 3){
			float dark = Util.boolToFloat(random.nextBoolean())/2 + .5f;
			red = Util.boolToFloat(random.nextBoolean()) * dark;
			dark = Util.boolToFloat(random.nextBoolean())/2 + .5f;
			green = Util.boolToFloat(random.nextBoolean()) * dark;
			dark = Util.boolToFloat(random.nextBoolean())/2 + .5f;
			blue = Util.boolToFloat(random.nextBoolean()) * dark;
		}else if(game.level >= 2){
			float dark = Util.boolToFloat(random.nextBoolean())/2 + .5f;
			red = Util.boolToFloat(random.nextBoolean()) * dark;
			green = Util.boolToFloat(random.nextBoolean()) * dark;
			blue = Util.boolToFloat(random.nextBoolean()) * dark;
		}else{
			red = Util.boolToFloat(random.nextBoolean());
			green = Util.boolToFloat(random.nextBoolean());
			blue = Util.boolToFloat(random.nextBoolean());
		}
		if(!acceptableColor(red, green, blue, 1)){
			switchColor();
		}
	}
	
	public void die(){
		isDead = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i = 0; i < 10; i++){
					yTop++;
					yBottom--;
					rotation += 1;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				forDestruction = true;
			}
			
		}).start();
	}
	
	public float getRed(){
		return red;
	}
	
	public float getGreen(){
		return green;
	}
	
	public float getBlue(){
		return blue;
	}
	
	public Color getColor(){
		return new Color(red, green, blue, 1);
	}
	
	public boolean acceptableColor(float r, float g, float b, float a){
		return acceptableColor(new Color(r, g, b, a));
	}
	
	public boolean acceptableColor(Color color){
		for(Color tmp: Colors.list){
			if(color.equals(tmp) && !color.equals(game.lastEnemyColor))	return true;
		}
		return false;
	}
	
	public String getColorString(){
		Color temp = new Color(red, green, blue, 1);
		if		(temp.equals(Colors.BRIGHT_RED)){
			return "Bright Red";
		}else if(temp.equals(Colors.BRIGHT_GREEN)){
			return "Bright Green";
		}else if(temp.equals(Colors.BRIGHT_BLUE)){
			return "Bright Blue";
		}else if(temp.equals(Colors.BRIGHT_YELLOW)){
			return "Bright Yellow";
		}else if(temp.equals(Colors.BRIGHT_MAGENTA)){
			return "Bright Magenta";
		}else if(temp.equals(Colors.BRIGHT_CYAN)){
			return "Bright Cyan";
		}else if(temp.equals(Colors.WHITE)){
			return "White";
		}else if(temp.equals(Colors.BLACK)){
			return "Black";
		}else if(temp.equals(Colors.DARK_RED)){
			return "Dark Red";
		}else if(temp.equals(Colors.DARK_GREEN)){
			return "Dark Green";
		}else if(temp.equals(Colors.DARK_BLUE)){
			return "Dark Blue";
		}else if(temp.equals(Colors.DARK_YELLOW)){
			return "Dark Yellow";
		}else if(temp.equals(Colors.DARK_MAGENTA)){
			return "Dark Magenta";
		}else if(temp.equals(Colors.DARK_CYAN)){
			return "Dark Cyan";
		}else if(temp.equals(Colors.GRAY)){
			return "Gray";
		}else if(temp.equals(Colors.PURPLE)){
			return "Purple";
		}else if(temp.equals(Colors.SKY_BLUE)){
			return "Sky Blue";
		}else if(temp.equals(Colors.ORANGE)){
			return "Orange";
		}else if(temp.equals(Colors.LIME_GREEN)){
			return "Lime Green";
		}else if(temp.equals(Colors.PINK)){
			return "Pink";
		}else if(temp.equals(Colors.PALE_BLUE)){
			return "Pale Blue";
		}else if(temp.equals(Colors.SALMON)){
			return "Salmon";
		}else{
			return "Unknown";
		}
	}

}
