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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ColorButton extends Component {
	
	private static int srcX = 0;
	private static int srcY = 50;
	private static int srcWidth = 50;
	private static int srcHeight = 50;
	
	private Color color = new Color(1, 1, 1, 1);
	
	public ColorButton(int x, int y) {
		super(x, y, srcX, srcY, srcWidth, srcHeight);
	}

	public ColorButton(int x, int y, int width, int height) {
		super(x, y, width, height, srcX, srcY, srcWidth, srcHeight);
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
