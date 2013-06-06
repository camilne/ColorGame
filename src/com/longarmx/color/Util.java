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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Util{
	
	private static Texture spritesheet;
	
	static{
		spritesheet = new Texture(Gdx.files.internal("res/spritesheet.png"));
	}
	
	public static TextureRegion getFromSpriteSheet(int x, int y, int width, int height){
		return new TextureRegion(spritesheet, x, y, width, height);
	}
	
	public static Texture loadTexture(String path){
		return new Texture(Gdx.files.internal(path));
	}
	
	public static int boolToInt(boolean bool){
		return bool ? 1 : 0;
	}
	
	public static void dispose(){
		spritesheet.dispose();
	}

}
