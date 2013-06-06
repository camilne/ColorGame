package com.longarmx.color;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Sort;

public class GuiHighscore extends Gui {
	
	ComponentClickableButton back;
	ComponentClickableButton resetScores;
	
	List<ComponentClickableButton> buttons = new ArrayList<ComponentClickableButton>();
	
	FileHandle path;
	
	String[] scores = new String[10];
	Sort sorter = Sort.instance();
	
	public GuiHighscore() {
		create();
	}
	
	public void create(){
		super.create();
		if(!Gdx.files.local("res/highscores.lgx").exists())	makeFile();
		path = Gdx.files.local("res/highscores.lgx");
		
		back = new ComponentClickableButton(Main.ORIGINAL_WIDTH/2 - 250, 200, 500, 75, new ClickManager() {

			@Override
			public void onClick() {
				game.state = States.OPTIONS;
				game.options.setColor(r, g, b);
			}
			
		});
		buttons.add(back);
		back.setHighlightColor(1, 1, .5f);
		back.setText("Back", 4);
		
		resetScores = new ComponentClickableButton(Main.ORIGINAL_WIDTH/2 - 75, 100, 150, 50, new ClickManager() {

			@Override
			public void onClick() {
				resetScores();
			}
			
		});
		buttons.add(resetScores);
		resetScores.setHighlightColor(1, 0, 0);
		resetScores.setText("Reset Scores", 1);	
		
		loadDirectory();
		
	}
	
	private void loadDirectory(){
		if(!path.exists()) makeFile();
		String fileString = path.readString();
		char[] tmp = new char[300];
		int index = scores.length - 1;
		for(int i = 0; i < fileString.length(); i++){
			if(fileString.charAt(i) == '\n' && index >= 0){
				scores[index] = new String(tmp);
				scores[index] = scores[index].trim();
				index--;
				tmp = null;
				tmp = new char[300];
			}
			if(fileString.charAt(i) != '\n' && !Character.isWhitespace(fileString.charAt(i))) tmp[i] = fileString.charAt(i);
		}
	}
	
	private void makeFile(){
		try {
			new File("res/highscores.lgx").createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < 10; i++){
			Gdx.files.local("res/highscores.lgx").writeString((i + 1)+"\n", true);
		}
	}
	
	public void score(int score){
		if(score <= Integer.parseInt(scores[getMinScoreIndex()]))	return;
		path.writeString("", false);	// Clear file
		
		scores[getMinScoreIndex()] = String.valueOf(score);
		
		int[] intScores = new int[scores.length];
		
		for(int i = 0; i< scores.length; i++){
			intScores[i] = Integer.parseInt(scores[i]);
		}
		Arrays.sort(intScores);
		for(int i = 0; i< scores.length; i++){
			scores[i] = String.valueOf(intScores[i]);
		}
		
		for(int i = 0; i < scores.length; i++){
			path.writeString(scores[i] + "\n", true);
		}
		
		loadDirectory();
	}
	
	private void resetScores(){
		path.delete();
		loadDirectory();
	}
	
	private int getMinScoreIndex(){
		int minScore = Integer.MAX_VALUE;
		int index = 0;
		for(int i = 0; i < scores.length; i++){
			minScore = Math.min(minScore, Integer.parseInt(scores[i]));
		}
		for(int i = 0; i < scores.length; i++){
			if(Integer.parseInt(scores[i]) == minScore){
				index = i;
				break;
			}
		}
		return index;
	}
	
	public void update(){
		super.update();
		for(ComponentClickableButton button : buttons){
			button.update();
		}
	}
	
	public void render(SpriteBatch batch){
		super.render(batch);
		for(ComponentClickableButton button : buttons){
			button.render(batch);
		}
		
		for(int i = 0; i < scores.length; i++){
			int x = Main.ORIGINAL_WIDTH/2 - (int) manager.getTextWidth("10. " + scores[0], 2)/2;
			if(i == scores.length - 1) x -= manager.getTextWidth(" ", 2);
			if(scores[i] != null)	manager.draw(String.valueOf(i + 1) + ". " + scores[i], x, -40 * i + 720, 2, batch);
		}
	}
	
	public void dispose(){
		super.dispose();
		for(ComponentClickableButton button : buttons){
			button.dispose();
		}
	}

}
