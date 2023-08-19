package main;

import java.awt.Color;

import javax.swing.JFrame;

public class main {
	public static void main(String[] args) {
		JFrame obj = new JFrame("snake game"); 
		
		//tao doi tuong cho game
		GamePlay game = new GamePlay();  
		
		// khoi tao khung hinh cho game
		obj.setBounds(10, 10, 905, 700);
		obj.setBackground(Color.DARK_GRAY);
		//game.setBackground(Color.DARK_GRAY);
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(game);

	}
}
