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
	
	public static float boolToFloat(boolean bool){
		return bool ? 1 : 0;
	}
	
	public static void dispose(){
		spritesheet.dispose();
	}

}
