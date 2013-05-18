package com.longarmx.color;

import com.badlogic.gdx.InputProcessor;

public class Input implements InputProcessor{
	
	Game game;
	
	public Input(){
		this.game = Main.instance;
	}

	@Override
	public boolean keyDown(int keycode) {
		if(!game.player.isDead && !game.paused){
			switch(keycode){
			case Config.PLAYER_RED:
					game.player.redDown = true;
				break;
			case Config.PLAYER_GREEN:
					game.player.greenDown = true;
				break;
			case Config.PLAYER_BLUE:
					game.player.blueDown = true;
				break;
			case Config.PLAYER_LIGHT:
					game.player.lightDown = true;
				break;
			case Config.PLAYER_DARK:
					game.player.darkDown = true;
				break;
			case Config.PLAYER_SHOOT:
					game.player.shoot();
				break;
			case Config.PLAYER_RESET:
					game.player.red = 0;
					game.player.green = 0;
					game.player.blue = 0;
					game.player.setColor = false;
				break;
			default:
				break;
			}
		}
		if(keycode == Config.GAME_RESET){
			game.reset();
		}
		if(keycode == Config.GAME_PAUSE){
			if(game.paused){
				game.paused = false;
			}else{
				game.paused = true;
			}
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(!game.player.isDead){
			switch(keycode){
			case Config.PLAYER_RED:
					game.player.redDown = false;
				break;
			case Config.PLAYER_GREEN:
					game.player.greenDown = false;
				break;
			case Config.PLAYER_BLUE:
					game.player.blueDown = false;
				break;
			case Config.PLAYER_LIGHT:
					game.player.lightDown = false;
				break;
			case Config.PLAYER_DARK:
					game.player.darkDown = false;
				break;
			default:
				break;
			}
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
