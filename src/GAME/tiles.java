package GAME;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class tiles {
	
	public int tileWidth;
	public int tileHeight;
	public int tile[][];
	public Random random = new Random();
	public float red = random.nextFloat();
	public float green = random.nextFloat();
	public float blue = random.nextFloat();
	public Color col = new Color(red,green,blue);
	
	public tiles(int row, int col) {
			
		tile = new int[row][col];
		
		for(int i=0;	i<row;	i++) {
			for(int j=0;	j<col;	j++) {
				tile[i][j] = 1;
			}
		}
		
		tileWidth = 657/col;
		tileHeight = 150/row;
		
	}
	
	public void setTile(int value, int r, int c) {
		
		tile[r][c] = 0;
		
	}
	
	public void drawTiles(Graphics2D g) {
		
		for(int i=0;  i<tile.length;  i++) {
			
			for(int j=0;  j<tile[i].length;	 j++) {
				
				if(tile[i][j] == 1) {
					
					g.setColor(col);
					g.fillRect(j*tileWidth+15, i*tileHeight+50, tileWidth, tileHeight);
					
					g.setColor(Color.black);
					g.setStroke(new BasicStroke(3));
					g.drawRect(j*tileWidth+15, i*tileHeight+50, tileWidth, tileHeight);
					
				}
			}
		}
	}
}
