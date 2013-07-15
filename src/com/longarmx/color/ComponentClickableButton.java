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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 * This class is for a click-able button that can also hold text.
 */
public class ComponentClickableButton extends Component implements Disposable{
	
	// All of the texture region x, y, width, height.
	private static int srcX = 50;
	private static int srcY = 50;
	private static int srcWidth = 50;
	private static int srcHeight = 50;
	
	// The text that is displayed in the button.
	private String text = "";
	// The scale of the text.
	private int scale = 1;
	// The font manager instance.
	private FontManager manager;
	// The click manager instance.
	private ClickManager clickManager;
	// The sound that is played whenever the button is clicked.
	private Sound click;
	
	// The red, green, and blue of the button.
	private float r = .9f;
	private float g = .9f;
	private float b = .9f;
	
	/**
	 * 
	 * @param x					The button's x position.
	 * @param y					The button's y position.
	 * @param clickManager		The click manager that gets called whenever a click has happened.
	 */
	public ComponentClickableButton(int x, int y, ClickManager clickManager) {
		super(x, y, srcX, srcY, srcWidth, srcHeight);
		this.clickManager = clickManager;
		create();
	}

	public ComponentClickableButton(int x, int y, int width, int height, ClickManager clickManager) {
		super(x, y, width, height, srcX, srcY, srcWidth, srcHeight);
		this.clickManager = clickManager;
		create();
	}
	
	/**
	 * Initializes everything.
	 */
	private void create(){
		manager = new FontManager();
		click = Gdx.audio.newSound(Gdx.files.internal("res/click.wav"));
	}
	
	/**
	 * 
	 * @param text		The text to be displayed
	 * @param scale		The scale of the text.
	 * @return			Returns this component for chaining.
	 */
	public ComponentClickableButton setText(String text, int scale){
		this.text = text;
		this.scale = scale;
		return this;
	}
	
	/**
	 * Sets the color that the button will become whenever the mouse is hovering over it.
	 * @param r		The red.
	 * @param g		The green.
	 * @param b		The blue.
	 * @return		Returns this component for chaining.
	 */
	public ComponentClickableButton setHighlightColor(float r, float g, float b){
		this.r = r;
		this.g = g;
		this.b = b;
		return this;
	}
	
	/**
	 * Renders this component.
	 */
	public void render(SpriteBatch batch){
		
		// If mouse button is hovering over this component.
		if(selected){
			// Set the color to the highlight color.
			batch.setColor(r, g, b, 1);
		}else{
			// Else light up the button
			batch.setColor(1, 1, 1, 1);
		}
		
		super.render(batch);
		
		// Render the text over the button.
		manager.draw(text, x + width/2 - (int)manager.getTextWidth(text, scale)/2, y + height/2 - (int)manager.getTextHeight(scale)/2, scale, batch);
	}

	/**
	 * Updates this component.
	 */
	@Override
	public void update(){
		
		super.update();
		
		// If mouse is within bounds.
		if(Input.mouseX > x && Input.mouseX < x+width && Input.mouseY > y && Input.mouseY < y+height){
			
			// Sets the button to be highlighted.
			selected = true;
			
			// If mouse button just got pressed.
			if(Gdx.input.justTouched()){
				click();
			}
		}else{
			selected = false;
		}
	}
	
	/**
	 * Called whenever the button gets clicked.
	 */
	private void click(){
		
		// Does whatever the click manager is supposed to do.
		clickManager.onClick();
		
		// Plays the sound
		click.play(game.masterSound);
	}
	
	@Override
	public void dispose(){
		click.dispose();
	}
	
}
