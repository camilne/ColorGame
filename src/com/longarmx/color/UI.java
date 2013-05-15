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
	
	public UI(Game game){
		this.game = game;
		create();
	}
	
	public void create(){
		components.add(new Component(100, 100, buttonSize, buttonSize, 0, 169, 31, 31, game));
		components.add(new Component(200, 100, buttonSize, buttonSize, 31, 169, 31, 31, game));
		components.add(new Component(300, 100, buttonSize, buttonSize, 62, 169, 31, 31, game));
		components.add(new Component(Main.WIDTH - (200 + buttonSize), 100, buttonSize, buttonSize, 93, 169, 31, 31, game));
		components.add(new Component(Main.WIDTH - (100 + buttonSize), 100, buttonSize, buttonSize, 124, 169, 31, 31, game));
		manager = new FontManager();
	}
	
	public void render(SpriteBatch batch){
		for(Component component: components){
			component.render(batch);
		}
		if(game.player.redDown) components.get(0).selected = true;
		else components.get(0).selected = false;
		
		if(game.player.greenDown) components.get(1).selected = true;
		else components.get(1).selected = false;
		
		if(game.player.blueDown) components.get(2).selected = true;
		else components.get(2).selected = false;
		
		if(game.player.darkDown) components.get(3).selected = true;
		else components.get(3).selected = false;
		
		if(game.player.lightDown) components.get(4).selected = true;
		else components.get(4).selected = false;
		
		manager.draw("Score: " + String.valueOf(game.score), 10, Main.HEIGHT - 60, 5, batch);
	}
	
	@Override
	public void dispose(){
		for(Component component: components){
			component.dispose();
		}
	}

}
