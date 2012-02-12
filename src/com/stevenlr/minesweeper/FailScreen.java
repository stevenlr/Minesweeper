package com.stevenlr.minesweeper;

import java.awt.Graphics;

public class FailScreen extends Screen {
	
	public FailScreen(Minesweeper game) {
		super(game);
	}
	
	public void update(Graphics buffer) {
		Text.print("YOU FAILED", 110, 80, buffer);
		Text.print("CLICK TO CONTINUE", 180, 187, buffer);
		
		boolean restart = false;
		
		while(game.eventsListener.dequeueMouseEvent() != null) {
			restart = true;
		}
		
		if(restart) {
			game.nextScreen = new MenuScreen(game);
		}
	}
}
