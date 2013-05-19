package com.longarmx.color;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class GuiOptions implements Disposable{
	
	Game game;
	
	List<ClickableButton> clickableButtons = new ArrayList<ClickableButton>();
	List<Slider> sliders = new ArrayList<Slider>();
	
	private Slider volume;
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
		
		volume = new Slider(Main.WIDTH/2 - 250, 500, 500, 75, new ClickManager(){

			@Override
			public void onClick() {
				
			}
			
		});
		sliders.add(volume);
		
		back = new ClickableButton(Main.WIDTH/2 - 250, 200, 500, 75, new ClickManager() {
			
			@Override
			public void onClick() {
				game.state = States.TITLE;
			}
			
		});
		clickableButtons.add(back);
		back.setText("Back", 4);
		back.setHighlightColor(1, 0, 1);
	}
	
	public void render(SpriteBatch batch){
		batch.setColor(1, 1, 1, 1);
		batch.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);
		for(ClickableButton component: clickableButtons){
			component.render(batch);
		}
		
		for(Slider slider: sliders){
			slider.render(batch);
		}
		
		manager.draw("Options", Main.WIDTH/2 - (int)manager.getTextWidth("Options", 7)/2, 600, 7, batch);
		manager.draw("Volume: ", volume.x - (int)manager.getTextWidth("Volume: ", 3), 500 + volume.height/2 - (int)manager.getTextHeight(3)/2, 3, batch);
	}
	
	public void update(){
		for(ClickableButton clickableButton: clickableButtons){
			clickableButton.update();
		}
		
		for(Slider slider: sliders){
			slider.update();
		}
		game.volume = volume.getValue();
	}

	@Override
	public void dispose() {
		background.dispose();
	}

}
