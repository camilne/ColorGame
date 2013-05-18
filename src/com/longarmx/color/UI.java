package com.longarmx.color;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class UI implements Disposable{
	
	public List<Component> components = new ArrayList<Component>();
	
	private int buttonSize = 75;
	
	FontManager manager;
	
	private Game game;
	
	private Button red;
	private Button green;
	private Button blue;
	private Button dark;
	private Button light;
	
	public UI(Game game){
		this.game = game;
		create();
	}
	
	public void create(){
		red = new Button(100, 100, buttonSize, buttonSize, game);
		red.setColor(1, 0, 0, 1);
		components.add(red);
		
		green = new Button(200, 100, buttonSize, buttonSize, game);
		green.setColor(0, 1, 0, 1);
		components.add(green);
		
		blue = new Button(300, 100, buttonSize, buttonSize, game);
		blue.setColor(0, 0, 1, 1);
		components.add(blue);
		
		dark = new Button(Main.WIDTH - (200 + buttonSize), 100, buttonSize, buttonSize, game);
		dark.setColor(.3f, .3f, .3f, 1);
		components.add(dark);
		
		light = new Button(Main.WIDTH - (100 + buttonSize), 100, buttonSize, buttonSize, game);
		light.setColor(.8f, .8f, .8f, 1);
		components.add(light);
		
		manager = FontManager.getInstance();
	}
	
	public void render(SpriteBatch batch){
		update();
		
		for(Component component: components){
			component.render(batch);
		}
		
		manager.setColor(0, 0, 0, 1);
		manager.draw("Score: " + String.valueOf(game.score), 10, Main.HEIGHT - 60, 5, batch);
		
		if(game.player.isDead){
			manager.setColor(1, 0, 0, 1);
			manager.draw("Game Over", Main.WIDTH/2 - (int)manager.getTextWidth("Game Over", 5)/2, Main.HEIGHT/2 - 25, 5, batch);
		}else if(game.paused){
			manager.setColor(0, 1, 0, 1);
			manager.draw("Paused", Main.WIDTH/2 - (int)manager.getTextWidth("Paused", 5)/2, Main.HEIGHT/2 - 25, 5, batch);
		}
	}
	
	public void update(){
		if(game.player.redDown) red.selected = true;
		else red.selected = false;
		
		if(game.player.greenDown) green.selected = true;
		else green.selected = false;
		
		if(game.player.blueDown) blue.selected = true;
		else blue.selected = false;
		
		if(game.player.darkDown) dark.selected = true;
		else dark.selected = false;
		
		if(game.player.lightDown) light.selected = true;
		else light.selected = false;
	}
	
	@Override
	public void dispose(){
		
	}

}
