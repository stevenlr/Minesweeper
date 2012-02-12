package com.stevenlr.minesweeper;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class MenuScreen extends Screen {
	
	public MenuScreen(Minesweeper game) {
		super(game);
	}
	
	public void update(Graphics buffer) {
		Textures.drawLogo(50, 40, buffer);
		
		Text.print("NEW GAME", 115, 100, buffer);
		
		Text.print("EASY", 125, 130, buffer);
		Text.print("MEDIUM", 119, 145, buffer);
		Text.print("HARD", 125, 160, buffer);
		
		Text.print("STEVENLR.COM", 205, 188, buffer);
		
		MouseEvent ev;
		
		int difficulty = GameGrid.EASY;
		boolean start = false;
		
		while((ev = game.eventsListener.dequeueMouseEvent()) != null) {
			int x = ev.getX() / Minesweeper.ASPECT, y = ev.getY() / Minesweeper.ASPECT;
			
			if(x >= 125 && x <= 145 && y >= 130 && y <= 136) {
				start = true;
				difficulty = GameGrid.EASY;
			}
			else if(x >= 119 && x <= 150 && y >= 145 && y <= 151) {
				start = true;
				difficulty = GameGrid.MEDIUM;
			}
			else if(x >= 125 && x <= 144 && y >= 160 && y <= 166) {
				start = true;
				difficulty = GameGrid.HARD;
			}
		}
		
		if(start) {
			game.nextScreen = new GameScreen(game, difficulty);
		}
	}
}
