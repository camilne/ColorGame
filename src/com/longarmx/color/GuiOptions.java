package com.longarmx.color;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class GuiOptions implements Disposable{
	
	Game game;
	
	List<ClickableButton> components = new ArrayList<ClickableButton>();
	
	//private ClickableButton start;
	//private ClickableButton options;
	private ClickableButton back;
	
	private FontManager manager;
	
	Texture background;
	
	public GuiOptions(){
		create();
	}
	
	public void create(){
		this.game = Main.instance;
		background = new Texture(Gdx.files.internal("res/titleBackground.png"));
		manager = new FontManager();
		
		/*start = new ClickableButton(Main.WIDTH/2 - 250, 400, 500, 75, new ClickManager(){

			@Override
			public void onClick() {
				game.state = States.GAME;
			}
			
		});
		components.add(start);
		start.setText("Start Game", 4);
		start.setHighlightColor(1, 0, 0);
		
		options = new ClickableButton(Main.WIDTH/2 - 250, 300, 500, 75, new ClickManager() {
			
			@Override
			public void onClick() {
				
			}
			
		});
		components.add(options);
		options.setText("Options", 4);
		options.setHighlightColor(0, 1, 0);*/
		
		back = new ClickableButton(Main.WIDTH/2 - 250, 200, 500, 75, new ClickManager() {
			
			@Override
			public void onClick() {
				game.state = States.TITLE;
			}
			
		});
		components.add(back);
		back.setText("Back", 4);
		back.setHighlightColor(1, 0, 1);
	}
	
	public void render(SpriteBatch batch){
		batch.setColor(1, 1, 1, 1);
		batch.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);
		for(ClickableButton component: components){
			component.render(batch);
		}
		manager.draw("Options", Main.WIDTH/2 - (int)manager.getTextWidth("Options", 7)/2, 600, 7, batch);
	}
	
	public void update(){
		for(ClickableButton component: components){
			component.update();
		}
	}

	@Override
	public void dispose() {
		
	}

}
