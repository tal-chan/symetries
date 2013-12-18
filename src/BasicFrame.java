import java.awt.*;
import java.io.IOException;

import javax.swing.*;

public class BasicFrame extends JFrame {

        // Create a constructor method
        public BasicFrame() {
                // All we do is call JFrame's constructor.
                // We don't need anything special for this
                // program.
                super();
        }

        // The following methods are instance methods.
        /*
         * Create a paint() method to override the one in JFrame. This is where the
         * drawing happens. We don't have to call it in our program, it gets called
         * automatically whenever the frame needs to be redrawn, like when it it
         * made visible or moved or whatever.
         */
        public void paint(Graphics g) {
        	testPRST(g);
        	//testPaquet(g);
        		

        }
        
        public void testPRST(Graphics g){
        	String file = "src/rosace.png";
            PRST square = new PRST(file,1000);				
            g.drawImage(square.getImage(), 0, 0, null);
            square.compute(g);
        }
        
        public void testPaquet(Graphics g){
        	Point p1 = new Point(0,0);
        	Point p2 = new Point(200,300);
        	Point p3 = new Point (1,3);
        	Point p4 = new Point (2,1);
        	Droite d1 = new Droite (p1,p2);
        	Droite d2 = new Droite (p3,p4);
        	Droite paquet1 = d1.paquet(8);
        	Droite paquet2 = d2.paquet(8);
        	g.setColor(Color.BLACK);
        	//d1.draw(g);
        	d2.draw(g);        	
        	g.setColor(Color.BLUE);
        	//paquet1.draw(g);
        	paquet2.draw(g);
        }

        public static void main(String arg[]) {
                // create an identifier named 'window' and
                // apply it to a new BasicFrame object
                // created using our constructor, above.
                BasicFrame frame = new BasicFrame();

                // Use the setSize method that our BasicFrame
                // object inherited to make the frame
                // 200 pixels wide and high.
                frame.setSize(300, 300);

                // Make the window show on the screen.
                frame.setVisible(true);
        }
}