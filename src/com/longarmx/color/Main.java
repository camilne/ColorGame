/*
 * Copyright 2013 Longarmx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.longarmx.color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	
	public static final int ORIGINAL_WIDTH = 1080;
	public static final int ORIGINAL_HEIGHT = 768;
	public static int WIDTH = ORIGINAL_WIDTH;
	public static int HEIGHT = ORIGINAL_HEIGHT;
	
	public static float scaleX = 1;
	public static float scaleY = 1;
	
	public static Game instance;

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = ORIGINAL_WIDTH;
		config.height = ORIGINAL_HEIGHT;
		config.useGL20 = true;
		config.resizable = false;
		config.addIcon("res/icon128.png", FileType.Internal);
		config.addIcon("res/icon32.png", FileType.Internal);
		config.addIcon("res/icon16.png", FileType.Internal);
		try {
			config.title = "Color Game - V: " + new Scanner(new File("res/VERSION.txt")).nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		instance = new Game();
		new LwjglApplication(instance, config);
	}
	
	public static void setWidth(int width){
		WIDTH = width;
		scaleX = WIDTH/ORIGINAL_WIDTH;
	}
	
	public static void setHeight(int height){
		HEIGHT = height;
		scaleY = HEIGHT/ORIGINAL_HEIGHT;
	}

}
