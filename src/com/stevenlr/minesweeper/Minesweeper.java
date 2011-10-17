package com.stevenlr.minesweeper;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Minesweeper implements Runnable {
	
	public static int WIDTH = 271;
	public static int HEIGHT = 198;
	public static int ASPECT = 3; 
	
	public static int PLAY = 0;
	public static int WIN = 1;
	public static int FAIL = 2;
	
	public Display display;
	public Events eventsListener;
	
	public int gameState = PLAY;
	
	public Minesweeper() {
		display = new Display(WIDTH, HEIGHT, ASPECT);
		
		eventsListener = new Events();
		display.addEventsListener(eventsListener);
		
		Textures.init();
		Text.init();
		
		Thread t = new Thread(this);
		t.start();
	}

	public void run() {
		Graphics buffer = display.getBuffer();
		GameGrid grid = new GameGrid(GameGrid.MEDIUM);
		MouseEvent ev;
		
		while(true) {
			
			grid.setHovered(eventsListener.x, eventsListener.y);
			
			
			while((ev = eventsListener.dequeueMouseEvent()) != null) {
				int[] clickGrid = grid.getGridFromMouse(ev.getX(), ev.getY());
				
				if(clickGrid[0] != -1 && clickGrid[1] != -1) {
					if(ev.getButton() == MouseEvent.BUTTON1)
						grid.openSquare(clickGrid[0], clickGrid[1]);
					if(ev.getButton() == MouseEvent.BUTTON3)
						grid.flagSquare(clickGrid[0], clickGrid[1]);
				}
				
				gameState = grid.evaluate();
			}
			
			grid.render(buffer);
			
			if(gameState == WIN)
				Text.print("WIN", 0, 0, buffer);
			if(gameState == FAIL)
				Text.print("FAIL", 0, 0, buffer);
			
			display.flip();
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Minesweeper();
	}

}
