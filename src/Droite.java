
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
		this.sinTheta = ((double)p2.getY() - p1.getY())/d;
		
		// droite d'equation ax + by + c = 0 (a = cosTheta, b = sinTheta, c = r cherché)
		double xMilieu = (((double)p2.getX()) + p1.getX())/2;
		double yMilieu = (((double)p2.getY()) + p1.getY())/2;
		this.r = Math.abs(- this.cosTheta * xMilieu - this.sinTheta * yMilieu);		
	}
	
	public String toString() {
		return "(r = " + r + ", cosTheta = " + cosTheta + ", sinTheta = " + sinTheta + ")";
	}

}
