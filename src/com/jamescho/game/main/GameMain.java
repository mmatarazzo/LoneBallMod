package com.jamescho.game.main;

import javax.swing.JFrame;

public class GameMain {

	private static final String GAME_TITLE = "LoneBall (Chapter 5)";
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 450;
	public static Game sGame;

	public static void main(String[] args) {
		JFrame frame = new JFrame(GAME_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);	// Prevents manual resizing of window
		sGame = new Game(GAME_WIDTH, GAME_HEIGHT);
		frame.add(sGame);
		frame.pack();
		frame.setLocationRelativeTo(null);	// Center the JFrame **mmatarazzo**
		frame.setVisible(true);
		frame.setIconImage(Resources.iconimage);
	}

}
