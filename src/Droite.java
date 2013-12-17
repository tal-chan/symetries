
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
		
		//System.out.println(cosTheta*cosTheta+sinTheta*sinTheta);
		
		// droite d'equation ax + by + c = 0 (a = cosTheta, b = sinTheta, c = r cherché)
		double xMilieu = (((double)p2.getX()) + p1.getX())/2;
		double yMilieu = (((double)p2.getY()) + p1.getY())/2;
		this.r =  this.cosTheta * xMilieu - this.sinTheta * yMilieu;	
	}
	
	@Override
	public boolean equals (Object o){
		if (o instanceof Droite) {
			return (r==((Droite)o).r && cosTheta==((Droite)o).cosTheta &&sinTheta==((Droite)o).sinTheta);
		}
		return false;
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
	
	public Droite paquet(int n) {
		return null;
	}
	
	public String toString() {
		return "(r = " + r + ", cosTheta = " + cosTheta + ", sinTheta = " + sinTheta + ")";
	}
	
	public static void main (String[] args){
		Droite d1 = new Droite (10,1,0);
		Droite d2 = new Droite (10,1,0);
		Droite d3 = new Droite (9,1,0);
		System.out.println(d1.equals(d2));
		System.out.println(d1.equals(d3));
		System.out.println(d1.hashCode());
		System.out.println(d2.hashCode());
		System.out.println(d3.hashCode());
	}

}
