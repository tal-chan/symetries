import java.awt.*;
import java.io.IOException;

import javax.swing.*;

public class BasicFrame extends JFrame {
	double th;
	int size;
	int N;
	String file;

	public BasicFrame() {
		super();
	}

	public BasicFrame(double th,int s,int N,String f) {
		super();
		this.th = th;
		this.size=s;
		this.N=N;
		file = f;
	}

	
	public void paint(Graphics g) {
		testPRST(g);
		//paquet(g);
		//sampling(g);
		//testPaquet(g);


	}

	public void testPRST(Graphics g){
		PRST prst = new PRST(file,size);
		prst.compute(N);
		prst.visualize(g, th);
	}
	
	public void paquet (Graphics g){
		PRST prst = new PRST (file,size);
		prst.showPaquet(g, N);
	}
	
	public void sampling(Graphics g){
		Sampling sample = new Sampling(file,size);
		sample.compute();
		sample.visualize(g);
	}

	/*public void testPaquet(Graphics g){
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
	}*/

	public static void main(String arg[]) {
		double th = .5;
		int size = 4000;
		int N=60;
		//String file = "src/carre.png";
		String file = "src/voiture.png";
		//String file = "src/voiture.gif";
		//String file = "src/pentagon.png";
		
		
		BasicFrame frame = new BasicFrame(th,size,N,file);

		frame.setSize(500, 300);

		frame.setVisible(true);
	}
}