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

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;

/**
 * A class containing all of the different color combinations. Colors are
 * made up of red, green, and blue. Light and dark values manipulate the
 * colors' rgb values.
 */
public class Colors {

	// The light value
	private static float l = 1f;
	// The dark value
	private static float d = .5f;
	// The alpha value (not really used)
	private static float a = 1f;
	
	public static final Color BRIGHT_RED = new Color(l, 0, 0, a);
	public static final Color BRIGHT_GREEN = new Color(0, l, 0, a);
	public static final Color BRIGHT_BLUE = new Color(0, 0, l, a);
	public static final Color BRIGHT_YELLOW = new Color(l, l, 0, a);
	public static final Color BRIGHT_MAGENTA = new Color(l, 0, l, a);
	public static final Color BRIGHT_CYAN = new Color(0, l, l, a);
	public static final Color WHITE = new Color(l, l, l, a);
	public static final Color GRAY = new Color(d, d, d, a);
	public static final Color BLACK = new Color(0, 0, 0, a);
	public static final Color DARK_RED = new Color(d, 0, 0, a);
	public static final Color DARK_GREEN = new Color(0, d, 0, a);
	public static final Color DARK_BLUE = new Color(0, 0, d, a);
	public static final Color DARK_YELLOW = new Color(d, d, 0, a);
	public static final Color DARK_MAGENTA = new Color(d, 0, d, a);
	public static final Color DARK_CYAN = new Color(0, d, d, a);
	public static final Color PURPLE = new Color(d, 0, l, a);
	public static final Color SKY_BLUE = new Color(0, d, l, a);
	public static final Color ORANGE = new Color(l, d, 0, a);
	public static final Color LIME_GREEN = new Color(d, l, 0, a);
	public static final Color PINK = new Color(l, d, l, a);
	public static final Color PALE_BLUE = new Color(d, d, l, a);
	public static final Color SALMON = new Color(l, d, d, a);
	
	// A list containing all of the colors. Used for looping through to find a color.
	public static List<Color> list = new ArrayList<Color>();
	
	// Static block that automatically adds all of the colors to the list.
	static{
		list.add(BRIGHT_RED);
		list.add(BRIGHT_GREEN);
		list.add(BRIGHT_BLUE);
		list.add(BRIGHT_YELLOW);
		list.add(BRIGHT_MAGENTA);
		list.add(BRIGHT_CYAN);
		list.add(WHITE);
		list.add(GRAY);
		list.add(BLACK);
		list.add(DARK_RED);
		list.add(DARK_GREEN);
		list.add(DARK_BLUE);
		list.add(DARK_YELLOW);
		list.add(DARK_MAGENTA);
		list.add(DARK_CYAN);
		list.add(PURPLE);
		list.add(SKY_BLUE);
		list.add(LIME_GREEN);
		list.add(PINK);
		list.add(PALE_BLUE);
		list.add(SALMON);
	}
}
