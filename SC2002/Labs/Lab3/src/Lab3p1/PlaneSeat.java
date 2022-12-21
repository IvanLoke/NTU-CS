package Lab3p1;

public class PlaneSeat {
//variables
	private int SeatId;
	private boolean assigned;
	private int customerId;
//methods
	public PlaneSeat(int seat_id){
		this.SeatId = seat_id;
	}
	public int getSeatID() {
		return this.SeatId;
	}
	public int getCustomerID() {
		return this.customerId;
	}
	public boolean isOccupied() {
		return this.assigned;
	}
	public void assign(int cust_id) {
		this.assigned = true;
		this.customerId = cust_id;
		System.out.println("This seat is assigned");
	}
	public void unAssign() {
		this.assigned = false;
		this.customerId = 0;
		System.out.println("Seat Unassigned!");
	}
	
}
