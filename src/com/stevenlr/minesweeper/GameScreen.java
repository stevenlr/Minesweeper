package com.stevenlr.minesweeper;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import com.stevenlr.minesweeper.Screen;

public class GameScreen extends Screen {
	
	private int difficulty;
	private GameGrid grid;
	
	public GameScreen(Minesweeper pGame, int diff) {
		super(pGame);	
		grid = new GameGrid();
		difficulty = diff;
		grid.generate(difficulty);
		game.gameState = Minesweeper.PLAY;
	}
	
	public void update(Graphics buffer) {
		
		grid.setHovered(game.eventsListener.x, game.eventsListener.y);
		
		MouseEvent ev;
		
		while((ev = game.eventsListener.dequeueMouseEvent()) != null) {
			int[] clickGrid = grid.getGridFromMouse(ev.getX(), ev.getY());
			
			if(clickGrid[0] != -1 && clickGrid[1] != -1) {
				if(ev.getButton() == MouseEvent.BUTTON1)
					grid.openSquare(clickGrid[0], clickGrid[1]);
				if(ev.getButton() == MouseEvent.BUTTON3)
					grid.flagSquare(clickGrid[0], clickGrid[1]);
			}
			
			game.gameState = grid.evaluate();
		}
		
		grid.render(buffer);
		
		int nFlags = grid.countFlags();
		
		Text.print(((Integer) nFlags).toString() 
				+ "/" 
				+ ((Integer) grid.countMines()).toString(),
				227, 20, buffer);
		
		if(game.gameState == Minesweeper.WIN) {
			game.nextScreen = new WinScreen(game);
		}
		else if(game.gameState == Minesweeper.FAIL) {
			game.nextScreen = new FailScreen(game);
		}
	}
}
