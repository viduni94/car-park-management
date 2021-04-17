public class MotorBike extends Vehicle {
	private String engineSize;
	
	public MotorBike(String idPlate, String brand, DateTime entryTime, String engineSize) {
		super(idPlate, brand, entryTime);
		this.engineSize=engineSize;
	}
	
	public String getEngineSize() {
		return engineSize;
	}
	public void setEngineSize(String engineSize) {
		this.engineSize=engineSize;
	}
}
