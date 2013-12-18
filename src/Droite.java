import java.awt.Color;
import java.awt.Graphics;


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
		
		//on veut avoir Theta entre -Pi/2 et Pi/2 donc :
		if(this.cosTheta<0){
			this.cosTheta = - this.cosTheta;
			this.sinTheta = - this.sinTheta;
		}
		if (this.cosTheta==0){
			this.sinTheta=1;
		}
		
		//System.out.println(cosTheta*cosTheta+sinTheta*sinTheta);
		
		// droite d'equation ax + by + c = 0 (a = cosTheta, b = sinTheta, c = r cherché)
		double xMilieu = (((double)p2.getX()) + p1.getX())/2;
		double yMilieu = (((double)p2.getY()) + p1.getY())/2;
		this.r =  this.cosTheta * xMilieu + this.sinTheta * yMilieu;	
	}
	
	@Override
	public boolean equals (Object o){
		if (o instanceof Droite) {
			return (r==((Droite)o).r && cosTheta==((Droite)o).cosTheta &&sinTheta==((Droite)o).sinTheta);
		}
		return false;
	}

	@Override
	public int hashCode() {
		double hash = 5;
		hash = 89 * hash + cosTheta*1057; ;
		hash = 89 * hash + ((int)r ^ ((int)r >>> 32));
		hash = 89 * hash + sinTheta*7001;
		return (int) hash;
	}
	
	public int[] toCoordinates () {
		int x = (int) (r*cosTheta);
		int y =  (int) (r*sinTheta);
		if(sinTheta!=0.){
			int[]t={x-400,(int)(y+cosTheta/sinTheta*400),x+400,(int)(y-cosTheta/sinTheta*400)};;
			return t;
			
		}
		int[] t = {x,y-400,x,y+400};
		return t;
		
	}
	
	public void draw(Graphics g){
		int[] t = toCoordinates();
		g.drawLine(t[0], t[1], t[2], t[3]);
	}
	
	public void draw(Graphics g, Color c){
		int[] t = toCoordinates();
		g.setColor(c);
		g.drawLine(t[0], t[1], t[2], t[3]);
	}
	
	public Droite paquet(int n) {
		int rayon = (int) Math.round(r);
		double theta = Math.asin(sinTheta);
		double k = Math.round(n*theta/(2*Math.PI));
		theta = 2*k*Math.PI/n;
		Droite res = new Droite (rayon,Math.cos(theta),Math.sin(theta));
		
		return res;
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
