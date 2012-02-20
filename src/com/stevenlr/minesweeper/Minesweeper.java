package com.stevenlr.minesweeper;

import java.awt.Graphics;

import com.stevenlr.minesweeper.screen.*;

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
	
	public Screen nextScreen;
	public Screen currentScreen;
	
	public Minesweeper() {
		display = new Display(WIDTH, HEIGHT, ASPECT);
		
		eventsListener = new Events();
		display.addEventsListener(eventsListener);
		
		Textures.init();
		Text.init();
		
		nextScreen = new MenuScreen(this);
		currentScreen = null;
		
		Thread t = new Thread(this);
		t.start();
	}

	public void run() {
		Graphics buffer = display.getBuffer();
		
		while(true) {
			
			if(nextScreen != null) {
				currentScreen = nextScreen;
				nextScreen = null;
			}
			
			currentScreen.update(buffer);
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
