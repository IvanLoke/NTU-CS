package Lab4p2;
import java.util.Scanner;
public class Shape3DApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose total number of Shapes: ");
		int number = sc.nextInt();
		Shape[] shapearray = new Shape[number];
		for(int i=1;i<number+1;i++) {
			System.out.println("Choose Shape for shape "+ i);
			System.out.println("Press (1) for Cube");
			System.out.println("Press (2) for Cylinder");
			System.out.println("Press (3) for Sphere");
			System.out.println("Press (4) for Cone");
			int choice = sc.nextInt();
			int dimension1 = 0, dimension2 =0, dimension3 = 0;
			switch(choice){
			case 1:
				System.out.println("Please input the length of your Cube: ");
				dimension1 = sc.nextInt();
				shapearray[i-1] = new Shape(dimension1,"Cube");
				break;
			case 2:
				System.out.println("Please input the radius of the Cylinder: ");
				dimension1 = sc.nextInt();
				System.out.println("Please input the height of the Cylinder: ");
				dimension2 = sc.nextInt();
				shapearray[i-1] = new Shape(dimension1,dimension2,"Cylinder");
				break;
			case 3:
				System.out.println("Please input the radius of the Sphere: ");
				dimension1 = sc.nextInt();
				shapearray[i-1] = new Shape(dimension1, "Sphere");
				break;
			case 4:
				System.out.println("Please input the radius of the base of Cone: ");
				dimension1 = sc.nextInt();
				System.out.println("Please input the height of the Cone: ");
				dimension2 = sc.nextInt();
				shapearray[i-1] = new Shape(dimension1, dimension2, "Cone");
				break;
			}

			
		}			
		double totalsurfacearea = 0;
			for(int j=0;j<number;j++) {
				totalsurfacearea = totalsurfacearea + shapearray[j].getTotalSurfaceArea();
			}
			System.out.println("The total surface area is "+ totalsurfacearea);
	}

}
