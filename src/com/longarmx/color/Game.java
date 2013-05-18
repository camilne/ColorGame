package com.longarmx.color;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game implements ApplicationListener{
	
	SpriteBatch batch;
	Texture background;
	Texture overlay;
	Player player;
	UI ui;
	List<Enemy> enemies = new ArrayList<Enemy>();
	int score = 0;
	public static int level = 1;
	
	boolean paused = false;

	@Override
	public void create() {
		Gdx.input.setInputProcessor(new Input());
		batch = new SpriteBatch();
		background = Util.loadTexture("res/background.png");
		overlay = Util.loadTexture("res/overlay.png");
		ui = new UI();
		reset();
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Total RAM used: " + ((runtime.totalMemory() - runtime.freeMemory()) / (1024*1024)) + "MB");
	}
	
	public void reset(){
		for(int i = 0; i < enemies.size(); i++){
			enemies.remove(i);
		}
		enemies.add(new Enemy());
		player = new Player();
		Enemy.SPEED = 1.0f;
		level = 1;
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
			batch.end();
	}
	
	private void update(){
		for(int i = 0; i < enemies.size(); i++){
			if(enemies.get(i).isDead){
				score++;
				enemies.remove(i);
				Enemy.SPEED += .2f;
				if(Enemy.SPEED < 2){
					level = 1;
				}else if(Enemy.SPEED < 3){
					level = 2;
				}
				enemies.add(new Enemy());
			}else{
				enemies.get(i).update();
			}
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
	}
}
