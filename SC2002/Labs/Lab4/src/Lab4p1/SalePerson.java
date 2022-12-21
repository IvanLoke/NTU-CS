package Lab4p1;

public class SalePerson implements Comparable<SalePerson> {

	private String firstName;
	private String lastName;
	private int totalSales;
	
	public SalePerson (String firstName, String lastName, int totalSales) {
		this.firstName = firstName;
		this.totalSales = totalSales;
		this.lastName = lastName;
	}
	
	public String toString() {
		return(lastName + "," + firstName + "," + totalSales);
	}
	
	public boolean equals(SalePerson o) {
		if (o.firstName == firstName && o.lastName == lastName) {
			return(true);
		}
		return(false);
	}

	public int compareTo(SalePerson o) {
		if(o.totalSales < totalSales) {
			return -1;
		}
		else if(o.totalSales > totalSales) {
			return 1;
		}
		else{
			if(o.lastName.compareTo(lastName)<0) {
				return -1;
			}
			else {
				return 1;
			}
		}

	}
	
	public String getFirstName() {
		return(this.firstName);
	}
	public String getLastName() {
		return(this.lastName);
	}
	public int getTotalSales() {
		return(this.totalSales);
	}
}
