package com.stevenlr.minesweeper;

import java.awt.Graphics;
import java.util.Random;

public class GameGrid {
	
	public static final int EASY = 0;
	public static final int MEDIUM = 1;
	public static final int HARD = 2;
	
	public static int[][] levels = {{10, 12}, {15, 30}, {20, 70}};
	
	private Square[] grid;
	private int level;
	
	private int render_offset = 0;
	
	private boolean hovered = false;
	private int hoveredX = 0;
	private int hoveredY = 0;
	
	public GameGrid() {
	}
	
	public void generate(int plevel) {
		level = plevel;
		
		Random rand = new Random();
		int nSquares = levels[level][0] * levels[level][0];
		
		render_offset = (20 - levels[level][0]) * 10 / 2;
		
		grid = new Square[nSquares];
		
		for(int i = 0; i < nSquares; i++) {
			grid[i] = new Square();
		}
		
		for(int i = 0; i < levels[level][1]; i++) {
			int sid = rand.nextInt(nSquares);
			
			if(grid[sid].mine) {
				i--;
				continue;
			}
			else {
				grid[sid].mine = true;
			}
		}
		
		for(int i = 0; i < nSquares; i++) {
			Square[] squares = getSquaresAroundSquare(i);
			Square here = getSquare(i);
			
			if(here.mine)
				continue;
			
			for(int j = 0; j < 8; j++) {
				if(squares[j] != null && squares[j].mine)
					here.nMinesAround++;
			}
		}
	}
	
	public int evaluate() {
		int nMinesOpened = 0;
		int nSquaresOpened = 0;
		int nSquares = levels[level][0] * levels[level][0];
		
		for(int i = 0; i < nSquares; i++) {
			Square s = getSquare(i);
			
			if(s.opened)
				nSquaresOpened++;
			
			if(s.opened && s.mine)
				nMinesOpened++;
		}
		
		if(nMinesOpened > 0)
			return Minesweeper.FAIL;
		
		if(nSquaresOpened == nSquares - levels[level][1])
			return Minesweeper.WIN;
		
		return Minesweeper.PLAY;
	}
	
	public int[] getGridFromMouse(int x, int y) {
		int size = levels[level][0];
		int gx, gy;
		int[] coords = {-1, -1};
		
		x /= Minesweeper.ASPECT;
		y /= Minesweeper.ASPECT;
		
		x -= render_offset;
		y -= render_offset;
		
		gx = x / 10;
		gy = y / 10;
		
		if(x >= gx * 10 && x <= gx * 10 + 8 && gx >= 0 && gx < size) {
			if(y >= gy * 10 && y <= gy * 10 + 8 && gy >= 0 && gy < size) {
				coords[0] = gx;
				coords[1] = gy;
			}
		}
		
		return coords;
	}
	
	public boolean setHovered(int x, int y) {
		int[] coords = getGridFromMouse(x, y);
		
		if(coords[0] != -1 && coords[1] != -1) {
			hovered = true;
			hoveredX = coords[0];
			hoveredY = coords[1];
		}
		else {
			hovered = false;
		}
		
		return hovered;
	}
	
	public void openSquare(int x, int y) {
		Square s = getSquare(x, y);
		
		if(s == null || s.opened)
			return;
		
		s.opened = true;
		
		if(s.nMinesAround == 0 && !s.mine) {
			openSquare(x - 1, y);
			openSquare(x + 1, y);
			openSquare(x, y + 1);
			openSquare(x, y - 1);
			openSquareDiag(x - 1, y - 1);
			openSquareDiag(x - 1, y + 1);
			openSquareDiag(x + 1, y + 1);
			openSquareDiag(x + 1, y - 1);
		}
	}
	
	public void openSquareDiag(int x, int y)
	{
		Square s = getSquare(x, y);
		
		if(s == null || s.opened)
			return;
		
		if(s.nMinesAround == 0)
			return;
		
		openSquare(x, y);
	}
	
	public void flagSquare(int x, int y) {
		Square s = getSquare(x, y);
		
		s.flag = !s.flag;
	}
	
	public void render(Graphics g) {
		int size = levels[level][0];
		
		for(int x = 0; x < size; x++) {
			for(int y = 0; y < size; y++) {
				Square s = getSquare(x, y);
				int gx = x * 10 + render_offset; int gy = y * 10 + render_offset;
				
				if(!s.opened) {
					if(hovered && x == hoveredX && y == hoveredY) {
						Textures.draw(Textures.SQ_HOVER, gx, gy, g);
					}
					else {
						Textures.draw(Textures.SQ, gx, gy, g);
					}
					
					if(s.flag) {
						Textures.draw(Textures.SQ_FLAG, gx, gy, g);
					}
				}
				else {
					if(s.nMinesAround == 0) {
						Textures.draw(Textures.SQ_EMPTY, gx, gy, g);
						
						if(s.mine) {
							Textures.draw(Textures.SQ_MINE, gx, gy, g);
						}
					}
					else {
						switch(s.nMinesAround) {
							case 1:
								Textures.draw(Textures.SQ_BLUE, gx, gy, g);
								Text.print("1", gx + 3, gy + 1, g);
								break;
							case 2:
								Textures.draw(Textures.SQ_GREEN, gx, gy, g);
								Text.print("2", gx + 2, gy + 1, g);
								break;
							case 3:
								Textures.draw(Textures.SQ_YELLOW, gx, gy, g);
								Text.print("3", gx + 2, gy + 1, g);
								break;
							case 4:
								Textures.draw(Textures.SQ_ORANGE, gx, gy, g);
								Text.print("4", gx + 2, gy + 1, g);
								break;
							default:
								Textures.draw(Textures.SQ_RED, gx, gy, g);
								Text.print(new Integer(s.nMinesAround).toString(), gx + 2, gy + 1, g);
								break;
						}
					}
				}
			}
		}
	}
	
	private Square getSquare(int sid) {
		int size = levels[level][0];
		int x = sid % size;
		int y = (sid - x) / size;
		
		return getSquare(x, y);
	}
	
	private Square getSquare(int x, int y) {
		if(x >= 0 && x < levels[level][0] && y >= 0 && y < levels[level][0]) {
			return grid[x + y * levels[level][0]];
		}
		else {
			return null;
		}
	}
	
	private Square[] getSquaresAroundSquare(int sid) {
		Square[] squares = new Square[8];
		int x = sid % levels[level][0];
		int y = (sid - x) / levels[level][0];
		
		squares[0] = getSquare(x - 1, y);
		squares[1] = getSquare(x + 1, y);
		squares[2] = getSquare(x, y - 1);
		squares[3] = getSquare(x, y + 1);
		squares[4] = getSquare(x - 1, y - 1);
		squares[5] = getSquare(x + 1, y + 1);
		squares[6] = getSquare(x + 1, y - 1);
		squares[7] = getSquare(x - 1, y + 1);
		
		return squares;
	}

	public int countFlags() {
		int i, c = 0, size = levels[level][0] * levels[level][0];
		
		for(i = 0; i < size; i++) {
			Square s = grid[i];
			if(s.flag && !s.opened)
				c++;
		}
		
		return c;
	}

	public int countMines() {
		return levels[level][1];
	}
}
