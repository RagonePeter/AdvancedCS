package carlsProblem;

import java.util.ArrayList;

public class Triangle extends Polygon {

	public static final int TRIANGLE_SIDES = 3;
	
	public Triangle(int numOfSides) {
		super(numOfSides);
		// TODO Auto-generated constructor stub
	}
	
	public Triangle(ArrayList<Point> pList) {
		super(TRIANGLE_SIDES, pList);
	}
	
	public Triangle(Point p1, Point p2, Point p3) {
		super(TRIANGLE_SIDES);
		addOneCoordinate(p1);
		addOneCoordinate(p2);
		addOneCoordinate(p3);	
	}
	
	public Triangle() {
		super(TRIANGLE_SIDES);
	}

	public double area() {
		// TODO Auto-generated method stub
		Point a = coordinates.get(0);
		Point b = coordinates.get(1);
		Point c = coordinates.get(2);
		
		//Cross Product
		
		//ac x ab
		
		//(b.x - a.x) (c.y - a.y) - (b.y - a.y) (c.x - a.x)
		
		double area = (b.x = a.x) * (c.y - a.y) -
				(b.y - a.y) * (c.x - a.x);
		
		return area/2;
	}

	public boolean isEquilateral() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
