package com.stevenlr.minesweeper;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Textures {
	
	private static BufferedImage tiles;
	
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void draw(int tid, int x, int y, Graphics g) {
		int tx = tid % 8;
		int ty = (tid - tx) / 8;
		g.drawImage(tiles, x, y, x + 8, y + 8, tx * 8, ty * 8, tx * 8 + 8, ty * 8 + 8, null);
	}
}
