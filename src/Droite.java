
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
		this.cosTheta = ((double)p2.getX() - p1.getX())/d;
		this.sinTheta = ((double)p1.getY() - p2.getY())/d; // because of the tricky orientation 
		
		//on veut avoir Theta entre -Pi/2 et Pi/2 donc :
		if(this.cosTheta<0){
			this.cosTheta = - this.cosTheta;
			this.sinTheta = - this.sinTheta;
		}
		
		System.out.println(cosTheta*cosTheta+sinTheta*sinTheta);
		
		// droite d'equation ax + by + c = 0 (a = cosTheta, b = sinTheta, c = r cherché)
		double xMilieu = (((double)p2.getX()) + p1.getX())/2;
		double yMilieu = (((double)p2.getY()) + p1.getY())/2;
		this.r =  this.cosTheta * xMilieu - this.sinTheta * yMilieu;	
	}
	
	public int[] toCoordinates () {
		int x = (int) (r*cosTheta);
		int y =  -(int) (r*sinTheta);
		if(sinTheta!=0.){
			int[]t={x,y,x+400,(int)(y+cosTheta/sinTheta*400)};;
			return t;
			
		}
		int t[] = {x,y-200,x,y+200};
		return t;
		
	}
	
	public String toString() {
		return "(r = " + r + ", cosTheta = " + cosTheta + ", sinTheta = " + sinTheta + ")";
	}

}
