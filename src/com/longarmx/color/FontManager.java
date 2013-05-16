package com.longarmx.color;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FontManager{
	
	private static FontManager instance = new FontManager();
	
	private TextureRegion[] charactersRegion = new TextureRegion[38];
	private char[] characters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9',' ',':'};
	private float spacing = 1.03f;
	private float r = 0;
	private float g = 0;
	private float b = 0;
	private float a = 1;
	
	public FontManager(){
		create();
	}
	
	public void create(){
		int x = 0, y = 0;
		for(int i = 0; i < charactersRegion.length; i++){
			charactersRegion[i] = getRegion(x, y);
			x++;
			if(x >= 14){
				x = 0;
				y++;
			}
		}
	}
	
	public void draw(String string, int x, int y, float scale, SpriteBatch batch){
		batch.enableBlending();
		batch.setColor(r, g, b, a);
		string = string.toUpperCase();
		char[] chars = string.toCharArray();
		int x1 = 0;
		for(int i = 0; i < chars.length; i++){
			batch.draw(charactersRegion[getCharI(chars[i])], x + x1 * 7 * scale * spacing, y, scale * 7, scale * 10);
			x1++;
		}
	}
	
	private int getCharI(char chars){
		for(int i = 0; i < characters.length; i++){
			if(chars == characters[i]){
				return i;
			}
		}
		return 36;
	}
	
	public void setColor(int r, int g, int b, int a){
		if(this.r != r)	this.r = r;
		if(this.g != g)	this.g = g;
		if(this.b != b)	this.b = b;
		if(this.a != a)	this.a = a;
	}
	
	public float getTextWidth(String string, float scale){
		return string.length() * scale * spacing * 7;
	}
	
	private TextureRegion getRegion(int x, int y){
		return Util.getFromSpriteSheet(x * 7 + 100, y * 10, 7, 10);
	}
	
	public static FontManager getInstance(){
		return instance;
	}
}
