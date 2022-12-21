package p1;
import java.util.Scanner;

public class p1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner userchoice = new Scanner(System.in);
		char c = userchoice.next().charAt(0);	
		
		switch (c) {
		case 'a':
		case 'A':
			System.out.println("Action movie fan");
			break;
		case 'c':
		case 'C':
			System.out.println("Comedy movie fan");
			break;
		case 'd':
		case 'D':
			System.out.println("Drama movie fan");
			break;
		
		default:
			System.out.println("Invalid choice");
			break;
		}
	}
}

	
