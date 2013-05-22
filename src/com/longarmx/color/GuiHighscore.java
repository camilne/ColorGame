package com.longarmx.color;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GuiHighscore extends Gui {
	
	ClickableButton back;
	
	String[] scores = new String[10];
	
	public GuiHighscore() {
		create();
	}
	
	public void create(){
		super.create();
		
		back = new ClickableButton(Main.WIDTH/2 - 250, 200, 500, 75, new ClickManager() {

			@Override
			public void onClick() {
				game.state = States.OPTIONS;
				game.options.setColor(r, g, b);
			}
			
		});
		back.setHighlightColor(1, 1, .5f);
		back.setText("Back", 4);
		
		loadDirectory();
	}
	
	private void loadDirectory(){		
		String fileString = Gdx.files.internal("res/highscores.lgx").readString();
		char[] tmp = new char[300];
		int index = 0;
		for(int i = 0; i < fileString.length(); i++){
			if(fileString.charAt(i) == '\n'){
				scores[index] = new String(tmp);
				scores[index] = scores[index].trim();
				index++;
				tmp = null;
				tmp = new char[300];
			}
			if(fileString.charAt(i) != '\n' && !Character.isWhitespace(fileString.charAt(i))) tmp[i] = fileString.charAt(i);
		}
	}
	
	public void score(int score){
		for(int i = 0; i < scores.length - 1; i++){
			if(Integer.parseInt(scores[i + 1]) > score && i != 0){
				Gdx.files.external("res/highscores.lgx").writeString(String.valueOf(score), false);
			}
		}
	}
	
	public void update(){
		super.update();
		back.update();
	}
	
	public void render(SpriteBatch batch){
		super.render(batch);
		back.render(batch);
		for(int i = 0; i < scores.length; i++){
			int x = Main.WIDTH/2 - (int) manager.getTextWidth("10. " + scores[0], 2)/2;
			if(i == 0) x -= manager.getTextWidth(" ", 2);
			if(scores[i] != null)	manager.draw(String.valueOf(10 - i) + ". " + scores[i], x, 40 * i + 320, 2, batch);
		}
	}
	
	public void dispose(){
		super.dispose();
		back.dispose();
	}

}
