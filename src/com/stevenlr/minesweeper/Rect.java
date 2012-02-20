package com.stevenlr.minesweeper;

import java.awt.Graphics;

public class Rect {
	public int x, y, w, h;
	
	public Rect(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public static Rect newRectFromCoords(int x1, int y1, int x2, int y2) {
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		int w = Math.abs(x1 - x2);
		int h = Math.abs(y1 - y2);
		return new Rect(x, y, w, h);
	}
	
	public void fill(Graphics g) {
		g.fillRect(x, y, w, h);
	}
	
	public boolean inRect(int px, int py) {
		if(px >= x && py >= y && px < x + w && py < y + h)
			return true;
		return false;
	}
}
