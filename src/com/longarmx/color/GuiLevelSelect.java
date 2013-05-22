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

public class GuiLevelSelect extends Gui {
	
	public List<ClickableButton> buttons = new ArrayList<ClickableButton>();
	
	ClickableButton easy;
	ClickableButton normal;
	ClickableButton hard;
	ClickableButton back;
	
	FontManager manager;

	public GuiLevelSelect() {
		create();
	}
	
	public void create(){
		super.create();
		manager = FontManager.getInstance();
		
		easy = new ClickableButton(Main.WIDTH/2 - 250, 500, 500, 75, new ClickManager(){

			@Override
			public void onClick() {
				game.state = States.GAME;
				game.difficulty = 1;
				game.reset();
			}
			
		});
		easy.setHighlightColor(0, 1, 0);
		easy.setText("Easy", 4);
		buttons.add(easy);
		
		normal = new ClickableButton(Main.WIDTH/2 - 250, 400, 500, 75, new ClickManager(){

			@Override
			public void onClick() {
				game.state = States.GAME;
				game.difficulty = 2;
				game.reset();
			}
			
		});
		normal.setHighlightColor(1, 1, 0);
		normal.setText("Normal", 4);
		buttons.add(normal);
		
		hard = new ClickableButton(Main.WIDTH/2 - 250, 300, 500, 75, new ClickManager(){

			@Override
			public void onClick() {
				game.state = States.GAME;
				game.difficulty = 3;
				game.reset();
			}
			
		});
		hard.setHighlightColor(1, 0, 0);
		hard.setText("Hard", 4);
		buttons.add(hard);
		
		back = new ClickableButton(Main.WIDTH/2 - 250, 100, 500, 75, new ClickManager(){

			@Override
			public void onClick() {
				game.state = States.TITLE;
				game.title.setColor(r, g, b);
			}
			
		});
		back.setHighlightColor(.5f, 1, .5f);
		back.setText("Back", 4);
		buttons.add(back);
	}
	
	public void update(){
		super.update();
		for(ClickableButton button: buttons){
			button.update();
		}
	}
	
	public void render(SpriteBatch batch){
		super.render(batch);
		for(ClickableButton button: buttons){
			button.render(batch);
		}
	}
	
	public void dispose(){
		super.dispose();
	}

}