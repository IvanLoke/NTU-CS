package p3;
import java.util.Scanner;
public class p3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan1 = new Scanner(System.in);
		System.out.println("Starting: ");
		Integer starting = scan1.nextInt();
		System.out.println("Ending: ");
		Integer ending = scan1.nextInt();
		System.out.println("Incremement" );
		Integer increment = scan1.nextInt();
		
		if (starting > ending) {
			System.out.println("Error input");
		}
		
		else{
		System.out.println("""
			      US$         S$
			      --------------""");
				
		for (int i = starting; i <= ending; i=i+increment) {
			System.out.println(i+ "           " + i*1.82);
		}

		System.out.println("""
			      US$         S$
			      --------------""");
		
		int i = 0;
		while(i<=ending) {
			System.out.println(i+ "           " + i*1.82);
			i = i + increment;
			
		}
		
		
		System.out.println("""
			      US$         S$
			      --------------""");
		
		int j = 0;
		do {
			System.out.println(j+ "           " + j*1.82);
			j = j + increment;
		}
		
		while(j<=ending);
		
	}
	}
}