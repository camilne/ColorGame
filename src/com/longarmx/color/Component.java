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

/**
 * The base class of all GUI elements. This class handles stuff such as initialization and rendering.
 */
public class Component {
	
	// The position and size values of the component.
	protected int x;
	protected int y;
	protected int width = 0;
	protected int height = 0;
	
	// The texture region used.
	protected TextureRegion texture;
	
	// Whether or not this component is selected.
	protected boolean selected = false;
	
	// The game instance.
	public Game game;
	
	/**
	 * @param x				The x position.
	 * @param y				The y position.
	 * @param srcX			The x position of the texture region.
	 * @param srcY			The y position of the texture region.
	 * @param srcWidth		The width of the texture region.
	 * @param srcHeight		The height of the texture region.
	 */
	public Component(int x, int y, int srcX, int srcY, int srcWidth, int srcHeight){
		this.game = Main.instance;
		this.x = x;
		this.y = y;
		create(srcX, srcY, srcWidth, srcHeight);
	}
	
	/**
	 * @param x				The x position.
	 * @param y				The y position.
	 * @param width			The width of the component.
	 * @param height		The height of the component.
	 * @param srcX			The x position of the texture region.
	 * @param srcY			The y position of the texture region.
	 * @param srcWidth		The width of the texture region.
	 * @param srcHeight		The height of the texture region.
	 */
	public Component(int x, int y, int width, int height, int srcX, int srcY, int srcWidth, int srcHeight){
		this(x, y, srcX, srcY, srcWidth, srcHeight);
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Initializes the component.
	 */
	public void create(int srcX, int srcY, int srcWidth, int srcHeight){
		texture = Util.getFromSpriteSheet(srcX, srcY, srcWidth, srcHeight);
		
		// Automatically sets the size to the texture size if no size was given.
		if(width == 0) width = texture.getRegionWidth();
		if(height == 0) height = texture.getRegionHeight();
	}

	/**
	 * 
	 * @param batch	The current, active SpriteBatch
	 */
	public void render(SpriteBatch batch){
		batch.enableBlending();
		batch.draw(texture, x, y, width, height);
	}
	
	/**
	 * Update method that is called every frame.
	 */
	public void update(){
		
	}

}
