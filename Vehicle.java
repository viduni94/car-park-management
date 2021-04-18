public abstract class Vehicle extends Object implements Comparable<Vehicle> {
	
	@Override
	public int compareTo(Vehicle o) {
		return this.entryTime.compareTo(o.entryTime);
	}
	
	// Vehicle Properties
	private String idPlate;
	private String brand;
	private DateTime entryTime;
	private int priority;
	
	// Constructor
	public Vehicle(String idPlate, String brand, DateTime entryTime, int priority ){
		this.idPlate=idPlate;
		this.brand=brand;
		this.entryTime=entryTime;
		this.priority = priority;
	}
	
	public String getIdPlate() {
		return idPlate;
	}
	public void setIdPlate(String idPlate) {
		this.idPlate=idPlate;
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand=brand;
	}
	
	public DateTime getEntryDate() {
		return entryTime;
	}
	public void setEntryDate(DateTime entryTime) {
		this.entryTime = entryTime;
	}

	public int getPriority() { return priority; }
	public void setPriority(int priority) { this.priority = priority; }
}
