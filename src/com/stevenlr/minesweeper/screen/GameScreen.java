package com.stevenlr.minesweeper.screen;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import com.stevenlr.minesweeper.*;

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
	
	public void render(Graphics buffer) {
		grid.render(buffer);
		
		int nFlags = grid.countFlags();
		
		Text.print(((Integer) nFlags).toString(), 220, 20, buffer);
		Text.print("/", 234, 20, buffer);
		Text.print(((Integer) grid.countMines()).toString(), 243, 20, buffer);
		
		Text.print(grid.getTime(), 229, 33, buffer);
		
		Textures.draw(Textures.SQ, 220, 8, buffer);
		Textures.draw(Textures.SQ_FLAG, 220, 8, buffer);
		
		Textures.draw(Textures.SQ, 243, 8, buffer);
		Textures.draw(Textures.SQ_MINE, 243, 8, buffer);
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
		
		render(buffer);
		
		if(game.gameState != Minesweeper.PLAY) {
			grid.end();
		}
		
		if(game.gameState == Minesweeper.WIN) {
			game.nextScreen = new WinScreen(game, this);
		}
		else if(game.gameState == Minesweeper.FAIL) {
			game.nextScreen = new FailScreen(game, this);
		}
	}
}
