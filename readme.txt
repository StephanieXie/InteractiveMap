Stephanie Xie
30 September 2015

Reflection - Assignment 3

The hardest part of this assignment was using a 2D array to create the layout for the map; once that was done, adding the ActionListeners were relatively straightforward, and it was fun experimenting with the different MouseListeners. The most challenging part was keeping the center point of the window fixed when zooming in/out - I gave it my best shot, but no matter what I tried the offset of my window stayed fixed. I did manage to adjust the view’s offset so that whatever point was clicked would become the center; I also added a mouseDragged function so the user could scroll by dragging the map. I attempted to create an applet version; however, I ran into a couple of difficulties: 1) java security wouldn’t allow me to run the applet unless I made an exception; 2) there were many $ classes created when MapGUI.java was compiled, so I had to make a jar file; 3) even with all of this, I kept getting either a ClassNotFound error (when I formatted the html file as, code="MapGUI.class") or a plain grey box (code=MapGUI.class).
