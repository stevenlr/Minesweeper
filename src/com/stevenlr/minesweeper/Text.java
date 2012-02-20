package com.stevenlr.minesweeper;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class Text {
	private static BufferedImage font;
	
	private static Hashtable<String, Integer> letters_pos;
	private static Hashtable<String, Integer> letters_size;
	
	public static void init() {
		try {
			font = ImageIO.read(Minesweeper.class.getResourceAsStream("/font.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		letters_pos = new Hashtable<String, Integer>();
		letters_size = new Hashtable<String, Integer>();
		
		letters_pos.put(" ", 63);
		letters_pos.put("A", 0);
		letters_pos.put("B", 1);
		letters_pos.put("C", 2);
		letters_pos.put("D", 3);
		letters_pos.put("E", 4);
		letters_pos.put("F", 5);
		letters_pos.put("G", 6);
		letters_pos.put("H", 7);
		letters_pos.put("I", 8);
		letters_pos.put("J", 9);
		letters_pos.put("K", 10);
		letters_pos.put("L", 11);
		letters_pos.put("M", 12);
		letters_pos.put("N", 13);
		letters_pos.put("O", 14);
		letters_pos.put("P", 15);
		letters_pos.put("Q", 16);
		letters_pos.put("R", 17);
		letters_pos.put("S", 18);
		letters_pos.put("T", 19);
		letters_pos.put("U", 20);
		letters_pos.put("V", 21);
		letters_pos.put("W", 22);
		letters_pos.put("X", 23);
		letters_pos.put("Y", 24);
		letters_pos.put("Z", 25);
		letters_pos.put("0", 32);
		letters_pos.put("1", 33);
		letters_pos.put("2", 34);
		letters_pos.put("3", 35);
		letters_pos.put("4", 36);
		letters_pos.put("5", 37);
		letters_pos.put("6", 38);
		letters_pos.put("7", 39);
		letters_pos.put("8", 40);
		letters_pos.put("9", 41);
		letters_pos.put(".", 48);
		letters_pos.put(",", 49);
		letters_pos.put("'", 50);
		letters_pos.put(":", 51);
		letters_pos.put("!", 52);
		letters_pos.put("?", 53);
		letters_pos.put("-", 54);
		letters_pos.put("/", 55);
		
		letters_size.put(" ", 3);
		letters_size.put("A", 4);
		letters_size.put("B", 4);
		letters_size.put("C", 4);
		letters_size.put("D", 4);
		letters_size.put("E", 4);
		letters_size.put("F", 4);
		letters_size.put("G", 4);
		letters_size.put("H", 4);
		letters_size.put("I", 3);
		letters_size.put("J", 4);
		letters_size.put("K", 4);
		letters_size.put("L", 4);
		letters_size.put("M", 5);
		letters_size.put("N", 5);
		letters_size.put("O", 5);
		letters_size.put("P", 4);
		letters_size.put("Q", 4);
		letters_size.put("R", 4);
		letters_size.put("S", 4);
		letters_size.put("T", 5);
		letters_size.put("U", 5);
		letters_size.put("V", 5);
		letters_size.put("W", 5);
		letters_size.put("X", 5);
		letters_size.put("Y", 5);
		letters_size.put("Z", 4);
		letters_size.put("0", 4);
		letters_size.put("1", 3);
		letters_size.put("2", 4);
		letters_size.put("3", 4);
		letters_size.put("4", 5);
		letters_size.put("5", 4);
		letters_size.put("6", 4);
		letters_size.put("7", 4);
		letters_size.put("8", 4);
		letters_size.put("9", 4);
		letters_size.put(".", 1);
		letters_size.put(",", 1);
		letters_size.put("'", 1);
		letters_size.put(":", 1);
		letters_size.put("!", 1);
		letters_size.put("?", 3);
		letters_size.put("-", 3);
		letters_size.put("/", 3);
	}
	
	private static void print_letter(String letter, int x, int y, Graphics g) {
		int pos = (Integer) letters_pos.get(letter);
		int tx = pos % 8;
		int ty = (pos - tx) / 8;
		
		g.drawImage(font, x, y, x + 8, y + 8, tx * 8, ty * 8, tx * 8 + 8, ty * 8 + 8, null);
	}
	
	public static void print(String str, int x, int y, Graphics g) {
		int len = str.length();
		str = str.toUpperCase();
		
		for(int i = 0; i < len; i++) {
			String s = str.substring(i, i + 1);
			
			if(letters_size.containsKey(s)) {
				print_letter(s, x, y, g);
				x += letters_size.get(s);
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
			if(letters_size.containsKey(s)) {
				w += letters_size.get(s);
				w += 1;
			}
		}
		
		w--;
		
		return new Rect(x, y, w, 6);
	}
}
