package Lab3p1;
import java.util.Scanner;
public class PlaneApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Plane plane = new Plane();
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("(1) Show number of empty seats");
			System.out.println("(2) Show the list of empty seats");
			System.out.println("(3) Show the list of seat assignment by seat ID");
			System.out.println("(4) Show the list of seat assignment by customer ID");
			System.out.println("(5) Assign a customer to a seat");
			System.out.println("(6) Remove a seat assignement");
			System.out.println("(7) Exit");
			choice = sc.nextInt();
			switch(choice){
				case 1:
					plane.showNumEmptySeats();
					break;
				case 2:
					plane.showEmptySeats();
					break;
				case 3:
					plane.showAssignedSeats(true);
					break;
				case 4:
					plane.showAssignedSeats(false);
					break;
				case 5:
					System.out.println("Please enter seat: ");
					int seatid = sc.nextInt();
					System.out.println("Please enter customer ID");
					int customerid = sc.nextInt();
					plane.assignSeat(seatid,customerid);
					break;
				case 6:
					System.out.println("Enter SeatID to unassign customer from: ");
					int unassignseatid = sc.nextInt();
					plane.unAssignSeat(unassignseatid);
					break;
				case 7:
					break;
			}

		}
			while (choice != 7);
	}

}
