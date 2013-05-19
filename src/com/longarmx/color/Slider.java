package com.longarmx.color;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Slider extends Component{
	
	private static int srcX = 0;
	private static int srcY = 100;
	private static int srcWidth = 50;
	private static int srcHeight = 50;
	
	private float sliderX = 0;
	private float sliderWidth = 10;
	
	private float value = 1.0f;
	
	private TextureRegion slider;
	
	private ClickManager clickManager;

	public Slider(int x, int y, ClickManager clickManager) {
		super(x, y, srcX, srcY, srcWidth, srcHeight);
		this.clickManager = clickManager;
		create();
	}

	public Slider(int x, int y, int width, int height, ClickManager clickManager) {
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
		batch.draw(slider, sliderX, y, sliderWidth, height);
	}
	
	public void update(){
		super.update();
		if(Input.mouseDown){
			if(Input.mouseX > x && Input.mouseX < x+width && Input.mouseY > y && Input.mouseY < y+height){
				sliderX = Input.mouseX - sliderWidth/2;
				value = (sliderX + sliderWidth/2 - x)/width;
				clickManager.onClick();
			}
		}
	}
	
	public float getValue(){
		return value;
	}
	
}
