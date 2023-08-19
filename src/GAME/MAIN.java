package GAME;

import javax.swing.*;
import javax.swing.JFrame;

public class MAIN {
	//updated code 
	public static void func() {
		JFrame jf = new JFrame();
		jf.setTitle("Breakout Ball Game");
		jf.setSize(700,600);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		jf.setResizable(false);
		
		Game gp = new Game();
		jf.add(gp);
	}
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				func();
			}
		});
		
	}
}