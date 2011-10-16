package com.stevenlr.minesweeper;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.Queue;

public class Events implements MouseListener, MouseMotionListener {
	
	public int x = 0;
	public int y = 0;
	
	private Queue<MouseEvent> mouseEvents;
	
	public Events() {
		mouseEvents = new LinkedList<MouseEvent>();
	}
	
	public void mouseDragged(MouseEvent arg0) {
	}

	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	public void mouseClicked(MouseEvent e) {
		mouseEvents.add(e);
	}
	
	
	public MouseEvent dequeueMouseEvent() {
		return mouseEvents.poll();
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}
}
