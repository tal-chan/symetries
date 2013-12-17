import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class PRST {	
	Sampling sample;
	HashMap<Droite,Double> prst;
	Double max;
	Droite principal;
	
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
		
	public void compute(){
		Point[] pts = sample.points;
		int n = sample.size;
		int N = 8;
		

		try {
			FileWriter fichier = new FileWriter("log2.txt");
			fichier.write("\n Début du fichier de log \n ");
			
		for (int i=0;i<n;i++){
			for (int j=0;j<i;j++){
				Droite mediatrice = new Droite(pts[i],pts[j]);
				int r = (int) Math.round(mediatrice.r);
				// les angles des droites sont entre -pi/2 et pi/2, un simple asin suffit pour recuperer l'angle.
				double theta = Math.asin(mediatrice.sinTheta);
				double k = Math.round(N*theta/(2*Math.PI));
				theta = 2*k*Math.PI/N;
				Droite paquet = new Droite (r,Math.cos(theta),Math.sin(theta));
			//	Droite paquet = new Droite (10,1,0);
			//	Droite paquet = mediatrice;

			//	System.out.println(paquet.toString());
			//	System.out.println(paquet.hashCode());
			//	System.out.println("");
				
				double d = pts[i].distanceFrom(pts[j]);
			//	System.out.println(d);
				if (d==0.){break;}
				//System.out.println(d);
				if (prst.containsKey(paquet)){
					//System.out.println("DANS BOUCLE");
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
						principal=paquet;
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
						principal=paquet;
					}
				}
			}
		}
		/*for (Droite d : prst.keySet()){
			prst.put(d, (1+prst.get(d))/2);
		}*/
		//max = (1+max)/2;
		System.out.println(max);
		System.out.println(principal);

		fichier.write("\n Fin du fichier de log");
		fichier.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
			
		
	}
	
	public void compute(Graphics g){
		Point[] pts = sample.points;
		int n = sample.size;
		int N = 8;
				
		for (int i=0;i<n;i++){//System.out.println(i);
            switch(i%10){
            	case 0: g.setColor(Color.black);         	break;
            	case 1: g.setColor(Color.black);         	break;
            	case 2: g.setColor(Color.black);         	break;
            	case 3: g.setColor(Color.black);         	break;
            	case 4: g.setColor(Color.black);         	break;
            	case 5: g.setColor(Color.magenta);         	break;
            	case 6: g.setColor(Color.magenta);         	break;
            	case 7: g.setColor(Color.magenta);         	break;
            	case 8: g.setColor(Color.magenta);         	break;
            	case 9: g.setColor(Color.magenta);         	break;
            }
			
            Object o=new Object();
            try
            {
                synchronized(o) { o.wait(50); }
            }
            catch(InterruptedException e) { }
            
			for (int j=0;j<i;j++){
				Droite mediatrice = new Droite(pts[i],pts[j]);
				int r = (int) Math.round(mediatrice.r);
				// les angles des droites sont entre -pi/2 et pi/2, un simple asin suffit pour recuperer l'angle.
				double theta = Math.asin(mediatrice.sinTheta);
				double k = Math.round(N*theta/(2*Math.PI));
				theta = 2*k*Math.PI/N;
				Droite paquet = new Droite (r,Math.cos(theta),Math.sin(theta));
				//mediatrice.draw(g);
                //g.setColor(Color.RED);
               // g.drawImage(square.getImage(), 0, 0, null);
                
               // g.setColor(Color.BLACK);
             //   System.out.println("x1 = "+t[0]+" y1 = "+t[1]+" x2 = "+t[2]+" y2 = "+t[3]);
             //   System.out.println(paquet.toString());
				
				
				// System.out.println(paquet.toString());
				double d = pts[i].distanceFrom(pts[j]);
				if (d==0.){break;}
				//System.out.println(d);
				if (prst.containsKey(paquet)){
					//System.out.println("DANS BOUCLE");
					double v = prst.get(paquet); 
					v+= 1/(2*d*n);
					prst.put(paquet,v);
					if (v>max){
						max=v;
						principal=paquet;
					}
				}
				else{
					prst.put(paquet, 1/(2*d*n));
					if (1/(2*d*n)>max){
						max = 1/(2*d*n);
						principal=paquet;
					}
					//paquet.draw(g);
				}
			}
		}

		//max = (1+max)/2;
		double s = 0.5*max;
		for (Droite d : prst.keySet()){
			//double valeur = (1+prst.get(d))/2;
			double valeur = prst.get(d);
			prst.put(d, valeur);
			if (valeur>s){
				d.draw(g);
			}
			System.out.println(valeur);
		}
		System.out.println("max = "+max);
		System.out.println("FIN DE COMPUTE");
		
	}
	
	public static void main (String[] args){
		String file = "src/carre.png";
		PRST square = new PRST(file,5000);
		square.compute();
		
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
