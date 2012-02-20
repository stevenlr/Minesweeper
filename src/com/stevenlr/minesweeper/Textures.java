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
package com.stevenlr.minesweeper;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Textures {
	
	private static BufferedImage tiles;
	private static BufferedImage logo;
	
	public static int SQ = 0;
	public static int SQ_HOVER = 1;
	
	public static int SQ_EMPTY = 8;
	public static int SQ_BLUE = 9;
	public static int SQ_GREEN = 10;
	public static int SQ_YELLOW = 11;
	public static int SQ_ORANGE = 12;
	public static int SQ_RED = 13;
	
	public static int SQ_MINE = 16;
	public static int SQ_FLAG = 17;
	
	public static void init() {
		try {
			tiles = ImageIO.read(Minesweeper.class.getResourceAsStream("/tiles.png"));
			logo = ImageIO.read(Minesweeper.class.getResourceAsStream("/logo.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void draw(int tid, int x, int y, Graphics g) {
		int tx = tid % 8;
		int ty = (tid - tx) / 8;
		g.drawImage(tiles, x, y, x + 8, y + 8, tx * 8, ty * 8, tx * 8 + 8, ty * 8 + 8, null);
	}
	
	public static void drawLogo(int x, int y, Graphics g) {
		g.drawImage(logo, x, y, null);
	}
}
