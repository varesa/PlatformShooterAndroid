package fi.dy.esav.GameEngine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputHandler implements MouseListener, MouseMotionListener, KeyListener {

	private InputState inputstate;
	
	/**
	 * Disabled default constructor
	 */
	@SuppressWarnings("unused")
	private InputHandler() {}
	
	/**
	 * Constructor
	 * @param state The InputState to hold the inputs states
	 */
	public InputHandler(InputState state) {
		this.inputstate = state;
	}
	
	public void keyPressed(KeyEvent e) {
		if(inputstate.keysDown.contains(e.getKeyCode())) return;
		inputstate.keysDown.add(e.getKeyCode());
		
	}

	public void keyReleased(KeyEvent e) {
		if(!inputstate.keysDown.contains(e.getKeyCode())) return;
		inputstate.keysDown.remove((Object)e.getKeyCode());
		
	}
	
	public void keyTyped(KeyEvent e) {
		// Do nothing
	}

	public void mouseDragged(MouseEvent e) {
		// Do nothing
		
	}

	public void mouseMoved(MouseEvent e) {
		inputstate.mousePos = e.getPoint();
	}

	public void mouseClicked(MouseEvent e) {
		// Do nothing
		
	}

	public void mouseEntered(MouseEvent e) {
		// Do nothing
		
	}

	public void mouseExited(MouseEvent e) {
		// Do nothing
		
	}

	public void mousePressed(MouseEvent e) {
		if(inputstate.mouseButtons.contains(e.getButton())) return;
		inputstate.mouseButtons.add(e.getButton());
		
	}

	public void mouseReleased(MouseEvent e) {
		if(!inputstate.mouseButtons.contains((Object)e.getButton())) return;
		inputstate.mouseButtons.remove((Object)e.getButton());		
	}
	
}
