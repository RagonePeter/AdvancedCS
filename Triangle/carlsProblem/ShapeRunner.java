package carlsProblem;

import java.util.ArrayList;

public class ShapeRunner {
	
	public static void main(String[] args) {
		ArrayList<Point> pList = new ArrayList<Point>();
		Point p1 = new Point(2.0, 1.0);
		Point p2 = new Point(7.0, 1.0);
		Point p3 = new Point (4.5, Math.sqrt(Math.pow((p1.x - p2.x), 2) - Math.pow((4.5 - p1.x), 2)) + p2.y);
		
		pList.add(p1);
		pList.add(p2);
		pList.add(p3);
		
		Triangle t1 = new Triangle(pList);
		
		System.out.println("The area for this project is: " + t1.area());
		System.out.println("We think this triangle is equilateral: " + t1.isEquilateral());
		Triangle test2 = new Triangle();
		test2.addOneCoordinate(0,0);
		test2.addOneCoordinate(4,0);
		test2.addOneCoordinate(2, 2*Math.sqrt(3));
		System.out.println(test2.isEquilateral());
	}

}
