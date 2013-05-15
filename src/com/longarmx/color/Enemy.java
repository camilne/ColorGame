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
	
	private TextureRegion texture;
	
	public Enemy(Game game){
		this.game = game;
		create();
	}
	
	public void create(){
		random = new Random();
		switchColor();
		texture = Util.getFromSpriteSheet(50, 0, 50, 50);
	}
	
	public void render(SpriteBatch batch){
		update();
		batch.enableBlending();
		batch.setColor(red, green, blue, 1);
		batch.draw(texture, x, 300);
		batch.setColor(1, 1, 1, 1);
	}
	
	public void update(){
		if(!game.player.isDead){
			x-=Config.ENEMY_SPEED;
			if(x <= game.player.x + 50 + 10){
				game.player.die();
			}
		}
		if(game.player.isShooting){
			//System.out.println("RED " + game.player.red + " : " + red + " GREEN " + game.player.green + " : " + green + " BLUE " + game.player.blue + " : " + blue);
			if(game.player.red == red && game.player.green == green && game.player.blue == blue){
				isDead = true;
			}
		}
	}
	
	public void dispose(){
		
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
	
	public int getRandomColor(){
		switch(Game.level){
		case 1:
			return getColor(random.nextInt(3));
		}
		return 0;
	}
	
	public int getColor(int random){
		switch(random){
		case 0:
			return Config.RED;
		case 1:
			return Config.GREEN;
		case 2:
			return Config.BLUE;
		case 3:
			return Config.MAGENTA;
		case 4:
			return Config.CYAN;
		case 5:
			return Config.YELLOW;
		case 6:
			return Config.WHITE;
		case 7:
			return Config.BLACK;
		default:
			return Config.BLACK;
		}
	}

}
