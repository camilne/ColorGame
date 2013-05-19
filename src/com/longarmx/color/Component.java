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

public class Component {
	
	protected int x;
	protected int y;
	protected int width = 0;
	protected int height = 0;
	
	protected TextureRegion texture;
	
	protected boolean selected = false;
	
	public Game game;
	
	public Component(int x, int y, int srcX, int srcY, int srcWidth, int srcHeight){
		this.game = Main.instance;
		this.x = x;
		this.y = y;
		create(srcX, srcY, srcWidth, srcHeight);
	}
	
	public Component(int x, int y, int width, int height, int srcX, int srcY, int srcWidth, int srcHeight){
		this(x, y, srcX, srcY, srcWidth, srcHeight);
		this.width = width;
		this.height = height;
	}
	
	public void create(int srcX, int srcY, int srcWidth, int srcHeight){
		texture = Util.getFromSpriteSheet(srcX, srcY, srcWidth, srcHeight);
		
		if(width == 0) width = texture.getRegionWidth();
		if(height == 0) height = texture.getRegionHeight();
	}

	public void render(SpriteBatch batch){
		batch.enableBlending();
		batch.draw(texture, x, y, width, height);
	}
	
	public void update(){
		
	}

}
