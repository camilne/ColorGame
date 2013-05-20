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

public class GuiOptions extends Gui implements Disposable{
	
	Game game;
	
	List<ClickableButton> clickableButtons = new ArrayList<ClickableButton>();
	List<Slider> sliders = new ArrayList<Slider>();
	
	private Slider volume;
	private ClickableButton back;
	
	private FontManager manager;
	
	public GuiOptions(){
		create();
	}
	
	public void create(){
		this.game = Main.instance;
		super.create();
		
		manager = new FontManager();
		
		volume = new Slider(Main.WIDTH/2 - 250, 500, 500, 75, new ClickManager(){

			@Override
			public void onClick() {
				
			}
			
		});
		sliders.add(volume);
		volume.setHighlightColor(0, 1, 1);
		
		back = new ClickableButton(Main.WIDTH/2 - 250, 200, 500, 75, new ClickManager() {
			
			@Override
			public void onClick() {
				game.state = States.TITLE;
				game.title.setColor(r, g, b);
			}
			
		});
		clickableButtons.add(back);
		back.setText("Back", 4);
		back.setHighlightColor(1, 0, 1);
	}
	
	public void render(SpriteBatch batch){
		super.render(batch);
		
		batch.setColor(1, 1, 1, 1);
		for(ClickableButton component: clickableButtons){
			component.render(batch);
		}
		
		for(Slider slider: sliders){
			slider.render(batch);
		}
		
		manager.draw("Options", Main.WIDTH/2 - (int)manager.getTextWidth("Options", 7)/2, 600, 7, batch);
		manager.draw("Volume: ", volume.x - (int)manager.getTextWidth("Volume: ", 3), 500 + volume.height/2 - (int)manager.getTextHeight(3)/2, 3, batch);
	}
	
	public void update(){
		super.update();
		for(ClickableButton clickableButton: clickableButtons){
			clickableButton.update();
		}
		
		for(Slider slider: sliders){
			slider.update();
		}
		game.volume = volume.getValue();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

}
