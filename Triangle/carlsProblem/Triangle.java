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
		
		System.out.println("We called this.");
		
		
		
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
		
		System.out.println("A.x is " + a.x);
		System.out.println("A.y is " + a.y);
		System.out.println("B.x is " + b.x);
		System.out.println("B.y is " + b.y);
		System.out.println("C.x is " + c.x);
		System.out.println("C.y is " + c.y);
		
		//Cross Product
		
		//ac x ab
		
		//(b.x - a.x) (c.y - a.y) - (b.y - a.y) (c.x - a.x)
		
		double area = (b.x - a.x) * (c.y - a.y) -
				(b.y - a.y) * (c.x - a.x);
		
		return area/2;
	}

	public boolean isEquilateral() {
		// TODO Auto-generated method stub
		boolean equal = false;
		Point a = coordinates.get(0);
		Point b = coordinates.get(1);
		Point c = coordinates.get(2);
		
		double sideA = Math.pow(a.x-b.x, 2) + Math.pow(a.y-b.y, 2);
		System.out.println(sideA);
		double sideB = Math.pow(b.x-c.x, 2) + Math.pow(b.y-c.y, 2);
		System.out.println(sideB);
		double sideC = Math.pow(c.x-a.x, 2) + Math.pow(c.y-a.y, 2);
		System.out.println(sideC);
		
		if( Math.abs(sideA - sideB) <= 0.00003 && Math.abs(sideC - sideB) <= 0.00003) {
			equal = true;
		}
		
		return equal;
	}
	

}
