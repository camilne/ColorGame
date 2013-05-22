package com.longarmx.color;

public enum Difficulty {
	
	EASY(1, "Easy"),
	NORMAL(2, "Normal"),
	HARD(3, "Hard");
	
	private final int difficulty;
	private final String text;
	
	Difficulty(int difficulty, String text){
		this.difficulty = difficulty;
		this.text = text;
	}
	
	public int getDifficulty(){
		return difficulty;
	}
	
	public String toString(){
		return text;
	}
}
