package com.longarmx.color;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Component {
	
	private int x;
	private int y;
	private int width = 0;
	private int height = 0;
	public boolean selected = false;
	
	private TextureRegion texture;
	private TextureRegion textureOverlay;
	
	public Game game;
	
	public Component(int x, int y, int srcX, int srcY, int srcWidth, int srcHeight, Game game){
		this.game = game;
		this.x = x;
		this.y = y;
		create(srcX, srcY, srcWidth, srcHeight);
	}
	
	public Component(int x, int y, int width, int height, int srcX, int srcY, int srcWidth, int srcHeight, Game game){
		this(x, y, srcX, srcY, srcWidth, srcHeight, game);
		this.width = width;
		this.height = height;
	}
	
	public void create(int srcX, int srcY, int srcWidth, int srcHeight){
		texture = Util.getFromSpriteSheet(srcX, srcY, srcWidth, srcHeight);
		textureOverlay = Util.getFromSpriteSheet(srcX, srcY - srcHeight, srcWidth, srcHeight);
		
		if(width == 0) width = texture.getRegionWidth();
		if(height == 0) height = texture.getRegionHeight();
	}

	public void render(SpriteBatch batch){
		batch.enableBlending();
		batch.draw(texture, x, y, width, height);
		if(selected) batch.draw(textureOverlay, x, y, width, height);
		batch.disableBlending();
	}
	
	public void dispose(){
		
	}

}
