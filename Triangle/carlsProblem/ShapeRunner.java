package carlsProblem;

import java.util.ArrayList;

public class ShapeRunner {
	
	public static void main(String[] args) {
		ArrayList<Point> pList = new ArrayList<Point>();
		Point p1 = new Point(0.0, 0.0);
		Point p2 = new Point(3.0, 0.0);
		Point p3 = new Point (3.0, 4.0);
		
		pList.add(p1);
		pList.add(p2);
		pList.add(p3);
		
		Triangle t1 = new Triangle(pList);
		
		System.out.println("The area for this project is: " + t1.area());
	}

}
