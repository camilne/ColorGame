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
	Player player;
	UI ui;
	List<Enemy> enemies = new ArrayList<Enemy>();
	int score = 0;
	
	public static int level = 1;

	@Override
	public void create() {
		Gdx.input.setInputProcessor(new Input(this));
		batch = new SpriteBatch();
		background = Util.loadTexture("res/background.png");
		player = new Player();
		ui = new UI(this);
		enemies.add(new Enemy(this));
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Total RAM used: " + ((runtime.totalMemory() - runtime.freeMemory()) / (1024*1024)) + "mb / " + (runtime.maxMemory()/(1024*1024)) + "mb");
	}
	
	public void reset(){
		for(int i = 0; i < enemies.size(); i++){
			enemies.remove(i);
		}
		enemies.add(new Enemy(this));
		player = new Player();
		Config.ENEMY_SPEED = 1.0f;
		level = 1;
		score = 0;
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.95f, 0.95f, 0.95f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.disableBlending();
		if(player.isDead) batch.setColor(.9f, .9f, .9f, 1);
		batch.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);
		player.render(batch);
		ui.render(batch);
		for(Enemy enemy : enemies){
			enemy.render(batch);
			
		}
		for(int i = 0; i < enemies.size(); i++){
			if(enemies.get(i).isDead){
				score++;
				enemies.remove(i);
				Config.ENEMY_SPEED += .2f;
				if(Config.ENEMY_SPEED < 2){
					level = 1;
				}else if(Config.ENEMY_SPEED < 3){
					level = 2;
				}
				enemies.add(new Enemy(this));
			}
		}
		batch.end();
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
		ui.dispose();
		for(Enemy enemy : enemies){
			enemy.dispose();
		}
		player.dispose();
		Util.dispose();
	}
}
