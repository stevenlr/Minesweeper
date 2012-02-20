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

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends Canvas {
	
	private static final long serialVersionUID = -7298191667496539682L;
	
	private int width;
	private int height;
	private int aspect;
	
	private BufferStrategy strategy;
	private Graphics buffer;
	
	private BufferedImage image;
	private Graphics image_g;
	
	public Display(int pwidth, int pheight, int paspect) {
		width = pwidth;
		height = pheight;
		aspect = paspect;
		
		JFrame frame = new JFrame("Minesweeper");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(width * aspect, height * aspect));
		panel.add(this);
		
		setIgnoreRepaint(true);
		
		frame.pack();
		frame.setVisible(true);
		
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		image_g = image.getGraphics();
		
		reset();
	}
	
	public void addEventsListener(Events listener) {
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}
	
	public void reset() {
		buffer = strategy.getDrawGraphics();
		
		image_g.setColor(Color.BLACK);
		image_g.fillRect(0, 0, width, height);
	}
	
	public Graphics getBuffer() {
		return image_g;
	}
	
	public void flip() {
		buffer.drawImage(image.getScaledInstance(width * aspect, height * aspect, Image.SCALE_FAST), 0, 0, null);
		buffer.dispose();
		
		strategy.show();
		
		reset();
	}
}
