package Lab4p2;
import java.util.Scanner;
import java.lang.Math.*;
public class Shape {
	int dimension1 =0, dimension2=0, dimension3=0;
	String shapetype;
	
	public Shape(int dimension1, String shapetype) {
		this.dimension1 = dimension1;
		this.shapetype = shapetype;
	}
	
	public Shape(int dimension1, int dimension2, String shapetype) {
		this.dimension1 = dimension1;
		this.dimension2 = dimension2;
		this.shapetype = shapetype;
	}
	
	public Shape(int dimension1, int dimension2, int dimension3, String shapetype) {
		this.dimension1 = dimension1;
		this.dimension2 = dimension2;
		this.dimension3 = dimension3;
		this.shapetype = shapetype;
	}
	public double getArea() {
		switch(shapetype) {
		case "Square":
			return(dimension1*dimension1);
		case "Rectangle":
			return(dimension1*dimension2);
		case "Circle":
			return(Math.PI*dimension1*dimension1);
		case "Triangle":
			return(0.5*dimension1*dimension2);
		}
		return 0;
	}
	public double getTotalSurfaceArea() {
		switch(shapetype) {
		case "Cube":
			return(dimension1*dimension1*6);
		case "Cylinder":
			return(Math.PI*dimension1*dimension1*dimension2);
		case "Sphere":
			return(4*Math.PI*dimension1*dimension1);
		case "Cone":
			double slantsquare = dimension2*dimension2 + ((dimension1)*(dimension1));
			double root = Math.sqrt(slantsquare);
			double base = (Math.PI*dimension1*dimension1);
			return(base+Math.PI*dimension1*root);
		}
		return 0;
	}
}

