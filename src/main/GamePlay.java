package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

	private int[] snakeXLenght = new int[750];
	private int[] snakeYLenght = new int[750];

	// di chuyen
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;

	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon downmouth;
	private ImageIcon upmouth;

	private int lenghtofsnake = 3;
	private int moves = 0;

	// toc do di chuyen
	private Timer timer;
	private int delay = 100;
	private int scores = 0;
	private ImageIcon snakeimage;

	private int[] enemyXpos = { 25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450,
			475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850 };
	private int[] enemyYpos = { 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500,
			525, 550, 575, 600, 625 };

	private ImageIcon enemyimage;
	private Random random = new Random();
	// 34 la tong so vitri x theo chieu ngang
	private int Xpos = random.nextInt(34);
	private int Ypos = random.nextInt(34);

	private ImageIcon titleImage;

	public GamePlay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();

	}

	public void paint(Graphics g) {
		// dichuyen
		if (moves == 0) {
			snakeXLenght[2] = 50;
			snakeXLenght[1] = 75;
			snakeXLenght[0] = 100;

			snakeYLenght[2] = 100;
			snakeYLenght[1] = 100;
			snakeYLenght[0] = 100;
		}

		// draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 51);

		// draw title image
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);

		// draw scores
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Scores: " + scores, 780, 30);

		// draw scores
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Lenght: " + lenghtofsnake, 780, 50);
		
		// draw border for game
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);

		// draw background for game
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 575);

		// lenghtofsnake ban dau = 3
		rightmouth = new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, snakeXLenght[0], snakeYLenght[0]);
		for (int a = 0; a < lenghtofsnake; a++) {
			if (a == 0 && right) {
				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, snakeXLenght[a], snakeYLenght[a]);
			}
			if (a == 0 && left) {
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, snakeXLenght[a], snakeYLenght[a]);
			}
			if (a == 0 && up) {
				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, snakeXLenght[a], snakeYLenght[a]);
			}
			if (a == 0 && down) {
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, snakeXLenght[a], snakeYLenght[a]);
			}
			if (a != 0) {
				snakeimage = new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this, g, snakeXLenght[a], snakeYLenght[a]);
			}
		}
		// check enemy co cham dau snake hay k
		enemyimage = new ImageIcon("enemy.png");
		if ((enemyXpos[Xpos]) == snakeXLenght[0] && enemyYpos[Ypos] == snakeYLenght[0]) {
			
			lenghtofsnake++; // neu cham enemy thi chieu dau snake tang
			Xpos = random.nextInt(34);
			Ypos = random.nextInt(23);
			scores++;
			
		}
		// hienthi/draw enemy trong khu vuc game
		enemyimage.paintIcon(this, g, enemyXpos[Xpos], enemyYpos[Ypos]);
		
		for(int b=1; b<lenghtofsnake; b++) {
			if(snakeXLenght[b] == snakeXLenght[0] && snakeYLenght[b] == snakeYLenght[0]) {
				right = false;
				left = false;
				up = false;
				down = false; 
				
				g.setColor(Color.YELLOW);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game Over", 300, 300);
				
				g.setFont(new Font("arial", Font.BOLD, 30));
				g.drawString("Space to 	RESTART", 350, 340);
			}
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		timer.start();
		if (right) {
			for (int r = lenghtofsnake - 1; r >= 0; r--) {
				snakeYLenght[r + 1] = snakeYLenght[r];
			}
			for (int r = lenghtofsnake; r >= 0; r--) {
				if (r == 0) {
					snakeXLenght[r] = snakeXLenght[r] + 25;
				} else {
					snakeXLenght[r] = snakeXLenght[r - 1];
				}
				if (snakeXLenght[r] > 850) {
					snakeXLenght[r] = 25;
				}
			}
			repaint();
		}
		if (left) {
			for (int r = lenghtofsnake - 1; r >= 0; r--) {
				snakeYLenght[r + 1] = snakeYLenght[r];
			}
			for (int r = lenghtofsnake; r >= 0; r--) {
				if (r == 0) {
					snakeXLenght[r] = snakeXLenght[r] - 25;
				} else {
					snakeXLenght[r] = snakeXLenght[r - 1];
				}
				if (snakeXLenght[r] < 25) {
					snakeXLenght[r] = 850;
				}
			}
			repaint();
		}
		if (up) {
			for (int r = lenghtofsnake - 1; r >= 0; r--) {
				snakeXLenght[r + 1] = snakeXLenght[r];
			}
			for (int r = lenghtofsnake; r >= 0; r--) {
				if (r == 0) {
					snakeYLenght[r] = snakeYLenght[r] - 25;
				} else {
					snakeYLenght[r] = snakeYLenght[r - 1];
				}
				if (snakeYLenght[r] < 75) {
					snakeYLenght[r] = 625;
				}
			}
			repaint();
		}
		if (down) {
			for (int r = lenghtofsnake - 1; r >= 0; r--) {
				snakeXLenght[r + 1] = snakeXLenght[r];
			}
			for (int r = lenghtofsnake; r >= 0; r--) {
				if (r == 0) {
					snakeYLenght[r] = snakeYLenght[r] + 25;
				} else {
					snakeYLenght[r] = snakeYLenght[r - 1];
				}
				if (snakeYLenght[r] > 625) {
					snakeYLenght[r] = 75;
				}
			}
			repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			moves=0;
			scores=0; 
			lenghtofsnake=3;
			repaint();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
			right = true;
			if (!left) {
				right = true;
			} else {
				right = false;
				left = true;
			}
			up = false;
			down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			left = true;
			if (!right) {
				left = true;
			} else {
				left = false;
				right = true;
			}
			up = false;
			down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			up = true;
			if (!down) {
				up = true;
			} else {
				up = false;
				down = true;
			}
			left = false;
			right = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			down = true;
			if (!up) {
				down = true;
			} else {
				down = false;
				up = true;
			}
			left = false;
			right = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
