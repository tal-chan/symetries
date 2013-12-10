import java.util.HashMap;

public class PRST {	
	Sampling sample;
	HashMap<Droite,Double> prst;
	
	public PRST (Sampling s){
		sample=s;
		prst = new HashMap<Droite,Double>();
	}
	
	public void compute(){
		Point[] pts = sample.points;
		int n = sample.size;
		int N = 8;
		for (int i=0;i<n;i++){
			for (int j=0;j<i;j++){
				Droite mediatrice = new Droite(pts[i],pts[j]);
				int r = (int) Math.round(mediatrice.r);
				// les angles des droites sont entre O et pi/2, un simple arcos suffit pour recuperer l'angle.
				double theta = Math.acos(mediatrice.cosTheta);
				double k = Math.floor(N*theta/(2*Math.PI));
				theta = 2*k*Math.PI/N;
				Droite paquet = new Droite (r,Math.sin(theta),Math.cos(theta));
				double d = pts[i].distanceFrom(pts[j]);
				if (prst.containsKey(paquet)){
					double v = prst.get(paquet);
					v+= 1/(2*d);
					prst.put(paquet,v);
				}
				else{
					prst.put(paquet, 1/(2*d));
				}
			}
		}
	}
}
