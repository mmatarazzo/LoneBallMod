package com.jamescho.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.jamescho.game.main.Resources;

public class MenuState extends State {

	@Override
	public void init() {
		System.out.println("Entered MenuState");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// Draws Resources.welcome to the screen at x=0, y=0
		g.drawImage(Resources.welcome, 0, 0, null);
	}

	@Override
	public void onClick(MouseEvent e) {
		setCurrentState(new PlayState());
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		System.out.println("OnKeyPress");
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		System.out.println("OnKeyRelease");
	}

}
