import java.math.BigDecimal;

public interface CarParkManager {
	public static final int MAX=80; // Number of slots available in the Car Park
	
	public void addVehicle(Vehicle obj);
	public void deleteVehicle(String IdPlate);
	public void printCurrentVehicles();
	public void printVehiclePercentage();
	public void printLongestPark();
	public void printLatestPark();
	public void printVehicleByDay(DateTime entryTime);
	public BigDecimal calculateChargers(String plateID, DateTime currentTime);
}
