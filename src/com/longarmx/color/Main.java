package com.longarmx.color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	
	public static final int WIDTH = 1080;
	public static final int HEIGHT = 768;
	public static Game instance;

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = WIDTH;
		config.height = HEIGHT;
		config.useGL20 = true;
		try {
			config.title = "Color Game - V: " + new Scanner(new File("VERSION.txt")).nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		instance = new Game();
		new LwjglApplication(instance, config);
	}

}
