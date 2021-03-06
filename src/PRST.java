import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class PRST {	
	Sampling sample;
	HashMap<Droite,Double> prst;
	Double max;

	public PRST (Sampling s){
		sample=s;
		sample.compute();
		prst = new HashMap<Droite,Double>();
		max=0.;
	}

	public PRST (String file,int n){
		sample = new Sampling(file,n);
		sample.compute();
		prst = new HashMap<Droite,Double>();
		max = 0.;
	}

	public BufferedImage getImage(){
		return sample.img.img;
	}

	public void compute(int N){
		Point[] pts = sample.points;
		int n = sample.size;
		try {
			FileWriter fichier = new FileWriter("log2.txt");
			fichier.write("\n Début du fichier de log \n ");

			for (int i=0;i<n;i++){
				for (int j=0;j<i;j++){
					Droite mediatrice = new Droite(pts[i],pts[j]);
					Droite paquet = mediatrice.paquet(N);

					double d = pts[i].distanceFrom(pts[j]);
					if (d==0.){break;}
					if (prst.containsKey(paquet)){
						double v = prst.get(paquet); 
						fichier.write("PAQUET DEJA TROUVE, ");
						fichier.write(paquet.toString());
						fichier.write("on ajoute " + 1/(2*d*n));
						v+= 1/(2*d*n);
						fichier.write(", RESULTAT =  " + v);
						fichier.write("\n");
						prst.put(paquet,v);
						if (v>max){
							max=v;
						}
					}
					else{
						fichier.write("nouveau PAQUET: ");
						fichier.write(paquet.toString());
						fichier.write(" on met RESULTAT = "+1/(2*d*n));
						fichier.write("\n");
						prst.put(paquet, 1/(2*d*n));
						if (1/(2*d*n)>max){
							max = 1/(2*d*n);
						}
					}
				}
			}
			/*for (Droite d : prst.keySet()){
			prst.put(d, (1+prst.get(d))/2);
		}
		max = (1+max)/2;*/
			System.out.println(max);
			fichier.write("\n Fin du fichier de log");
			fichier.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
	
	public void showPaquet(Graphics g, int N){
		Point[] pts = sample.points;
		int n = sample.size;
		g.drawImage(getImage(), 0, 0, null);
		for (int i=0;i<n;i++){
			for (int j=0;j<i;j++){


				switch((i+j)%10){
				case 0: g.setColor(Color.black);         	break;
				case 1: g.setColor(Color.yellow);         	break;
				case 2: g.setColor(Color.blue);         	break;
				case 3: g.setColor(Color.cyan);         	break;
				case 4: g.setColor(Color.green);         	break;
				case 5: g.setColor(Color.magenta);         	break;
				case 6: g.setColor(Color.gray);         	break;
				case 7: g.setColor(Color.orange);         	break;
				case 8: g.setColor(Color.pink);         	break;
				case 9: g.setColor(Color.red);         		break;
				}
				Object o=new Object();
	            try
	            {
	                synchronized(o) { o.wait(2000); }
	            }
	            catch(InterruptedException e) { }
	            Droite mediatrice = new Droite(pts[i],pts[j]);
	            Graphics2D g2 = (Graphics2D)g;
	            Stroke s = g2.getStroke();
				//trait épais
				g2.setStroke(new BasicStroke(8));
				g.drawLine(pts[i].x, pts[i].y,pts[i].x, pts[i].y);
				g.drawLine(pts[j].x, pts[j].y,pts[j].x, pts[j].y);
				// retour au trait "normal"
				g2.setStroke(s);
				Droite paquet = mediatrice.paquet(N);
				paquet.draw(g);
			}
		}
	}

	public void visualize(Graphics g, double th){
		g.drawImage(getImage(), 0, 0, null);
		for (Droite d : prst.keySet()){
			double valeur = prst.get(d);
			if (valeur>th*max){
				Color c = new Color ((float)(valeur/max),(float)0.,(float)(1.-valeur/max),(float).5);
				d.draw(g,c);
			}
			System.out.println(valeur);
		}
		System.out.println("max = "+max);		
	}

	public static void main (String[] args){
		String file = "src/carre.png";
		PRST square = new PRST(file,5000);
		square.compute(30);

		try {
			FileWriter fichier = new FileWriter("log.txt");
			fichier.write("\n Début du fichier de log");
			for (Double d : square.prst.values()){
				fichier.write("\n"+d);
				//System.out.println(d);
			}
			fichier.write("\n Fin du fichier de log");
			fichier.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
