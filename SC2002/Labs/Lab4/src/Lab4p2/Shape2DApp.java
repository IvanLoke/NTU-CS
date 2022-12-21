package Lab4p2;

import java.util.Scanner;

public class Shape2DApp {
	
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter the total number of shapes: ");
			int number = sc.nextInt();
			Shape[] shapearray = new Shape[number];
			for (int i=1;i<number+1;i++) {
				System.out.println("Choose shape for shape " + i);
				System.out.println("Press (1) for Square");
				System.out.println("Press (2) for  Rectangle");
				System.out.println("Press (3) for Circle");
				System.out.println("Press (4) for  Triangle");
				int choice = sc.nextInt();
				int dimension1 =0, dimension2 =0;
				switch(choice) {
				case 1:
					System.out.println("Enter the length of your square: ");
					dimension1 = sc.nextInt();
					shapearray[i-1] = new Shape(dimension1,"Square");
					break;	
				case 2:
					System.out.println("Enter length of rectangle: ");
					dimension1 = sc.nextInt();
					System.out.println("Enter breadth of rectangle: ");
					dimension2 = sc.nextInt();
					shapearray[i-1] = new Shape(dimension1, dimension2, "Rectangle");
					break;
				case 3:
					System.out.println("Enter radius of circle: ");
					dimension1 = sc.nextInt();
					shapearray[i-1]= new Shape(dimension1,"Circle");
					break;
				case 4:
					System.out.println("Enter height of triange: ");
					dimension1 = sc.nextInt();
					System.out.println("Enter base of triangle: ");
					dimension2 = sc.nextInt();
					shapearray[i-1] = new Shape(dimension1, dimension2,"Triangle");
					break;
				}
			}
			double totalarea = 0;
			for (int j=1;j<number+1;j++) {
				totalarea = totalarea+ shapearray[j-1].getArea();
			}
			System.out.println("total area is "+totalarea);
		}
	}


