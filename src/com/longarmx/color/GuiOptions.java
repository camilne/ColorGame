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
	
	List<ComponentClickableButton> clickableButtons = new ArrayList<ComponentClickableButton>();
	List<ComponentSlider> sliders = new ArrayList<ComponentSlider>();
	
	private ComponentSlider soundVolume;
	private ComponentSlider musicVolume;
	private ComponentClickableButton soundMute;
	private ComponentClickableButton musicMute;
	private ComponentClickableButton back;
	private ComponentClickableButton highscores;
	
	public boolean musicMuted = false;
	
	public GuiOptions(){
		create();
	}
	
	public void create(){
		this.game = Main.instance;
		super.create();
		
		soundVolume = new ComponentSlider(Main.ORIGINAL_WIDTH/2 - 250, 500, 500, 50, new ClickManager(){

			@Override
			public void onClick() {
				
			}
			
		});
		sliders.add(soundVolume);
		soundVolume.setHighlightColor(0, 1, 1);
		
		soundMute = new ComponentClickableButton(Main.ORIGINAL_WIDTH/2 + 300, 500, 100, 50, new ClickManager() {
			
			@Override
			public void onClick() {
				if(game.soundMuted){
					game.soundMuted = false;
					soundMute.setText("Mute", 2);
				}else{
					game.soundMuted = true;
					soundMute.setText("Unmute", 2);
				}
			}
			
		});
		clickableButtons.add(soundMute);
		soundMute.setText("Mute", 2);
		soundMute.setHighlightColor(1, .5f, .5f);
		
		musicVolume = new ComponentSlider(Main.ORIGINAL_WIDTH/2 - 250, 400, 500, 50, new ClickManager(){

			@Override
			public void onClick() {
				game.music.setVolume(musicVolume.getValue());
			}
			
		});
		sliders.add(musicVolume);
		musicVolume.setHighlightColor(.5f, .5f, 1);
		
		musicMute = new ComponentClickableButton(Main.ORIGINAL_WIDTH/2 + 300, 400, 100, 50, new ClickManager() {
			
			@Override
			public void onClick() {
				if(!musicMuted){
					musicMuted = true;
					game.music.pause();
					musicMute.setText("Unmute", 2);
				}else{
					musicMuted = false;
					game.music.play();
					musicMute.setText("Mute", 2);
				}
			}
			
		});
		clickableButtons.add(musicMute);
		musicMute.setText("Mute", 2);
		musicMute.setHighlightColor(1, .5f, 1);
		
		highscores = new ComponentClickableButton(Main.ORIGINAL_WIDTH/2 - 250, 300, 500, 75, new ClickManager() {
			
			@Override
			public void onClick() {
				game.state = States.HIGHSCORE;
				game.highscore.setColor(r, g, b);
			}
			
		});
		clickableButtons.add(highscores);
		highscores.setText("Highscores", 4);
		highscores.setHighlightColor(.5f, 0, 1);
		
		back = new ComponentClickableButton(Main.ORIGINAL_WIDTH/2 - 250, 200, 500, 75, new ClickManager() {
			
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
		for(ComponentClickableButton component: clickableButtons){
			component.render(batch);
		}
		
		for(ComponentSlider slider: sliders){
			slider.render(batch);
		}
		
		manager.draw("Options", Main.ORIGINAL_WIDTH/2 - (int)manager.getTextWidth("Options", 7)/2, 600, 7, batch);
		manager.draw("Sound: ", soundVolume.x - (int)manager.getTextWidth("Sound: ", 3), soundVolume.y + soundVolume.height/2 - (int)manager.getTextHeight(3)/2, 3, batch);
		manager.draw("Music: ", musicVolume.x - (int)manager.getTextWidth("Music: ", 3), musicVolume.y + musicVolume.height/2 - (int)manager.getTextHeight(3)/2, 3, batch);
	}
	
	public void update(){
		super.update();
		for(ComponentClickableButton clickableButton: clickableButtons){
			clickableButton.update();
		}
		
		for(ComponentSlider slider: sliders){
			slider.update();
		}
		game.soundVolume = soundVolume.getValue();
		game.musicVolume = musicVolume.getValue();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

}
