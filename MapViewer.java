import java.awt.*;
import javax.swing.*;

/**
 *  Class that draws the map
 *
 *  @author Stephanie Xie
 *  @version 30 September 2015
 */

public class MapViewer extends JComponent {
	private MapGrid mapGrid;
	
	/** Magnification */
	private int magnify;
	
	/** Points of offset */
	private int x, y;
	
	/** Window size */
	private static int s;
	
	/** Creates a map of specified size with default color green. */
	public MapViewer(MapGrid mGrid) {
		super();
		mapGrid = mGrid;
		s = 800;
	}
	
	/** Accessor: gets x of offset */
	public int getOffsetX() {
		return x;
	}

	/** Accessor: gets y of offset */
	public int getOffsetY() {
		return y;
	}
	
	/** Manipulator: sets x of offset */
	public void setOffsetX(int x) {
		this.x = x;
		repaint();
	}
	
	/** Manipulator: sets y of offset */
	public void setOffsetY(int y) {
		this.y = y;
		repaint();
	}
	
	/** Accessor: gets magnification */
	public int getMagnify() {
		return magnify;
	}
	
	/** Manipulator: sets magnification */
	public void setMagnify(int mag) {
		magnify = mag;
		repaint();
	}
	
	/** Zoom in/out on the map */
	public void zoom(int z) {
		this.magnify = magnify + z;
		repaint();
	}
	
	/** Scroll directions on the map */
	public void direction(int a, int b) {
		this.x = x + a; 
		this.y = y + b;
		repaint();
	}
	
    /**
     *  Draws the magnified map in the graphics window
     *
     *  @param g The graphics object to draw into
     */
    public void paintComponent(Graphics g) {
    	// set background color of map to beige and fill map
    	Color beige = new Color(245, 245, 220);
    	g.setColor(beige);
    	g.fillRect(0, 0, s, s);
    	
    	// Fill in individual pixels with a different color you want
    	for (int i = 0; i < mapGrid.getWidth(); i++) {
    		for (int j = 0; j < mapGrid.getHeight(); j++) {	
    			
    			// gets the MapGrid color to fill in the pixel i, j that's being drawn
    			g.setColor(mapGrid.getColor(i, j)); 
    			g.fillRect(x + i*magnify, y + j*magnify, magnify, magnify);
    		}
    	}
    }    
	
    /**
     *  @returns The minimum dimension
     */
    public Dimension getMinimumSize() {
	return new Dimension(s, s);
    }

    /**
     *  @returns The preferred dimension
     */
    public Dimension getPreferredSize() {
	return new Dimension(s, s);
    }
	
} // end of MapViewer