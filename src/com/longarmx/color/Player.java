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
	public TextureRegion shootTexture;
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
	
	public Player(Game game){
		this.game = game;
		create();
	}
	
	public void create(){
		x = 50;
		y = 300;
		isDead = false;
		player = Util.getFromSpriteSheet(0, 0, 50, 50);
		shootTexture = Util.getFromSpriteSheet(0, 128, 200, 10);
		lightning = Util.getFromSpriteSheet(0, 127, 1, 1);
		death = Gdx.audio.newSound(Gdx.files.internal("res/player_dead_drain.ogg"));
		shoot = Gdx.audio.newSound(Gdx.files.internal("res/shoot.wav"));
	}
	
	public void render(SpriteBatch batch){
		update();
		batch.enableBlending();
		batch.setColor(new Color(red, green, blue, 1));
		if(isShooting){
			//batch.draw(shootTexture, x + player.getRegionWidth() * .75f, y + player.getRegionHeight()/2 - shootTexture.getRegionHeight()/2, Main.WIDTH - (x + player.getRegionWidth()/2), shootTexture.getRegionHeight());
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
					shoot.play();
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
		if((redDown || greenDown || blueDown)){
			if(darkDown ^ lightDown){
				red = Util.boolToFloat(redDown) * (Util.boolToFloat(lightDown)/2 + .5f);
				green = Util.boolToFloat(greenDown) * (Util.boolToFloat(lightDown)/2 + .5f);
				blue = Util.boolToFloat(blueDown) * (Util.boolToFloat(lightDown)/2 + .5f);
				setColor = true;
			}
		}
	}
	
	@Override
	public void dispose(){
		death.dispose();
		shoot.dispose();
	}
	
	public void die(){
		if(!isDead){
			death.play();
			isDead = true;
		}
	}

}
