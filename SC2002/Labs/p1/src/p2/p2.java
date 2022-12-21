package p2;
import java.util.Scanner;

public class p2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan1 = new Scanner(System.in);
		System.out.println("Salary: ");
		Integer salary = scan1.nextInt();
		System.out.println("Merit: ");
		Integer merit = scan1.nextInt();		
		if (salary >= 700 && salary < 900) {
			if (salary >= 700 && salary < 800 && merit <20) {
				System.out.println("Grade B");
			}
			else{
				System.out.println("Grade A");
			}
		} else if (salary >=600 && salary <700) {
			if(salary >=600 && salary <650 && merit<10) {
				System.out.println("Grade C");
			}
			else {
				System.out.println("Grade B");
			}
		} else if (salary >=500 && salary <600) {
			System.out.println("Grade C");
		}
	}

}
