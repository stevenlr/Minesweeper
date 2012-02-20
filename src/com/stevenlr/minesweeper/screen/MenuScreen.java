package com.stevenlr.minesweeper.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import com.stevenlr.minesweeper.*;

public class MenuScreen extends Screen {
	
	public static final Rect easyRect = Text.getRect("EASY", 125, 85);
	public static final Rect mediumRect = Text.getRect("MEDIUM", 119, 98);
	public static final Rect hardRect = Text.getRect("HARD", 125, 111);
	
	public MenuScreen(Minesweeper game) {
		super(game);
	}
	
	public void update(Graphics buffer) {
		Textures.drawLogo(50, 24, buffer);
		
		Text.print("HOW TO PLAY", 108, 140, buffer);
		Text.print("LEFT CLICK TO OPEN A CELL", 75, 155, buffer);
		Text.print("RIGHT CLICK TO FLAG/UNFLAG A CELL", 57, 169, buffer);
		
		Text.print("NEW GAME", 115, 70, buffer);
		
		Text.print("EASY", easyRect.x, easyRect.y, buffer);
		Text.print("MEDIUM", mediumRect.x, mediumRect.y, buffer);
		Text.print("HARD", hardRect.x, hardRect.y, buffer);
		
		Color black = new Color(0, 0, 0, 127);
		buffer.setColor(black);
		
		int mx = -1, my = -1;
		
		Point mousePos = game.display.getMousePosition();
		
		if(mousePos != null) {
			mx = mousePos.x / Minesweeper.ASPECT;
			my = mousePos.y / Minesweeper.ASPECT;
		}
		
		if(!easyRect.inRect(mx, my))
			easyRect.fill(buffer);
		
		if(!mediumRect.inRect(mx, my))
			mediumRect.fill(buffer);
		
		if(!hardRect.inRect(mx, my))
			hardRect.fill(buffer);
		
		
		Text.print("STEVENLR.COM", 205, 188, buffer);
		
		MouseEvent ev;
		
		int difficulty = GameGrid.EASY;
		boolean start = false;
		
		while((ev = game.eventsListener.dequeueMouseEvent()) != null) {
			int x = ev.getX() / Minesweeper.ASPECT, y = ev.getY() / Minesweeper.ASPECT;
			
			if(easyRect.inRect(x, y)) {
				start = true;
				difficulty = GameGrid.EASY;
			}
			else if(mediumRect.inRect(x, y)) {
				start = true;
				difficulty = GameGrid.MEDIUM;
			}
			else if(hardRect.inRect(x, y)) {
				start = true;
				difficulty = GameGrid.HARD;
			}
		}
		
		if(start) {
			game.nextScreen = new GameScreen(game, difficulty);
		}
	}
}
