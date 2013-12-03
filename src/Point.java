public class Point {

	public int x, y;

	public Point() {
	}

	/**
	 * Pixel de l'image: coordonnées discretes
	 * @param x 
	 * @param y (attention, y vers le bas)
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double distanceFrom(Point p2) {
		double dX = ((double) p2.getX()) - x;
		double dY = ((double) p2.getY()) - y;
		return Math.sqrt(dX * dX + dY * dY);
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
