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

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game implements ApplicationListener{
	
	SpriteBatch batch;
	Texture background;
	Texture overlay;
	Player player;
	
	List<Enemy> enemies = new ArrayList<Enemy>();
	Color lastEnemyColor = new Color();
	
	int score = 0;
	public int level = 1;
	public int difficulty = 1;
	public float volume = 1.0f;
	
	boolean paused = false;
	
	public States state = States.TITLE;
	
	GuiGame ui;
	GuiTitle title;
	GuiOptions options;
	GuiLevelSelect levelSelect;
	
	@Override
	public void create() {
		Gdx.input.setInputProcessor(new Input());
		batch = new SpriteBatch();
		background = Util.loadTexture("res/background.png");
		overlay = Util.loadTexture("res/overlay.png");
		
		ui = new GuiGame();
		title = new GuiTitle();
		options = new GuiOptions();
		levelSelect = new GuiLevelSelect();
		
		reset();
		
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Total RAM used: " + ((runtime.totalMemory() - runtime.freeMemory()) / (1024*1024)) + "MB");
	}
	
	public void reset(){
		for(int i = 0; i < enemies.size(); i++){
			enemies.remove(i);
		}
		enemies.add(new Enemy());
		lastEnemyColor = enemies.get(0).getColor();
		player = new Player();
		Enemy.SPEED = 1.0f * difficulty;
		level = difficulty;
		score = 0;
		paused = false;
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void render() {
			if(!paused) update();
			
			Gdx.gl.glClearColor(0.95f, 0.95f, 0.95f, 1);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			
			batch.begin();
			switch(state){
			case TITLE:
				
				batch.disableBlending();
				title.render(batch);
				
				break;
				
			case GAME:
				
				batch.disableBlending();
				if(player.isDead) batch.setColor(.9f, .9f, .9f, 1);
				else batch.setColor(1, 1, 1, 1);
				batch.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);
				player.render(batch);
				
				for(Enemy enemy : enemies){
					enemy.render(batch);
				}
				
				batch.setColor(1, 1, 1, 1);
				batch.enableBlending();
				batch.draw(overlay, 0, 0, Main.WIDTH, Main.HEIGHT);
				
				ui.render(batch);
				
				break;
				
			case OPTIONS:
				
				options.render(batch);
				
				break;
			
			case LEVEL_SELECT:
				
				levelSelect.render(batch);
				
				break;
				
			default:
				break;
			}
			batch.end();
	}
	
	private void update(){
		switch(state){
		
		case TITLE:
			
			title.update();
			
			break;
			
		case GAME:
			
			for(int i = 0; i < enemies.size(); i++){
				if(enemies.get(i).forDestruction){
					score++;
					enemies.remove(i);
					Enemy.SPEED += .2f;
					enemies.add(new Enemy());
					lastEnemyColor = enemies.get(enemies.size() - 1).getColor();
				}else{
					enemies.get(i).update();
				}
			}
			
			if(Gdx.input.isKeyPressed(Keys.ESCAPE)) state = States.TITLE;
			
			break;
		case OPTIONS:
			
			options.update();
			
			break;
			
		case LEVEL_SELECT:
			
			levelSelect.update();
			
			break;
			
		default:
			break;
		}
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		background.dispose();
		overlay.dispose();
		ui.dispose();
		player.dispose();
		Util.dispose();
		title.dispose();
		levelSelect.dispose();
		options.dispose();
	}
}
