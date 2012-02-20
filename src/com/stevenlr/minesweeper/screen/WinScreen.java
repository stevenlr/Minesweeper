/*******************************************************************************
 * Copyright (C) 2012 Steven Le Rouzic
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.stevenlr.minesweeper.screen;

import java.awt.Graphics;

import com.stevenlr.minesweeper.*;

public class WinScreen extends Screen {
	
	private GameScreen gameScreen;
	
	public WinScreen(Minesweeper game, GameScreen gameScreen) {
		super(game);
		
		this.gameScreen = gameScreen;
	}
	
	public void update(Graphics buffer) {
		gameScreen.render(buffer);
		
		Text.print("YOU WON!", 215, 60, buffer);
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
