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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class ComponentSlider extends Component{
	
	private static int srcX = 0;
	private static int srcY = 100;
	private static int srcWidth = 50;
	private static int srcHeight = 50;
	
	private float sliderX = 0;
	private float sliderWidth = 10;
	
	private float value = 1.0f;
	
	private float r = 1;
	private float g = 1;
	private float b = 1;
	
	private TextureRegion slider;
	
	private ClickManager clickManager;

	public ComponentSlider(int x, int y, ClickManager clickManager) {
		super(x, y, srcX, srcY, srcWidth, srcHeight);
		this.clickManager = clickManager;
		create();
	}

	public ComponentSlider(int x, int y, int width, int height, ClickManager clickManager) {
		super(x, y, width, height, srcX, srcY, srcWidth, srcHeight);
		this.clickManager = clickManager;
		create();
	}
	
	private void create(){
		slider = Util.getFromSpriteSheet(50, 100, 50, 50);
		sliderX = x + width - sliderWidth/2;
	}
	
	public void render(SpriteBatch batch){
		batch.setColor(1, 1, 1, 1);
		super.render(batch);
		if(selected) batch.setColor(r, g, b, 1);
		batch.draw(slider, sliderX, y, sliderWidth, height);
	}
	
	public void update(){
		super.update();
			if(Input.mouseX > x && Input.mouseX < x + width && Input.mouseY > y && Input.mouseY < y + height){
				if(Input.mouseDown){
					sliderX = Input.mouseX - sliderWidth/2;
					value = (sliderX + sliderWidth/2 - x)/width;
					clickManager.onClick();
				}
			}
			if(Input.mouseX > sliderX && Input.mouseX < sliderX + sliderWidth && Input.mouseY > y && Input.mouseY < y + height){
				selected = true;
			}else{
				selected = false;
			}
	}
	
	public void setHighlightColor(float r, float g, float b){
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public float getValue(){
		return value;
	}
	
}
