package com.longarmx.color;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	
	public static final int WIDTH = 1080;
	public static final int HEIGHT = 768;

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = WIDTH;
		config.height = HEIGHT;
		config.useGL20 = true;
		config.title = "Color Game - V:0.0.1";
		new LwjglApplication(new Game(), config);
	}

}
