package com.longarmx.color;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class Gui implements Disposable{
	
	private Texture background;
	
	protected float shade = 1.0f;
	
	protected void create(){
		background = new Texture(Gdx.files.internal("res/titleBackground.png"));
	}
	
	protected void render(SpriteBatch batch){
		batch.setColor(shade, shade, shade, 1);
		batch.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);
	}
	
	protected void update(){
		shade = (float)Input.mouseY/Main.HEIGHT + .1f;
		if(shade > 1)	shade = 1.0f;
	}

	@Override
	public void dispose() {
		background.dispose();
	}

}
