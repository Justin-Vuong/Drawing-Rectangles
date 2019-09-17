import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner user_input = new Scanner(System.in);
		IcsRectangle r = new IcsRectangle(2,-3,0,2);
		System.out.println("print summary: "+ r.toString());
		System.out.println("\nArea: " + r.area());
		System.out.println("Perimeter: " + r.perimeter());
		
		//IcsRectangle rec1 = new IcsRectangle(0,0,30,25); //Problem
		//IcsRectangle rec2 = new IcsRectangle(0,0,30,10); //Problem

		IcsRectangle rec1 = new IcsRectangle();
		System.out.print("\nThis program will output the overlap of 2 rectangles. \nEnter the x-coordinate of the leftmost points of the first rectangle: ");
		int left = user_input.nextInt();
		System.out.print("Enter the y-coordinate of the lowest points on the first rectangle: ");
		int bottom = user_input.nextInt();
		System.out.print("Enter the width of the first rectangle: ");
		int width = user_input.nextInt();
		System.out.print("Enter the y-coordinate of the height of the first rectangle: ");
		int height = user_input.nextInt();
		 rec1.set(left, bottom, width, height);
		
		IcsRectangle rec2 = new IcsRectangle();
		System.out.print("Enter the x-coordinate of the leftmost points on the second rectangle: ");
		left = user_input.nextInt();
		System.out.print("Enter the y-coordinate of the lowest points on the second rectangle: ");
		bottom = user_input.nextInt();
		System.out.print("Enter the width of the second rectangle: ");
		width = user_input.nextInt();
		System.out.print("Enter the height of the second rectangle: ");
		height = user_input.nextInt();
		 rec2.set(left, bottom, width, height);
		 if ((rec1).getLeft() < rec2.getLeft()) {
			System.out.println(IcsRectangle.partialOverlap(rec1,rec2).toString());
		 } else if((rec1).getLeft() > rec2.getLeft()) {
			 System.out.println(IcsRectangle.partialOverlap(rec2,rec1).toString());
		 } else if (rec1.area() > rec2.area()){
			 System.out.println(IcsRectangle.partialOverlap(rec1,rec2).toString()); // if both rectangles have the same left most point, then pass the rectangle with the larger area
		 } else {
			 System.out.println(IcsRectangle.partialOverlap(rec2,rec1).toString());
		 }
	
		 if ((rec1).getLeft() <= rec2.getLeft()) {
			 System.out.println("The total perimeter of these rectangles combined is: " + IcsRectangle.totalPerimeter(rec1, rec2));
		 } else {
			 System.out.println("The total perimeter of these rectangles combined is: " + IcsRectangle.totalPerimeter(rec2, rec1));
		 }
		 
		 if (rec1.contains(rec2) == true) {
			 System.out.println("Rectangle 2 is inside rectangle 1!");
		 } else {
			 System.out.println("Rectangle 2 is not inside rectangle 1!");
		 }
	}
}
