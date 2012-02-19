package com.stevenlr.minesweeper;

import java.awt.Graphics;

public class FailScreen extends Screen {
	
	private GameScreen gameScreen;
	
	public FailScreen(Minesweeper game, GameScreen gameScreen) {
		super(game);
		
		this.gameScreen = gameScreen;
	}
	
	public void update(Graphics buffer) {
		gameScreen.render(buffer);
		
		Text.print("YOU FAILED", 210, 34, buffer);
		Text.print("CLICK", 224, 175, buffer);
		Text.print("TO CONTINUE", 205, 187, buffer);
		
		boolean restart = false;
		
		while(game.eventsListener.dequeueMouseEvent() != null) {
			restart = true;
		}
		
		if(restart) {
			game.nextScreen = new MenuScreen(game);
		}
	}
}
