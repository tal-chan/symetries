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

                /*Point p1 = new Point(240, 20);
                Point p2 = new Point(150, 230);
                
                System.out.println("Point 1: " + p1.toString());
                System.out.println("Point 2: " + p2.toString());

                Droite d = new Droite(p1, p2);
                System.out.println("Segment p1 - p2 : " + d.toString());

                g.setColor(Color.RED);
                g.drawLine(p1.x, p1.y, p2.x, p2.y); // Segment p1 - p2
                

                g.setColor(Color.BLACK);
                int [] t = d.toCoordinates();
                g.drawLine(t[0], t[1], t[2], t[3]);*/
                //g.drawLine((int) (d.r * d.cosTheta), -(int) (d.r * d.sinTheta), (p1.x + p2.x) / 2, (p1.y + p2.y) / 2); // normale
        		String file = "src/carre.png";
                PRST square = new PRST(file,100);
				square.compute(g);
                Droite d = square.principal;
                int[] t = d.toCoordinates();
                g.setColor(Color.RED);
                g.drawImage(square.getImage(), 0, 0, null);
                g.drawLine(t[0], t[1], t[2], t[3]);
              //  g.setColor(Color.BLACK);
                System.out.println("x1 = "+t[0]+" y1 = "+t[1]+" x2 = "+t[2]+" y2 = "+t[3]);
                System.out.println(d.toString());

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