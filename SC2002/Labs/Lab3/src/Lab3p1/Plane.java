package Lab3p1;
import java.util.Comparator;
import java.util.Arrays;
public class Plane {
	//variables
	private int numEmptySeats = 12;
	private PlaneSeat[]seat  = new PlaneSeat[12];
	//methods
	public Plane() {
		for (int j = 0; j < seat.length; j++) {
			seat[j] = new PlaneSeat(j+1);
		}
	}
	public void showNumEmptySeats(){
		System.out.println("Number of empty seats is "+ numEmptySeats);
	}
	public void showEmptySeats() {
		System.out.println("These seats are empty");
		for (int i = 0; i< this.seat.length; i++) {
			if(this.seat[i].isOccupied() == false) {
				System.out.println("SeatID " + seat[i].getSeatID());
			}
		}
	}
	public void showAssignedSeats(boolean bySeatId) {
		if (bySeatId == false) {
			PlaneSeat[] copySeats =sortSeats();
			for (int i=0; i<copySeats.length;i++) {
				if (copySeats[i].isOccupied()) {
					System.out.println("SeatID "+ copySeats[i].getSeatID() + " assigned to CustomerID " + copySeats[i].getCustomerID());					
				}
			}
		}
		else {
			for(int i=0;i<seat.length;i++) {
				if(seat[i].isOccupied()) {
					System.out.println("SeatID "+ seat[i].getSeatID()+" assigned to CustomerID "+ seat[i].getCustomerID());
				}
			}
			
		}
	}
	
	private PlaneSeat[] sortSeats() {
		PlaneSeat[] copySeats = new PlaneSeat[12];
		for(int k=0;k<seat.length;k++) {
			copySeats[k]=seat[k];
		}
		Arrays.sort(copySeats, new Comparator<PlaneSeat>(){
			public int compare(PlaneSeat seat1, PlaneSeat seat2){
				return seat1.getCustomerID() - seat2.getCustomerID();
			}
		});
		return copySeats;
	}
	
	public void assignSeat(int seatId, int cust_id) {
		for(int i = 0; i<seat.length; i++) {
			if(seat[i].getSeatID()== seatId) {
				if(seat[i].isOccupied()) {
					System.out.println("Seat already assigned to a customer.");
				}
				else {
					seat[i].assign(cust_id);
					numEmptySeats--;
				}
			}
		}
	}
	public void unAssignSeat(int seatId) {
		for(int i = 0; i<seat.length; i++) {
			if(seat[i].getSeatID() == seatId) {
				if (seat[i].isOccupied()) {
					seat[i].unAssign();
					numEmptySeats++;
				}
				else {
					System.out.println("Seat is not occupied");
				}

			}
		}
	}
}
