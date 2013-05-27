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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class GuiTitle extends Gui implements Disposable{
	
	List<ClickableButton> components = new ArrayList<ClickableButton>();
	
	private ClickableButton start;
	private ClickableButton options;
	private ClickableButton exit;
	
	public GuiTitle(){
		create();
	}
	
	public void create(){
		super.create();
		
		start = new ClickableButton(Main.ORIGINAL_WIDTH/2 - 250, 400, 500, 75, new ClickManager(){

			@Override
			public void onClick() {
				game.state = States.LEVEL_SELECT;
				game.levelSelect.setColor(r, g, b);
			}
			
		});
		components.add(start);
		start.setText("Start Game", 4);
		start.setHighlightColor(1, 0, 0);
		
		options = new ClickableButton(Main.ORIGINAL_WIDTH/2 - 250, 300, 500, 75, new ClickManager() {
			
			@Override
			public void onClick() {
				game.state = States.OPTIONS;
				game.options.setColor(r, g, b);
			}
			
		});
		components.add(options);
		options.setText("Options", 4);
		options.setHighlightColor(0, 1, 0);
		
		exit = new ClickableButton(Main.ORIGINAL_WIDTH/2 - 250, 200, 500, 75, new ClickManager() {
			
			@Override
			public void onClick() {
				Gdx.app.exit();
			}
			
		});
		components.add(exit);
		exit.setText("Exit", 4);
		exit.setHighlightColor(0, 0, 1);
	}
	
	public void render(SpriteBatch batch){
		super.render(batch);
		
		batch.setColor(1, 1, 1, 1);
		for(ClickableButton component: components){
			component.render(batch);
		}
		
		manager.setColor(0, 0, 0, 1);
		manager.draw("Chromalusus", Main.ORIGINAL_WIDTH/2 - (int)manager.getTextWidth("Chromalusus", 7)/2, 600, 7, batch);
		manager.draw("A game by Longarmx", Main.ORIGINAL_WIDTH/2 - (int)manager.getTextWidth("A game by Longarmx", 2)/2, 550, 2, batch);
	}
	
	public void update(){
		super.update();
		for(ClickableButton component: components){
			component.update();
		}
	}
	
	@Override
	public void dispose(){
		super.dispose();
	}

}
