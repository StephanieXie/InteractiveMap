import java.awt.*;

/**
 *  Class that stores a 2D array of Color values for the map
 *
 *  @author Stephanie Xie
 *  @version 30 September 2015
 */

public class MapGrid {
	private Color[][] map;

	/** Constructor: default color of map = green */
	public MapGrid(int w, int h) {  /* in order to draw the grid, the function first needs to know 
										the width and height, so pass w and h into MapGrid */
		Color green = new Color(34, 139, 34);
		map = new Color[w][h]; // set map dimensions w, h
		for (int i = 0; i < map.length; i++) { 
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = green;
			}
		}		
	}
	
	/** Manipulator: set color of rectangle */
	public void setRect(int gapX, int gapY, int rectWidth, int rectHeight, Color c) {  
		for (int i = 0; i < rectWidth; i++) {
        	for (int j = 0; j < rectHeight; j++) {
        		setColor(i + gapX, j + gapY, c);
        	}
        }
	}

	/** Accessor: gets color of map */
	public Color getColor(int x, int y) { /* to get the color of a given pixel, the function 
											first needs to know where the pixel is (x and y) */
		return map[x][y]; /* x = length from 0 to wherever part of width the pixel is
							y = length from 0 to wherever part of height the pixel is */
	}
	
	/** Manipulator: sets color of map */
	public void setColor(int x, int y, Color c) {  /* to set the pixel a given color, the function
														first needs to know its dimensions x, y and
														the color it's being set to */
		map[x][y] = c; // set pixel x, y to Color c
	}
	
	
	/** Accessor: gets width and height of map */
	public int getWidth() {
		return map.length;	
	}
	
	public int getHeight() {
		return map[0].length;
	}
	
} // end of MapGrid


	
	
