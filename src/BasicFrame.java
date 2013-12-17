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
        	//testPRST(g);
        	testPaquet(g);
        		

        }
        
        public void testPRST(Graphics g){
        	String file = "src/carre.png";
            PRST square = new PRST(file,1000);				
            g.drawImage(square.getImage(), 0, 0, null);
            square.compute(g);
            Droite d = square.principal;
            int[] t = d.toCoordinates();
            g.setColor(Color.RED);
            g.drawLine(t[0], t[1], t[2], t[3]);
          //  g.setColor(Color.BLACK);
            System.out.println("x1 = "+t[0]+" y1 = "+t[1]+" x2 = "+t[2]+" y2 = "+t[3]);
            System.out.println(d.toString());
        }
        
        public void testPaquet(Graphics g){
        	Point p1 = new Point(0,0);
        	Point p2 = new Point(200,300);
        	Point p3 = new Point (10,300);
        	Point p4 = new Point (200,10);
        	Droite d1 = new Droite (p1,p2);
        	Droite paquet1 = d1.paquet(8);
        	g.setColor(Color.BLACK);
        	int[] t1 = d1.toCoordinates();
        	g.drawLine(t1[0], t1[1], t1[2], t1[3]);
        	g.setColor(Color.BLUE);
        	int[] t2 = paquet1.toCoordinates();
        	g.drawLine(t2[0], t2[1], t2[2], t2[3]);
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