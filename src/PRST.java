import java.awt.image.BufferedImage;
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
		for (int i=0;i<n;i++){
			for (int j=0;j<i;j++){
				Droite mediatrice = new Droite(pts[i],pts[j]);
				int r = (int) Math.round(mediatrice.r);
				// les angles des droites sont entre -pi/2 et pi/2, un simple asin suffit pour recuperer l'angle.
				double theta = Math.asin(mediatrice.sinTheta);
				double k = Math.floor(N*theta/(2*Math.PI));
				theta = 2*k*Math.PI/N;
				Droite paquet = new Droite (r,Math.sin(theta),Math.cos(theta));
				//System.out.println(paquet.toString());
				double d = pts[i].distanceFrom(pts[j]);
				if (d==0.){break;}
				if (prst.containsKey(paquet)){
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
				}
			}
		}
	}
	public static void main (String[] args){
		String file = "src/carre.png";
		PRST square = new PRST(file,100);
		square.compute();
		System.out.println(square.principal.toString());
		
	}
}
