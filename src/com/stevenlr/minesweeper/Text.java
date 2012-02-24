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

public class Text {
	private static BufferedImage font;
	
	private static String letter_pos = "ABCDEFGH"
									  + "IJKLMNOP"
									  + "QRSTUVWX"
									  + "YZ      "
									  + "01234567"
									  + "89      "
									  + ".,':!?-/"
									  + "        ";
	private static int[] letter_size = {4, 4, 4, 4, 4, 4, 4, 4,
	                                    3, 4, 4, 4, 5, 5, 5, 4,
	                                    4, 4, 4, 5, 5, 5, 5, 5,
	                                    5, 4, 3, 3, 3, 3, 3, 3,
	                                    4, 3, 4, 4, 5, 4, 4, 4,
	                                    4, 4, 3, 3, 3, 3, 3, 3,
	                                    1, 1, 1, 1, 1, 3, 3, 3,
	                                    3, 3, 3, 3, 3, 3, 3, 3};
	public static void init() {
		try {
			font = ImageIO.read(Minesweeper.class.getResourceAsStream("/font.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void print_letter(int pos, int x, int y, Graphics g) {
		int tx = pos % 8;
		int ty = (pos - tx) / 8;
		
		g.drawImage(font, x, y, x + 8, y + 8, tx * 8, ty * 8, tx * 8 + 8, ty * 8 + 8, null);
	}
	
	public static void print(String str, int x, int y, Graphics g) {
		int len = str.length();
		str = str.toUpperCase();
		
		for(int i = 0; i < len; i++) {
			String s = str.substring(i, i + 1);
			int pos = letter_pos.indexOf(s);
			if(pos > -1) {
				print_letter(pos, x, y, g);
				x += letter_size[pos];
				x += 1;
			}
		}
	}
	
	public static Rect getRect(String str, int x, int y) {
		int len = str.length();
		str = str.toUpperCase();
		int w = 0;
		
		for(int i = 0; i < len; i++) {
			String s = str.substring(i, i + 1);
			int pos = letter_pos.indexOf(s);
			
			if(pos > -1) {
				w += letter_size[pos];
				w += 1;
			}
		}
		
		w--;
		
		return new Rect(x, y, w, 6);
	}
}
