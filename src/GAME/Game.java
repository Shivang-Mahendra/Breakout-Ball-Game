package GAME;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private int t = 0;
	private boolean play = false;
	private int totalTiles = 0;
	private Timer timer;
	private int delay = 6;
	private int max = 400;
	private int min = 200;
	private int ballPosX = (int)(Math.random()*(max-min+1)+min);
	private int ballPosY = (int)(Math.random()*(max-min+1)+min);
	private int ballDirX = -1;
	private int ballDirY = -2;
	private int playerX = 350;
	private tiles tile;
	private int score = 0;
	private int bestScore = 0;
	private int temp = 0;
	private int x = 1;
	private int y = 2;
	private int sliderLength = 100;
	private int level = 0;
	private int count = 0;
	
	public Game() {
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		
		timer = new Timer(delay,this);
		timer.start();
		
	}
	
	public void paint(Graphics g) {
		//background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		//border
		g.setColor(Color.gray);
		g.fillRect(0, 0, 684, 4);	//top
		g.fillRect(683, 4, 3, 592);	//right
		g.fillRect(0, 4, 3, 592);	//left
		
		if(t == 0) {
			g.setColor(Color.gray);
			g.fillRect(1, 1, 692, 592);
			g.setColor(Color.blue);
			g.setFont(new Font("serif", Font.ITALIC, 40));
			g.drawString("Press Enter to Start the Game", 110, 270);
		}
		
		if(t == 1) {
			//Score 
			g.setColor(Color.red);
			g.setFont(new Font ("serif",Font.ITALIC, 20));
			g.drawString("Your Score : "+score, 530, 30);
			
			//Best Score
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.ITALIC,20));
			g.drawString("Best Score : "+bestScore, 10, 30);
			
			//Level
			g.setColor(Color.yellow);
			g.setFont(new Font("serif", Font.ITALIC, 20));
			g.drawString("LEVEL : "+level, 300, 30);
			
			//Instructions
			if(temp == 0) {
				g.setColor(Color.blue);
				g.setFont(new Font("serif",Font.BOLD,25));
				g.drawString("Use Left and Right Arrow Keys to Play", 100, 280);
				g.drawString("Press ESC To Pause", 100, 330);
				g.drawString("Press Enter To Restart", 100, 380);
			}
			
			//Slider
			g.setColor(Color.yellow);
			g.fillRect(playerX, 525, sliderLength, 15);
			
			//Tiles
			tile.drawTiles((Graphics2D) g);
			
			//ball
			g.setColor(Color.red);
			g.fillOval(ballPosX, ballPosY, 15, 15);
			
			//Game Over
			if(ballPosY > 575) {
				
				play = false;
				ballDirX = 0;
				ballDirY = 0;
				
				g.setColor(Color.red);
				g.setFont(new Font ("serif",Font.BOLD,40));
				g.drawString("GAME OVER !!", 220, 250);
				g.setFont(new Font ("serif",Font.BOLD,30));
				g.drawString("Your Score : "+score, 220, 300);
				g.setFont(new Font("serif",Font.ITALIC,25));
				g.drawString("Press Enter To Restart", 220, 350);
				
			}
			
			//Game Won
			if(totalTiles <= 0) {
				
				play = false;
				ballDirX = 0;
				ballDirY = 0;
				
				g.setColor(Color.red);
				g.setFont(new Font("serif", Font.BOLD, 40));
				g.drawString("Congratulations!!!",220,250);
				g.setFont(new Font("serif", Font.ITALIC,30));
				g.drawString("You Won!!", 220, 300);
				g.setFont(new Font("serif",Font.ITALIC,25));
				if(level == 10)
					g.drawString("Press Enter To Restart The Game", 220, 350);
				else
					g.drawString("Press Enter To Go To Next Level", 220, 350);
				
			}
		}			
	}
	
	private void moveLeft() {
		
		play = true;
		temp = 1;
		playerX -= 20;
	
	}
	
	private void moveRight() {
		
		play = true;
		temp = 1;
		playerX += 20;
	
	}

	public void keyPressed(KeyEvent e) {
		
		//When Left Arrow Key is pressed
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			if(playerX>10)
				moveLeft();
		
		//When Right Arrow Key is pressed
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			if( playerX+sliderLength <= 667)
				moveRight();
		
		//When ESC is pressed
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			count++;
			if(count%2 != 0)
				play = false;
			else 
				play = true;
		}
			
		//When Enter is pressed
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			if(play == false) {
				t=1;
				temp = 0; 
				ballPosX = (int)(Math.random()*(max-min+1)+min);
				ballPosY = (int)(Math.random()*(max-min+1)+min);
				ballDirX = -1;
				ballDirY = -2;
				level++;
				if(totalTiles > 0 && play == false) {
					level = 1;
					x=1;
					y=2;
					sliderLength = 100;
				}
				if(totalTiles == 0 && sliderLength >= 50 && level != 1)
					sliderLength -= 10;
				if(level > 10 || level == 1) {
					level = 1;
					score = 0;
				}
				if(x>10 && y>11) {
					x=1;
					y=2;
				}
				tile = new tiles(++x,++y);
				totalTiles = x*y;
			}
		
		repaint();
		
	}

	public void actionPerformed(ActionEvent e) {
		
		if(play) {
			
			//To keep ball inside borders
			if(ballPosX <= 0)
				ballDirX = -ballDirX;
			
			if(ballPosY <= 0)
				ballDirY = -ballDirY;
			
			if(ballPosX >= 670)
				ballDirX = -ballDirX;
			
			Rectangle bRect = new Rectangle(ballPosX,ballPosY,15,15);
			Rectangle sRect = new Rectangle(playerX,525,sliderLength,15);
			
			if(bRect.intersects(sRect)) 
				ballDirY = -ballDirY;
			
			//To remove tiles
			A:for(int i=0;  i<tile.tile.length;  i++) {
				for(int j=0;  j<tile.tile[i].length;  j++) {
					if(tile.tile[i][j]==1) {
						
						int width = tile.tileWidth;
						int height = tile.tileHeight;
						Rectangle tRect = new Rectangle(j*width+15, i*height+50, width, height);
						if(bRect.intersects(tRect)) {
							
							tile.setTile(0, i, j);
							totalTiles--;
							score += 5;
							if(bestScore < score)
								bestScore = score;
							
							if(ballPosX+14 <= j*width+15 || ballPosX+14 >= j*width+15+width)
								ballDirX = -ballDirX;
							else
								ballDirY = -ballDirY;
							
							break A; 
						}
					}
				}
			}
			
			ballPosX += ballDirX;
			ballPosY += ballDirY;
			
		}
		
		repaint();
		
	}
	
	public void keyTyped(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}
}
