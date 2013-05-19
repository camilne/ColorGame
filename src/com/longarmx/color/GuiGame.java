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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class GuiGame extends Gui implements Disposable{
	
	public List<Component> components = new ArrayList<Component>();
	
	private int buttonSize = 75;
	
	FontManager manager;
	
	private Game game;
	
	private ColorButton red;
	private ColorButton green;
	private ColorButton blue;
	private ColorButton dark;
	private ColorButton light;
	
	public GuiGame(){
		this.game = Main.instance;
		create();
	}
	
	public void create(){
		red = new ColorButton(100, 100, buttonSize, buttonSize);
		red.setColor(1, 0, 0, 1);
		components.add(red);
		
		green = new ColorButton(200, 100, buttonSize, buttonSize);
		green.setColor(0, 1, 0, 1);
		components.add(green);
		
		blue = new ColorButton(300, 100, buttonSize, buttonSize);
		blue.setColor(0, 0, 1, 1);
		components.add(blue);
		
		dark = new ColorButton(Main.WIDTH - (200 + buttonSize), 100, buttonSize, buttonSize);
		dark.setColor(.3f, .3f, .3f, 1);
		components.add(dark);
		
		light = new ColorButton(Main.WIDTH - (100 + buttonSize), 100, buttonSize, buttonSize);
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
