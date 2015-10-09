import java.awt.*;
import java.awt.event.*;
import javax.swing.*;        

/**
 *  This file provides the skeleton of a Java Swing application
 *  for assignment 2 of CSC 112.  It is intended to create two windows
 *  with differing views of a map.  Portions are left unfinished for
 *  students to fill in.
 *
 *  @author  Stephanie Xie
 *  @version 30 September 2015
 */
public class MapGUI{
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
	
	/** map to be displayed */
	private static MapGrid map;
	
	/** viewport of the map */
	private static MapViewer view;
	
	/** point clicked by the mouse */
	private Point mousePt;
	
	/** buttons */
	private JButton northButton, southButton, eastButton, westButton, zoomInButton, zoomOutButton;
	
    /** This method is called by the application version. */
    public void createAndShowGUI() {
        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the windows.
        JFrame frame = new JFrame("Map");
        try { frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (Exception e) {}

        // Set up map:
        map = new MapGrid(60, 60); // tells MapGrid to create a 60x60 map
        map.setRect(0, 10, 60, 10, Color.GRAY); // horizontal road
        map.setRect(10, 10, 10, 50, Color.GRAY); // vertical road
        map.setRect(30,4,20,8, Color.YELLOW); // schoolbus body
        map.setRect(26, 8, 5, 4, Color.YELLOW); // schoolbus nose
        map.setRect(28, 10, 4, 4, Color.BLACK); // front wheel
        map.setRect(43, 10, 4, 4, Color.BLACK); // back wheel
        map.setRect(30, 5, 4, 3, Color.LIGHT_GRAY); // schoolbus window
        map.setRect(26, 8, 1, 1, Color.RED); // schoolbus headlight
        map.setRect(6, 2, 3, 4, Color.RED); // stop sign
        map.setRect(7, 3, 1, 2, Color.WHITE); // sign text
        map.setRect(7, 6, 1, 4, Color.BLACK); // signpost
        Color pondBlue = new Color(0, 191, 255); // pond
        map.setRect(40, 40, 20, 20, pondBlue);
        
        // Create and add the viewers:
        view = new MapViewer(map);
        view.setMagnify(13); /** smallest = 1*/
        view.setOffsetX(0);
        view.setOffsetY(0);
        frame.getContentPane().add(view);
        
        // Add components
        createComponents(frame.getContentPane());

        // Display the window:
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     *  Application calls this to set up the GUI contents.
     *
     *  @param pane  The pane of the JFrame
     */
    public void createComponents(Container pane) {
    	
        pane.setLayout(new FlowLayout());
        pane.add(view);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        this.northButton = new JButton("North");
        panel.add(northButton, BorderLayout.NORTH);
        northButton.addActionListener(new DirectionListener());
        
        this.southButton = new JButton("South");
        panel.add(southButton, BorderLayout.SOUTH);
        southButton.addActionListener(new DirectionListener());

        this.eastButton = new JButton("East");
        panel.add(eastButton, BorderLayout.EAST);
        eastButton.addActionListener(new DirectionListener());

        this.westButton = new JButton("West");
        panel.add(westButton, BorderLayout.WEST);
        westButton.addActionListener(new DirectionListener());
        
        pane.add(panel);

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2,1));
        
        this.zoomInButton = new JButton("Zoom In");
        p.add(zoomInButton, 0);
        zoomInButton.addActionListener(new ZoomListener());
        
        this.zoomOutButton = new JButton("Zoom Out");
        p.add(zoomOutButton, 1);
        zoomOutButton.addActionListener(new ZoomListener());
        
        panel.add(p);
        
        // Mouse event handlers
        view.addMouseListener(new MouseAdapter() {
        	public void mousePressed(MouseEvent e) {
        		mousePt = e.getPoint();
        	}
        	
        	public void mouseClicked(MouseEvent e) {
        		mousePt = e.getPoint();
        		int dx = mousePt.x - view.getWidth()/2;
        		int dy = mousePt.y - view.getHeight()/2;
        		view.setOffsetX(view.getOffsetX() - dx);
        		view.setOffsetY(view.getOffsetY() - dy);
        	}
        	
        	public void mouseReleased(MouseEvent e) {
        		// returns cursor to the default cursor upon release
        		view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        	}
        }); 
        
        view.addMouseMotionListener(new MouseAdapter() {
        	public void mouseDragged(MouseEvent e) {
        		// sets cursor to the hand cursor when dragging
        		view.setCursor(new Cursor(Cursor.HAND_CURSOR));
        		int dx = e.getX() - mousePt.x;
        		int dy = e.getY() - mousePt.y;
        		view.setOffsetX(view.getOffsetX() + dx);
        		view.setOffsetY(view.getOffsetY() + dy);
        		mousePt = e.getPoint();
        	}
        });   
    }
    
	 /** Event handler for Zoom In/Out buttons */
    class ZoomListener implements ActionListener {
        /**
         *  Zooms in/out when the respective buttons are pushed.
         *
         *  @param e  Holds information about the button-push event
         */
	    public void actionPerformed(ActionEvent e) {
	    	Object source = e.getSource();
	    	if (source == zoomInButton) {
	    		view.zoom(1);
	    	} else if (source == zoomOutButton) {
	    		view.zoom(-1); 
	    	}
	    }
    }
    
    /** Event handler for North/South/East/West buttons */
    class DirectionListener implements ActionListener {
        /**
         *  Scrolls north/south/east/west when the respective buttons are pushed.
         *
         *  @param e  Holds information about the button-push event
         */
    	public void actionPerformed(ActionEvent e) {
    		Object source = e.getSource();
    		if (source == northButton) {
    			view.direction(0, 15);
    		} else if (source == southButton) {
    			view.direction(0, -15);
    		} else if (source == eastButton) {
    			view.direction(-15, 0);
    		} else if (source == westButton) {
    			view.direction(15, 0);
    		}
    	}
    }

    /** 
     *  This is the entry point for the application version
     */
    public static void main(String[] args) {
        final MapGUI GUI = new MapGUI();
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		    GUI.createAndShowGUI();
		}
	    });
    }
} // end of MapApplication