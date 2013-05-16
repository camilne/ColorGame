package com.longarmx.color;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button extends Component {
	
	private static int srcX = 0;
	private static int srcY = 50;
	private static int srcWidth = 50;
	private static int srcHeight = 50;
	
	private Color color = new Color(1, 1, 1, 1);
	
	public Button(int x, int y, Game game) {
		super(x, y, srcX, srcY, srcWidth, srcHeight, game);
	}

	public Button(int x, int y, int width, int height,Game game) {
		super(x, y, width, height, srcX, srcY, srcWidth, srcHeight, game);
	}

	public void render(SpriteBatch batch){
		if(selected){
			batch.setColor(color.r, color.g, color.b, color.a - .25f);
		}else{
			batch.setColor(color);
		}
		super.render(batch);

	}
	
	public void setColor(float r, float g, float b, float a){
		this.color = new Color(r, g, b, a);
	}
}
