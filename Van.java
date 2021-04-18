public class Van extends Vehicle {
	// Properties
	private double cargoVolume;
	
	// Constructor
	public Van(String idPlate, String brand, DateTime entryTime, double cargoVolume, int priority) {
		super(idPlate, brand, entryTime, priority);
		this.cargoVolume = cargoVolume;
	}

	public double getCargoVolume() {
		return cargoVolume;
	}
	public void setCargoVolume(double cargoVolume) {
		this.cargoVolume=cargoVolume;
	}
}
