
public class Droite {
	
	public double r, cosTheta, sinTheta;
	
	public Droite(){}
	
	public Droite(double r, double cosTheta, double sinTheta){
		this.r = r;
		this.cosTheta = cosTheta;
		this.sinTheta = sinTheta;
	}

	/**
	 * Droite Mediatrice
	 * @param p1 point1
	 * @param p2 point2
	 */
	public Droite(Point p1, Point p2){
		double d = p1.distanceFrom(p2);		
		this.cosTheta = ((double)p2.getY() - p1.getY())/d;
		this.sinTheta = ((double)p2.getX() - p1.getX())/d;
		
		// droite d'equation ax + by + c = 0 (a = cosTheta, b = sinTheta, c = r cherché)
		double xMilieu = (((double)p2.getX()) + p1.getX())/2;
		double yMilieu = (((double)p2.getY()) + p1.getY())/2;
		this.r = - this.cosTheta * yMilieu - this.sinTheta * xMilieu;	
		
		if(r<0){
			this.cosTheta = - this.cosTheta;
			this.sinTheta = - this.sinTheta;
		}
	}
	
	public int[] toPoints () {
		int x = (int) (r*cosTheta);
		int y = (int) (r*sinTheta);
		if(sinTheta!=0.){
			int[]t={x,y,x+200,(int)(y+cosTheta/sinTheta*200)};;
			return t;
			
		}
		int t[] = {x,y,x,y+200};
		return t;
		
	}
	
	public String toString() {
		return "(r = " + r + ", cosTheta = " + cosTheta + ", sinTheta = " + sinTheta + ")";
	}

}
