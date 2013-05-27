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

public class ClickableButton extends Component implements Disposable{
	
	private static int srcX = 50;
	private static int srcY = 50;
	private static int srcWidth = 50;
	private static int srcHeight = 50;
	
	private String text = "";
	private int scale = 1;
	private FontManager manager;
	private ClickManager clickManager;
	private Sound click;
	
	private float r = .9f;
	private float g = .9f;
	private float b = .9f;
	
	public ClickableButton(int x, int y, ClickManager clickManager) {
		super(x, y, srcX, srcY, srcWidth, srcHeight);
		this.clickManager = clickManager;
		create();
	}

	public ClickableButton(int x, int y, int width, int height, ClickManager clickManager) {
		super(x, y, width, height, srcX, srcY, srcWidth, srcHeight);
		this.clickManager = clickManager;
		create();
	}
	
	private void create(){
		manager = new FontManager();
		click = Gdx.audio.newSound(Gdx.files.internal("res/click.wav"));
	}
	
	public ClickableButton setText(String text, int scale){
		this.text = text;
		this.scale = scale;
		return this;
	}
	
	public ClickableButton setHighlightColor(float r, float g, float b){
		this.r = r;
		this.g = g;
		this.b = b;
		return this;
	}
	
	public void render(SpriteBatch batch){
		if(selected){
			batch.setColor(r, g, b, 1);
		}else{
			batch.setColor(1, 1, 1, 1);
		}
		super.render(batch);
		manager.draw(text, x + width/2 - (int)manager.getTextWidth(text, scale)/2, y + height/2 - (int)manager.getTextHeight(scale)/2, scale, batch);
	}

	@Override
	public void update(){
		super.update();
		if(Input.mouseX > x && Input.mouseX < x+width && Input.mouseY > y && Input.mouseY < y+height){
			selected = true;
			if(Gdx.input.justTouched()){
				click();
			}
		}else{
			selected = false;
		}
	}
	
	private void click(){
		clickManager.onClick();
		click.play(game.volume);
	}
	
	@Override
	public void dispose(){
		click.dispose();
	}
	
}
