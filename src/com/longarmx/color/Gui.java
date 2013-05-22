package com.longarmx.color;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class Gui implements Disposable{
	
	private Texture background;
	protected Game game;
	
	protected float r = 1.0f;
	protected float g = 1.0f;
	protected float b = 1.0f;
	
	protected void create(){
		background = new Texture(Gdx.files.internal("res/titleBackground.png"));
		this.game = Main.instance;
	}
	
	protected void render(SpriteBatch batch){
		batch.setColor(r, g, b, 1);
		batch.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);
	}
	
	protected void update(){
		
		r = (float)Input.mouseX/Main.WIDTH;
		if(r > .9f)	r = 0.9f;
		if(r < .1f)	r = 0.1f;
		
		g = (float)(Main.HEIGHT - Input.mouseY)/Main.HEIGHT;
		if(g > .9f)	g = 0.9f;
		if(g < .1f)	g = 0.1f;
		
		b = (float)(Main.WIDTH - Input.mouseX)/Main.HEIGHT;
		if(b > .9f)	b = 0.9f;
		if(b < .1f)	b = 0.1f;
	}

	@Override
	public void dispose() {
		background.dispose();
	}
	
	public void setColor(float r, float g, float b){
		this.r = r;
		this.g = g;
		this.b = b;
	}
}
