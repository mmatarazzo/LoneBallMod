package com.jamescho.game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;
import com.jamescho.game.model.Ball;
import com.jamescho.game.model.Paddle;

public class PlayState extends State {
	
	private Paddle paddleLeft, paddleRight;
	private static final int PADDLE_WIDTH = 15;
	private static final int PADDLE_HEIGHT = 60;
	
	private Ball ball;
	private static final int BALL_DIAMETER = 20;
	
	private int playerOneScore = 0;
	private int playerTwoScore = 0;	// Two Players **mmatarazzo**
	private Font scoreFont;

	@Override
	public void init() {
		paddleLeft = new Paddle(0, 195, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddleRight = new Paddle(785, 195, PADDLE_WIDTH, PADDLE_HEIGHT);
		scoreFont = new Font("SansSerif", Font.BOLD, 25);
		ball = new Ball(300, 200, BALL_DIAMETER, BALL_DIAMETER);
	}

	@Override
	public void update() {
		paddleLeft.update();
		paddleRight.update();
		ball.update();
		
		if (ballCollides(paddleLeft)) {
			playerOneScore++;
			ball.onCollideWidth(paddleLeft);
			Resources.hit.play();
		} else if (ballCollides(paddleRight)) {
			playerTwoScore++;
			ball.onCollideWidth(paddleRight);
			Resources.hit.play();
		} else if (ball.isDead()) {
			if (ball.getX() < 0) {
				playerOneScore -= 3;
			} else {
				playerTwoScore -= 3;
			}
			ball.reset(ball.getX() < 0);	// Reset x-direction **mmatarazzo**
		}
	}

	@Override
	public void render(Graphics g) {
		// Draw Background
		g.setColor(Resources.darkBlue);
		g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
		g.setColor(Resources.darkRed);
		g.fillRect(GameMain.GAME_WIDTH /2, 0, GameMain.GAME_WIDTH / 2, GameMain.GAME_HEIGHT);
		
		// Draw Separator Line
		g.drawImage(Resources.line, (GameMain.GAME_WIDTH / 2) - 2, 0, null);
		
		// Draw Paddles
		g.setColor(Color.WHITE);
		g.fillRect(paddleLeft.getX(), paddleLeft.getY(), paddleLeft.getWidth(), paddleLeft.getHeight());
		g.fillRect(paddleRight.getX(), paddleRight.getY(), paddleRight.getWidth(), paddleRight.getHeight());
		
		// Draw Ball
		g.drawOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());	// Circular Ball **mmatarazzo**
		
		// Draw UI
		g.setFont(scoreFont);
		g.drawString("" + playerOneScore, 350, 450);
		g.drawString("" + playerTwoScore, 450, 450);	// Two Players **mmatarazzo**
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		// Two Players **mmatarazzo**
		if (e.getKeyCode() == KeyEvent.VK_W) {
			paddleLeft.accelUp();
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			paddleLeft.accelDown();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			paddleRight.accelUp();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			paddleRight.accelDown();
		}
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		// Two Players **mmatarazzo**
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
			paddleLeft.stop();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
			paddleRight.stop();
		}
	}
	
	private boolean ballCollides(Paddle p) {
		return ball.getCircle().intersects(p.getRect());	// Circular Ball **mmatarazo**
	}

}
