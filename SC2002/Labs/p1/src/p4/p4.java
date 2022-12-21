package p4;
import java.util.Scanner;
public class p4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan1 = new Scanner(System.in);
		System.out.println("Height = ");
		Integer height = scan1.nextInt();
		
		
		if (height <= 0 ) {
			System.out.println("Error Input!");
			return;
		}
		
		String previous = "";
		for (int i = 1; i <= height; i++){
			
			if (i%2 == 1){
				previous = "AA" + previous;
				System.out.println(previous);
			}
			
			if (i%2 == 0){
				previous = "BB" + previous;
				System.out.println(previous);
			}

		}
	}

}
